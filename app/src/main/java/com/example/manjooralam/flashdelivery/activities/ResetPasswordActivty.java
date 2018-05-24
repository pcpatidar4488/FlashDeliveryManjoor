package com.example.manjooralam.flashdelivery.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manjooralam.flashdelivery.R;

public class ResetPasswordActivty extends BaseActivity implements View.OnClickListener {
    private TextView mail,password,login,toolbar;
    private EditText mail_e,password_e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_activty);
        init();
        textWatcher();
        changeText();

        login.setOnClickListener(this);
    }

    private void textWatcher() {
        mail_e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    mail.setVisibility(View.INVISIBLE);
                }else {
                    mail.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password_e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    password.setVisibility(View.INVISIBLE);
                }else {
                    password.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void init() {
        mail= (TextView) findViewById(R.id.t_reset_email);
        password= (TextView) findViewById(R.id.t_reset_password);
        login= (TextView) findViewById(R.id.t_reset_login);

        toolbar= (TextView) findViewById(R.id.tv_title);

        mail_e= (EditText) findViewById(R.id.mail_reset_edit);
        password_e= (EditText) findViewById(R.id.password_reset_edit);
    }
    private void changeText() {
        toolbar.setText("Reset your Password");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.t_reset_login:





                break;

        }

    }


}
