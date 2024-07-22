package com.example.shoppie;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable{
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

    // Parcelable implementation
    protected Product(Parcel in) {
        title = in.readString();
        price = in.readString();
        imageUrl = in.readString();

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

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(price);
        parcel.writeString(imageUrl);

    }
}
