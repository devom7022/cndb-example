package com.devom.cndb_example.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ResultUserList implements Parcelable {
    List<User> users;

    public ResultUserList(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    protected ResultUserList(Parcel in) {
        if (in.readByte() == 0x01) {
            users = new ArrayList<User>();
            in.readList(users, User.class.getClassLoader());
        } else {
            users = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (users == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(users);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResultUserList> CREATOR = new Parcelable.Creator<ResultUserList>() {
        @Override
        public ResultUserList createFromParcel(Parcel in) {
            return new ResultUserList(in);
        }

        @Override
        public ResultUserList[] newArray(int size) {
            return new ResultUserList[size];
        }
    };
}