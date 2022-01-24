package com.example.ec3milla.network;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import com.example.ec3milla.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MovieEntry
{
    private static final String TAG=MovieEntry.class.getSimpleName();

    public final String title;
    public final Uri dynamicUrl;
    public final String url;
    public final String description;
    public final String date;

    public MovieEntry(String title, String dynamicUrl, String url, String description, String date) {
        this.title = title;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.url = url;
        this.description = description;
        this.date=date;
    }

    public static List<MovieEntry> initProductEntryList(Resources resources)
    {
        InputStream inputStream=resources.openRawResource(R.raw.movies);
        Writer writer=new StringWriter();
        char[] buffer= new char[1024];
        try {
            Reader reader=new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer=reader.read(buffer))!=-1)
            {
                writer.write(buffer,0,pointer);
            }
        }catch (IOException exception)
        {
            Log.e(TAG,"Error al escribir o leer el archivo JSON",exception);
        }
        finally
        {
            try
            {
                inputStream.close();

            } catch (IOException exception)
            {
                Log.e(TAG,"Error al cerrar la conexion con el archivo",exception);
            }
        }
        String jsonProductsString= writer.toString();
        Gson gson=new Gson();
        Type productListType=new TypeToken<ArrayList<MovieEntry>>()
        {
        }.getType();

        return gson.fromJson(jsonProductsString, productListType);
    }
}
