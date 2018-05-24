package com.example.manjooralam.flashdelivery.models.offers_response;

import com.example.manjooralam.flashdelivery.models.QuantityBean;

import java.util.ArrayList;
/**
 * Created by manjooralam on 9/30/2017.
 */

public class OffersModel {

    public String imageUrl;
    public String itemName;
    public ArrayList<QuantityBean> itemQuantity;
    public String itemDiscountPrice;
    public String itemOriginalPrice;
    public String maxItemCount;
    public String subtype;
    public String type;
    public int count = 1;

    public OffersModel(){

    }
    public OffersModel(String imageUrl, String itemName, ArrayList<QuantityBean> itemQuantity,
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
