package com.devom.cndb_example.ui.user;

import com.devom.cndb_example.api.ApiClient;
import com.devom.cndb_example.app.BaseApplication;
import com.devom.cndb_example.models.User;

import java.util.List;

public class UserInteractor {
    private ApiClient apiClient;
    private List<User> dataResponse;

    interface OnFinishedListener {
        void onFailure(String error);

        void onSuccess(List<User> responseCity);
    }

    public UserInteractor(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    void addUser(User user, OnFinishedListener listener) {

    }

    void getUsers(final OnFinishedListener listener) {
        if (!BaseApplication.getConnectionToNetwork()) {
            listener.onFailure("Revise su conexión a internet");
        }
        /*
        Call<ResponseCity> call = apiClient.getUserList();
        call.enqueue(new Callback<ResponseCity>() {
            @Override
            public void onResponse(Call<ResponseCity> call, Response<ResponseCity> response) {
                if (response.isSuccessful()) {
                    dataResponse = response.body();
                    if (dataResponse != null) {
                        listener.onSuccess(dataResponse.getBrastlewark());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCity> call, Throwable t) {
                listener.onFailure("Intente más tarde");
            }
        });*/
    }

}
