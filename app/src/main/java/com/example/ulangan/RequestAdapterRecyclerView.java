package com.example.ulangan;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestAdapterRecyclerView extends RecyclerView.Adapter<RequestAdapterRecyclerView.MyViewHolder> {

    private List<Item> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTanggal, tvJudul, tvDeskripsi;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.ctxtTanggal);
            tvJudul = itemView.findViewById(R.id.ctxtJudul);
            tvDeskripsi = itemView.findViewById(R.id.ctxtDeskripsi);
        }
    }

    public RequestAdapterRecyclerView(List<Item> moviesList, Activity activity){
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Item movie = moviesList.get(position);

        holder.tvTanggal.setText(movie.getTanggal());
        holder.tvJudul.setText(movie.getJudul());
        holder.tvDeskripsi.setText(movie.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        Log.d("sds", String.valueOf(moviesList.size()));
        return moviesList.size();
    }
}
