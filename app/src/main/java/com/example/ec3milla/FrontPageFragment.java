package com.example.ec3milla;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class FrontPageFragment extends Fragment
{
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
        View view=inflater.inflate(R.layout.front_page_fragment,container,false);
        MaterialButton catalogueButton=view.findViewById(R.id.catalogue_movie);

        catalogueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Aqui nos redireccionamos al layout CatalogueFragment o a la siguiente página
                ((NavigationHost) getActivity()).navigateTo(new MovieGrid(),true);
            }
        });

        return view;
    }
}
