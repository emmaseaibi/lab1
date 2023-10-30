package com.example.lab4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnSetTeamInfos (View view) {

        EditText teamName = (EditText) findViewById(R.id.newTeamName);
        EditText teamAddress = (EditText) findViewById(R.id.newTeamLocalisation);

        if (teamName != null) {
            TextView currentTeamName = (TextView) findViewById(R.id.teamName);
            currentTeamName.setText(teamName.getText());

            teamAddress.setText(teamAddress.getText());

            // Resetting the field for team Name
            teamName.setText("");
        }

    }


    public void OnOpenInGoogleMaps (View view) {

        EditText teamAddress = (EditText) findViewById(R.id.newTeamLocalisation);

        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + teamAddress.getText());

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        mapIntent.setPackage("com.google.android.apps.maps");

        startActivity(mapIntent);

    }

    // GetContent creates an ActivityResultLauncher<String> to let you pass
    // in the mime type you want to let the user select
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_CANCELED) return;

                    // Getting the data since the result is OK
                    // Handling the Intent

                    Intent intent = result.getData();

                    // Getting the avatar image we show to our users
                    ImageView avatarImage = (ImageView) findViewById(R.id.currentAvatar);

                    // Figuring out the correct image
                    String drawableName = Integer.toString(R.drawable.ic_logo_00);
                    switch (intent.getIntExtra("ImageID", R.id.teamId00)) {
                        case R.id.teamId01:
                            drawableName = Integer.toString(R.drawable.ic_logo_01);
                            break;
                        case R.id.teamId02:
                            drawableName = Integer.toString(R.drawable.ic_logo_02);
                            break;
                        case R.id.teamId03:
                            drawableName = Integer.toString(R.drawable.ic_logo_03);
                            break;
                        case R.id.teamId04:
                            drawableName = Integer.toString(R.drawable.ic_logo_04);
                            break;
                        case R.id.teamId05:
                            drawableName = Integer.toString(R.drawable.ic_logo_05);
                            break;
                        default:
                            drawableName = Integer.toString(R.drawable.ic_logo_00);
                            break;
                    }

                    int resId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                    avatarImage.setImageResource(resId);

                }
            });



    public void OnSetAvatarButton(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        // Pass in the mime type you want to let the user select
        // as the input
        mStartForResult.launch(intent);

    }
}
