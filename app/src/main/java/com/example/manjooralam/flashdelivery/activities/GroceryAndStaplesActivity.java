package com.example.manjooralam.flashdelivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import com.example.manjooralam.flashdelivery.fragments.AllFruitVegitableFragment;
import com.example.manjooralam.flashdelivery.fragments.AllGroceryStapleFragment;
import com.example.manjooralam.flashdelivery.fragments.DryFruitsFragment;
import com.example.manjooralam.flashdelivery.fragments.EdibleOilsGheeFragment;
import com.example.manjooralam.flashdelivery.fragments.FloursFragment;
import com.example.manjooralam.flashdelivery.fragments.FruitFragment;
import com.example.manjooralam.flashdelivery.fragments.PulsesFragment;
import com.example.manjooralam.flashdelivery.fragments.RiceVariantsFragment;
import com.example.manjooralam.flashdelivery.fragments.SaltAndSugerFragment;
import com.example.manjooralam.flashdelivery.fragments.SpicesFragment;
import com.example.manjooralam.flashdelivery.fragments.VegitableFragment;

/**
 * Created by manjooralam on 9/22/2017.
 */

public class GroceryAndStaplesActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TabLayout tabLayoutHead, tabLayoutChild;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView rvSubCategory;
    private SubCatergoryAdapter subCategoryAdapter;
    private String[] subCategoryList = {};
    private String all[], DryFruits[], EdibleOilGhee[] ,Flours[],pulses[],rice_varients[],salts_and_sugar[],spices[];
    private int selectedTabPosition ;
    private TextView search;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_and_vegitable);
        initViews();
        initialPageSetup();
    }

    private void initialPageSetup() {

        setupViewPager(viewPager);
        all = getResources().getStringArray(R.array.all);
        DryFruits = getResources().getStringArray(R.array.DryFruits);
        EdibleOilGhee = getResources().getStringArray(R.array.EdibleOilGhee);
        Flours = getResources().getStringArray(R.array.Flours);
        pulses = getResources().getStringArray(R.array.pulses);
        rice_varients = getResources().getStringArray(R.array.rice_varients);

        salts_and_sugar = getResources().getStringArray(R.array.salts_and_sugar);
        spices = getResources().getStringArray(R.array.spices);

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
                        subCategoryAdapter.addList(DryFruits);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 2 :
                        subCategoryAdapter.addList(EdibleOilGhee);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 3 :
                        subCategoryAdapter.addList(Flours);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 4 :
                        subCategoryAdapter.addList(pulses);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 5 :
                        subCategoryAdapter.addList(rice_varients);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 6 :
                        subCategoryAdapter.addList(salts_and_sugar);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 7 :
                        subCategoryAdapter.addList(spices);
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
        viewPagerAdapter.addFragment(new AllGroceryStapleFragment(), "All");
        viewPagerAdapter.addFragment(new DryFruitsFragment(), "Dry Fruits");
        viewPagerAdapter.addFragment(new EdibleOilsGheeFragment(), "Edible Oils & Ghee ");


        viewPagerAdapter.addFragment(new FloursFragment(), "Flours");
        viewPagerAdapter.addFragment(new PulsesFragment(), "Pulses");
        viewPagerAdapter.addFragment(new RiceVariantsFragment(), "Rice Variants ");

        viewPagerAdapter.addFragment(new SaltAndSugerFragment(), "Salt & Sugar");
        viewPagerAdapter.addFragment(new SpicesFragment(), "Spices ");
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initViews() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
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
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + 1);
                switch (position) {
                    case 0:
                        ((DryFruitsFragment)fragment).updateList(0);
                        break;

                    case 1:
                        ((DryFruitsFragment)fragment).updateList(1);

                        break;

                    case 2:
                        ((DryFruitsFragment)fragment).updateList(2);

                        break;

                    case 3:
                        ((DryFruitsFragment)fragment).updateList(3);

                        break;

                    case 4:
                        ((DryFruitsFragment)fragment).updateList(4);

                        break;

                    case 5:
                        Toast.makeText(this, "Other fruits", Toast.LENGTH_LONG).show();

                        break;
                }
                break;

            case 2:
                Fragment edibleOilsGheeFragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + 2);
                switch (position) {
                    case 0:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(0);
                        break;

                    case 1:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(1);

                        break;

                    case 2:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(2);

                        break;

                    case 3:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(3);

                        break;

                    case 4:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(4);

                        break;

                    case 5:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(5);

                        break;
                    case 6:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(6);

                        break;
                    case 7:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(7);

                        break;
                    case 8:
                        ((EdibleOilsGheeFragment)edibleOilsGheeFragment).updateList(8);

                        break;
                }
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
