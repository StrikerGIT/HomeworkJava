package GeekCloud.Client;

import GeekCloud.Common.FileSender;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class ClientController implements Initializable {
    @FXML
    TextField tfFileName;

    @FXML
    ListView<String> filesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> Network.getInstance().start(networkStarter)).start();
        try {
            networkStarter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pressOnDownloadBtn (ActionEvent actionEvent) {
        if (tfFileName.getLength() > 0) {
            try {
                FileSender.getFile(Paths.get("server_storage/" + tfFileName.getText()), Network.getInstance().getCurrentChannel(), future -> {
                    if (!future.isSuccess()) {
                        future.cause().printStackTrace();
                    }
                    if (future.isSuccess()) {
                        System.out.println("Файл успешно получен с сервера");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            tfFileName.clear();
        }
    }

    public void pressOnUploadBtn (ActionEvent actionEvent) {
        if (tfFileName.getLength() > 0) {
            try {
                FileSender.sendFile(Paths.get("client_storage/" + tfFileName.getText()), Network.getInstance().getCurrentChannel(), future -> {
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
            tfFileName.clear();
        }
    }
    public static void updateUI(Runnable r) {
        if (Platform.isFxApplicationThread()) {
            r.run();
        } else {
            Platform.runLater(r);
        }
    }
}
