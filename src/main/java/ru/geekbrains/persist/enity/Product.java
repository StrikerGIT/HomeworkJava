package ru.geekbrains.persist.enity;

public class Product {
    private Long id;
    private String title;
    private String description;
    private Double coast;

    public Product() {

    }

    public Product(Long id, String title, String description, Double coast) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coast = coast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCoast() {
        return coast;
    }

    public void setCoast(Double coast) {
        this.coast = coast;
    }
}
