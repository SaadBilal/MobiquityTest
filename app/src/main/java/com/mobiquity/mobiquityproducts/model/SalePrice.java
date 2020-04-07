package com.mobiquity.mobiquityproducts.model;

import com.google.gson.annotations.SerializedName;

public class SalePrice{

	@SerializedName("amount")
	private String amount;

	@SerializedName("currency")
	private String currency;

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	@Override
 	public String toString(){
		return 
			"SalePrice{" + 
			"amount = '" + amount + '\'' + 
			",currency = '" + currency + '\'' + 
			"}";
		}
}