package com.devom.cndb_example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Joke {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("joke")
    @Expose
    private String joke;

    public Joke(String id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id='" + id + '\'' +
                ", joke='" + joke + '\'' +
                '}';
    }
}
