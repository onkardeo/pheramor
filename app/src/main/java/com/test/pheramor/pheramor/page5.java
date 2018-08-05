package com.test.pheramor.pheramor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

//This class implements the logic to store users gender and age preferences
public class page5 extends AppCompatActivity {
    MaterialBetterSpinner gender;
    MaterialBetterSpinner minAge;
    MaterialBetterSpinner maxAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page5);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);

            //set gender dropdown list
            gender = findViewById(R.id.gender);
            gender.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserDetails.genderList));

            //Set min and max age spinners
            int age = 18;
            for(int i=0;i <84;i++){
                UserDetails.ageList[i] = Integer.toString(age);
                age++;
            }


            minAge = findViewById(R.id.minAge);
            minAge.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserDetails.ageList));


            maxAge = findViewById(R.id.maxAge);
            maxAge.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserDetails.ageList));

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }




    //Function to take appropriate action when next button is clicked

    public void OnClick(View view) {
        try{
            if(gender.getText().toString().trim().isEmpty()){
                //check if gender is selected
                Toast.makeText(getApplicationContext(), "Select a valid gender", Toast.LENGTH_LONG).show();
            }else{
                UserDetails.genderInterested = gender.getText().toString().trim();

                if(minAge.getText().toString().trim().isEmpty()){
                    //check if min age is selected
                    Toast.makeText(getApplicationContext(), "Select a valid minimum age", Toast.LENGTH_LONG).show();
                }else{
                    UserDetails.minAgeInterested = Integer.parseInt(minAge.getText().toString().trim());

                    if(maxAge.getText().toString().trim().isEmpty()){
                        //check if max age is selected
                        Toast.makeText(getApplicationContext(), "Select a valid maximum age", Toast.LENGTH_LONG).show();
                    }else{


                        UserDetails.maxAgeInterested = Integer.parseInt(maxAge.getText().toString().trim());
                        if(UserDetails.maxAgeInterested<UserDetails.minAgeInterested){
                            //check if age range is valid
                            Toast.makeText(getApplicationContext(), "Select a valid age range", Toast.LENGTH_LONG).show();
                        }else{
                            //go to next page
                            Intent intent = new Intent(this, page6.class);

                            startActivity(intent);

                        }



                    }

                }
            }


        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}



    }
}
