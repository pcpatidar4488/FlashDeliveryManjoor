package com.example.manjooralam.flashdelivery.models;

import java.util.ArrayList;

/**
 * Created by manjooralam on 9/30/2017.
 */

public class DryFruitsModel {


    public String imageUrl;
    public String itemName;
    public ArrayList<QuantityBean> itemQuantity;
    public String itemDiscountPrice;
    public String itemOriginalPrice;
    public String maxItemCount;
    public int count = 1;
    public String subtype;
    public String type;

    public DryFruitsModel(){

    }
    public DryFruitsModel(String imageUrl, String itemName, ArrayList<QuantityBean> itemQuantity,
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
