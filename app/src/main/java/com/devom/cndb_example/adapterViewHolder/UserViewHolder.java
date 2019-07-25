package com.devom.cndb_example.adapterViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devom.cndb_example.R;
import com.devom.cndb_example.models.User;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private CardView itemJoke;
    private TextView tvName;
    private ImageView ivHello, ivDelete;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        itemJoke = itemView.findViewById(R.id.item_user);
        tvName = itemView.findViewById(R.id.tv_name);
        ivHello = itemView.findViewById(R.id.iv_hello);
        ivDelete = itemView.findViewById(R.id.iv_delete);
    }

    public void bind(User user) {
        tvName.setText(user.getName());
    }
}