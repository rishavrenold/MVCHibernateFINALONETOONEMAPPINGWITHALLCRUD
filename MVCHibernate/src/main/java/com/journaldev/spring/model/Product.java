package com.journaldev.spring.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="product")
@Component
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer product_id;

     public String product_code;

     public String product_description;
     @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name="category_code" , referencedColumnName = "category_code")
     public Category category;


    @OneToOne(cascade = CascadeType.ALL)
    public Stock stock;
    @OneToOne(cascade = CascadeType.ALL)
    public Price price;



    public Product() {
    }

    public Product(int product_id, String product_code, String product_description) {
        this.product_id = product_id;
        this.product_code = product_code;
        this.product_description = product_description;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_code='" + product_code + '\'' +
                ", product_description='" + product_description + '\'' +'}';
    }
}
