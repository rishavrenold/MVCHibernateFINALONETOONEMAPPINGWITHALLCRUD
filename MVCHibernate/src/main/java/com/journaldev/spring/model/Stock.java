package com.journaldev.spring.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name="stock")
@Component
public class Stock {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int stock_id;
    public int inventory;
    public String location;

//    @ManyToOne(targetEntity = Product.class)
//    @JoinColumn(name="product_id",referencedColumnName = "product_id")
//    public Product product_id;
//
//    public Product getProduct() {
//        return product_id;
//    }
//
//    public void setProduct(Product product) {
//        this.product_id = product;
//    }

    public Stock() {
    }

    public Stock(int stock_id, int inventory, String location) {
        this.stock_id = stock_id;
        this.inventory = inventory;
        this.location = location;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public Product getProduct_id() {
//        return product_id;
//    }
//
//    public void setProduct_id(Product product_id) {
//        this.product_id = product_id;
//    }1

    @Override
    public String toString() {
        return "Stock{" +
                "stock_id=" + stock_id +
                ", inventory=" + inventory +
                ", location='" + location + '\'' +
//                ", product_id='" + product_id + '\'' +
                '}';
    }
}
