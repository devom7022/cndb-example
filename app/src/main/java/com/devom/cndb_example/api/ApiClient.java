package com.devom.cndb_example.api;

import com.devom.cndb_example.models.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.devom.cndb_example.api.ApiConstants.DATA_JOKE_RANDOM;

public interface ApiClient {
    @GET(DATA_JOKE_RANDOM)
    Call<Joke> getDataJokeRandom();
}