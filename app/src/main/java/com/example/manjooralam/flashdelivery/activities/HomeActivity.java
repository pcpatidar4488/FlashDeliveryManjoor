package com.example.manjooralam.flashdelivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.ExpendableDrawerListAdapter;
import com.example.manjooralam.flashdelivery.adapters.MainCategoryAdapter;
import com.example.manjooralam.flashdelivery.models.CategoryModel;
import com.example.manjooralam.flashdelivery.utilities.AppSharedPreferences;
import com.example.manjooralam.flashdelivery.utilities.AppUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;

public class HomeActivity extends BaseActivity implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private ImageView ivNavbar;
    public   DrawerLayout drawerLayout;
    private ExpendableDrawerListAdapter listAdapter;
    public ExpandableListView expListView;
    private RecyclerView rvCategory;
    private GridLayoutManager gridLayoutManager;
    private MainCategoryAdapter mainCategoryAdapter;
    private ArrayList<String> listDataHeader;
    private HashMap<String, ArrayList<String>> listDataChild;
    private TextView tvNewArrival,layouttoolbar,featuredproduct,heavydiscount, tvUserName, tvMobileNumber;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    private int sliderPosition;
    private CircleImageView civProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());

        initView();
        initialPageSetUp();
        logUser();
    }
    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("manjooralam28@gmail.com");
        Crashlytics.setUserName("Test User");
    }


    private void initialPageSetUp() {
        navigationDrawerListSetUp();
        autoSliderSetUp();
        mainCategoriesSetUp();
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                AppUtils.getInstance().hideProgessDialog();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    AppSharedPreferences.clearAllPrefs(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        };
    }


    private void setUserDataInSidePanel() {
        Glide.with(this).load(AppSharedPreferences.getString(this, AppSharedPreferences.PREF_KEY.USER_IMAGE)).centerCrop().into(civProfileImage);
        tvUserName.setText(getResources().getString(R.string.s_welcome) + ", " + AppSharedPreferences.getString(this, AppSharedPreferences.PREF_KEY.FULL_NAME));
        tvMobileNumber.setText( AppSharedPreferences.getString(this, AppSharedPreferences.PREF_KEY.MOBILE_NUMBER));

    }

    private void mainCategoriesSetUp() {
        List<CategoryModel> rowListItem = getAllItemList();
        gridLayoutManager = new GridLayoutManager(HomeActivity.this, 2);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(gridLayoutManager);
        mainCategoryAdapter = new MainCategoryAdapter(HomeActivity.this, rowListItem);
        rvCategory.setAdapter(mainCategoryAdapter);
    }

    private List<CategoryModel> getAllItemList(){

        List<CategoryModel> allItems = new ArrayList<CategoryModel>();
        allItems.add(new CategoryModel(getResources().getString(R.string.s_offers), R.drawable.fruits_and_vegetables));
        allItems.add(new CategoryModel(getResources().getString(R.string.s_cake_n_flowers), R.drawable.fruits_and_vegetables));
        allItems.add(new CategoryModel(getResources().getString(R.string.s_fruits_n_vegitables), R.drawable.fruits_and_vegetables));
        allItems.add(new CategoryModel(getResources().getString(R.string.s_grocery_n_staples), R.drawable.fruits_and_vegetables));
        allItems.add(new CategoryModel(getResources().getString(R.string.s_household), R.drawable.fruits_and_vegetables));
        allItems.add(new CategoryModel(getResources().getString(R.string.s_beverages), R.drawable.fruits_and_vegetables));
        allItems.add(new CategoryModel(getResources().getString(R.string.s_personal_care), R.drawable.fruits_and_vegetables));
        allItems.add(new CategoryModel(getResources().getString(R.string.s_dairy_n_bread), R.drawable.fruits_and_vegetables));
        allItems.add(new CategoryModel(getResources().getString(R.string.s_branded_foods), R.drawable.fruits_and_vegetables));

        return allItems;
    }

    /**
     * method for preparing auto slider data
     */
    private void autoSliderSetUp() {

        Hash_file_maps = new HashMap<String, String>();
        Hash_file_maps.put("Android CupCake", "http://images.all-free-download.com/images/graphicthumb/vegetables_a_collection_of_picture_167139.jpg");
        Hash_file_maps.put("Android Donut", "https://www.phoolwala.com/adminpanel/uploads/large/1296377312-PH-N-15-YP-R-0.5KGCCAKE.jpg");
        Hash_file_maps.put("Android Eclair", "http://www.allaccessdisney.com/assets/groceries1.jpg");
        Hash_file_maps.put("Android Froyo", "https://media.mercola.com/ImageServer/Public/2015/June/household-products-fb.jpg");
        Hash_file_maps.put("Android GingerBread", "https://www.godairyfree.org/wp-content/uploads/2014/06/Silver-Hills-Bakery-Sprouted-Breads.jpg");

        for(String name : Hash_file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(HomeActivity.this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDrawingCacheEnabled(true);
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }

    /**
     * method for initializing views & registering listeners
     */
    private void initView() {
        ivNavbar = (ImageView) findViewById(R.id.iv_navbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        expListView = (ExpandableListView) findViewById(R.id.lvExp_drawer);
        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        tvNewArrival = (TextView) findViewById(R.id.arrivals);
        layouttoolbar = (TextView) findViewById(R.id.tv_title);
        featuredproduct = (TextView) findViewById(R.id.featured_product);
        heavydiscount = (TextView) findViewById(R.id.heavy_discount);
        tvNewArrival   = (TextView) findViewById(R.id.arrivals);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvMobileNumber = (TextView) findViewById(R.id.tv_mobile_number);
        civProfileImage = (CircleImageView) findViewById(R.id.civ_profile_image);
        //--registering Lsteners
        ivNavbar.setOnClickListener(this);
        tvNewArrival.setOnClickListener(this);
        featuredproduct.setOnClickListener(this);
        heavydiscount.setOnClickListener(this);
    }

    /**
     * method for preparing list data for navigation drawer sidepanel
     */
    private void navigationDrawerListSetUp() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<String>>();

        // Adding child data
        listDataHeader.add(getResources().getString(R.string.s_home));
        listDataHeader.add(getResources().getString(R.string.s_my_account));
        listDataHeader.add(getResources().getString(R.string.s_credit_program));
        listDataHeader.add(getResources().getString(R.string.s_order_on_phone));
        listDataHeader.add(getResources().getString(R.string.s_notification_center));
        listDataHeader.add(getResources().getString(R.string.s_super_store_locator));
        listDataHeader.add(getResources().getString(R.string.s_contact_us));
        listDataHeader.add(getResources().getString(R.string.s_company_info));
        listDataHeader.add(getResources().getString(R.string.s_share_app));
        listDataHeader.add(getResources().getString(R.string.s_logout));


        // Adding child data
        ArrayList<String> homeSubList = new ArrayList<String>();

        ArrayList<String> myAccountSubList = new ArrayList<String>();
        myAccountSubList.add(getResources().getString(R.string.s_my_orders));
        myAccountSubList.add(getResources().getString(R.string.s_wallet));
        myAccountSubList.add(getResources().getString(R.string.s_rewards));
        myAccountSubList.add(getResources().getString(R.string.s_shopping_list));
        myAccountSubList.add(getResources().getString(R.string.s_change_mobile_number));
        myAccountSubList.add(getResources().getString(R.string.s_address_book));
        myAccountSubList.add(getResources().getString(R.string.s_edit_profile));

        ArrayList<String> crditProgramSublist = new ArrayList<String>();
        crditProgramSublist.add(getResources().getString(R.string.s_my_orders));
        crditProgramSublist.add(getResources().getString(R.string.s_wallet));
        crditProgramSublist.add(getResources().getString(R.string.s_rewards));

        ArrayList<String> orderSubList = new ArrayList<String>();
        ArrayList<String> notificationSubList = new ArrayList<String>();
        ArrayList<String> superstoreSubList = new ArrayList<String>();
        ArrayList<String> contactUsSubList = new ArrayList<String>();
        ArrayList<String> companyInfoSubList = new ArrayList<String>();
        companyInfoSubList.add(getResources().getString(R.string.s_about_us));
        companyInfoSubList.add(getResources().getString(R.string.s_privacy_policy));
        companyInfoSubList.add(getResources().getString(R.string.s_terms_and_conditions));


        ArrayList<String> shareAppSubList = new ArrayList<String>();
        ArrayList<String> logoutSublist = new ArrayList<String>();


        listDataChild.put(listDataHeader.get(0), homeSubList); // Header, Child data
        listDataChild.put(listDataHeader.get(1), myAccountSubList);
        listDataChild.put(listDataHeader.get(2), crditProgramSublist);
        listDataChild.put(listDataHeader.get(3), orderSubList);
        listDataChild.put(listDataHeader.get(4), notificationSubList);
        listDataChild.put(listDataHeader.get(5), superstoreSubList);
        listDataChild.put(listDataHeader.get(6), contactUsSubList);
        listDataChild.put(listDataHeader.get(7), companyInfoSubList);
        listDataChild.put(listDataHeader.get(8), shareAppSubList);
        listDataChild.put(listDataHeader.get(9), logoutSublist);



        listAdapter = new ExpendableDrawerListAdapter(this, listDataHeader, listDataChild);
        expListView.setGroupIndicator(null);
        expListView.setAdapter(listAdapter);
    }

    /**
     * method for handling clicks
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_navbar:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.featured_product:
                startActivity(new Intent(this, FeaturedProductActivity.class).putExtra("EXTRA_FROM", "featuredProduct"));
                break;
            case R.id.heavy_discount:
                startActivity(new Intent(this, FeaturedProductActivity.class).putExtra("EXTRA_FROM", "heavyDiscounts"));
                break;
            case R.id.arrivals:
                startActivity(new Intent(this, FeaturedProductActivity.class).putExtra("EXTRA_FROM", "newArrivals"));
                break;
        }
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

        switch (sliderPosition) {
            case 0:
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                break;

            case 1:
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                break;

            case 2:
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                break;

            case 3:
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                break;

            case 4:
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        sliderPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    /**
     * method for opening screens
     * @param groupPosition
     */
    public void openItem(int groupPosition) {
        switch (groupPosition) {

            case 3:
                startActivity(new Intent(this, OrderOnPhoneActivity.class));
                break;
        }
    }

    public void logoutApiCall() {
        auth.signOut();
    }


    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
        setUserDataInSidePanel();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}


