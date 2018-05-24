package com.example.manjooralam.flashdelivery.models;

/**
 * Created by manjooralam on 9/30/2017.
 */

public class FruitsModel {


    public String imageUrl;
    public String itemName;
    public String itemQuantity;
    public String itemDiscountPrice;
    public String itemOriginalPrice;
    public String maxItemCount;
    public int count = 1;
    public int subtype;
    public String type;

    public FruitsModel(){

    }
    public FruitsModel(String imageUrl, String itemName, String itemQuantity,
                       String itemDiscountPrice, String itemOriginalPrice, String maxItemCount,
                       int subtype, String type) {

        this.imageUrl = imageUrl;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemDiscountPrice = itemDiscountPrice;
        this.itemOriginalPrice = itemOriginalPrice;
        this.maxItemCount = maxItemCount;
        this.type = type;
        this.subtype = subtype;
    }

}
