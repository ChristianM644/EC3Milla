package com.example.ec3milla;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec3milla.network.MovieEntry;
import com.google.android.material.button.MaterialButton;

public class MovieGrid extends Fragment
{
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ){
        View view=inflater.inflate(R.layout.movie_grid,container,false);
        setUpToolbar(view);

        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false));
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(
                MovieEntry.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);

        int largePadding=getResources().getDimensionPixelSize(R.dimen.item_grid_spacing);
        int smallPadding=getResources().getDimensionPixelSize(R.dimen.item_grid_spacing_small);

        recyclerView.addItemDecoration(new MovideGridDecoration(largePadding,smallPadding));

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            view.findViewById(R.id.movie_grid).setBackgroundResource(R.drawable.shape);
        }


        MaterialButton buttonAccount=view.findViewById(R.id.button_account);
        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                getActivity().startActivity(intent);
            }
        });

        MaterialButton cryptoButton=view.findViewById(R.id.button_crypto);
        cryptoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CategoryActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    private void setUpToolbar(View view)
    {
        Toolbar toolbar=view.findViewById(R.id.app_bar);
        AppCompatActivity activity=(AppCompatActivity) getActivity();
        if(activity !=null)
        {
            activity.setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(getContext(),
                view.findViewById(R.id.movie_grid),
                new AccelerateDecelerateInterpolator(),
                getContext().getResources().getDrawable(R.drawable.menus),
                getContext().getResources().getDrawable(R.drawable.menu_cancel)
                ));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater)
    {
        menuInflater.inflate(R.menu.tool_bar,menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }
}
