package com.devom.cndb_example.ui.greet;

import com.devom.cndb_example.models.Joke;

public interface GreetView {
    void setTextRandom(Joke joke);

    void onFailure(String error);
}
