package com.test.pheramor.pheramor;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
// Model class to store the values of the user attributes
public class UserDetails {

    public static String email = "";
    public static String password = "";
    public static String fullName = "";
    public static int zipcode = 0;
    public static double height = 0;
    public static String gender = "";
    public static int dobDate = 0;
    public static int dobMonth = 0;
    public static int dobYear = 0;
    public static String genderInterested = "";
    public static int minAgeInterested = 0;
    public static int maxAgeInterested = 0;
    public static String race = "";
    public static String religion = "";
    public static Bitmap profileImage;
    public static String[] genderList = new String[]{
            "Agender",
            "Androgyne",
            "Androgynous",
            "Bigender",
            "Female",
            "Male",
            "Gender Fluid",
            "Gender Nonconforming",
            "Gender Questioning",
            "Gender Variant",
            "Genderqueer",
            "Intersex",
            "Female to Male",
            "Male to Female",
            "Neither",
            "Neutrois",
            "Non-binary",
            "Other",
            "Pangender",
            "Transgender",
            "Transgender Female",
            "Transgender Male",
            "Transgender Person",
            "Two-Spirit"};

    public static String[] dayList = new String[]{ "01","02",
    "03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
    "21","22","23","24","25","26","27","28","29","30","31"
    };

    public static String[] monthList = new String[]{ "01","02",
            "03","04","05","06","07","08","09","10","11","12"
    };

    public static String[] yearList = new String[120];
    public static String[] ageList = new String[84];

    public static String[] raceList = new String[]{
            "Asian",
            "Black",
            "Hispanic/Latin",
            "Indian",
            "Middle Eastern",
            "Native American",
            "Pacific Islander",
            "White",
            "Other"
            };

    public static String[] religionList = new String[]{
            "Agnosticism",
            "Atheism",
            "Christianity",
            "Judaism",
            "Catholicism",
            "Islam",
            "Hinduism",
            "Buddhism",
            "Sikh",
            "Other"
    };



    //logic to check permissions on runtime
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context)
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

}
