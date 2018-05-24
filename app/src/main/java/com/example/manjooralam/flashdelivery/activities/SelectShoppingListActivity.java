package com.example.manjooralam.flashdelivery.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.AddShoppingListAdapter;
import com.example.manjooralam.flashdelivery.models.FruitsModel;
import com.example.manjooralam.flashdelivery.models.ItemModel;
import com.example.manjooralam.flashdelivery.models.SampleBean;
import com.example.manjooralam.flashdelivery.models.ShoppingListModel;
import com.example.manjooralam.flashdelivery.utilities.AppConstants;
import com.example.manjooralam.flashdelivery.utilities.AppUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjooralam on 10/14/2017.
 */

public class SelectShoppingListActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout llRootLayout;
    private Activity mActivity;
    private ImageView Ivback;
    private TextView tvTitle, tvSubmit, tvNewList;
    private EditText et_ShoppingList;
    private static int id =0;
    private RecyclerView rvShoppingList;
    FirebaseAuth mAuth;
    FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ProgressBar progressbar;
    private AddShoppingListAdapter shoppingListAdapter;
    List<String> shoppingList = new ArrayList<>();
    public SelectShoppingListActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_shoppinglist);
        initViews();
        initialPageSetup();
        tvNewList.setOnClickListener(this);
        Ivback.setOnClickListener(this);
    }

    private void initialPageSetup() {
        tvTitle.setText("Add Shopping List");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();
        mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.SHOPPING_LIST
        ).child(mFirebaseUser.getUid());
        rvShoppingList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true));
        shoppingListAdapter=new AddShoppingListAdapter(this,shoppingList);
        rvShoppingList.setAdapter(shoppingListAdapter);



        hitApiToGetList();
    }

    private void hitApiToGetList() {
        progressbar.setVisibility(View.VISIBLE);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.GONE);
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        if(child.getChildrenCount() > 0){
                            for (DataSnapshot grandChild : child.getChildren()) {
                                String shoppingItem = grandChild.getValue(String.class);
                                shoppingList.add(shoppingItem);


                            }
                        }
                    }
                }
                shoppingListAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }

    private void initViews() {
     //   llMoneyWallet = (LinearLayout) findViewById(R.id.ll_money_wallet_container);
       // tvSubmit = (TextView) findViewById(R.id.tv_submit);
        tvNewList = (TextView) findViewById(R.id.tv_newlist);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rvShoppingList= (RecyclerView) findViewById(R.id.rv_shopping);
        llRootLayout= (LinearLayout) findViewById(R.id.ll_root_layout);
        Ivback= (ImageView) findViewById(R.id.iv_back);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_newlist:
                final Dialog dialog1 = new Dialog(SelectShoppingListActivity.this);
                dialog1.setContentView(R.layout.dialog_createshopping_list);
                Window window = dialog1.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog1.setCancelable(true);
                TextView tvCancel=dialog1.findViewById(R.id.tv_cancellist);
                et_ShoppingList= (EditText)dialog1. findViewById(R.id.et_shoppinglist);
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef;
                Uri filePath;
                ProgressDialog pd;
                 Uri url;


                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       dialog1.dismiss();
                    }
                });

                TextView tvCreateList=dialog1.findViewById(R.id.tv_createlist);

                tvCreateList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(et_ShoppingList.getText().toString().trim().length() == 0){
                            AppUtils.getInstance().showSnackBar("please enter shopping list",llRootLayout);

                        }else {
                            hitApi();
                        }
                    }

                    private void hitApi() {
                      //  ShoppingListModel itemData = new ShoppingListModel( );
                        FirebaseDatabase.getInstance()
                                .getReference()
                                .child(AppConstants.SHOPPING_LIST)
                                .child(mFirebaseUser.getUid())
                                .push()
                                .child("item")
                                .setValue(et_ShoppingList.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()) {
                                            AppUtils.getInstance().showSnackBar("SignUp Failed", llRootLayout);
                                        } else {
                                            AppUtils.getInstance().showSnackBar("Successfully Uploaded", llRootLayout);


                                        }
                                        et_ShoppingList.setText("");

                                    }
                                });

                    }


                });
                dialog1.show();

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}


