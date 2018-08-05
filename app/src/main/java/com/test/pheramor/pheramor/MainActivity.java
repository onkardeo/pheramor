package com.test.pheramor.pheramor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;
// This class handles the logic to get emaill address from the user
public class MainActivity extends AppCompatActivity {

    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            email = findViewById(R.id.email);
            email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    //Function to take user to next page if valid email address is entered

    public void OnClick(View view) {
        try{
            if(isValid(email.getText().toString().trim())){

                UserDetails.email = email.getText().toString().trim();
                Intent intent = new Intent(this, page2.class);

                startActivity(intent);
            }else{

                Toast.makeText(getApplicationContext(), "Enter a valid email address", Toast.LENGTH_LONG).show();
            }

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}




    }


    //Helper function to check if email is valid
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
