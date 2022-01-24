package com.example.ec3milla;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec3milla.network.ImageRequest;
import com.example.ec3milla.network.MovieEntry;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MovieCardViewHolder>
{
    private List<MovieEntry> productList;
    private ImageRequest imageRequest;

    RecyclerViewAdapter(List<MovieEntry> productList)
    {
        this.productList=productList;
        imageRequest=ImageRequest.getInstance();
    }

    @NonNull
    @Override
    public MovieCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);
        return new MovieCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCardViewHolder holder, int position)
    {
        if(productList!=null && position<productList.size())
        {
            MovieEntry product=productList.get(position);
            holder.movieTitle.setText(product.title);
            holder.movieDescription.setText(product.description);
            holder.movieDate.setText(product.date);
            imageRequest.setImageFromUrl(holder.movieImage,product.url);
        }
    }

    @Override
    public int getItemCount(){return productList.size();}
}
