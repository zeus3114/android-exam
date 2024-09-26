package com.example.userprofileandroidexam.helper;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.userprofileandroidexam.R;
import com.example.userprofileandroidexam.data.model.LocalUserSavedDataModel;

import java.time.LocalDate;
import java.time.Period;

import io.reactivex.disposables.CompositeDisposable;

public class Utils {

    public static boolean hasNetworkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void dispose(final CompositeDisposable compositeDisposable) {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    //Intent data
    public static LocalUserSavedDataModel getUserListSavedDataModel(Intent intent, String tempKey) {

        LocalUserSavedDataModel localUserSavedDataModel = new LocalUserSavedDataModel();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (intent.getSerializableExtra(tempKey, LocalUserSavedDataModel.class) == null) {
                return localUserSavedDataModel;
            }
            localUserSavedDataModel = (LocalUserSavedDataModel) intent.getSerializableExtra(tempKey, LocalUserSavedDataModel.class);
        } else {
            if (intent.getSerializableExtra(tempKey) == null) {
                return localUserSavedDataModel;
            }
            localUserSavedDataModel = (LocalUserSavedDataModel) intent.getSerializableExtra(tempKey);
        }
        return localUserSavedDataModel;
    }



    //Setting image.
    public static void setImage(Context context, String loadImage, ImageView imageView) {
        Glide.with(context)
                .load(loadImage)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.baseline_person_24)
                        .error(R.drawable.baseline_error_24))
                .into(imageView);
    }

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String loadImage) {
        Glide.with(imageView)
                .load(loadImage)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.baseline_person_24)
                        .error(R.drawable.baseline_error_24))
                .into(imageView);
    }

    public static int getAge(int year, int month, int dayOfMonth) {
        return Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
        ).getYears();
    }

    public static String getdob(String dob) {
        String[] date = dob.split("T");
        String birthday = date[0];
        return birthday;
    }

    public static int getActualAge(String dob) {
        String string = dob;
        String[] date = string.split("T");
        String birthday = date[0];

        String[] age = birthday.split("-");
        int year = Integer.parseInt(age[0]);
        int month = Integer.parseInt(age[1]);
        int day = Integer.parseInt(age[2]);
        return getAge(year, month, day);
    }
}
