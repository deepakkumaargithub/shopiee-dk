package com.example.shoppie;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductSpecificationModel implements Parcelable {

    private String featureName;
    private String featureValue;

    public ProductSpecificationModel(String featureName, String featureValue) {
        this.featureName = featureName;
        this.featureValue = featureValue;
    }

    protected ProductSpecificationModel(Parcel in) {
        featureName = in.readString();
        featureValue = in.readString();
    }

    public String getFeatureName() {return featureName;}

    public String getFeatureValue() {return featureValue;}



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(featureName);
        dest.writeString(featureValue);
    }

    public static final Creator<ProductSpecificationModel> CREATOR = new Creator<ProductSpecificationModel>() {
        @Override
        public ProductSpecificationModel createFromParcel(Parcel in) {
            return new ProductSpecificationModel(in);
        }

        @Override
        public ProductSpecificationModel[] newArray(int size) {
            return new ProductSpecificationModel[size];
        }
    };
}
