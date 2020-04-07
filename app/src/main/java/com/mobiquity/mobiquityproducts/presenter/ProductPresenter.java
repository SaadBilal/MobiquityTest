package com.mobiquity.mobiquityproducts.presenter;

import com.mobiquity.mobiquityproducts.ItemListActivity;
import com.mobiquity.mobiquityproducts.contract.ProductContract;
import com.mobiquity.mobiquityproducts.interactor.ProductInteractor;
import com.mobiquity.mobiquityproducts.model.ProductsResponse;

import java.lang.ref.WeakReference;
import java.util.List;


public class ProductPresenter implements ProductContract.Presenter {

    private final WeakReference<ItemListActivity> mView;
    private ProductInteractor mInteractor;

    public ProductPresenter(ItemListActivity view) {
        this.mView = new WeakReference<>(view);
        this.mInteractor = new ProductInteractor();

        getProductList();
    }

    @Override
    public void getProductList() {
        mInteractor.getAvailableProducts(new ProductContract.Interactor.OnFinishProductListListener() {


            @Override
            public void onSuccess(List<ProductsResponse> productsResponses) {
                if (mView.get() != null)
                    mView.get().getAllProducts(productsResponses);
            }

            @Override
            public void onFailure(int errorCode, String message) {
                if (mView.get() != null) {
                        mView.get().showError(message);
                }
            }
        });
    }
}
