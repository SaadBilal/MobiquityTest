package com.mobiquity.mobiquityproducts.contract;

import com.mobiquity.mobiquityproducts.model.ProductsResponse;

import java.util.List;

public interface ProductContract {

    interface Presenter {
        void getProductList();

    }

    interface View {
        void getAllProducts(List<ProductsResponse> productsResponses);
        void onConnectionFailed();
        void showProgress();
        void showError(String message);

    }

    interface Interactor {

        void getAvailableProducts(OnFinishProductListListener listener);

        interface OnFinishProductListListener {
            void onSuccess(List<ProductsResponse> productsResponses);
            void onFailure(int errorCode, String message);
        }


    }


}
