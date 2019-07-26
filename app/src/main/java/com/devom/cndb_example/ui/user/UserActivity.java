package com.devom.cndb_example.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devom.cndb_example.R;
import com.devom.cndb_example.adapters.UserAdapter;
import com.devom.cndb_example.models.ResultUserList;
import com.devom.cndb_example.models.User;
import com.devom.cndb_example.ui.userAdd.AddUserActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.devom.cndb_example.utils.Constants.RESULT_DATA;

public class UserActivity extends AppCompatActivity implements UserView {
    private ProgressBar progressBar;
    private static long back_pressed;
    private static final long SECONDS_LAPSE = 2000;
    UserAdapter adapter;

    UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        progressBar = findViewById(R.id.pb_load);

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> startActivityForResult(new Intent(this, AddUserActivity.class), 1));

        Toolbar toolbar = findViewById(R.id.tb_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle(getString(R.string.title_user_list));
        }

        presenter = new UserPresenter();
        presenter.setView(this);
        presenter.getUserList();

        setAdapter();

    }

    private void setAdapter() {
        if (adapter == null)
            adapter = new UserAdapter(presenter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView rvCity = findViewById(R.id.rv_users);
        rvCity.setAdapter(adapter);
        rvCity.setLayoutManager(layoutManager);
    }
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setItemsOnAdapters(List<User> users) {
        setAdapter();
        adapter.setData(users);
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

    @Override
    public void onBackPressed() {
        if (back_pressed + SECONDS_LAPSE > System.currentTimeMillis()) super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), getText(R.string.msj_exit_app), Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                ResultUserList list = data.getParcelableExtra(RESULT_DATA);

                List<User> newUsers = list.getUsers();
                setItemsOnAdapters(newUsers);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
