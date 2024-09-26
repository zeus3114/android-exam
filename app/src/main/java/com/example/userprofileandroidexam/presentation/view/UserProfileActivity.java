package com.example.userprofileandroidexam.presentation.view;
import static com.example.userprofileandroidexam.helper.Constants.INTENT_USER_INFORMATION;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.userprofileandroidexam.R;
import com.example.userprofileandroidexam.data.model.LocalUserSavedDataModel;
import com.example.userprofileandroidexam.databinding.ActivityUserInformationBinding;
import com.example.userprofileandroidexam.helper.Utils;
import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * User Profile activity.
 *
 * @author Zeus
 * @version 1.0
 * @since 2024-09-20
 */

public class UserProfileActivity extends AppCompatActivity {

    private LocalUserSavedDataModel localUserSavedDataModel = new LocalUserSavedDataModel();

    @Inject
    Context context;

    private ActivityUserInformationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        initIntentData();
        initDesign();
    }

    private void initIntentData() {
        final Intent intent = getIntent();
        localUserSavedDataModel = Utils.getUserListSavedDataModel(intent, INTENT_USER_INFORMATION);

    }

    private void initDesign() {
        EdgeToEdge.enable(this);
        binding = ActivityUserInformationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.setUserModel(localUserSavedDataModel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayoutMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(context, UserListActivity.class);
        startActivity(intent);
        finish();
    }
}
