package com.ncs.plataformes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncs.plataformes.R;
import com.ncs.plataformes.models.User;
import com.squareup.picasso.Picasso;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private User user;

    public UserAdapter(User user) {
        this.user = user;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView tvUserId, tvUsername, tvFullname, tvEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgUser);
            tvUserId = itemView.findViewById(R.id.tvUserId);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvFullname = itemView.findViewById(R.id.tvFullname);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        Context context = holder.tvUserId.getContext();
        holder.tvUserId.setText(user.getId());
        holder.tvUsername.setText(user.getUsername());
        holder.tvFullname.setText(context.getString(R.string.fullName, user.getFirstname(), user.getLastname()));
        holder.tvEmail.setText(user.getEmail());
        Picasso.with(context).load(user.getAvatarPath()).into(holder.imgUser);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
