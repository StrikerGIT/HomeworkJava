package ru.geekbrains;

import javax.persistence.*;

@Entity
@Table(name = "unitOrder")
public class UnitOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public UnitOrder() {}

    public UnitOrder(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "UnitOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", customer=" + customer.getId() +
                ", product=" + product.getId() +
                '}';
    }
}
