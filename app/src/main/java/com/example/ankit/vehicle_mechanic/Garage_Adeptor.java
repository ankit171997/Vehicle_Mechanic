package com.example.ankit.vehicle_mechanic;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by JUNED on 6/16/2016.
 */
public class Garage_Adeptor extends RecyclerView.Adapter<Garage_Adeptor.ViewHolder> {

    public Context context;

    List<Actors> getDataAdapter;


    public Garage_Adeptor(List<Actors> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_garage_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, final int position) {

        final Actors getDataAdapter1 =  getDataAdapter.get(position);


        Viewholder.garage_name.setText(getDataAdapter1.getName());
        Viewholder.garage_dec.setText(getDataAdapter1.getDescription());
        Viewholder.area.setText(getDataAdapter1.getArea());
String rr = getDataAdapter1.getRating();
        Viewholder.Rating.setRating(Float.parseFloat(rr));


        String imgUrl = "http:/192.168.225.37:8888/VehicleMechanic1/"+getDataAdapter1.getImage();


        Glide.with(context).load(imgUrl).into(Viewholder.Image);


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView garage_name,garage_dec,area;
        public ImageView Image;
        public RatingBar Rating;

        public ViewHolder(View itemView) {

            super(itemView);

            garage_name = (TextView) itemView.findViewById(R.id.textView);
            garage_dec = (TextView) itemView.findViewById(R.id.textView4);
            area = (TextView) itemView.findViewById(R.id.textView6);
            Image = (ImageView) itemView.findViewById(R.id.imageView);

Rating = (RatingBar)itemView.findViewById(R.id.ratingBar);



        }
    }
}
