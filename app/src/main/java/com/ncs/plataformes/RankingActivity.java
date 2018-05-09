package com.ncs.plataformes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ncs.plataformes.adapters.GameListAdapter;
import com.ncs.plataformes.adapters.RankingUserListAdapter;
import com.ncs.plataformes.models.Game;
import com.ncs.plataformes.models.GameList;
import com.ncs.plataformes.models.RankingList;
import com.ncs.plataformes.models.User;
import com.ncs.plataformes.tasks.GameSearchTask;
import com.ncs.plataformes.tasks.RankingSearchTask;

public class RankingActivity extends AppCompatActivity
        implements GameSearchTask.WeakReference,
        RankingSearchTask.WeakReference,
        GameListAdapter.OnItemClickListener,
        RankingUserListAdapter.OnItemClickListener {

    //    VIEWS
    ActionBar actionBar;
    TextView titleTextView;
    ProgressBar progressBar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

            titleTextView = new TextView(actionBar.getThemedContext());
        }

        findViews();

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        Log.d("ncs", "Intent: " + search);

        String actionBarTitle;
        if (search == null) {
            actionBarTitle = getContext().getString(R.string.gameSearch);
            titleTextView.setText(actionBarTitle);
            GameSearchTask gameSearchTask = new GameSearchTask(this);
            gameSearchTask.execute();
        } else {
            switch (search) {
                case "rankingGame":
                    int gameId = intent.getIntExtra("gameId", 0);
                    String gameName = intent.getStringExtra("gameName");
                    RankingSearchTask rankingSearchTask = new RankingSearchTask(this, gameId);
                    rankingSearchTask.execute();
                    actionBarTitle = String.format(getContext().getString(R.string.rankingGame), gameName);
                    titleTextView.setText(actionBarTitle);
                    break;
            }
        }

        setTitleStyle();
        // Add titleTextView into ActionBar
        actionBar.setCustomView(titleTextView);
    }

    /**
     * Function to find the views from the layout
     */
    private void findViews() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
    }

    /**
     * Function to style the action bar title
     */
    private void setTitleStyle() {
        titleTextView.setTextSize(20.0f);
        titleTextView.setTextColor(getResources().getColor(R.color.colorText));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    //    region RANKING USER LIST
    @Override
    public void finished(RankingList rankingList) {
        RankingUserListAdapter userListAdapter = new RankingUserListAdapter(rankingList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userListAdapter);
        userListAdapter.setOnClickListener(this);
    }

    @Override
    public void itemClicked(View view, User user) {
        int userId = user.getId();
        String username = user.getUsername();
        Log.d("ncs", "itemClicked: ID: " + userId);
        String strUserId = String.valueOf(userId);
        Toast.makeText(view.getContext(), "USER ID: " + strUserId, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("userId", strUserId);
        intent.putExtra("username", user.getUsername());
        intent.putExtra("fullname", getString(R.string.fullName, user.getFirstname(), user.getLastname()));
        intent.putExtra("email", user.getEmail());
        intent.putExtra("imgPath", user.getAvatarPath());
        startActivity(intent);
    }
//    endregion

    //    region GAME LIST
    @Override
    public void finished(GameList gameList) {
        GameListAdapter gameListAdapter = new GameListAdapter(gameList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(gameListAdapter);
        gameListAdapter.setOnClickListener(this);
    }

    @Override
    public void itemClicked(View view, Game game) {
        int gameId = game.getId();
        String gameName = game.getName() + "'";
        if (!gameName.endsWith("s'")) {
            gameName += "s";
        }
//        String strGameId = String.valueOf(gameId);
        Toast.makeText(view.getContext(), "GAME ID: " + gameId, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra("search", "rankingGame");
        intent.putExtra("gameId", gameId);
        intent.putExtra("gameName", gameName);
        startActivity(intent);
    }
//    endregion
}
