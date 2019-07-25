package com.devom.cndb_example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultJokes {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private List<Joke> value;
}
