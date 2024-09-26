package com.example.userprofileandroidexam.presentation.adapter;

import static com.example.userprofileandroidexam.helper.Utils.setImage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.userprofileandroidexam.R;
import com.example.userprofileandroidexam.data.model.LocalUserSavedDataModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private final PublishSubject<View> clickedView = PublishSubject.create();

    private List<LocalUserSavedDataModel> listUser;
    private Context context;

    public Observable<View> observableClickedView() {
        return clickedView;
    }

    public UserListAdapter(Context context, List<LocalUserSavedDataModel> listUser) {
        this.context = context;
        this.listUser = listUser;
    }

    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_user_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.MyViewHolder holder, int position) {
        if (listUser.size() != 0) {
            LocalUserSavedDataModel user = listUser.get(position);
            holder.bind(user);
        }

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide));
        holder.cardView.setTag(position);
        clickedView.onNext(holder.cardView);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView username;
        private final TextView address;
        private final MaterialCardView cardView;
        private final ImageView userAvatar;

        final Context context;

        public MyViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            username = itemView.findViewById(R.id.user_name);
            address = itemView.findViewById(R.id.user_address);
            cardView = itemView.findViewById(R.id.user_cardview);
            userAvatar = itemView.findViewById(R.id.user_img);

        }

        @SuppressLint("SetTextI18n")
        public void bind(LocalUserSavedDataModel localUserSavedDataModel) {
            username.setText(localUserSavedDataModel.getName());
            address.setText(localUserSavedDataModel.getEmail());
            setImage(context, localUserSavedDataModel.getPicture(), userAvatar);
        }
    }
}
