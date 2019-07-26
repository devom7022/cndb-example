package com.devom.cndb_example.ui.greet;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.devom.cndb_example.R;
import com.devom.cndb_example.app.BaseApplication;
import com.devom.cndb_example.models.Joke;

public class GreetActivity extends AppCompatActivity implements GreetView {

    GreetPresenter presenter;
    TextView tvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet);

        tvJoke = findViewById(R.id.tv_joke);

        Toolbar toolbar = findViewById(R.id.tb_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle(getString(R.string.title_greet));
        }

        presenter = new GreetPresenter(BaseApplication.getInstance());
        presenter.setView(this);
        presenter.getText();
    }

    @Override
    public void setTextRandom(Joke joke) {
        tvJoke.setText(joke.getJoke());
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}
