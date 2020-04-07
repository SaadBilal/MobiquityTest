package com.mobiquity.mobiquityproducts;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mobiquity.mobiquityproducts.model.ProductsItem;
import com.mobiquity.mobiquityproducts.utils.DataManager;
import com.mobiquity.mobiquityproducts.utils.Urls;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ItemDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView bgImageView;
    private List<ProductsItem> mItem;
    private TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        bgImageView  =(ImageView)findViewById(R.id.bgImage);
        price = (TextView)findViewById(R.id.tv_price);
        mItem =ItemListActivity.getActualProductSize(DataManager.getInstance().getProductList());
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hey, Mobiquity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int index  = getIntent().getIntExtra(ItemDetailFragment.ARG_ITEM_POSITION,0);
        Picasso.get().load(Urls.AMAZON_BASE_URL+mItem.get(index).getUrl()).into(bgImageView);
        price.setText("Product Price: "+mItem.get(index).getSalePrice().getAmount()+" "+mItem.get(Integer.valueOf(index)).getSalePrice().getCurrency());
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putInt(ItemDetailFragment.ARG_ITEM_POSITION, index);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}