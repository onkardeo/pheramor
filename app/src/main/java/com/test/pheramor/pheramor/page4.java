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

import java.util.Arrays;
//This class implements the logic for storing users gender and date of birth
public class page4 extends AppCompatActivity {

    MaterialBetterSpinner gender;
    MaterialBetterSpinner months;
    MaterialBetterSpinner days;
    MaterialBetterSpinner years;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page4);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);

            //set gender dropdown list
            gender = findViewById(R.id.gender);
            gender.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserDetails.genderList));

            //set months dropdown list
            months = findViewById(R.id.month);
            months.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserDetails.monthList));

            //set days drop down list
            days = findViewById(R.id.day);
            days.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                    UserDetails.dayList));


            //set year list
            int year = 1900;

            for(int i=0;i <120;i++){
                UserDetails.yearList[i] = Integer.toString(year);
                year++;
            }
            years = findViewById(R.id.year);
            years.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserDetails.yearList));


        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }

    //Helper funtion to hide keyboard
    public void hideKeyboard(View view) {
        try{
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }


    //Function to take appropriate action when next button is clicked

    public void OnClick(View view) {
        try{
            if(gender.getText().toString().trim().isEmpty()){
                //check if gender is selected
                Toast.makeText(getApplicationContext(), "Select a valid gender", Toast.LENGTH_LONG).show();
            }else{
                UserDetails.gender = gender.getText().toString().trim();

                if(months.getText().toString().trim().isEmpty()){
                    //check if month is selected

                    Toast.makeText(getApplicationContext(), "Select a valid month", Toast.LENGTH_LONG).show();

                }else{
                    UserDetails.dobMonth = Integer.parseInt(months.getText().toString().trim());

                    if(days.getText().toString().trim().isEmpty()){

                        //check if day is selected

                        Toast.makeText(getApplicationContext(), "Select a valid day", Toast.LENGTH_LONG).show();
                    }else{
                        UserDetails.dobDate = Integer.parseInt(days.getText().toString().trim());

                        if(years.getText().toString().trim().isEmpty()){
                            //check if year is selected

                            Toast.makeText(getApplicationContext(), "Select a valid year", Toast.LENGTH_LONG).show();
                        }else{
                            UserDetails.dobYear= Integer.parseInt(years.getText().toString().trim());

                            if(isValidDate(UserDetails.dobDate,UserDetails.dobMonth,UserDetails.dobYear)){


                                Intent intent = new Intent(this, page5.class);

                                startActivity(intent);
                            }else{

                                Toast.makeText(getApplicationContext(), "Select a valid date", Toast.LENGTH_LONG).show();
                            }



                        }

                    }
                }

            }

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

        }


        // helper function to check if date is valid or not
        static boolean isValidDate(int d,int m, int y)
        {
            //check if date is within acceptable range
            if (y > 2018 ||
                    y < 1900)
                return false;
            if (m < 1 || m > 12)
                return false;
            if (d < 1 || d > 31)
                return false;

            //handle leap years
            if (m == 2)
            {
                if (isLeap(y))
                    return (d <= 29);
                else
                    return (d <= 28);
            }
            //handle months with 30 days

            if (m == 4 || m == 6 ||
                    m == 9 || m == 11)
                return (d <= 30);

            return true;
        }

        //helper function to detect leap year
        static boolean isLeap(int year)
        {

            return (((year % 4 == 0) &&
                    (year % 100 != 0)) ||
                    (year % 400 == 0));
        }
    }

