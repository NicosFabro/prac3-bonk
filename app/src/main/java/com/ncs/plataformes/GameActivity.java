package com.ncs.plataformes;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // FULL SCREEN GAME, NO ACTION BAR (theme also must be some "no-actionbar")
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Volume controls to control the music and effects volume for the game
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // GameView as the content view for the application
        GameView gameView = new GameView(this);
        setContentView(gameView);

        // Create the engine
        gameEngine = new GameEngine(this, gameView);
        gameEngine.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        gameEngine.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        gameEngine.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gameEngine.onDestroy();
    }
}