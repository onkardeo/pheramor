package com.test.pheramor.pheramor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class page7 extends AppCompatActivity {

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page7);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            profileImage = findViewById(R.id.pImage);
            btnSelect = (Button) findViewById(R.id.btnSelectPhoto);
            btnSelect.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    selectImage();
                }
            });

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }

    //Function to take appropriate action when next button is clicked

    public void OnClick(View view) {
        try{
            if(UserDetails.profileImage==null){
                //check if image is selected
                Toast.makeText(getApplicationContext(), "Select a valid image", Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(this, page8.class);

                startActivity(intent);
            }

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}




    }

    //display options to select image location
    private void selectImage() {
        try{
            final CharSequence[] items = { "Take Photo", "Choose from Library",
                    "Cancel" };

            AlertDialog.Builder builder = new AlertDialog.Builder(page7.this);
            builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    boolean result=UserDetails.checkPermission(page7.this);

                    if (items[item].equals("Take Photo")) {

                        if(result)
                            cameraIntent();

                    } else if (items[item].equals("Choose from Library")) {

                        if(result)
                            galleryIntent();

                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }

    //helper function to select image from gallery
    private void galleryIntent()
    {   try{
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

    }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }

    //helper function to select image using camera
    private void cameraIntent()

    {
        try{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == SELECT_FILE)
                    onSelectFromGalleryResult(data);
                else if (requestCode == REQUEST_CAMERA)
                    onCaptureImageResult(data);
            }

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }


    //helper function to procees image obtained using camera
    private void onCaptureImageResult(Intent data) {
        try{
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);


            UserDetails.profileImage = thumbnail;
            profileImage.setImageBitmap(thumbnail);

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}

    }


    //helper function to process image obtained using gallery
    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        try{
            Bitmap bm=null;
            if (data != null) {
                try {
                    bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            UserDetails.profileImage = bm;
            profileImage.setImageBitmap(bm);

        }catch(Exception e){Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();}


    }
}
