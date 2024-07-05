package com.example.shoppie.ModelClass;

public class Category {
    private String id;

    private String title;

    public Category() {
        // Default constructor required for calls to DataSnapshot.getValue(Category.class)
    }

    public Category(String id,  String title) {
        this.id = id;

        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
