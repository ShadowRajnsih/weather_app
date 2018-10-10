package com.example.shadow47.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/*seller*/
import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ProductViewHolder> {
    private Context mcx;
    private List<weather> weathersList;


    public productAdapter(Context mcx, List<weather> weathersList) {
        this.mcx = mcx;
        this.weathersList = weathersList;
    }



    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcx);
        View view=inflater.inflate(R.layout.weather,null);
        ProductViewHolder holder=new ProductViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        weather weather=weathersList.get(position);
        holder.summ.setText(weather.getSummary());
        holder.timez.setText(weather.getTimeZone());
        holder.ws.setText(weather.getWs());
        holder.humid.setText(weather.getHumid());
        holder.temp.setText(weather.getTemp());


    }

    @Override
    public int getItemCount() {
        return weathersList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView temp,summ,timez,humid,ws;


        public ProductViewHolder(View itemView) {
            super(itemView);
            temp=itemView.findViewById(R.id.temperature);
            summ=itemView.findViewById(R.id.summary);
            timez=itemView.findViewById(R.id.timeZone);
            humid=itemView.findViewById(R.id.humidity);
            ws=itemView.findViewById(R.id.wp);

        }
    }
}
