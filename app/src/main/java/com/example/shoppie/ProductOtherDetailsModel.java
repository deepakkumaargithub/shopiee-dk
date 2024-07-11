package com.example.shoppie;

public class ProductOtherDetailsModel {

    private String detailName;
    private String detailValue;

    public ProductOtherDetailsModel(String detailName, String detailValue) {
        this.detailName = detailName;
        this.detailValue = detailValue;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDetailValue() {
        return detailValue;
    }

    public void setDetailValue(String detailValue) {
        this.detailValue = detailValue;
    }
}