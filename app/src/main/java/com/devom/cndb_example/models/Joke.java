package com.devom.cndb_example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Joke {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("joke")
    @Expose
    private String joke;
    @SerializedName("categories")
    @Expose
    private List<String> categories;

    public Joke(String id, String joke, List<String> categories) {
        this.id = id;
        this.joke = joke;
        this.categories = categories;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
