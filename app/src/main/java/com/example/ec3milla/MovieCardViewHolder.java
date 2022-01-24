package com.example.ec3milla;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

public class MovieCardViewHolder extends RecyclerView.ViewHolder
{
    public NetworkImageView movieImage;
    public TextView movieTitle;
    public TextView movieDescription;
    public TextView movieDate;

    public MovieCardViewHolder(@NonNull View itemView)
    {
        super(itemView);
        movieImage=itemView.findViewById(R.id.image_movie);
        movieTitle=itemView.findViewById(R.id.title_movie);
        movieDescription=itemView.findViewById(R.id.description_movie);
        movieDate=itemView.findViewById(R.id.date_movie);
    }
}
