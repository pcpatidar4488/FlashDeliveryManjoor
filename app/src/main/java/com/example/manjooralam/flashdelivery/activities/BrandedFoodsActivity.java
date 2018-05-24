package com.example.manjooralam.flashdelivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.SubCatergoryAdapter;
import com.example.manjooralam.flashdelivery.adapters.ViewPagerAdapter;
import com.example.manjooralam.flashdelivery.fragments.AllBrandedFoodFragment;
import com.example.manjooralam.flashdelivery.fragments.AllFruitVegitableFragment;
import com.example.manjooralam.flashdelivery.fragments.BabyFoodFragment;
import com.example.manjooralam.flashdelivery.fragments.BakingAndDesert_ItemFragment;
import com.example.manjooralam.flashdelivery.fragments.BiscuitsAndCookiesFragment;
import com.example.manjooralam.flashdelivery.fragments.BreakFastCerealsFragment;
import com.example.manjooralam.flashdelivery.fragments.ChocolateAndCandiesSweetsFragment;
import com.example.manjooralam.flashdelivery.fragments.FruitFragment;
import com.example.manjooralam.flashdelivery.fragments.GiftAndSweetsFragment;
import com.example.manjooralam.flashdelivery.fragments.IndianSweetsFragment;
import com.example.manjooralam.flashdelivery.fragments.JamsSpreadsFragment;
import com.example.manjooralam.flashdelivery.fragments.NamkeenFragment;
import com.example.manjooralam.flashdelivery.fragments.NoodlesMacroniPastaFragment;
import com.example.manjooralam.flashdelivery.fragments.PickleFragment;
import com.example.manjooralam.flashdelivery.fragments.ReadyEatCookFragment;
import com.example.manjooralam.flashdelivery.fragments.SaucesKetchupFragment;
import com.example.manjooralam.flashdelivery.fragments.VegSnacksFragment;
import com.example.manjooralam.flashdelivery.fragments.VegitableFragment;

/**
 * Created by manjooralam on 9/22/2017.
 */

public class BrandedFoodsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TabLayout tabLayoutHead, tabLayoutChild;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView rvSubCategory;
    private SubCatergoryAdapter subCategoryAdapter;
    private String[] subCategoryList = {};
    private String all[], baby_food[], baking_dessert_items[],biscuits_cookies[],breakfast_cereals[],chocolates_candies_sweets[],gift_and_sweets[],indian_sweets[],jams_and_spreads[],namkeen[],noodles_marconi_and_paste[],pickles[],ready_to_eat_and_cook[],sauces_and_ketchup[],veg_snakes[];
    private int selectedTabPosition ;
    private TextView search, tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_and_vegitable);
        initViews();
        initialPageSetup();
    }

    private void initialPageSetup() {
        tvTitle.setText(getString(R.string.s_branded_foods).toUpperCase());
        setupViewPager(viewPager);
        all = getResources().getStringArray(R.array.all);
        baby_food = getResources().getStringArray(R.array.baby_food);
        baking_dessert_items = getResources().getStringArray(R.array.baking_dessert_items);
        biscuits_cookies = getResources().getStringArray(R.array.biscuits_cookies);
        breakfast_cereals = getResources().getStringArray(R.array.breakfast_cereals);
        chocolates_candies_sweets = getResources().getStringArray(R.array.chocolates_candies_sweets);
        gift_and_sweets = getResources().getStringArray(R.array.gift_and_sweets);
        indian_sweets = getResources().getStringArray(R.array.indian_sweets);
        jams_and_spreads = getResources().getStringArray(R.array.jams_and_spreads);
        namkeen = getResources().getStringArray(R.array.namkeen);
        noodles_marconi_and_paste = getResources().getStringArray(R.array.noodles_marconi_and_paste);
        pickles = getResources().getStringArray(R.array.pickles);

        ready_to_eat_and_cook = getResources().getStringArray(R.array.ready_to_eat_and_cook);
        sauces_and_ketchup = getResources().getStringArray(R.array.sauces_and_ketchup);
        veg_snakes = getResources().getStringArray(R.array.veg_snakes);
        recyclerViewSetup();
        tabLayoutHead.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTabPosition = tab.getPosition();
                switch (tab.getPosition()) {
                    case 0 :
                        subCategoryAdapter.addList(all);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 1 :
                        subCategoryAdapter.addList(baby_food);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 2 :
                        subCategoryAdapter.addList(baking_dessert_items);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 3 :
                        subCategoryAdapter.addList(biscuits_cookies);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 4 :
                        subCategoryAdapter.addList(breakfast_cereals);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 5 :
                        subCategoryAdapter.addList(chocolates_candies_sweets);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 6 :
                        subCategoryAdapter.addList(gift_and_sweets);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 7 :
                        subCategoryAdapter.addList(indian_sweets);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 8 :
                        subCategoryAdapter.addList(jams_and_spreads);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 9 :
                        subCategoryAdapter.addList(namkeen);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 10 :
                        subCategoryAdapter.addList(noodles_marconi_and_paste);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 11 :
                        subCategoryAdapter.addList(pickles);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 12 :
                        subCategoryAdapter.addList(ready_to_eat_and_cook);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 13 :
                        subCategoryAdapter.addList(sauces_and_ketchup);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 14 :
                        subCategoryAdapter.addList(veg_snakes);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void recyclerViewSetup() {
        subCategoryAdapter = new SubCatergoryAdapter(this, all);
        rvSubCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSubCategory.setAdapter(subCategoryAdapter);
    }

    private void setupViewPager(ViewPager viewPager) {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new AllBrandedFoodFragment(), "All");
        viewPagerAdapter.addFragment(new BabyFoodFragment(), " Baby Food");
        viewPagerAdapter.addFragment(new BakingAndDesert_ItemFragment(), "Baking & Dessert Items");
        viewPagerAdapter.addFragment(new BiscuitsAndCookiesFragment(), "Biscuits & Cookies");
        viewPagerAdapter.addFragment(new BreakFastCerealsFragment(), "BreakFast Cereals");
        viewPagerAdapter.addFragment(new ChocolateAndCandiesSweetsFragment(), "Chocolates,Candies& Sweets");

        viewPagerAdapter.addFragment(new GiftAndSweetsFragment(), "Gift And Sweets");
        viewPagerAdapter.addFragment(new IndianSweetsFragment(), "Indian Sweets");
        viewPagerAdapter.addFragment(new JamsSpreadsFragment(), "Jams & Spreads");
        viewPagerAdapter.addFragment(new NamkeenFragment(), "Namkeen");

        viewPagerAdapter.addFragment(new NoodlesMacroniPastaFragment(), "Noodles Macroni & Pasta");
        viewPagerAdapter.addFragment(new PickleFragment(), "Pickle");
        viewPagerAdapter.addFragment(new ReadyEatCookFragment(), "Ready to eat & cook");
        viewPagerAdapter.addFragment(new SaucesKetchupFragment(), "Sauces & Ketchup");
        viewPagerAdapter.addFragment(new VegSnacksFragment(), "Veg Snacks");
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initViews() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayoutHead = (TabLayout) findViewById(R.id.tab_category);
        rvSubCategory = (RecyclerView) findViewById(R.id.rv_sub_category);
        tabLayoutHead.setupWithViewPager(viewPager);
        search= (TextView) findViewById(R.id.tv_search);
        search.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    public void handleSubCategory(int position) {

        switch (selectedTabPosition) {

            case 0:
                Toast.makeText(this, "All", Toast.LENGTH_LONG).show();
                break;

            case 1:
                switch (position) {
                    case 0:
                        Toast.makeText(this, "Apple", Toast.LENGTH_LONG).show();
                        break;

                    case 1:
                        Toast.makeText(this, "Apple", Toast.LENGTH_LONG).show();

                        break;

                    case 2:
                        Toast.makeText(this, "Banana", Toast.LENGTH_LONG).show();

                        break;

                    case 3:
                        Toast.makeText(this, "Mango", Toast.LENGTH_LONG).show();

                        break;

                    case 4:
                        Toast.makeText(this, "Orange and Sweet limes", Toast.LENGTH_LONG).show();

                        break;

                    case 5:
                        Toast.makeText(this, "Other fruits", Toast.LENGTH_LONG).show();

                        break;
                }
                break;

            case 2:
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }
}
