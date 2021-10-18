package ru.pcs.models;

import java.util.Objects;

public class Product {
    private Long id;
    private String category;
    private String name;
    private Double price;
    private Integer stock;
    private Integer discount;

    public Product(Long id, String category, String name, Double price, Integer stock, Integer discount) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
    }

    public Product(String category, String name, Double price, Integer stock, Integer discount) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(category, product.category) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(stock, product.stock) && Objects.equals(discount, product.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, price, stock, discount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", discount=" + discount +
                '}';
    }
}
