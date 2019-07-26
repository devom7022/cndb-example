package com.devom.cndb_example.repository;

import android.util.Log;

import com.devom.cndb_example.models.User;
import com.devom.cndb_example.modelsRealm.UserRealm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class UsersSource {
    private static UsersSource instance;

    private List<User> userList;
    private Realm realm;
    private UsersSource() {
        userList = new ArrayList<>();
        realm = Realm.getDefaultInstance();
    }

    public static synchronized UsersSource getInstance() {
        if (instance == null) {
            instance = new UsersSource();
        }

        return instance;
    }

    public List<User> getUsers() {
        RealmResults<UserRealm> usersModel = realm.where(UserRealm.class).findAll();

        Log.i("realm", usersModel.toString());

        userList.clear();
        for (UserRealm model : usersModel) {
            User local = new User();
            local.setId(model.getId());
            local.setName(model.getName());
            local.setAge(model.getAge());
            local.setColor(model.getColor());

            userList.add(local);
        }

        return userList;
    }

    public void addUser(User user) {

        realm.beginTransaction();
        UserRealm userRealm = realm.createObject(UserRealm.class);
        userRealm.setId(user.getId());
        userRealm.setName(user.getName());
        userRealm.setAge(user.getAge());
        userRealm.setColor(user.getColor());

        realm.commitTransaction();

        userList.add(user);
    }

    public void deleteUser(User user) {
        realm.beginTransaction();
        UserRealm userRealm = realm.where(UserRealm.class)
                .equalTo("id",user.getId())
                .equalTo("name",user.getName())
                .findFirst();

        if (userRealm != null) {
            userRealm.deleteFromRealm();
        }

        realm.commitTransaction();

        //userList.remove(user);
        //realm.deleteFromRealm();
    }
}
