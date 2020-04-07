package com.mobiquity.mobiquityproducts.interactor;

import com.mobiquity.mobiquityproducts.contract.ProductContract;
import com.mobiquity.mobiquityproducts.model.ProductsResponse;
import com.mobiquity.mobiquityproducts.restclient.ApiClient;
import com.mobiquity.mobiquityproducts.restclient.ApiInterface;
import com.mobiquity.mobiquityproducts.utils.DataManager;

import java.util.List;

import androidx.annotation.NonNull;
import cz.msebera.android.httpclient.HttpStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInteractor implements ProductContract.Interactor {

    private final ApiInterface mApiInterface;
    private static final String TAG = ProductInteractor.class.getName();

    public ProductInteractor() {
        this.mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void getAvailableProducts(final OnFinishProductListListener listener) {
        Call<List<ProductsResponse>> call = mApiInterface.getProducts();
        call.enqueue(new Callback<List<ProductsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductsResponse>> call, @NonNull Response<List<ProductsResponse>> response) {

                if (response.code() == HttpStatus.SC_OK) {
                    if (listener != null) {
                        DataManager.getInstance().setProductList(response.body());
                        listener.onSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductsResponse>> call, @NonNull Throwable t) {
                if (listener != null) {
                    listener.onFailure(0, t.getMessage());
                }
            }
        });
    }
}
