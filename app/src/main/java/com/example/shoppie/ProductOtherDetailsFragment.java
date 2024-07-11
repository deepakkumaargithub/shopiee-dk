package com.example.shoppie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductOtherDetailsFragment extends Fragment {

    private RecyclerView productOtherDetailsRecyclerView;

    public ProductOtherDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other_details, container, false);

        productOtherDetailsRecyclerView = view.findViewById(R.id.other_product_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        productOtherDetailsRecyclerView.setLayoutManager(linearLayoutManager);

        List<ProductOtherDetailsModel> productOtherDetailsModelList = new ArrayList<>();

        // Add sample other details (replace with your actual data)
        productOtherDetailsModelList.add(new ProductOtherDetailsModel("Date First Available", "4 February 2023"));
        productOtherDetailsModelList.add(new ProductOtherDetailsModel("Manufacturer", "Whirlpool of India Limited,"));
        productOtherDetailsModelList.add(new ProductOtherDetailsModel("Item Weight", "33 kg 400 g"));
        productOtherDetailsModelList.add(new ProductOtherDetailsModel("Date First Available", "4 February 2023"));
        productOtherDetailsModelList.add(new ProductOtherDetailsModel("Manufacturer", "Whirlpool of India Limited,"));
        productOtherDetailsModelList.add(new ProductOtherDetailsModel("Item Weight", "33 kg 400 g"));

        ProductOtherDetailsAdapter productOtherDetailsAdapter = new ProductOtherDetailsAdapter(productOtherDetailsModelList);
        productOtherDetailsRecyclerView.setAdapter(productOtherDetailsAdapter);

        return view;
    }
}