package com.example.manjooralam.flashdelivery.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.UserModel;
import com.example.manjooralam.flashdelivery.utilities.AppSharedPreferences;
import com.example.manjooralam.flashdelivery.utilities.AppUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {
    private static final int PICK_FROM_CAMERA = 1, PICK_FROM_FILE = 3, PERMISSION_REQUEST_CODE = 4;
    private TextView name, last_name, adress1, adress2, pincode, submit, tvToolbarTitle;
    private EditText etFullName, etEmail, etMobile, etPassword, e_conformpassword;
    FirebaseStorage storage;
    StorageReference storageRef;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private ImageView ivBack, ivAddProfileImage, ivProfilePic;
    private String semail, spassword, snumber;
    private LinearLayout llRootLayout;
    private Uri mImageCaptureUri, outputUri;
    private Uri filePath;
    ProgressDialog pd;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        initialPageSetup();


    }

    private void initialPageSetup() {
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        textWatcher();
        tvToolbarTitle.setText(getResources().getString(R.string.s_signup));
        ivBack.setVisibility(View.INVISIBLE);
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");
    }

    private void textWatcher() {
        etFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 0) {
                    name.setVisibility(View.INVISIBLE);
                } else {
                    name.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 0) {
                    last_name.setVisibility(View.INVISIBLE);
                } else {
                    last_name.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 0) {
                    adress1.setVisibility(View.INVISIBLE);
                } else {
                    adress1.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 0) {
                    adress2.setVisibility(View.INVISIBLE);
                } else {
                    adress2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e_conformpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 0) {
                    pincode.setVisibility(View.INVISIBLE);
                } else {
                    pincode.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void init() {
        name = (TextView) findViewById(R.id.tname);
        last_name = (TextView) findViewById(R.id.tlast_name);
        adress1 = (TextView) findViewById(R.id.tadress1);
        adress2 = (TextView) findViewById(R.id.tadress2);
        pincode = (TextView) findViewById(R.id.tpin);
        submit = (TextView) findViewById(R.id.tsubmit);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_title);

        etFullName = (EditText) findViewById(R.id.et_full_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etPassword = (EditText) findViewById(R.id.et_password);
        e_conformpassword = (EditText) findViewById(R.id.conform_edit);
        llRootLayout = (LinearLayout) findViewById(R.id.ll_rootlayout);
        ivAddProfileImage = (ImageView) findViewById(R.id.iv_add_profile_image);
        ivProfilePic = (ImageView) findViewById(R.id.iv_profile_pic);
        //  selectCity.setOnClickListener(this);
        submit.setOnClickListener(this);
        ivAddProfileImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tsubmit:
                if (validate()) {
                   if(AppUtils.getInstance().isNetworkAvailable(SignUpActivity.this)){
                       signUpApiHit();
                   }else {
                       AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_no_internet), llRootLayout);
                   }
                }

                break;
            case R.id.iv_add_profile_image:
                if (Build.VERSION.SDK_INT < 23) {
                    selectImage();
                } else {
                    if (checkAndRequestPermissions()) {
                        selectImage();
                    }
                }
                break;
        }
    }


    private boolean checkAndRequestPermissions() {
        int permissionCAMERA = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int storagePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_REQUEST_CODE);
            return false;
        }

        return true;
    }

    /**
     * dialog opens providing photo upload options with gallery or camera
     */
    private void selectImage() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_images);
        TextView cameraTV = (TextView) dialog.findViewById(R.id.tv_camera);
        TextView galleryTV = (TextView) dialog.findViewById(R.id.tv_gallery);
        ImageView cameraIV = (ImageView) dialog.findViewById(R.id.iv_camera);
        ImageView galleryIV = (ImageView) dialog.findViewById(R.id.iv_gallery);
        ImageView cancelIV = (ImageView) dialog.findViewById(R.id.iv_cancel);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//----width math parent for dialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));//----makes default dialog background transparent

        dialog.show();
        cameraTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraSelect();
                dialog.dismiss();
            }
        });

        galleryTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picImageFromGallery();
                dialog.dismiss();
            }
        });


        cameraIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraSelect();
                dialog.dismiss();
            }
        });

        galleryIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picImageFromGallery();
                dialog.dismiss();
            }
        });

        cancelIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * pic image from gallery
     */
    private void picImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.image_action)), PICK_FROM_FILE);
    }

    /**
     * Selecting camera for taking images
     */
    private void cameraSelect() {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


      //  mImageCaptureUri = Uri.fromFile(new File(getFilename()));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        try {
            intent.putExtra("return-data", true);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean validate() {
        if(etFullName.getText().toString().trim().length() == 0){
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_please_enter_name), llRootLayout);
            etFullName.requestFocus();
            return false;
        }else if(etFullName.getText().toString().trim().length() < 2){
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_name_limit), llRootLayout);
            etFullName.requestFocus();
            return false;
        }else if(etEmail.getText().toString().trim().length() == 0){
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_please_enter_email), llRootLayout);
            etEmail.requestFocus();
            return false;
        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_please_enter_valid_email), llRootLayout);
            etEmail.requestFocus();
            return  false;
        }else if (etMobile.getText().toString().trim().length() == 0) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_please_enter_number), llRootLayout);
            etMobile.requestFocus();
            return  false;
        }else if (etMobile.getText().toString().trim().length() == 0) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_please_enter_number), llRootLayout);
            etMobile.requestFocus();
            return  false;
        }else if (etMobile.getText().toString().trim().length() < 10 || etMobile.getText().toString().trim().length() > 12) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_number_limit), llRootLayout);
            etMobile.requestFocus();
            return  false;
        }else if (etPassword.getText().toString().trim().length() == 0){
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_password_can_not_be_empty), llRootLayout);
            etPassword.requestFocus();
            return false;
        } else if(etPassword.getText().toString().trim().length() < 6 || etPassword.getText().toString().trim().length() > 20) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_password_length), llRootLayout);
            etPassword.requestFocus();
            return false;
        }else if (e_conformpassword.getText().toString().trim().length() == 0){
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_confirm_password_can_not_be_empty), llRootLayout);
            e_conformpassword.requestFocus();
            return false;
        } else if(!e_conformpassword.getText().toString().trim().equals(etPassword.getText().toString().trim())) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_password_length), llRootLayout);
            etPassword.setText("");
            e_conformpassword.setText("");
            etPassword.requestFocus();
            return false;
        }else {
            return true;
        }
    }

    private void convert() {

        semail = etEmail.getText().toString();
        spassword = etPassword.getText().toString();


    }

    private void signUpApiHit() {
        pd.show();
        auth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            uploadImageToServer();

                        }
                    }
                });


    }


    private void addUserToDatabase(Context context, final FirebaseUser firebaseUser) {
        UserModel user = new UserModel(url, firebaseUser.getUid(), firebaseUser.getEmail(), etFullName.getText().toString(), etMobile.getText().toString());
        FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child(firebaseUser.getUid())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            AppUtils.getInstance().showSnackBar("SignUp Failed", llRootLayout);
                        } else {
                            Toast.makeText(SignUpActivity.this, "success", Toast.LENGTH_LONG).show();
                            AppSharedPreferences.putString(SignUpActivity.this, AppSharedPreferences.PREF_KEY.USER_IMAGE, url);
                            AppSharedPreferences.putString(SignUpActivity.this, AppSharedPreferences.PREF_KEY.USER_ID, firebaseUser.getUid());
                            AppSharedPreferences.putString(SignUpActivity.this, AppSharedPreferences.PREF_KEY.FULL_NAME, etFullName.getText().toString());
                            AppSharedPreferences.putString(SignUpActivity.this, AppSharedPreferences.PREF_KEY.MOBILE_NUMBER, etMobile.getText().toString());
                            AppSharedPreferences.putString(SignUpActivity.this, AppSharedPreferences.PREF_KEY.EMAIL_ID, firebaseUser.getEmail());
                            AppSharedPreferences.putBoolean(SignUpActivity.this, AppSharedPreferences.PREF_KEY.ISLOGIN, true);
                            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                            finish();
                        }
                    }
                });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_FROM_FILE:
                startCrop(data.getData());
                break;

            case PICK_FROM_CAMERA:
                startCrop(data.getData());

                break;
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    ivProfilePic.setImageURI(resultUri);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
                break;
        }
    }

    private void uploadImageToServer() {
        if (filePath != null) {
            Long tsLong = System.currentTimeMillis() / 1000;
            StorageReference childRef = storageRef.child("images");
            StorageReference imaepathRef = childRef.child(tsLong.toString() + filePath.getLastPathSegment());

            //uploading the image
            UploadTask uploadTask = imaepathRef.putFile(filePath);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(SignUpActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                    url = taskSnapshot.getDownloadUrl().toString();
                    firebaseUser = auth.getCurrentUser();
                    addUserToDatabase(SignUpActivity.this, firebaseUser);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(SignUpActivity.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void startCrop(Uri imageUri) {
        filePath = imageUri;
        CropImage.activity(imageUri).start(this);

    }


    /**
     * method for handling callbacks of requested permissions
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    selectImage();
                } else if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectImage();
                }
                break;
        }
    }
}
