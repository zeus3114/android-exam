package com.example.userprofileandroidexam.helper;

import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;

public class Utils {

    public static void dispose(final CompositeDisposable compositeDisposable) {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public static void logging(String tag, String message) {
        Log.e(tag, message);
    }

}
