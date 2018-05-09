package com.ncs.plataformes.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.ncs.plataformes.R;
import com.ncs.plataformes.models.Game;
import com.ncs.plataformes.models.GameList;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class GameSearchTask extends AsyncTask<String, Integer, GameList> {

    public interface WeakReference {
        Context getContext();

        ProgressBar getProgressBar();

        void finished(GameList gameList);
    }

    private WeakReference weakReference;

    public GameSearchTask(WeakReference weakReference) {
        super();
        this.weakReference = weakReference;
    }

    @Override
    protected void onPreExecute() {
        Log.d("ncs", "onPreExecute: ");
        ProgressBar progressBar = weakReference.getProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Function which works in the background and makes all the API things
     *
     * @param strings Not using
     * @return gameList GameList found
     */
    @Override
    protected GameList doInBackground(String... strings) {

        Log.d("ncs", "doInBackground: ");

        InputStream in = null;

        try {
//            BUILD URL
            String urlStr = weakReference.getContext().getString(R.string.searchGameList);
            Log.d("ncs", "URL: " + urlStr);

//            OPEN CONNECTION
            URL url = new URL(urlStr);
            URLConnection connection = url.openConnection();

            int length = connection.getContentLength();
            Log.d("ncs", "ContentLength: " + length);

//            DOWNLOAD JSON
            in = url.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int total = 0, nRead;
            while ((nRead = in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, nRead);
                total += nRead;
            }
            String json = new String(outputStream.toByteArray());
            Log.d("ncs", "JSON: " + json);

//            DECODE JSON
            Gson gson = new Gson();
            GameList gameList = gson.fromJson(json, GameList.class);
            Log.d("ncs", Arrays.toString(gameList.getGames()) + " GAMES");

            Game[] games = gameList.getGames();
            for (Game game :
                    games) {
                Log.d("ncs", game.getId() + " - " + game.getName());
            }

            return gameList;
        } catch (Exception e) {
            Log.d("ncs", e.getMessage());
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception ignored) {
            }
        }

        return null;
    }

    /**
     * Function called after the execute
     * This function makes the ProgressBar disappear
     *
     * @param gameList List of the games recieved
     */
    @Override
    protected void onPostExecute(GameList gameList) {
        Log.d("ncs", "onPostExecute: ");
        ProgressBar progressBar = weakReference.getProgressBar();
        progressBar.setVisibility(View.GONE);
        weakReference.finished(gameList);
    }
}