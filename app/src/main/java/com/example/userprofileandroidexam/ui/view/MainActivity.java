package com.example.userprofileandroidexam.ui.view;

import static com.example.userprofileandroidexam.helper.Utils.logging;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.OnSwipe;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.userprofileandroidexam.R;
import com.example.userprofileandroidexam.helper.Utils;
import com.example.userprofileandroidexam.ui.adapter.UserListAdapter;
import com.example.userprofileandroidexam.ui.viewmodel.UserProfileViewModel;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Main activity.
 *
 * @author Zeus
 * @version 1.0
 * @since 2024-09-17
 */


public class MainActivity extends AppCompatActivity {

    /**
     * Instantiates the activity.
     **/

    /*Test*/
    public MainActivity() {
        this.mContext = MainActivity.this;
    }

    private final Context mContext;

    private Activity binding;

    private CompositeDisposable compositeDisposableView = new CompositeDisposable();
    private CompositeDisposable compositeDisposableRecycler = new CompositeDisposable();
    private CompositeDisposable compositeDisposableSwipe = new CompositeDisposable();

    private final UserListAdapter userListAdapter = new UserListAdapter();

    @Inject
    Context appContext;

    @Inject
    ViewModelProvider.Factory factory;


    /**
     * Data Model
     **/

    private UserProfileViewModel userProfileViewModel;

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AndroidInjection.inject(this);
        initDesign();
        initRecyclerButton();
        //initViewModel();
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
        Utils.dispose(compositeDisposableView);
    }


    private void initDesign() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        logging("InitDesign", "Instantiates the design");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        swipeRefreshLayout = findViewById(R.id.userProfileRecyclerViewSwipeTop);
        mRecyclerView = findViewById(R.id.userProfileRecyclerView);
        mRecyclerView.setAdapter(userListAdapter);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void swipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            logging("REFRESH", "REFRESH");
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void initRecyclerButton() {
        swipeRefreshLayout();
        recyclerButton();

    }

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
                                    logging("RECYCLER VIEW", "TAP");
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

    private void initViewModel() {

    }


}