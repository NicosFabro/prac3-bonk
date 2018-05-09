package com.ncs.plataformes;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncs.plataformes.R;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {

    //    VIEWS
    ActionBar actionBar;
    TextView titleTextView;
    ImageView imgAvatar;
    TextView tvUserId, tvUsername, tvFullname, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

            titleTextView = new TextView(actionBar.getThemedContext());
        }

        findViews();

        setValues();

        setTitleStyle();
        actionBar.setCustomView(titleTextView);
    }

    /**
     * Function to find the views from the layout
     */
    private void findViews() {
        imgAvatar = findViewById(R.id.imgAvatar);
        tvUserId = findViewById(R.id.tvUserId);
        tvUsername = findViewById(R.id.tvUsername);
        tvFullname = findViewById(R.id.tvFullname);
        tvEmail = findViewById(R.id.tvEmail);
    }

    /**
     * Function to style the title of the actionbar
     */
    private void setTitleStyle() {
        titleTextView.setTextSize(20.0f);
        titleTextView.setTextColor(getResources().getColor(R.color.colorText));
    }

    /**
     * Function to set the TextViews and ImageView with the parameters recieved from the intent
     */
    private void setValues() {
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        Log.d("ncs", "USER ID: " + userId);
        String username = intent.getStringExtra("username");
        Log.d("ncs", "USERNAME: " + username);
        String fullname = intent.getStringExtra("fullname");
        Log.d("ncs", "USER FULLNAME: " + fullname);
        String email = intent.getStringExtra("email");
        Log.d("ncs", "USER EMAIL: " + email);
        String imgPath = intent.getStringExtra("imgPath");

        tvUserId.setText(userId);
        tvUsername.setText(username);
        tvFullname.setText(fullname);
        tvEmail.setText(email);
        Picasso.with(this).load(imgPath).into(imgAvatar);
        titleTextView.setText(username.toUpperCase());
    }
}