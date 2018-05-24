package com.example.manjooralam.flashdelivery.models;

/**
 * Created by manjooralam on 10/8/2017.
 */

public class DryFruitQuantity {

    public String itemDiscountPrice;
    public String itemOriginalPrice;
    public String quantity;
    public DryFruitQuantity() {

    }

    public DryFruitQuantity(String itemDiscountPrice, String itemOriginalPrice, String quantity) {
        this.itemDiscountPrice = itemDiscountPrice;
        this.itemOriginalPrice = itemOriginalPrice;
        this.quantity = quantity;
    }
}
