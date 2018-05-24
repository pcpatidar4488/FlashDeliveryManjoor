package com.example.manjooralam.flashdelivery.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Contact_us extends BaseActivity implements View.OnClickListener {
    private TextView tvName,tvEmail,mobile,select_reason,submit, tvTitleToolbar;
    private EditText etName, etEmail, etNumber,e_comment;
    private ImageView ivBack;
    private View viewSeparate;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private LinearLayout llRootLayout;

    String semail,smobile_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initViews();

        initialPageSetup();
    }

    private void initialPageSetup() {
     // firebase_auth object
        auth=FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        if(getIntent().getStringExtra("EXTRA_FROM").equals("EditProfile")){
            select_reason.setVisibility(View.GONE);
            e_comment.setVisibility(View.GONE);
            viewSeparate.setVisibility(View.GONE);
            tvTitleToolbar.setText(getResources().getText(R.string.s_edit_profile));
            etName.setText(AppSharedPreferences.getString(this, AppSharedPreferences.PREF_KEY.FULL_NAME));
            etEmail.setText(AppSharedPreferences.getString(this, AppSharedPreferences.PREF_KEY.EMAIL_ID));
            etNumber.setText(AppSharedPreferences.getString(this, AppSharedPreferences.PREF_KEY.MOBILE_NUMBER));
            etName.setSelection(etName.getText().toString().length());
            tvName.setVisibility(View.VISIBLE);
            tvEmail.setVisibility(View.VISIBLE);
            mobile.setVisibility(View.VISIBLE);
        }else {
            tvTitleToolbar.setText(getResources().getText(R.string.s_contact_us));
        }
       
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()==0){
                    tvName.setVisibility(View.INVISIBLE);

                }else {
                    tvName.setVisibility(View.VISIBLE);
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
                if (charSequence.toString().length()==0){
                    tvEmail.setVisibility(View.INVISIBLE);
                }else {
                    tvEmail.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    mobile.setVisibility(View.INVISIBLE);
                }else {
                    mobile.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initViews() {

        tvTitleToolbar = (TextView) findViewById(R.id.tv_title);
        tvName= (TextView) findViewById(R.id.tname);
        tvEmail= (TextView) findViewById(R.id.temail);
        mobile= (TextView) findViewById(R.id.tmobile);
        select_reason= (TextView) findViewById(R.id.tselect);
        submit= (TextView) findViewById(R.id.tsubmit);
        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etNumber = (EditText) findViewById(R.id.et_mobile);
        e_comment= (EditText) findViewById(R.id.comment_edit);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        viewSeparate=findViewById(R.id.view_separate);
        llRootLayout= (LinearLayout) findViewById(R.id.ll_rootlayout);

        //------ registering listeners---------

        ivBack.setOnClickListener(this);
        select_reason.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_back:
                finish();
                break;

            case R.id.tselect:
                openDialogSelectReason();
                break;

            case R.id.tsubmit:
                if(!validate()){
                    return;
                }else {
                    signUpApiHit();
                }

                break;
        }
    }

    private void signUpApiHit() {
        addUserToDatabase(Contact_us.this, firebaseUser);
    }

    private void addUserToDatabase(Contact_us contact_us, final FirebaseUser firebaseUser) {

        UserModel user = new UserModel("",firebaseUser.getUid(), firebaseUser.getEmail(), etName.getText().toString(), etNumber.getText().toString());
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
                            Toast.makeText(Contact_us.this,"success", Toast.LENGTH_LONG).show();
                            AppSharedPreferences.putString(Contact_us.this, AppSharedPreferences.PREF_KEY.USER_ID, firebaseUser.getUid());
                            AppSharedPreferences.putString(Contact_us.this, AppSharedPreferences.PREF_KEY.FULL_NAME, etName.getText().toString());
                            AppSharedPreferences.putString(Contact_us.this, AppSharedPreferences.PREF_KEY.MOBILE_NUMBER, etNumber.getText().toString());
                            AppSharedPreferences.putString(Contact_us.this, AppSharedPreferences.PREF_KEY.EMAIL_ID, firebaseUser.getEmail());
                            AppSharedPreferences.putBoolean(Contact_us.this, AppSharedPreferences.PREF_KEY.ISLOGIN, true);
                            startActivity(new Intent(Contact_us.this, HomeActivity.class));
                            finish();
                        }
                    }
                });
    }

    private boolean validate() {

        boolean valid=true;
        convert();
        if(semail.isEmpty()||!android.util.Patterns.EMAIL_ADDRESS.matcher(semail).matches()){
            Toast.makeText(getApplicationContext(),"Please enter valid email address",Toast.LENGTH_SHORT).show();
            valid=false;
        }
        if(smobile_no.isEmpty()||smobile_no.length()>12||smobile_no.length()<10){
            Toast.makeText(getApplicationContext(),"please enter valid mobile no",Toast.LENGTH_SHORT).show();
            valid=false;
        }


        return valid;
    }

    private void convert() {
        semail= etEmail.getText().toString();
        smobile_no= etNumber.getText().toString();
    }

    /**
     * method for opening dialog for selecting reason
     */
    private void openDialogSelectReason() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_select_reason);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.show();
    }
}
