package com.devom.cndb_example.adapterViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devom.cndb_example.R;
import com.devom.cndb_example.models.User;
import com.devom.cndb_example.ui.greet.GreetActivity;

import static com.devom.cndb_example.utils.Constants.PARAM_USER;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private CardView itemJoke;
    private TextView tvName;
    public ImageView ivHello, ivDelete;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        itemJoke = itemView.findViewById(R.id.item_user);
        tvName = itemView.findViewById(R.id.tv_name);
        ivHello = itemView.findViewById(R.id.iv_hello);
        ivDelete = itemView.findViewById(R.id.iv_delete);
    }

    public void bind(User user) {
        tvName.setText(user.getName());

        Intent intent = new Intent(itemView.getContext(), GreetActivity.class);
        intent.putExtra(PARAM_USER, user);
        ivHello.setOnClickListener(v-> {
            Toast.makeText(itemView.getContext(),user.getName(),Toast.LENGTH_LONG).show();
            itemView.getContext().startActivity(intent);
        });


    }
}