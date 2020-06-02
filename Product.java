package ru.geekbrains.persist.entity;

import javax.persistence.*;
import java.math.BigInteger;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer coast;

    public Product() { }

    public Product(Long id, String title, Integer coast) {
        this.id = id;
        this.title = title;
        this.coast = coast;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getCoast() {
        return coast;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoast(Integer coast) {
        this.coast = coast;
    }

}
