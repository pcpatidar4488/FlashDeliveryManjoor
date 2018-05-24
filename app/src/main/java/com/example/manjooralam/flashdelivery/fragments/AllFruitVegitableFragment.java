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

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.CakesAndFlowerAdapter;
import com.example.manjooralam.flashdelivery.adapters.FruitsAndVegitableAdapter;
import com.example.manjooralam.flashdelivery.models.FruitsAndVegitableModel;
import com.example.manjooralam.flashdelivery.models.FruitsModel;
import com.example.manjooralam.flashdelivery.utilities.AppConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AllFruitVegitableFragment extends Fragment {

    private RecyclerView recyclerViewAll;
    private Activity mActivity;
    private FruitsAndVegitableAdapter fruitsAndVegitableAdapter;
    private List<FruitsModel> fruitVegitableList = new ArrayList<>();
    private List<FruitsAndVegitableModel> cakesAndFlowerList = new ArrayList<>();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ProgressBar progressbar;

    public AllFruitVegitableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        initViews(view);
        initialPageSetup();
        return view;
    }

    /**
     * initial Page Setup
     */
    private void initialPageSetup() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES);

        recyclerViewAll.setLayoutManager(new LinearLayoutManager(mActivity));
        fruitsAndVegitableAdapter = new FruitsAndVegitableAdapter(mActivity, fruitVegitableList);
        recyclerViewAll.setAdapter(fruitsAndVegitableAdapter);
        recyclerViewAll.setAdapter(fruitsAndVegitableAdapter);

        hitApiVegitableFruitFullList();
    }

    private void hitApiVegitableFruitFullList() {
        progressbar.setVisibility(View.VISIBLE);
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.GONE);
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        if (child.getChildrenCount() > 0) {
                            for (DataSnapshot subChild : child.getChildren()) {
                                FruitsModel fruitsModel = subChild.getValue(FruitsModel.class);
                                fruitVegitableList.add(fruitsModel);
                            }
                        }
                    }
                    fruitsAndVegitableAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     *
     * @param view
     */
    private void initViews(View view) {

        progressbar = view.findViewById(R.id.progressbar);
        recyclerViewAll = view.findViewById(R.id.recycler_all);
    }

    public void updateList() {
        fruitVegitableList.clear();
        hitApiVegitableFruitFullList();
    }
}
