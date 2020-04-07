package com.mobiquity.mobiquityproducts;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mobiquity.mobiquityproducts.model.ProductsItem;
import com.mobiquity.mobiquityproducts.utils.DataManager;

import java.util.List;

import androidx.fragment.app.Fragment;

public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_POSITION = "item_pos";
    private List<ProductsItem> mItem;
    int index;
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_POSITION)) {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                index = bundle.getInt(ARG_ITEM_POSITION);
            }
            mItem =ItemListActivity.getActualProductSize(DataManager.getInstance().getProductList());
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.get(index).getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.get(index).getDescription());

        }

        return rootView;
    }
}
