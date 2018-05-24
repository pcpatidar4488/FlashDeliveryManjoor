package com.example.manjooralam.flashdelivery.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FeaturedProductModel {

    public String name;
    public Long discount_price;
    public Long id;
    public Long original_price;
    public String quantity;
    public int count = 1;
    public int maxCount = 12 ;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public FeaturedProductModel() {
    }

    public FeaturedProductModel(String name, Long discount_price, Long id, String quantity,Long original_price) {
        this.name = name;
        this.discount_price = discount_price;
        this.id = id;
        this.quantity = quantity;
        this.original_price = original_price;
    }
}
