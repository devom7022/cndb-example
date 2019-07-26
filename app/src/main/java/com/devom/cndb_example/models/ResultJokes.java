package com.devom.cndb_example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultJokes {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private Joke value;

    public ResultJokes(String type, Joke value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Joke getValue() {
        return value;
    }

    public void setValue(Joke value) {
        this.value = value;
    }
}
