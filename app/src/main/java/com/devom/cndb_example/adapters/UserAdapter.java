package com.devom.cndb_example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devom.cndb_example.R;
import com.devom.cndb_example.adapterViewHolder.UserViewHolder;
import com.devom.cndb_example.models.User;
import com.devom.cndb_example.ui.user.UserPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> userList = new ArrayList<>();
    private UserPresenter presenter;

    public UserAdapter() {
    }

    public UserAdapter(UserPresenter presenter) {
        this.presenter = presenter;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int pos) {
        final User localItem = userList.get(pos);

        holder.bind(localItem);

        holder.ivDelete.setOnClickListener(v -> {
            userList.remove(localItem);
            notifyDataSetChanged();
            presenter.deleteUser(localItem);
        });

    }

    @Override
    public int getItemCount() {
        return null != userList ? userList.size() : 0;
    }

    public void setData(List<User> userList) {
        this.userList.clear();
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

}