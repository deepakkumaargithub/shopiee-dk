package com.example.shoppie;

public class Product {
    private String title;
    private String price;
    private String imageUrl;

    // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    public Product() {
    }

    public Product(String title, String price, String imageUrl) {
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }


    public String getPrice() {
        return price;
    }


    public String getImageUrl() {
        return imageUrl;
    }

}
