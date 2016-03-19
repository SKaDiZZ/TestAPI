package com.example.samir.testapi;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>{

    Context context;
    List<Post> posts;

    PostsAdapter(Context context, List<Post> posts) {

        this.context = context;
        this.posts = posts;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PostsAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kartica, parent, false);

        return new PostsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostsAdapter.PostsViewHolder holder, int position) {

        holder.cardView.getUseCompatPadding();
        holder.postTitle.setText(posts.get(position).title);
        holder.content.setText(posts.get(position).content);
        Glide.with(context).load(posts.get(position).thumbnail).into(holder.post_image);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostsViewHolder extends  RecyclerView.ViewHolder {

        CardView cardView;
        TextView postTitle;
        TextView content;
        ImageView post_image;

        public PostsViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cv);
            postTitle = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            post_image = (ImageView) itemView.findViewById(R.id.post_image);

        }
    }
}
