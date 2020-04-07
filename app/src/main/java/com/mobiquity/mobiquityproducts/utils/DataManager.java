package com.mobiquity.mobiquityproducts.utils;

import android.content.Context;

import com.mobiquity.mobiquityproducts.model.ProductsResponse;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DataManager {


    private SharedPrefsHelper mSharedPrefsHelper;
    private static DataManager mDataManagerInstance;
    private List<ProductsResponse> productsResponses;
    private List<String> mOrderMedicineImages;



    private DataManager() {

        mSharedPrefsHelper = null;
    }

    private DataManager(Context context) {

        mSharedPrefsHelper = new SharedPrefsHelper(context.getSharedPreferences(SharedPrefsHelper.PREF_KEY_NAME, MODE_PRIVATE));
    }

    public void setInstance( SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void setInstance(Context context) {

        if (mSharedPrefsHelper == null) {
            mSharedPrefsHelper = new SharedPrefsHelper(
                    context.getSharedPreferences(SharedPrefsHelper.PREF_KEY_NAME, MODE_PRIVATE));
        }
    }

    public boolean isInstanceSet() {
        return mSharedPrefsHelper != null;
    }

    public static DataManager getInstance() {
        if (mDataManagerInstance == null)
            mDataManagerInstance = new DataManager();
        return mDataManagerInstance;
    }

    public static DataManager getInstance(Context context) {
        if (mDataManagerInstance == null)
            mDataManagerInstance = new DataManager(context);
        return mDataManagerInstance;
    }

    public void release() {
        mDataManagerInstance = null;
    }

    public void setProductList(List<ProductsResponse> productList) {
        this.productsResponses = productList;
    }

    public List<ProductsResponse> getProductList() {
        return productsResponses;
    }




}
