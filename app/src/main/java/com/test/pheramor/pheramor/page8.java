package com.test.pheramor.pheramor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;


//this class displays the user details summary
public class page8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8);
        overridePendingTransition(R.anim.slidein, R.anim.slideout);

        try{

                CircleImageView profilePic = findViewById(R.id.profile_image);
                profilePic.setImageBitmap(UserDetails.profileImage);



            TextView name = findViewById(R.id.name);
            name.setText(UserDetails.fullName);

            TextView DOB = findViewById(R.id.birthDate);
            DOB.setText(UserDetails.dobMonth+"/"+UserDetails.dobDate+"/"+UserDetails.dobYear);

            TextView gender = findViewById(R.id.genderUser);
            gender.setText(UserDetails.gender);

            TextView height = findViewById(R.id.heightUser);
            height.setText(UserDetails.height+" cm" );

            TextView race = findViewById(R.id.raceUser);
            race.setText(UserDetails.race);

            TextView religion = findViewById(R.id.ReligionUser);
            religion.setText(UserDetails.religion);

            TextView email = findViewById(R.id.emailUser);
            email.setText(UserDetails.email);



            TextView genderInterested = findViewById(R.id.interestedIn);
            genderInterested.setText(UserDetails.genderInterested);

            TextView agerange = findViewById(R.id.ageRange);
            agerange.setText(UserDetails.minAgeInterested +" - "+UserDetails.maxAgeInterested);

            TextView zip = findViewById(R.id.zipcodeUser);
            zip.setText(UserDetails.zipcode+"");

            SendData();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();

        }



    }

    //helper function to send data to server
    public void SendData(){

        try {
            final String REQ_TAG = "VACTIVITY";
            RequestQueue requestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext())
                    .getRequestQueue();
            //create json object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", UserDetails.email);
            jsonObject.put("password", UserDetails.password);
            jsonObject.put("dobDate", UserDetails.dobDate);
            jsonObject.put("dobMonth", UserDetails.dobMonth);
            jsonObject.put("dobYear", UserDetails.dobYear);
            jsonObject.put("gender", UserDetails.gender);
            jsonObject.put("race", UserDetails.race);
            jsonObject.put("religion", UserDetails.religion);
            jsonObject.put("height", UserDetails.height);
            jsonObject.put("genderInterested", UserDetails.genderInterested);
            jsonObject.put("minAgeInterested", UserDetails.minAgeInterested);
            jsonObject.put("maxAgeInterested", UserDetails.maxAgeInterested);
            jsonObject.put("profilePic", UserDetails.profileImage);
            jsonObject.put("zipcode", UserDetails.zipcode);



            //post json object to web api
            String url = "https://external.dev.pheramor.com/";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //display response from web api
                            Toast.makeText(getApplicationContext(), "String Response : "+ response.toString(), Toast.LENGTH_LONG).show();


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //display response from web api
                    Toast.makeText(getApplicationContext(), "Error getting response", Toast.LENGTH_LONG).show();
                }
            });

            jsonObjectRequest.setTag(REQ_TAG);
            requestQueue.add(jsonObjectRequest);







        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}
