package com.test.pheramor.pheramor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

//This class implements the logic to store user's name , zipcode and height
public class page3 extends AppCompatActivity {

    EditText FullName;
    EditText ZipCode;
    EditText Height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page3);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            FullName = findViewById(R.id.fullName);
            ZipCode = findViewById(R.id.zipCode);
            Height = findViewById(R.id.height);

            FullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        hideKeyboard(v);
                    }
                }
            });

            ZipCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        hideKeyboard(v);
                    }
                }
            });

            Height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        hideKeyboard(v);
                    }
                }
            });

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}


    }

    //Helper funtion to hide keyboard
    public void hideKeyboard(View view) {
        try{
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }

    //Function to take user to next page if valid credentials are entered
    public void OnClick(View view) {
        try{
            if(FullName.getText().toString().trim().isEmpty()){
                //checks if valid name is entered
                Toast.makeText(getApplicationContext(), "Enter a valid Name", Toast.LENGTH_LONG).show();

            }else{
                UserDetails.fullName = FullName.getText().toString().trim();
                if(isValidZipCode(ZipCode.getText().toString().trim())){

                    //checks if valid zip code is entered
                    UserDetails.zipcode = Integer.parseInt(ZipCode.getText().toString().trim());

                    if(isValidHeight(Height.getText().toString().trim())){

                        //goes to next page if all details are valid

                        UserDetails.height = Double.parseDouble(Height.getText().toString().trim());

                        Intent intent = new Intent(this, page4.class);

                        startActivity(intent);
                    }else{

                        Toast.makeText(getApplicationContext(), "Enter valid height in centi meters", Toast.LENGTH_LONG).show();
                    }



                }else{
                    Toast.makeText(getApplicationContext(), "Enter a valid Zip Code", Toast.LENGTH_LONG).show();
                }
            }

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}


    }



    //helper function to check if zipcode is valid
    public static boolean isValidZipCode(String zip){
    if(android.text.TextUtils.isDigitsOnly(zip)){
        if(zip.length() == 5){
            return  true;
        }
    }
    return  false;
    }

    //helper function to check if height is valid
    public static boolean isValidHeight(String height){
        String temp="";
        if(height.contains(".")){
             temp = height.substring(0,height.indexOf(".")) + height.substring(height.indexOf(".")+1);

        }

        if(android.text.TextUtils.isDigitsOnly(temp)){

            if(Double.parseDouble(height)>30 && Double.parseDouble(height)<600){
                return  true;
            }
        }
        return  false;
    }
}
