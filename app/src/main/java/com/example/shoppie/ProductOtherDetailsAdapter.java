package com.example.shoppie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductOtherDetailsAdapter extends RecyclerView.Adapter<ProductOtherDetailsAdapter.ViewHolder> {

    private List<ProductOtherDetailsModel> otherDetailsList;

    public ProductOtherDetailsAdapter(List<ProductOtherDetailsModel> otherDetailsList) {
        this.otherDetailsList = otherDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_other_detail_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductOtherDetailsModel item = otherDetailsList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return otherDetailsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDetailName;
        private TextView tvDetailValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetailName = itemView.findViewById(R.id.other_detail_feature_name);
            tvDetailValue = itemView.findViewById(R.id.other_detail_feature_value);
        }

        public void bind(ProductOtherDetailsModel item) {
            tvDetailName.setText(item.getDetailName());
            tvDetailValue.setText(item.getDetailValue());
        }
    }
}