package com.mobiquity.mobiquityproducts.model;

import com.google.gson.annotations.SerializedName;

public class ProductsItem {

    @SerializedName("salePrice")
    private SalePrice salePrice;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private String id;

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName("url")
    private String url;

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    private int uniqueId;

    public boolean isMainCategory() {
        if (isMainCategoryParent()) {
            return true;
        } else {
            return false;
        }
    }


    private String mainCatName;


    private String mainCatDescription;

    public boolean isMainCategoryParent() {
        return isMainCategoryParent;
    }

    public void setMainCategoryParent(boolean mainCategoryParent) {
        isMainCategoryParent = mainCategoryParent;
    }

    private boolean isMainCategoryParent;


    public String getMainCatName() {
        return mainCatName;
    }

    public void setMainCatName(String mainCatName) {
        this.mainCatName = mainCatName;
    }

    public String getMainCatDescription() {
        return mainCatDescription;
    }

    public void setMainCatDescription(String mainCatDescription) {
        this.mainCatDescription = mainCatDescription;
    }

    public String getMainCatId() {
        return mainCatId;
    }

    public void setMainCatId(String mainCatId) {
        this.mainCatId = mainCatId;
    }

    private String mainCatId;


    public void setMainCategory(boolean mainCategory) {
        isMainCategory = mainCategory;
    }

    private boolean isMainCategory;

    public void setSalePrice(SalePrice salePrice) {
        this.salePrice = salePrice;
    }

    public SalePrice getSalePrice() {
        return salePrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return
                "ProductsItem{" +
                        "salePrice = '" + salePrice + '\'' +
                        ",name = '" + name + '\'' +
                        ",description = '" + description + '\'' +
                        ",id = '" + id + '\'' +
                        ",categoryId = '" + categoryId + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}