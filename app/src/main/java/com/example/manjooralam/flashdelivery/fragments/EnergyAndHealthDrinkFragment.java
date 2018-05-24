package com.example.manjooralam.flashdelivery.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.FruitsAdapter;
import com.example.manjooralam.flashdelivery.adapters.FruitsAndVegitableAdapter;
import com.example.manjooralam.flashdelivery.models.FeaturedProductModel;
import com.example.manjooralam.flashdelivery.models.FruitsAndVegitableModel;
import com.example.manjooralam.flashdelivery.models.FruitsModel;
import com.example.manjooralam.flashdelivery.utilities.AppConstants;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class EnergyAndHealthDrinkFragment extends Fragment {

    private RecyclerView recyclerViewAll;
    private Activity mActivity;
    private FruitsAdapter fruitsAdapter;
    private List<FruitsModel> fruitsList = new ArrayList<>();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private RelativeLayout rlRootlayout;
    private ProgressBar progressbar;
    private Query query;
    public EnergyAndHealthDrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fruits, container, false);
        initViews(view);
        initialPageSetup();
        return view; }

    /**
     * initial Page Setup
     */
    private void initialPageSetup() {

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES +"/"+ AppConstants.FRUITS);
        query = mFirebaseDatabase.orderByChild("subtype").equalTo(1);

        recyclerViewAll.setLayoutManager(new LinearLayoutManager(mActivity));
        fruitsAdapter = new FruitsAdapter(mActivity, fruitsList);
        recyclerViewAll.setAdapter(fruitsAdapter);

        hitApiToGetList();
    }


    private void hitApiToGetList() {

        progressbar.setVisibility(View.VISIBLE);
       /* mFirebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                progressbar.setVisibility(View.GONE);
                GenericTypeIndicator<List<FruitsModel>> t = new GenericTypeIndicator<List<FruitsModel>>() {};
                fruitsList.addAll(dataSnapshot.getValue(t));
                fruitsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.GONE);
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        FruitsModel fruitsModel = child.getValue(FruitsModel.class);
                        fruitsList.add(fruitsModel);
                    }
                    fruitsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       /* mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.GONE);
                if (dataSnapshot.getChildrenCount() > 0) {
                    int count = (int) dataSnapshot.getChildrenCount();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        FruitsModel fruitsModel = child.getValue(FruitsModel.class);
                      //  GenericTypeIndicator<List<FruitsModel>> t = new GenericTypeIndicator<List<FruitsModel>>() {};
                        fruitsList.add(fruitsModel);
                    }
                    fruitsAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });*/
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
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.FRUITS);
                query = mFirebaseDatabase.orderByChild("subtype").equalTo(1);
                fruitsList.clear();
                fruitsAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;

            case 1:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.FRUITS);
                query = mFirebaseDatabase.orderByChild("subtype").equalTo(2);
                fruitsList.clear();
                fruitsAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;
            case 2:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.FRUITS);
                query = mFirebaseDatabase.orderByChild("subtype").equalTo(3);
                fruitsList.clear();
                fruitsAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;

            case 3:

                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.FRUITS);
                query = mFirebaseDatabase.orderByChild("subtype").equalTo(4);
                fruitsList.clear();
                fruitsAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;
            case 4:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.FRUITS);
                query = mFirebaseDatabase.orderByChild("subtype").equalTo(5);
                fruitsList.clear();
                fruitsAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;

            case 5:
                mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FRUITS_AND_VEGITABLES
                        +"/"+ AppConstants.FRUITS);
                query = mFirebaseDatabase.orderByChild("subtype").equalTo(6);
                fruitsList.clear();
                fruitsAdapter.notifyDataSetChanged();
                hitApiToGetList();
                break;

        }
    }
}
