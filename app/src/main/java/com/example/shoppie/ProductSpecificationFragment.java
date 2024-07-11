package com.example.shoppie;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationFragment extends Fragment {



    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    private RecyclerView productSpecRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_product_specification, container, false);

         productSpecRecyclerView = view.findViewById(R.id.product_spec_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        productSpecRecyclerView.setLayoutManager(linearLayoutManager);

        List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();

        productSpecificationModelList.add(new ProductSpecificationModel("Brand","Whirlpool"));
        productSpecificationModelList.add(new ProductSpecificationModel("Model","72681"));
        productSpecificationModelList.add(new ProductSpecificationModel("Capacity","184 litres"));
        productSpecificationModelList.add(new ProductSpecificationModel("Annual Energy Consumption","190 Kilowatt Hours Per Year"));
        productSpecificationModelList.add(new ProductSpecificationModel("Refrigerator Fresh Food Capacity","169.2 litres"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));




        ProductSpecAdapter productSpecAdapter = new ProductSpecAdapter(productSpecificationModelList);
        productSpecRecyclerView.setAdapter(productSpecAdapter);
        productSpecAdapter.notifyDataSetChanged();
        return view;
    }
}