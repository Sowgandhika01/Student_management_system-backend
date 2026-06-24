package com.cscorner.helloapp.dto;

public class OrderDTO {
    private int id;
    private String product;
    private double price;

    public OrderDTO() {
    }

    public OrderDTO(int id, String product, double price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
    