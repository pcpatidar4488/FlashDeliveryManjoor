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
import com.example.manjooralam.flashdelivery.fragments.AllDairyAndBreadFragment;
import com.example.manjooralam.flashdelivery.fragments.AllFruitVegitableFragment;
import com.example.manjooralam.flashdelivery.fragments.BakeAndShakeFragment;
import com.example.manjooralam.flashdelivery.fragments.BreadAndBakeryFragment;
import com.example.manjooralam.flashdelivery.fragments.DairyProductFragment;
import com.example.manjooralam.flashdelivery.fragments.EggsFragment;
import com.example.manjooralam.flashdelivery.fragments.FruitFragment;
import com.example.manjooralam.flashdelivery.fragments.VegitableFragment;

/**
 * Created by manjooralam on 9/22/2017.
 */

public class DairyAndBreadActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TabLayout tabLayoutHead, tabLayoutChild;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView rvSubCategory;
    private SubCatergoryAdapter subCategoryAdapter;
    private String[] subCategoryList = {};
    private String all[], bakes_and_shake[], bread_and_bakery[],dairy_product[],eggs[] ;
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

        tvTitle.setText(getString(R.string.s_dairy_n_bread).toUpperCase());
        setupViewPager(viewPager);
        all = getResources().getStringArray(R.array.all);
        bakes_and_shake = getResources().getStringArray(R.array.bakes_and_shake);
        bread_and_bakery = getResources().getStringArray(R.array.bread_and_bakery);

        dairy_product = getResources().getStringArray(R.array.dairy_product);
        eggs = getResources().getStringArray(R.array.eggs);
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
                        subCategoryAdapter.addList(bakes_and_shake);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 2 :
                        subCategoryAdapter.addList(bread_and_bakery);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 3 :
                        subCategoryAdapter.addList(dairy_product);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 4 :
                        subCategoryAdapter.addList(eggs);
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
        viewPagerAdapter.addFragment(new AllDairyAndBreadFragment(), "All");
        viewPagerAdapter.addFragment(new BakeAndShakeFragment(), "Bake & Shake");
        viewPagerAdapter.addFragment(new BreadAndBakeryFragment(), "Bread &Bakery");
        viewPagerAdapter.addFragment(new DairyProductFragment(), "Dairy Product");
        viewPagerAdapter.addFragment(new EggsFragment(), "Eggs");
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
