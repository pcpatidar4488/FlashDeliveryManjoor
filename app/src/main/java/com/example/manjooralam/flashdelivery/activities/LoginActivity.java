package com.example.manjooralam.flashdelivery.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.UserModel;
import com.example.manjooralam.flashdelivery.utilities.AppConstants;
import com.example.manjooralam.flashdelivery.utilities.AppSharedPreferences;
import com.example.manjooralam.flashdelivery.utilities.AppUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import io.fabric.sdk.android.Fabric;
@ReportsCrashes(mailTo = "manjooralam28@gmail.com", mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text)
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvEmail,tvPassword,tvlogin,tvForgotPassword, tvToolbarTitle;
    private EditText etEmailPass,etPassword;
    private TextView tvSignup;
    private RelativeLayout rlRootlayout;
    private ImageView ivBack;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fabric.with(this, new Crashlytics());

        init();
        initialPageSetup();
        logUser();
    }
    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("manjooralam28@gmail.com");
        Crashlytics.setUserName("Test User");
    }

    private void initialPageSetup() {

        textWatcher();
        ivBack.setVisibility(View.INVISIBLE);
        tvToolbarTitle.setText(getResources().getString(R.string.s_login));
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        SpannableString styledString = new SpannableString(getResources().getString(R.string.s_do_not_have_account_signup));
        styledString.setSpan(new StyleSpan(Typeface.BOLD), 23, 29, 0);
        ClickableSpan clickableSpan = new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }

            public void updateDrawState(TextPaint ds) {// override updateDrawState
                ds.setUnderlineText(false); // set to false to remove underline
            }
        };
        styledString.setSpan(clickableSpan, 23, 29, 0);
        styledString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 23, 29, 0);
        tvSignup.setMovementMethod(LinkMovementMethod.getInstance());
        tvSignup.setText(styledString);
    }

    private void textWatcher() {
        etEmailPass.addTextChangedListener(new TextWatcher() {
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
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    tvPassword.setVisibility(View.INVISIBLE);
                }else {
                    tvPassword.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void init() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_title);
        etEmailPass= (EditText) findViewById(R.id.et_email_or_mobile);
        etPassword= (EditText) findViewById(R.id.et_password);
        tvForgotPassword= (TextView) findViewById(R.id.tv_forgot_password);
        tvlogin= (TextView) findViewById(R.id.tv_login);
        tvSignup = (TextView) findViewById(R.id.tv_signup);
        tvEmail = (TextView) findViewById(R.id.lebel_email);
        tvPassword = (TextView) findViewById(R.id.tv_password);
        rlRootlayout = (RelativeLayout) findViewById(R.id.rl_rootlayout);
        tvForgotPassword.setOnClickListener(this);
        tvlogin.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login:
                if(validate()) {
                    if(AppUtils.getInstance().isNetworkAvailable(this)){
                        loginApiCall();
                    }else {
                      AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_no_internet), rlRootlayout);
                    }
                }
                break;
            case R.id.tv_forgot_password:

                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
        }

    }

    /**
     * method for login credential validation
     * @return
     */
    private boolean validate() {
        if (etEmailPass.getText().toString().trim().length() == 0) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_email_can_not_empty),rlRootlayout );
            etEmailPass.requestFocus();
                     return false;
        }else if (etPassword.getText().toString().trim().length() == 0) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_password_can_not_empty),rlRootlayout );
            etPassword.requestFocus();
            return false;
        }else if (etPassword.getText().toString().trim().length() < 6 || etPassword.getText().toString().trim().length() > 15) {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_password_length),rlRootlayout );
            etPassword.requestFocus();
            return false;
        }else return true;
    }

    /**
     * method for login api call using firebase
     */
    private void loginApiCall() {

        AppUtils.getInstance().showProgessDialog(LoginActivity.this);
        auth.signInWithEmailAndPassword(etEmailPass.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        AppUtils.getInstance().hideProgessDialog();
                        if (!task.isSuccessful()) {
                            AppUtils.getInstance().showSnackBar("LOGIN FAIL",rlRootlayout);
                        } else {
                            firebaseUser = auth.getCurrentUser();
                            fetchUserDataApiCall(firebaseUser.getUid());
                        }
                    }
                });
    }


    private void fetchUserDataApiCall(String userId) {
        firebaseDatabase.getReference().child(AppConstants.USERS).child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                         AppSharedPreferences.putString(LoginActivity.this, AppSharedPreferences.PREF_KEY.USER_ID, dataSnapshot.child("imageUrl").getValue().toString());

                    AppSharedPreferences.putString(LoginActivity.this, AppSharedPreferences.PREF_KEY.USER_ID, dataSnapshot.child("uid").getValue().toString());
                    AppSharedPreferences.putString(LoginActivity.this, AppSharedPreferences.PREF_KEY.FULL_NAME, dataSnapshot.child("fullname").getValue().toString());
                    AppSharedPreferences.putString(LoginActivity.this, AppSharedPreferences.PREF_KEY.MOBILE_NUMBER, dataSnapshot.child("mobile").getValue().toString());
                    AppSharedPreferences.putString(LoginActivity.this, AppSharedPreferences.PREF_KEY.EMAIL_ID, dataSnapshot.child("email").getValue().toString());
                    AppSharedPreferences.putBoolean(LoginActivity.this, AppSharedPreferences.PREF_KEY.ISLOGIN, true);
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               AppUtils.getInstance().showSnackBar(getString(R.string.s_something_went_wrong), rlRootlayout);
            }
        });

    }
}
