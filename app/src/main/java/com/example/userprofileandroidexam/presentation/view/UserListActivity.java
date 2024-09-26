package com.example.userprofileandroidexam.presentation.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.userprofileandroidexam.helper.Constants.INTENT_USER_INFORMATION;
import static com.example.userprofileandroidexam.helper.Utils.dispose;
import static com.example.userprofileandroidexam.helper.Utils.getdob;
import static com.example.userprofileandroidexam.helper.Utils.hasNetworkConnection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.userprofileandroidexam.R;
import com.example.userprofileandroidexam.data.model.LocalUserSavedDataModel;
import com.example.userprofileandroidexam.domain.ErrorState;
import com.example.userprofileandroidexam.databinding.ActivityUserListBinding;
import com.example.userprofileandroidexam.domain.RandomUserState;
import com.example.userprofileandroidexam.domain.UserApiStatusEnum;
import com.example.userprofileandroidexam.presentation.adapter.PaginationScrollListener;
import com.example.userprofileandroidexam.presentation.adapter.UserListAdapter;
import com.example.userprofileandroidexam.presentation.viewmodel.UserProfileViewModel;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * User List activity.
 *
 * @author Zeus
 * @version 1.0
 * @since 2024-09-17
 */


public class UserListActivity extends AppCompatActivity {

    /**
     * Instantiates the activity.
     **/

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private static final String TAG = UserListActivity.class.getSimpleName();

    private UserApiStatusEnum userApiStatusEnum = UserApiStatusEnum.DEFAULT;
    private RandomUserState randomUserState = RandomUserState.DEFAULT;

    private LocalUserSavedDataModel localUserSavedDataModel = new LocalUserSavedDataModel();

    private List<LocalUserSavedDataModel> listUser = new ArrayList<>();

    @Inject
    Context context;

    private ActivityUserListBinding binding;

    private CompositeDisposable compositeDisposableView = new CompositeDisposable();
    private CompositeDisposable compositeDisposableRecycler = new CompositeDisposable();

    private UserListAdapter userListAdapter = new UserListAdapter(context, listUser);

    /**
     * View Model
     **/
    private UserProfileViewModel mUserProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        initDesign();
        initRecyclerView();
        initViewModel();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose(compositeDisposableRecycler);
        dispose(compositeDisposableView);
    }


    /**
     * Initialize Design.
     **/
    private void initDesign() {
        EdgeToEdge.enable(this);
        binding = ActivityUserListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    /**
     * Initialize Recyclerview button.
     **/
    private void initRecyclerButton() {
        swipeRefreshLayout();
        recyclerButton();
    }

    /**
     * Recyclerview button.
     **/
    private void swipeRefreshLayout() {
        binding.userProfileRecyclerViewSwipeTop.setOnRefreshListener(() -> {
            if (!hasNetworkConnection(context)) {
                binding.progressBar.setVisibility(GONE);
                binding.userProfileRecyclerViewSwipeTop.setRefreshing(false);
                Toast.makeText(context, "Network connection error!",
                        Toast.LENGTH_LONG).show();
                return;
            }
            binding.userProfileRecyclerViewSwipeTop.setRefreshing(false);
            binding.progressBar.setVisibility(VISIBLE);
            mUserProfileViewModel.clearCache();
        });
    }


    /**
     * Recyclerview button.
     **/
    private void recyclerButton() {
        compositeDisposableView.clear();
        compositeDisposableView.add(userListAdapter.observableClickedView()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<View>() {
                    @Override
                    public void onNext(View view) {
                        compositeDisposableRecycler.add(RxView.clicks(view)
                                .throttleFirst(500, TimeUnit.MILLISECONDS)
                                .subscribe(unit -> {
                                    final int position = Integer.parseInt(view.getTag().toString());
                                    localUserSavedDataModel.setName(listUser.get(position).getName());
                                    localUserSavedDataModel.setEmail(listUser.get(position).getEmail());
                                    localUserSavedDataModel.setPicture(listUser.get(position).getPicture());
                                    localUserSavedDataModel.setBirthday(getdob(listUser.get(position).getBirthday()));
                                    localUserSavedDataModel.setAge(listUser.get(position).getAge());
                                    localUserSavedDataModel.setPhone(listUser.get(position).getPhone());
                                    localUserSavedDataModel.setAddress(listUser.get(position).getAddress());

                                    Intent intent = new Intent(context, UserProfileActivity.class);
                                    intent.putExtra(INTENT_USER_INFORMATION, localUserSavedDataModel);
                                    startActivity(intent);
                                    finish();
                                }));
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    /**
     * Initialize view model
     **/
    private void initViewModel() {
        initViewModelConstructor();
        initLoadCacheChecker();
        initLoadCacheData();
        initError();
    }

    /**
     * Get Random user
     * Requesting API for user.
     */
    private void initGetRandomUser(RandomUserState randomUserState) {
        switch (userApiStatusEnum) {
            case DEFAULT:
            case LOADING_SUCCESS:
                mUserProfileViewModel.getRandomUser(randomUserState);
                userApiStatusEnum = UserApiStatusEnum.LOADING;
                break;
            case LOADING:
                //No action, to prevent multiple request.
                break;
            default:
                break;
        }
    }

    /**
     * View Model Constructor
     **/
    private void initViewModelConstructor() {
        mUserProfileViewModel = new ViewModelProvider(this, viewModelFactory).get(UserProfileViewModel.class);
    }


    /**
     * Initialize Recyclerview
     **/
    private void initRecyclerView() {
        userListAdapter = new UserListAdapter(context, listUser);
        binding.userProfileRecyclerView.setAdapter(userListAdapter);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        binding.userProfileRecyclerView.setLayoutManager(mLayoutManager);
        loadUserRecyclerView();
        initRecyclerButton();
    }


    /**
     * Load more users
     * Requesting API for users.
     **/
    private void loadUserRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.userProfileRecyclerView.setLayoutManager(linearLayoutManager);
        binding.userProfileRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                if (!hasNetworkConnection(context)) {
                    binding.progressBar.setVisibility(GONE);
                    binding.userProfileRecyclerViewSwipeTop.setRefreshing(false);
                    Toast.makeText(context, "Network connection error!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                binding.progressBar.setVisibility(VISIBLE);
                randomUserState = RandomUserState.RANDOM_STATE_NEXT_PAGE;
                initGetRandomUser(randomUserState);
            }
        });
    }


    /**
     * Init Error handler.
     **/
    private void initError() {
        mUserProfileViewModel.getErrorAsLiveData().observe(this, error -> {
            if (error.getErrorState().equals(ErrorState.INTERNET_CONNECTION_ERROR)) {
                binding.progressBar.setVisibility(GONE);
                binding.userProfileRecyclerViewSwipeTop.setRefreshing(false);
                Toast.makeText(context, "Network connection error!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Init Load Cache Checker
     **/
    private void initLoadCacheChecker() {
        mUserProfileViewModel.getAllUsers();
    }


    /**
     * Load Cache Data.
     **/
    private void initLoadCacheData() {
        mUserProfileViewModel.getloadCacheMutableLiveData().observe(this, loadCache -> {
            Log.d(TAG, "Load cache " + loadCache.getLoadDataUserState());
            userApiStatusEnum = UserApiStatusEnum.LOADING_SUCCESS;
            switch (loadCache.getLoadDataUserState()) {
                case LOAD_CACHE:
                    listUser.addAll(loadCache.getLoadLocalUser());
                    userListAdapter.notifyDataSetChanged();
                    break;
                case CACHE_UPDATE:
                    listUser.clear();
                    userListAdapter.notifyDataSetChanged();
                    binding.progressBar.setVisibility(GONE);
                    listUser.addAll(loadCache.getLoadLocalUser());
                    break;
                case FORCE_UPDATE_CACHE:
                    listUser.clear();
                    userListAdapter.notifyDataSetChanged();
                    randomUserState = RandomUserState.RANDOM_STATE_REFRESH;
                    initGetRandomUser(randomUserState);
                    break;
                case CACHE_NOT_AVAILABLE:
                    binding.progressBar.setVisibility(VISIBLE);
                    randomUserState = RandomUserState.RANDOM_STATE_REFRESH;
                    initGetRandomUser(randomUserState);
                    break;
                default:
                    break;
            }
        });
    }
}