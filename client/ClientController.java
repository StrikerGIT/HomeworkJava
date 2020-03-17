package com.striker.geekcloud.client;

import com.striker.geekcloud.common.FileSender;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sun.security.util.Length;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class ClientController implements Initializable {

    @FXML
    VBox leftPanel, rightPanel;

    //Path rootDir;
    PanelController leftPC;
    PanelController rightPC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.leftPC = (PanelController) leftPanel.getProperties().get("control");
        this.rightPC = (PanelController) rightPanel.getProperties().get("control");

        //инициализируем сетевое подключение клиента
        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> Network.getInstance().start(networkStarter)).start();
        try {
            networkStarter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // инициализируем левую панель (локальную)
        leftPC.initializeLocalPanel();

        // инициализируем правую панель (серверную)
        rightPC.initializeServerPanel();

    }

    public void pressOnDownloadBtn (ActionEvent actionEvent) {
        if (leftPC.getSelectedFileName() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не выбран файл для загрузки с сервера");
            alert.showAndWait();
            return;
        }
        try {
            FileSender.getFile(Paths.get(leftPC.getSelectedFileName()), Network.getInstance().getCurrentChannel());

            leftPC.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void pressOnUploadBtn (ActionEvent actionEvent) {
        if (leftPC.getSelectedFileName() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Не выбран файл для передачи на сервер");
            alert.showAndWait();
            return;
        }
        Platform.runLater(() ->
        {
            try {
                FileSender.sendFile(Paths.get(leftPC.getCurrentPath()).resolve(leftPC.getSelectedFileName()), Network.getInstance().getCurrentChannel(), future  -> {
                    if (!future.isSuccess()) {
                        future.cause().printStackTrace();
                    }
                    if (future.isSuccess()) {
                        System.out.println("Файл успешно передан на сервер");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        rightPC.refresh();
    }

}
