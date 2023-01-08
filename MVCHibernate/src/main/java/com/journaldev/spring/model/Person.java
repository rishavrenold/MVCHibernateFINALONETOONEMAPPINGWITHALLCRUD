package com.journaldev.spring.model;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Person {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public String product;



	public String product_description;

	public String category;
	public int price;

	public String currency;

	public int inventory;

	public String location;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	public Product pro;

	public Product getPro() {
		return pro;
	}

	public void setPro(Product pro) {
		this.pro = pro;
	}

	public Person()
	{

	}
	public Person(int id,String product, String product_description, String category, int price, String currency, int inventory, String location) {
		this.id=id;
		this.product = product;
		this.product_description = product_description;
		this.category = category;
		this.price = price;
		this.currency = currency;
		this.inventory = inventory;
		this.location = location;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	@Override
	public String toString() {
		return "Person{" +
				"id='"+id +'\''+
				"product='" + product + '\'' +
				", product_description='" + product_description + '\'' +
				", category='" + category + '\'' +
				", price=" + price +
				", currency='" + currency + '\'' +
				", inventory=" + inventory +
				", location='" + location + '\'' +
				'}';
	}
}
