package com.example.manjooralam.flashdelivery.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.DryFruitQuantity;
import com.example.manjooralam.flashdelivery.models.ItemModel;
import com.example.manjooralam.flashdelivery.models.SampleBean;
import com.example.manjooralam.flashdelivery.models.UserModel;
import com.example.manjooralam.flashdelivery.utilities.AppConstants;
import com.example.manjooralam.flashdelivery.utilities.AppSharedPreferences;
import com.example.manjooralam.flashdelivery.utilities.AppUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class AdminPanelActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST =122 ;
    EditText itemName, itemDiscount, itemOriginal, itemMaxCount, discount, original, quantity;

    TextView pushItem, itemAmount;
    private  LinearLayout llRootlayout;
    ImageView itemImage;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef;
    Uri filePath;
    ProgressDialog pd;
    private Uri url;
    private static int id =0;

    ArrayList<SampleBean> dryFruitQuantitieslist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        initViews();
        itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

          findViewById(R.id.amount_detail).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  dryFruitQuantitieslist.add(new SampleBean(discount.getText().toString(), original.getText().toString(),
                          quantity.getText().toString(), url.toString()));
              }
          });

        pushItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemModel itemData = new ItemModel(url.toString(), itemName.getText().toString().toUpperCase(),
                        dryFruitQuantitieslist,
                        itemDiscount.getText().toString().trim(),
                        itemOriginal.getText().toString().trim(),
                        itemMaxCount.getText().toString().trim(),
                        id+1 +"",
                        "dry_fruit");
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child(AppConstants.GROCERY_AND_STAPLES)
                        .child(AppConstants.EDIBLE_OIL_AND_GHEE)
                        .child(id + "")
                        .setValue(itemData)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()) {
                                    AppUtils.getInstance().showSnackBar("SignUp Failed", llRootlayout);
                                } else {
                                    AppUtils.getInstance().showSnackBar("Successfully Uploaded", llRootlayout);
                                    dryFruitQuantitieslist.clear();
                                    id++;
                                }
                            }
                        });
            }
        });
    }

    private void initViews() {

        itemName = (EditText) findViewById(R.id.item_name);
        itemAmount = (TextView) findViewById(R.id.item_amount);
        itemDiscount = (EditText) findViewById(R.id.item_discount);
        itemOriginal = (EditText) findViewById(R.id.item_orginal);
        itemMaxCount = (EditText) findViewById(R.id.item_maxcount);
        pushItem = (TextView) findViewById(R.id.push);
        itemImage = (ImageView) findViewById(R.id.item_pic);
        llRootlayout = (LinearLayout) findViewById(R.id.ll_rootlayout);
        discount = (EditText) findViewById(R.id.discount_price);
        original = (EditText) findViewById(R.id.original_price);
        quantity = (EditText) findViewById(R.id.quantity);
        storageRef = storage.getReference();


        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Setting image to ImageView
                itemImage.setImageBitmap(bitmap);

                if(filePath != null) {
                    pd.show();
                    Long tsLong = System.currentTimeMillis()/1000;
                    StorageReference childRef = storageRef.child("images");
                    StorageReference imaepathRef = childRef.child(tsLong.toString() +filePath.getLastPathSegment());

                    //uploading the image
                    UploadTask uploadTask = imaepathRef.putFile(filePath);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(AdminPanelActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                           url = taskSnapshot.getDownloadUrl();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(AdminPanelActivity.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(AdminPanelActivity.this, "Select an image", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
