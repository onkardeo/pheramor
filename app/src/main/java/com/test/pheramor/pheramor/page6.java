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

//This class implements the logic to store users race and religion
public class page6 extends AppCompatActivity {

    MaterialBetterSpinner race;
    MaterialBetterSpinner religion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page6);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);

            //set race and religion dropdown list
            race = findViewById(R.id.race);
            race.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserDetails.raceList));

            religion = findViewById(R.id.religion);
            religion.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserDetails.religionList));

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }



    //Function to take appropriate action when next button is clicked

    public void OnClick(View view) {
        try{
            if(race.getText().toString().trim().isEmpty()){
                //check if race is selected
                Toast.makeText(getApplicationContext(), "Select a valid race", Toast.LENGTH_LONG).show();
            }else{
                UserDetails.race = race.getText().toString().trim();

                if(religion.getText().toString().trim().isEmpty()){
                    //check if religion is selected
                    Toast.makeText(getApplicationContext(), "Select a valid religion", Toast.LENGTH_LONG).show();

                }else{

                    //go to next page
                    UserDetails.religion = religion.getText().toString().trim();

                    Intent intent = new Intent(this, page7.class);

                    startActivity(intent);
                }
            }

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}





    }
}
