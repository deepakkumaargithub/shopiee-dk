package com.example.shoppie;

public class ProductSpecificationModel {

    private String featureName;
    private String featureValue;

    public ProductSpecificationModel(String featureName, String featureValue) {
        this.featureValue = featureValue;
        this.featureName = featureName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }
}