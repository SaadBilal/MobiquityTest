
package com.mobiquity.mobiquityproducts.restclient;

import com.mobiquity.mobiquityproducts.model.ProductsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;



public interface ApiInterface {

    @GET(".")
    @Headers({"Content-Type: application/json"})
    Call<List<ProductsResponse>> getProducts();

}
