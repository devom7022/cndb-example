package com.devom.cndb_example.ui.greet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.devom.cndb_example.R;
import com.devom.cndb_example.models.Joke;

import javax.inject.Inject;

public class GreetActivity extends AppCompatActivity implements GreetView {

    GreetPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet);
        //presenter = new GreetPresenter();
        //presenter.setView(this);
    }

    @Override
    public void setTextRandom(Joke joke) {

    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}
