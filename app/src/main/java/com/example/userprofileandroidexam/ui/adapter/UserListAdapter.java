package com.example.userprofileandroidexam.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.userprofileandroidexam.R;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class UserListAdapter  extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private final PublishSubject<View> clickedView = PublishSubject.create();

    public Observable<View> observableClickedView() {
        return clickedView;
    }

    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_user_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.MyViewHolder holder, int position) {

        clickedView.onNext(holder.userListRelativeView);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView user;
        private final View userListRelativeView;
        private final ImageView arrowNext;

        final Context context;

        public MyViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            user = itemView.findViewById(R.id.userName);
            userListRelativeView = itemView.findViewById(R.id.selectable);
            arrowNext = itemView.findViewById(R.id.arrowNext);
        }
    }
}
