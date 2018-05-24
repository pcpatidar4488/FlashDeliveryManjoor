package com.example.manjooralam.flashdelivery.activities;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manjooralam.flashdelivery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {
     private TextView mobile,submit, tvtoolbarTitle;
    private EditText edit_email;
    private String semail;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        init();
        initialPageSetup();
        tvtoolbarTitle.setOnClickListener(this);
    }

    private void initialPageSetup() {
        mAuth = FirebaseAuth.getInstance();
        tvtoolbarTitle.setText("Forgot Password");
        textWatcher();

    }

    private void init() {

        submit= (TextView) findViewById(R.id.tnext);
        tvtoolbarTitle = (TextView) findViewById(R.id.tv_title);
        edit_email= (EditText) findViewById(R.id.forgot_mob_edit);
        mobile= (TextView) findViewById(R.id.tmob_forgot_number);
        submit.setOnClickListener(this);
    }

    private void textWatcher() {
        edit_email.addTextChangedListener(new TextWatcher() {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.tnext:
                if(!validate()){

                    return;

                }else {

                    mAuth.sendPasswordResetEmail(edit_email.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ForgotPasswordActivity.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText( ForgotPasswordActivity.this,"Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                break;
        }

    }

    private boolean validate() {
        boolean valid=true;
        if(edit_email.getText().toString().isEmpty()||!android.util.Patterns.EMAIL_ADDRESS.matcher(edit_email.getText().toString()).matches()){
            Toast.makeText(getApplicationContext(),"Please enter valid email address",Toast.LENGTH_SHORT).show();
            valid=false;
        }
        return valid;
    }
}
