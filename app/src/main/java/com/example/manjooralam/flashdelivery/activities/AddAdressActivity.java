package com.example.manjooralam.flashdelivery.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;

public class AddAdressActivity extends BaseActivity implements View.OnClickListener{
    private TextView name,last_name,adress1,adress2,pincode,submit,selectCity,textname;
   private EditText e_name,e_lastname,e_adress1,e_adress2,e_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);
        init();
        textWatcher();
        changeText();
        selectCity.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    private void changeText() {
        textname.setText("Add Address");
    }

    private void textWatcher() {
        e_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    name.setVisibility(View.INVISIBLE);
                }else {
                    name.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e_lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    last_name.setVisibility(View.INVISIBLE);
                }else {
                    last_name.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e_adress1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    adress1.setVisibility(View.INVISIBLE);
                }else {
                    adress1.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e_adress2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    adress2.setVisibility(View.INVISIBLE);
                }else {
                    adress2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e_pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==0){
                    pincode.setVisibility(View.INVISIBLE);
                }else {
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
        selectCity= (TextView) findViewById(R.id.tselectcity);
        textname= (TextView) findViewById(R.id.tv_title);

        e_name = (EditText) findViewById(R.id.et_name);
        e_lastname = (EditText) findViewById(R.id.lastname_edit);
        e_adress1 = (EditText) findViewById(R.id.adress1_edit);
        e_adress2 = (EditText) findViewById(R.id.adress2_edit);
        e_pin = (EditText) findViewById(R.id.pin_edit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tsubmit:
                break;
            case R.id.tselectcity:
                openDialog();
                break;
        }
    }

    private void openDialog() {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.city_dialog);
        final TextView tvPatna = dialog.findViewById(R.id.tv_patna);
        final TextView tvMuzaffarpur = dialog.findViewById(R.id.tv_muzaffarpur);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.show();

        tvPatna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCity.setText(tvPatna.getText());
                dialog.dismiss();
            }
        });

        tvMuzaffarpur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCity.setText(tvMuzaffarpur.getText());
                dialog.dismiss();
            }
        });
    }
}
