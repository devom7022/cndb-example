package com.devom.cndb_example.ui.greet;

import com.devom.cndb_example.api.ApiClient;
import com.devom.cndb_example.app.BaseApplication;
import com.devom.cndb_example.models.Joke;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreetInteractor {
    private ApiClient apiClient;
    private Joke dataResponse;

    interface OnFinishedListener {
        void onFailure(String error);

        void onSuccess(Joke joke);
    }

    public GreetInteractor(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    void getTextRandom(OnFinishedListener listener) {
        if (!BaseApplication.getConnectionToNetwork()) {
            listener.onFailure("Revise su conexión a internet");
        }
        Call<Joke> call = apiClient.getDataJokeRandom();
        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                if (response.isSuccessful()) {
                    dataResponse = response.body();
                    if (dataResponse != null) {
                        listener.onSuccess(dataResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                listener.onFailure("Intente más tarde");
            }
        });

    }

}
