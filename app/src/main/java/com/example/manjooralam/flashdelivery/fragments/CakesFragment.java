package com.example.manjooralam.flashdelivery.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.CakesAdapter;
import com.example.manjooralam.flashdelivery.adapters.VegetableAdapter;
import com.example.manjooralam.flashdelivery.models.FruitsModel;
import com.example.manjooralam.flashdelivery.utilities.AppConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CakesFragment extends Fragment {

    private RecyclerView recyclerViewAll;
    private Activity mActivity;
    private CakesAdapter cakesAdapter;
    private List<FruitsModel> cakessList = new ArrayList<>();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private RelativeLayout rlRootlayout;
    private ProgressBar progressbar;
    public CakesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cake, container, false);
        initViews(view);
        initialPageSetup();
        return view; }

    /**
     * initial Page Setup
     */
    private void initialPageSetup() {

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                +"/"+ AppConstants.VEGETABLES
                +"/"+AppConstants.BEANSAndBRINJLE);
        recyclerViewAll.setLayoutManager(new LinearLayoutManager(mActivity));
        cakesAdapter = new CakesAdapter(mActivity, cakessList);
        recyclerViewAll.setAdapter(cakesAdapter);

        hitApiToGetList();
    }


    private void hitApiToGetList() {

        progressbar.setVisibility(View.VISIBLE);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.GONE);
                GenericTypeIndicator<List<FruitsModel>> t = new GenericTypeIndicator<List<FruitsModel>>() {};
                if (dataSnapshot.getValue()!= null) {
                    cakessList.addAll(dataSnapshot.getValue(t));
                }
                cakesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    /**
     *
     * @param view
     */
    private void initViews(View view) {

        recyclerViewAll = view.findViewById(R.id.recycler_fruits);
        progressbar = view.findViewById(R.id.progressbar);
    }

    public void updateList(int position) {

        switch (position) {
            case 0:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.CAKES_AND_FLOWER
                        +"/"+ AppConstants.CAKES
                        +"/"+AppConstants.BEANSAndBRINJLE);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;

            case 1:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.CAKES_AND_FLOWER
                        +"/"+ AppConstants.CAKES
                        +"/"+AppConstants.CHILLILEMON);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;
            case 2:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.CAKES_AND_FLOWER
                        +"/"+ AppConstants.CAKES
                        +"/"+AppConstants.EXOTIC_VEGETABLES);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;

            case 3:

                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.VEGETABLES
                        +"/"+AppConstants.GARLICGINGER);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;
            case 4:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.VEGETABLES
                        +"/"+AppConstants.GUARDS_CUCUMBER);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;

            case 5:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.VEGETABLES
                        +"/"+AppConstants.LEAFY_VEGETABLES);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;

            case 6:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.VEGETABLES
                        +"/"+AppConstants.OTHERS_VEGETABLES);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;
            case 7:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.VEGETABLES
                        +"/"+AppConstants.POTATO_ONION_TOMATO);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;
            case 8:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.VEGETABLES
                        +"/"+AppConstants.ROOT_VEGETABLES);
                cakessList.clear();
                cakesAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;


        }
    }
}
