package com.example.manjooralam.flashdelivery.models.offers_response;

/**
 * Created by manjooralam on 10/8/2017.
 */

public class OffersBean {

    public String itemDiscountPrice;
    public String itemOriginalPrice;
    public String quantity;
    public String imageUrl;
    public OffersBean() {

    }

    public OffersBean(String itemDiscountPrice, String itemOriginalPrice, String quantity, String imageUrl) {
        this.itemDiscountPrice = itemDiscountPrice;
        this.itemOriginalPrice = itemOriginalPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }
}
