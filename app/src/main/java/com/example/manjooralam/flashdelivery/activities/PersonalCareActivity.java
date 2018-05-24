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
import com.example.manjooralam.flashdelivery.fragments.AllFruitVegitableFragment;
import com.example.manjooralam.flashdelivery.fragments.AllPersonalCareFragment;
import com.example.manjooralam.flashdelivery.fragments.BabyCareFragment;
import com.example.manjooralam.flashdelivery.fragments.CosmeticsFragment;
import com.example.manjooralam.flashdelivery.fragments.DeosAndPerfumeFragment;
import com.example.manjooralam.flashdelivery.fragments.FruitFragment;
import com.example.manjooralam.flashdelivery.fragments.HairCareFragment;
import com.example.manjooralam.flashdelivery.fragments.HealthCareFragment;
import com.example.manjooralam.flashdelivery.fragments.OralCareFragment;
import com.example.manjooralam.flashdelivery.fragments.PersonalHygieneFragment;
import com.example.manjooralam.flashdelivery.fragments.SanitaryNeedsFragment;
import com.example.manjooralam.flashdelivery.fragments.SavingNeedsFragment;
import com.example.manjooralam.flashdelivery.fragments.SkinCareFragment;
import com.example.manjooralam.flashdelivery.fragments.VegitableFragment;

/**
 * Created by manjooralam on 9/22/2017.
 */

public class PersonalCareActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TabLayout tabLayoutHead, tabLayoutChild;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView rvSubCategory;
    private SubCatergoryAdapter subCategoryAdapter;
    private String[] subCategoryList = {};
    private String all[], baby_care[], cosmatics[],deos_and_perfumes[],hair_care[],health_care[],oral_care[],personal_hygine[],sanitary_needs[],shaving_needs[],skin_care[] ;
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
        tvTitle.setText(getString(R.string.s_personal_care).toUpperCase());
        setupViewPager(viewPager);
        all = getResources().getStringArray(R.array.all);
        baby_care = getResources().getStringArray(R.array.baby_care);
        cosmatics = getResources().getStringArray(R.array.cosmatics);
        deos_and_perfumes = getResources().getStringArray(R.array.deos_and_perfumes);
        hair_care = getResources().getStringArray(R.array.hair_care);
        health_care = getResources().getStringArray(R.array.health_care);

        oral_care = getResources().getStringArray(R.array.oral_care);
        personal_hygine = getResources().getStringArray(R.array.personal_hygine);
        sanitary_needs = getResources().getStringArray(R.array.sanitary_needs);
        shaving_needs = getResources().getStringArray(R.array.shaving_needs);
        skin_care = getResources().getStringArray(R.array.skin_care);
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
                        subCategoryAdapter.addList(baby_care);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 2 :
                        subCategoryAdapter.addList(cosmatics);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 3 :
                        subCategoryAdapter.addList(deos_and_perfumes);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 4 :
                        subCategoryAdapter.addList(hair_care);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 5 :
                        subCategoryAdapter.addList(health_care);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 6 :
                        subCategoryAdapter.addList(oral_care);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 7 :
                        subCategoryAdapter.addList(personal_hygine);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 8 :
                        subCategoryAdapter.addList(sanitary_needs);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;
                    case 9 :
                        subCategoryAdapter.addList(shaving_needs);
                        subCategoryAdapter.notifyDataSetChanged();
                        break;

                    case 10 :
                        subCategoryAdapter.addList(skin_care);
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
        viewPagerAdapter.addFragment(new AllPersonalCareFragment(), "All");
        viewPagerAdapter.addFragment(new BabyCareFragment(), "Baby Care");
        viewPagerAdapter.addFragment(new CosmeticsFragment(), "Cosmetics");
        viewPagerAdapter.addFragment(new DeosAndPerfumeFragment(), "Deos & Perfume");
        viewPagerAdapter.addFragment(new HairCareFragment(), "Hair Care");
        viewPagerAdapter.addFragment(new HealthCareFragment(), "Health Care");
        viewPagerAdapter.addFragment(new OralCareFragment(), "Oral Care");
        viewPagerAdapter.addFragment(new PersonalHygieneFragment(), "Personal Hygiene");
        viewPagerAdapter.addFragment(new SanitaryNeedsFragment(), "Sanitary Needs");
        viewPagerAdapter.addFragment(new SavingNeedsFragment(), "Saving Needs");
        viewPagerAdapter.addFragment(new SkinCareFragment(), "Skin Care");


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
