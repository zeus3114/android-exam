package com.example.userprofileandroidexam.presentation.view;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.userprofileandroidexam.R;
import com.example.userprofileandroidexam.databinding.ActivityInitialBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Initial activity.
 *
 * @author Zeus
 * @version 1.0
 * @since 2024-09-20
 */

public class InitialActivity extends AppCompatActivity {

    ActivityInitialBinding binding;

    private Animation topAnimation;
    private Animation botAnimation;

    @Inject
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        initDesign();
        initAnimation();
        intentToNextScreen();
    }

    private void initDesign() {
        EdgeToEdge.enable(this);
        binding = ActivityInitialBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.initial), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        topAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_top);
        botAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_bot);
    }

    private void initAnimation() {
        binding.imageView2.setAnimation(botAnimation);
        binding.titleLogo.setAnimation(topAnimation);
    }

    private void intentToNextScreen() {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = () -> {
            Intent intent = new Intent(context, UserListActivity.class);

            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View,String>(binding.imageView2, "image_logo");
            pairs[1] = new Pair<View,String>(binding.titleLogo, "text_logo");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InitialActivity.this, pairs);
            startActivity(intent, options.toBundle());
        };
        handler.postDelayed(runnable, 1600);
    }
}
