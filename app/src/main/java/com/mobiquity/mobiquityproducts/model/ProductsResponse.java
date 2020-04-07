package com.mobiquity.mobiquityproducts.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse{

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("products")
	private List<ProductsItem> products;


    public boolean isMainCategory() {
        return true;
    }

    public void setMainCategory(boolean mainCategory) {
        isMainCategory = mainCategory;
    }

    private boolean isMainCategory;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setProducts(List<ProductsItem> products){
		this.products = products;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}

	@Override
 	public String toString(){
		return 
			"ProductsResponse{" + 
			"name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",products = '" + products + '\'' + 
			"}";
		}
}