package com.example.samir.testapi;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>{

    List<Post> posts;

    PostsAdapter(List<Post> posts) {
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

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostsViewHolder extends  RecyclerView.ViewHolder {

        CardView cardView;
        TextView postTitle;
        TextView content;

        public PostsViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cv);
            postTitle = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);

        }
    }
}
