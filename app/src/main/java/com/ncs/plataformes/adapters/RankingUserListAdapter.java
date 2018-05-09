package com.ncs.plataformes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncs.plataformes.R;
import com.ncs.plataformes.models.Ranking;
import com.ncs.plataformes.models.RankingList;
import com.squareup.picasso.Picasso;

public class RankingUserListAdapter
        extends RecyclerView.Adapter<RankingUserListAdapter.ViewHolder> {

    private RankingList rankingList;

    public RankingUserListAdapter(RankingList rankingList) {
        this.rankingList = rankingList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView tvUserId, tvFullName, tvUsername, tvScore;

        public ViewHolder(View view) {
            super(view);
            imgUser = view.findViewById(R.id.imgUser);
            tvUserId = view.findViewById(R.id.tvGameId);
            tvFullName = view.findViewById(R.id.tvFullName);
            tvUsername = view.findViewById(R.id.tvUsername);
            tvScore = view.findViewById(R.id.tvScore);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_ranking_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RankingUserListAdapter.ViewHolder holder, int position) {
        Ranking ranking = rankingList.getRankings()[position];
        Context context = holder.tvUserId.getContext();
        holder.tvUserId.setText(context.getString(R.string.strId, ranking.getId()));
        Log.d("ncs", "onBindViewHolder: USERNAME: " + ranking.getUser().getUsername());
        holder.tvUsername.setText(ranking.getUser().getUsername());
//        holder.tvFullName.setText(ranking.getUser().getFirstname() + ranking.getUser().getLastname());
        holder.tvFullName.setText(context.getString(R.string.fullName, ranking.getUser().getFirstname(), ranking.getUser().getLastname()));
        holder.tvScore.setText(context.getString(R.string.position, position + 1, Integer.parseInt(ranking.getScore())));
        Picasso.with(context).load(ranking.getUser().getAvatarPath()).into(holder.imgUser);
    }

    @Override
    public int getItemCount() {
        return rankingList.getCount();
    }
}
