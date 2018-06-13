package com.example.ankit.vehicle_mechanic;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
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
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Update_Adeptor extends RecyclerView.Adapter<Update_Adeptor.ViewHolder> {

    public Context context;

    List<Actors> getDataAdapter;

    public Update_Adeptor(List<Actors> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }


    public void onBindViewHolder(ViewHolder Viewholder, final int position) {

        final Actors getDataAdapter1 =  getDataAdapter.get(position);

        Viewholder.service_name.setText(getDataAdapter1.getService_name());
        Viewholder.vehicle_name.setText(getDataAdapter1.getVehicle_name());
        Viewholder.estimated_time.setText(getDataAdapter1.getGarage_id());


        //Viewholder.service_amount.setText(getDataAdapter1.getService_amount());
        if(getDataAdapter1.getCompleted().equals("0")){
            Viewholder.service_amount.setText("Pending");
        }else {
            Viewholder.service_amount.setText("Completed");
        }


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView service_name,service_amount,vehicle_name,estimated_time;

        public ViewHolder(View itemView) {

            super(itemView);
            service_name = (TextView) itemView.findViewById(R.id.title);
            service_amount=(TextView)itemView.findViewById(R.id.amount);
            vehicle_name=(TextView)itemView.findViewById(R.id.title1);
            estimated_time=(TextView)itemView.findViewById(R.id.amount1);
        }
    }
}
