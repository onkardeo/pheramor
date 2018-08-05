package com.test.pheramor.pheramor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

//This class implements the logic to store user password
public class page2 extends AppCompatActivity {

    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page2);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            password = findViewById(R.id.password);
            password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    //Function to take user to next page if valid password is entered

    public void OnClick(View view) {
        try{
            if(password.getText().toString().trim().isEmpty()){
                Toast.makeText(getApplicationContext(), "Enter a valid password", Toast.LENGTH_LONG).show();

            }else{
                UserDetails.password = password.getText().toString().trim();
                Intent intent = new Intent(this, page3.class);

                startActivity(intent);
            }


        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}


    }
}
