package com.example.manjooralam.flashdelivery.models;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by manjooralam on 9/30/2017.
 */

public class ItemModel {

    public String imageUrl;
    public String itemName;
    public ArrayList<SampleBean> itemQuantity;
    public String itemDiscountPrice;
    public String itemOriginalPrice;
    public String maxItemCount;
    public String subtype;
    public String type;

    public ItemModel(){

    }
    public ItemModel(String imageUrl, String itemName, ArrayList<SampleBean> itemQuantity,
                          String itemDiscountPrice, String itemOriginalPrice, String maxItemCount,
                          String subtype, String type) {

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
