package com.example.ec3milla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ec3milla.R;
import com.example.ec3milla.model.categoryEntity;
import com.example.ec3milla.model.categoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends AppCompatActivity {

    private TextView categoryId;
    TextView categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);

        categoryId=findViewById(R.id.text_category_id);
        get_list();
    }

    public void get_list()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/api/v3/coins/categories/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        categoryService service=retrofit.create(categoryService.class);
        Call<List<categoryEntity>> call=service.findAll();
        call.enqueue(new Callback<List<categoryEntity>>() {
            @Override
            public void onResponse(Call<List<categoryEntity>> call, Response<List<categoryEntity>> response) {
                if(!response.isSuccessful())
                {
                    categoryId.setText("Categoria Id: "+response.code());
                    return;
                }
                List<categoryEntity> categoryList=response.body();
                //creamos un for para recorrer cada elemento por id
                for(categoryEntity cat: categoryList)
                {
                    String result="";
                    result+="Category Id: "+cat.getCategory_id()+"\n";
                    result+="Category Name: "+cat.getName()+"\n\n";
                    categoryId.append(result);

                }
            }

            @Override
            public void onFailure(Call<List<categoryEntity>> call, Throwable t) {
                categoryId.setText(t.getMessage());
            }
        });

    }
}