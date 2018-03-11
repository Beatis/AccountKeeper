package com.example.kbeatis.acckeeper;

import android.content.Context;

/**
 * Created by kbeatis on 11.03.18.
 */
public class ContextSingleton {
    private static ContextSingleton instance;

    public static void init(Context context) {
        if (instance == null) {
            context = context.getApplicationContext();
            instance = new ContextSingleton(context);
        }
    }

    public static ContextSingleton getInstance() {
        return instance;
    }

    private final Context context;

    private ContextSingleton(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
