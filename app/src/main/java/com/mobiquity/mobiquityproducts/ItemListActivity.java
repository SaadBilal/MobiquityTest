package com.mobiquity.mobiquityproducts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mobiquity.mobiquityproducts.contract.ProductContract;
import com.mobiquity.mobiquityproducts.model.ProductsItem;
import com.mobiquity.mobiquityproducts.model.ProductsResponse;
import com.mobiquity.mobiquityproducts.presenter.ProductPresenter;
import com.mobiquity.mobiquityproducts.utils.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobiquity.mobiquityproducts.ItemDetailFragment.ARG_ITEM_POSITION;
public class ItemListActivity extends AppCompatActivity implements ProductContract.View {

    private boolean mTwoPane;
    private ProductPresenter mPresenter;
    View recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        mPresenter = new ProductPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hey Mobiquity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.item_list);
        mPresenter.getProductList();
        assert recyclerView != null;

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<ProductsResponse> productsResponses) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, productsResponses, mTwoPane));
    }

    @Override
    public void getAllProducts(List<ProductsResponse> productsResponses) {
        setupRecyclerView((RecyclerView) recyclerView, productsResponses);
    }

    @Override
    public void onConnectionFailed() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showError(String message) {

    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int LAYOUT_ONE = 0;
        private static final int LAYOUT_TWO = 1;
        private final ItemListActivity mParentActivity;
        private final List<ProductsResponse> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductsItem item = (ProductsItem) view.getTag();
                if (mTwoPane) {//for tablet view
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.getId());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {

                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getId());
                    intent.putExtra(ARG_ITEM_POSITION,0);
                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent, List<ProductsResponse> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public int getItemViewType(int position) {
            if (getActualProductSize(mValues).get(position).isMainCategory())
                return LAYOUT_ONE;
            else
                return LAYOUT_TWO;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            RecyclerView.ViewHolder viewHolder = null;
            if (viewType == LAYOUT_ONE) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list_content_cat, parent, false);
                viewHolder = new ViewHolder(view);
            } else {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list_content, parent, false);
                viewHolder = new ViewHolderTwo(view);
            }


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            if (holder.getItemViewType() == LAYOUT_ONE) {
                ViewHolder viewHolder = (ViewHolder) holder;
                List<ProductsItem> productsItem= getActualProductSize(mValues);
                viewHolder.mIdView.setText(productsItem.get(position).getMainCatName());
                if (!productsItem.get(position).getDescription().isEmpty())
                    viewHolder.mContentView.setText(productsItem.get(position).getMainCatDescription());
                else
                    viewHolder.mContentView.setText(R.string.no_des);
                holder.itemView.setTag(productsItem.get(position));
              //  holder.itemView.setOnClickListener(mOnClickListener);
            } else {
                final List<ProductsItem> productsItem = getActualProductSize(mValues);
                ViewHolderTwo viewHolder = (ViewHolderTwo) holder;
                viewHolder.mIdView.setText(productsItem.get(position).getName());
                if (!productsItem.get(position).getDescription().isEmpty())
                    viewHolder.mContentView.setText(productsItem.get(position).getDescription());
                else
                    viewHolder.mContentView.setText(R.string.no_des);
                Picasso.get().load(Urls.AMAZON_BASE_URL+productsItem.get(position).getUrl()).into(viewHolder.ivImage);
                viewHolder.itemView.setTag(productsItem.get(position));
               // viewHolder.itemView.setOnClickListener(mOnClickListener);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, productsItem.get(position).getId());
                        intent.putExtra(ARG_ITEM_POSITION,position);
                        context.startActivity(intent);
                    }
                });
            }
        }


        @Override
        public int getItemCount() {
            return getActualProductSize(mValues).size();/* mValues.size();*/
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }

        public class ViewHolderTwo extends RecyclerView.ViewHolder {

            final TextView mIdView;
            final TextView mContentView;
            final ImageView ivImage;

            ViewHolderTwo(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
                ivImage = (ImageView) view.findViewById(R.id.iv_image);
            }

        }
    }

    public static List<ProductsItem> getActualProductSize(List<ProductsResponse> productsResponses) {
        List<ProductsItem> productsItemList = new ArrayList<ProductsItem>();
        for (int i = 0; i < productsResponses.size(); i++) {
            ProductsItem productsItem = new ProductsItem();
            productsItem.setMainCatName(productsResponses.get(i).getName());
            productsItem.setDescription(productsResponses.get(i).getDescription());

            productsItem.setMainCategoryParent(true);
           // productsItem.setMainCategory(true);
            productsItemList.add(productsItem);
            for (int j = 0; j < productsResponses.get(i).getProducts().size(); j++) {
                productsItemList.add(productsResponses.get(i).getProducts().get(j));
            }
        }
        return productsItemList;
    }
}
