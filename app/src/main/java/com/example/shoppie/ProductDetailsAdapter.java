package com.example.shoppie;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;

    public ProductDetailsAdapter(FragmentManager fm, int totalTabs){
        super(fm);
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                ProductDescriptionFragment pdf1 = new ProductDescriptionFragment();
                return pdf1;
            case 1:
                ProductSpecificationFragment psf= new ProductSpecificationFragment();
                return psf;
            case 2:
                ProductOtherDetailsFragment odf = new ProductOtherDetailsFragment();
                return odf;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
