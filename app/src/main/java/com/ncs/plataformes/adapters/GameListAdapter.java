package com.ncs.plataformes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncs.plataformes.R;
import com.ncs.plataformes.models.Game;
import com.ncs.plataformes.models.GameList;
import com.squareup.picasso.Picasso;

public class GameListAdapter
        extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    private GameList gameList;

    public GameListAdapter(GameList gameList) {
        this.gameList = gameList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Game game;
        ImageView imgView;
        TextView tvId, tvName, tvDescription;

        public ViewHolder(View view) {
            super(view);
            imgView = view.findViewById(R.id.imgGame);
            tvId = view.findViewById(R.id.tvGameId);
            tvName = view.findViewById(R.id.tvName);
            tvDescription = view.findViewById(R.id.tvDescription);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (game == null) return;
                    if (listener != null) listener.itemClicked(view, game);
                }
            });
        }
    }

//    region ItemClickListener

    /**
     * Interface to implement the click listener
     */
    public interface OnItemClickListener {
        void itemClicked(View view, Game game);
    }

    private OnItemClickListener listener;

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
//    endregion

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_game, parent, false);
        return new ViewHolder(view);
    }

    /**
     * @param holder   ViewHolder
     * @param position number of the game in the RecyclerView
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game game = gameList.getGames()[position];
        Context context = holder.tvId.getContext();
        holder.game = game;
        holder.tvId.setText(context.getString(R.string.strId, game.getId()));
        holder.tvName.setText(game.getName());
        holder.tvDescription.setText(game.getDescription());
        Picasso.with(context).load(game.getImagePath()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return gameList.getCount();
    }

}
