package com.example.shadow47.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class weatherFragment extends Fragment {
    RecyclerView recyclerView;
    productAdapter adapter;
    List<weather> weatherList;
    String s1,s2,d2,d3,d4;







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weather_fragment,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(),"Weather Fragment",Toast.LENGTH_SHORT).show();


        weatherList=new ArrayList<>();
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //sellerList.add(new seller("kishor","z405",500));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(weatherApi.Base_Url)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        weatherApi api = retrofit.create(weatherApi.class);
        for(double i=10;i<=30;i++)
        {
            Call<weatherPojo> call = api.getWeather(i,i+10);
            call.enqueue(new Callback<weatherPojo>() {
                @Override
                public void onResponse(Call<weatherPojo> call, Response<weatherPojo> response) {
                    weatherPojo weatherPojo=response.body();
                    s1=weatherPojo.getTimezone();
                    s2=weatherPojo.getCurrently().getSummary();
                    d2=weatherPojo.getCurrently().getHumidity().toString();
                    d3=weatherPojo.getCurrently().getTemperature().toString();
                    d4=weatherPojo.getCurrently().getWindSpeed().toString();
                    weatherList.add(new weather(s1,s2,d4,d2,d3));

                    System.out.print("Weather"+ weatherPojo.getTimezone());
                    adapter=new productAdapter( getActivity().getApplicationContext(),weatherList);
                    recyclerView.setAdapter(adapter);


                }

                @Override
                public void onFailure(Call<weatherPojo> call, Throwable t) {

                }
            });
        }




    }

}
