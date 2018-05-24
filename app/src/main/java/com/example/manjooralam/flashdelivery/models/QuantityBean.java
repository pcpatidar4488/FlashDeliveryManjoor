package com.example.manjooralam.flashdelivery.models;

/**
 * Created by manjooralam on 10/12/2017.
 */

public class QuantityBean {

    public String itemDiscountPrice;
    public String itemOriginalPrice;
    public String quantity;
    public String imageUrl;
    public QuantityBean() {

    }

    public QuantityBean(String itemDiscountPrice, String itemOriginalPrice, String quantity, String imageUrl) {
        this.itemDiscountPrice = itemDiscountPrice;
        this.itemOriginalPrice = itemOriginalPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }
}