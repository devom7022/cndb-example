package com.devom.cndb_example.ui.greet;

import android.app.Application;
import android.util.Log;

import com.devom.cndb_example.api.ApiClient;
import com.devom.cndb_example.app.BaseApplication;
import com.devom.cndb_example.models.Joke;
import com.devom.cndb_example.models.ResultJokes;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreetInteractor {
    @Inject
    ApiClient apiClient;


    interface OnFinishedListener {
        void onFailure(String error);

        void onSuccess(Joke joke);
    }

    public GreetInteractor(Application app) {
        ((BaseApplication) app).getApplicationComponent().inject(this);
    }

    void getTextRandom(OnFinishedListener listener) {
        if (!BaseApplication.getConnectionToNetwork()) {
            listener.onFailure("Revise su conexión a internet");
        }

        Call<ResultJokes> call = apiClient.getDataJokeRandom();
        call.enqueue(new Callback<ResultJokes>() {
            @Override
            public void onResponse(Call<ResultJokes> call, Response<ResultJokes> response) {
                if (response.isSuccessful()) {
                    Joke joke = response.body().getValue();

                    if (joke != null) {
                        listener.onSuccess(joke);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultJokes> call, Throwable t) {
                listener.onFailure("Intente más tarde");
            }
        });

/*
        Call<Joke> call = apiClient.getDataJokeRandom();

        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                if (response.isSuccessful()) {
                    String k = response.message();
                    Joke joke = response.body();
                    String j = joke.getJoke();

                    Log.i("str",j + " : "+k);

                    if (joke != null) {
                        listener.onSuccess(joke);
                    }
                }
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                listener.onFailure("Intente más tarde");
            }
        });*/
    }

}
