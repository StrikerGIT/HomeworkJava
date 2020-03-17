package com.striker.geekcloud.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInfo {
    private String fileName;
    private long length;

    public String getFileName() {
        return fileName;
    }

    public long getLength() {
        return length;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public FileInfo(String fileName, long length) {
        this.fileName = fileName;
        this.length = length;
    }

    public FileInfo(Path path){
            try {
                this.fileName = path.getFileName().toString();
                if (Files.isDirectory(path)) {
                    this.length = -1L;
                }
                else{
                this.length = Files.size(path);
            }
            }
            catch (IOException e) {
                throw new RuntimeException("Что-то пошло не так при чтении инфо о файле: " + path.toAbsolutePath().toString());
            }
    }

    public boolean isDIR(){
        return  length == -1L;
    }

    public boolean isBackDIR(){
        return  length == -2L;
    }

}
