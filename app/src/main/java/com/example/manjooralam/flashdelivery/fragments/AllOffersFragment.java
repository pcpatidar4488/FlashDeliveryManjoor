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
import com.example.manjooralam.flashdelivery.adapters.OffersAdapter;
import com.example.manjooralam.flashdelivery.models.offers_response.OffersModel;
import com.example.manjooralam.flashdelivery.utilities.AppConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AllOffersFragment extends Fragment {

    private RecyclerView recyclerViewAll;
    private Activity mActivity;
    private OffersAdapter offersAdapter;
    private List<OffersModel> offersList = new ArrayList<>();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ProgressBar progressbar;

    public AllOffersFragment() {
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
        mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.OFFERS);
        recyclerViewAll.setLayoutManager(new LinearLayoutManager(mActivity));
        offersAdapter = new OffersAdapter(mActivity, offersList);
        recyclerViewAll.setAdapter(offersAdapter);
        hitApiOffersFullList();
    }

    private void hitApiOffersFullList() {
        progressbar.setVisibility(View.VISIBLE);
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.GONE);
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        OffersModel offersModel = child.getValue(OffersModel.class);
                        offersList.add(offersModel);
                    }
                    offersAdapter.notifyDataSetChanged();
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
        offersList.clear();
        hitApiOffersFullList();
    }
}
