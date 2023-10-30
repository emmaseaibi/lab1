package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void setTeamIcon(View view) {
        // Creating a return intent to pass to the main activity
        Intent returnIntent = new Intent();

        // Figuring out which image was clicked
        ImageView selectedImage = (ImageView) view;

        // Adding stuff to the return Intent
        returnIntent.putExtra("ImageID", selectedImage.getId());
        setResult(RESULT_OK, returnIntent);

        // Finishing activity and return to main screen!
        finish();
    }


}