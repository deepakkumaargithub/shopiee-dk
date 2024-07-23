package com.example.shoppie;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Product implements Parcelable {
    private String title;
    private String price;
    private String description;
    private List<String> imagesUrls;
    private String location;
    private String sellerInfo;
    private List<ProductSpecificationModel> specification;
    //private float rating;

    // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    public Product() {
    }

    public Product(String title, String price, String description, List<String> imagesUrls, String location, String sellerInfo, List<ProductSpecificationModel> specification) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imagesUrls = imagesUrls;
        this.location = location;
        this.sellerInfo = sellerInfo;
        this.specification = specification;
      //  this.rating = rating;
    }

    // Parcelable implementation
    protected Product(Parcel in) {
        title = in.readString();
        price = in.readString();
        description = in.readString();
        imagesUrls = in.createStringArrayList();
        location = in.readString();
        sellerInfo = in.readString();
        specification = in.createTypedArrayList(ProductSpecificationModel.CREATOR);
        //rating = in.readFloat();
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImagesUrls() {
        return imagesUrls;
    }

    public String getLocation() {
        return location;
    }

    public String getSellerInfo() {
        return sellerInfo;
    }

    public List<ProductSpecificationModel> getSpecification() {
        return specification;
    }

//    public float getRating() {
//        return rating;
//    }

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
        parcel.writeString(description);
        parcel.writeStringList(imagesUrls);
        parcel.writeString(location);
        parcel.writeString(sellerInfo);
        parcel.writeTypedList(specification);
      //  parcel.writeFloat(rating);
    }
}
