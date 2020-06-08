package ru.geekbrains.persist.dto;

public class MyException extends RuntimeException{
    private String description;
    private int status;

    public MyException(String description, int status) {
        this.status = status;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
