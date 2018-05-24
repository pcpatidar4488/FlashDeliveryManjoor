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
import com.example.manjooralam.flashdelivery.fragments.FruitFragment;
import com.example.manjooralam.flashdelivery.fragments.VegitableFragment;

/**
 * Created by manjooralam on 9/22/2017.
 */

public class FruitsAndVegitableActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TabLayout tabLayoutHead, tabLayoutChild;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView rvSubCategory;
    private SubCatergoryAdapter subCategoryAdapter;
    private String[] subCategoryList = {};
    private String all[], fruitsArray[], vegitableArray[] ;
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

        tvTitle.setText(getString(R.string.s_fruits_n_vegitables).toUpperCase());
        setupViewPager(viewPager);
        all = getResources().getStringArray(R.array.all);
        fruitsArray = getResources().getStringArray(R.array.fruits_array);
        vegitableArray = getResources().getStringArray(R.array.vegitable_array);
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
                        subCategoryAdapter.addList(fruitsArray);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 2 :
                        subCategoryAdapter.addList(vegitableArray);
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
        viewPagerAdapter.addFragment(new AllFruitVegitableFragment(), "All");
        viewPagerAdapter.addFragment(new FruitFragment(), "FRUITS");
        viewPagerAdapter.addFragment(new VegitableFragment(), "VEGITABLES");
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
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
                Fragment allFruitVegitableFragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + 0);
                ((AllFruitVegitableFragment)allFruitVegitableFragment).updateList();
                break;

            case 1:
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + 1);
                switch (position) {
                    case 0:
                        ((FruitFragment)fragment).updateList(0);
                        break;

                    case 1:
                        ((FruitFragment)fragment).updateList(1);
                        break;

                    case 2:
                        ((FruitFragment)fragment).updateList(2);
                        break;

                    case 3:
                        ((FruitFragment)fragment).updateList(3);

                        break;

                    case 4:
                        ((FruitFragment)fragment).updateList(4);

                        break;

                    case 5:
                        ((FruitFragment)fragment).updateList(5);

                        break;
                    case 6:
                        ((FruitFragment)fragment).updateList(6);

                        break;
                }
                break;

            case 2:
                Fragment fragment1 = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + 2);
                switch (position){
                    case 0:
                        ((VegitableFragment)fragment1).updateList(0);
                        break;
                    case 1:
                        ((VegitableFragment)fragment1).updateList(1);
                        break;
                    case 2:
                        ((VegitableFragment)fragment1).updateList(2);
                        break;
                    case 3:
                        ((VegitableFragment)fragment1).updateList(3);
                        break;
                    case 4:
                        ((VegitableFragment)fragment1).updateList(4);
                        break;
                    case 5:
                        ((VegitableFragment)fragment1).updateList(5);
                        break;
                    case 6:
                        ((VegitableFragment)fragment1).updateList(6);
                        break;
                    case 7:
                        ((VegitableFragment)fragment1).updateList(7);
                        break;
                    case 8:
                        ((VegitableFragment)fragment1).updateList(8);
                        break;
                    case 9:
                        ((VegitableFragment)fragment1).updateList(9);
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
