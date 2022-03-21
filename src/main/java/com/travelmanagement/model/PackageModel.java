package com.travelmanagement.model;

public class PackageModel {
    private int id;
    private String name;
    private String duration;
    private String description;
    private double price;
    private double discount;
    private int userId;

    public PackageModel(String name, String duration, String description, double price, double discount,int userId) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.userId=userId;
    }

    public PackageModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public int getUserId(){
        return userId;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void  setUserId(int userId){
        this.userId=userId;
    }
}
