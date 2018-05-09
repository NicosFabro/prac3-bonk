package com.ncs.plataformes.models;

import com.google.gson.annotations.SerializedName;

public class Result {

    private int error;
    private String message;
    @SerializedName("data")
    private Game game;
    @SerializedName("data")
    private User user;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Result{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", game=" + game +
                '}';
    }
}
