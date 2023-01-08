package com.journaldev.spring.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name="price")
@Component
public class Price {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int price_id;
    public int price;
    public String currency;
//    @ManyToOne(targetEntity = Product.class)
//    @JoinColumn(name="product_id",referencedColumnName = "product_id")
//    public Product product;
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public Price() {
    }

    public Price(int price_id, int price, String currency) {
        this.price_id = price_id;
        this.price = price;
        this.currency = currency;
    }

    public int getPrice_id() {
        return price_id;
    }

    public void setPrice_id(int price_id) {
        this.price_id = price_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

//    public Product getProduct_code() {
//        return product_code;
//    }
//
//    public void setProduct_code(Product product_code) {
//        this.product_code = product_code;
//    }

    @Override
    public String toString() {
        return "Price{" +
                "price_id=" + price_id +
                ", price=" + price +
                ", currency='" + currency + '\'' +
//                ", product_code='" + product_code + '\'' +
                '}';
    }
}
