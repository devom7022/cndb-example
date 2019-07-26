package com.devom.cndb_example.ui.userAdd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.devom.cndb_example.R;
import com.devom.cndb_example.models.ResultUserList;
import com.devom.cndb_example.models.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import static com.devom.cndb_example.utils.Constants.RESULT_DATA;

public class AddUserActivity extends AppCompatActivity implements AddUserView {
    private TextInputLayout tilName, tilAge, tilColor;

    ProgressBar progress;
    AddUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        progress = findViewById(R.id.pb_load);

        Toolbar toolbar = findViewById(R.id.tb_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle(getString(R.string.title_user_add));
        }


        Button btSave = findViewById(R.id.bt_save);
        btSave.setOnClickListener(v -> {
            if (validData()) {
                User userSave = new User(tilName.getEditText().getText().toString(),
                        Integer.parseInt(tilAge.getEditText().getText().toString()),
                        tilColor.getEditText().getText().toString()
                );
                presenter.setUserToList(userSave);
            }
        });

        tilName = findViewById(R.id.til_name);
        tilAge = findViewById(R.id.til_age);
        tilColor = findViewById(R.id.til_color);

        presenter = new AddUserPresenter();
        presenter.setView(this);
    }

    private boolean validData() {
        if (TextUtils.isEmpty(tilName.getEditText().getText()) || TextUtils.isEmpty(tilAge.getEditText().getText()) || TextUtils.isEmpty(tilColor.getEditText().getText())) {
            onFailure(this.getString(R.string.empty_data));
            return false;
        }

        return true;
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessSave(List<User> users) {
        ResultUserList list = new ResultUserList(users);
        Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_LONG).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(RESULT_DATA, list);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        //Intent returnIntent = new Intent();
        //setResult(Activity.RESULT_CANCELED, returnIntent);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}
