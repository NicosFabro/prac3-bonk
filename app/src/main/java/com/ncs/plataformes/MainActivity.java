package com.ncs.plataformes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    //    VIEWS
    ViewGroup layoutButtons;
    ImageView logo;
    Button btnPlay, btnRanking, btnSettings, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setClickListeners();

    }

    /**
     * Finds the view for each component of the layout
     */
    private void findViews() {
        layoutButtons = findViewById(R.id.layoutButtons);
        logo = findViewById(R.id.logo);
        btnPlay = findViewById(R.id.btnPlay);
        btnRanking = findViewById(R.id.btnRanking);
        btnSettings = findViewById(R.id.btnSettings);
        btnAbout = findViewById(R.id.btnAbout);
    }

    /**
     * Starts the new activity for each button click
     *
     * @param v is the View which was clicked
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                startActivity(new Intent(MainActivity.this, GameActivity.class));
                break;
            case R.id.btnRanking:
                startActivity(new Intent(MainActivity.this, RankingActivity.class));
                break;
            case R.id.btnSettings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.btnAbout:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * Set click listeners for all the buttons of the constraint with id "layoutButtons"
     */
    private void setClickListeners() {
        View v;
        for (int i = 0; i < layoutButtons.getChildCount(); i++) {
            v = layoutButtons.getChildAt(i);
            if (v instanceof Button) v.setOnClickListener(this);
        }
    }
}
