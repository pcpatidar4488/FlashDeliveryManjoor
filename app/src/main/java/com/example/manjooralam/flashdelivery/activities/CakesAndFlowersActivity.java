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
import com.example.manjooralam.flashdelivery.fragments.CakesFragment;
import com.example.manjooralam.flashdelivery.fragments.FlowersFragment;

/**
 * Created by manjooralam on 9/22/2017.
 */

public class CakesAndFlowersActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TabLayout tabLayoutHead, tabLayoutChild;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView rvSubCategory;
    private SubCatergoryAdapter subCategoryAdapter;
    private String[] subCategoryList = {};
    private String all[], cakesArray[], flowersArray[] ;
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
        cakesArray = getResources().getStringArray(R.array.cakes_array);
        flowersArray = getResources().getStringArray(R.array.flowers_array);
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
                        subCategoryAdapter.addList(cakesArray);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 2 :
                        subCategoryAdapter.addList(flowersArray);
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
        viewPagerAdapter.addFragment(new CakesFragment(), "CAKES");
        viewPagerAdapter.addFragment(new FlowersFragment(), "FLOWERS");
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
                Fragment fragment = getSupportFragmentManager().getFragments().get(1);
                switch (position) {
                    case 0:
                        ((CakesFragment)fragment).updateList(0);
                        break;

                    case 1:
                        ((CakesFragment)fragment).updateList(1);

                        break;

                    case 2:
                        ((CakesFragment)fragment).updateList(2);

                        break;


                }
                break;

            case 2:
                Fragment fragment1 = getSupportFragmentManager().getFragments().get(2);
                switch (selectedTabPosition){
                    case 0:
                        ((FlowersFragment)fragment1).updateList(0);
                        break;

                    case 1:
                        ((FlowersFragment)fragment1).updateList(1);

                        break;

                    case 2:
                        ((FlowersFragment)fragment1).updateList(2);

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
