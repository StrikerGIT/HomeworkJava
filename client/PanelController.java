package com.striker.geekcloud.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PanelController implements Initializable {
    @FXML
    TextField tfFileName;

    @FXML
    ListView<FileInfo> filesList;

    Path rootDir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void initializeLocalPanel(){

            changeRootPath(Paths.get("client_storage"));

            filesList.setCellFactory(new Callback<ListView<FileInfo>, ListCell<FileInfo>>() {
                @Override
                public ListCell<FileInfo> call(ListView<FileInfo> param) {
                    return new ListCell<FileInfo>(){
                        @Override
                        protected void updateItem(FileInfo item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item == null || empty){
                                setText(null);
                                setStyle("");
                            }else {
                                String formatFN = String.format("%-30s",item.getFileName());
                                String formatFL = String.format("%,d bytes",item.getLength());
                                if (item.isDIR()){
                                    formatFL = String.format("%s","[DIR]");
                                }
                                if (item.isBackDIR()){
                                    formatFL = "";
                                }
                                String textFileInfo = String.format("%s %-20s", formatFN,formatFL);
                                setText(textFileInfo);
                            }
                        }
                    };
                }
            });
        }

    public void initializeServerPanel(){
        //Запрашиваем у сервера список файлов
    }

        public void changeRootPath(Path path){
            rootDir = path;
            tfFileName.setText(rootDir.toAbsolutePath().toString());
            filesList.getItems().clear();
            filesList.getItems().add(new FileInfo("...", -2L));
            filesList.getItems().addAll(scanFiles(path));
            filesList.getItems().sort(new Comparator<FileInfo>() {
                @Override
                public int compare(FileInfo o1, FileInfo o2) {
                    if (o1.getFileName().equals("...")){
                        return -1;
                    }
                    if ((int) Math.signum(o1.getLength()) == (int) Math.signum(o2.getLength())){
                        return o1.getFileName().compareTo(o2.getFileName());
                    }else{
                        return new Long(o1.getLength() - o2.getLength()).intValue();
                    }
                }
            });
        }

        public void refresh (){
            changeRootPath(rootDir);
        }

        public List<FileInfo> scanFiles(Path rootDir){
            try {
                return Files.list(rootDir).map(FileInfo::new).collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при сканировании: " +rootDir);
            }
        }
        public void fileListClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getClickCount() == 2){
                FileInfo fileInfo = filesList.getSelectionModel().getSelectedItem();
                if (fileInfo != null){
                    if (fileInfo.isDIR()){
                        Path pathTo = rootDir.resolve(fileInfo.getFileName());
                        changeRootPath(pathTo);
                    }
                    if (fileInfo.isBackDIR()){
                        Path pathTo = rootDir.toAbsolutePath().getParent();
                        changeRootPath(pathTo);
                    }

                }
            }
        }
    public void pressOnDeleteFile(ActionEvent actionEvent) {
        FileInfo fileInfo = filesList.getSelectionModel().getSelectedItem();
        if (fileInfo == null || fileInfo.isDIR() || fileInfo.isBackDIR()){
            return;
        }
        try {
            Files.delete(rootDir.resolve(fileInfo.getFileName()));
            refresh();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось удалить файл!");
            alert.showAndWait();
        }
    }
    public String getSelectedFileName(){
        if(!filesList.isFocused()){
            return null;
        }
        return filesList.getSelectionModel().getSelectedItem().getFileName();
    }

    public String getCurrentPath(){
        return tfFileName.getText();
    }
}
