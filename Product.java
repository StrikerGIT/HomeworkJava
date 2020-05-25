package ru.geekbrains;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "coast", nullable = false)
    private double coast;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<UnitOrder> unitsOrder;

    public Product() { }

    public Product(String title, double coast) {
        this.title = title;
        this.coast = coast;
        //this.unitsOrder = unitOrder;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCoast() {
        return coast;
    }

    public List<UnitOrder> getUnitsOrder() {
        return unitsOrder;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public void setUnitsOrder(List<UnitOrder> unitsOrder) {
        this.unitsOrder = unitsOrder;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coast=" + coast +
                ", unitsOrder=" + unitsOrder +
                '}';
    }
}
