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

/**
 * Created by JUNED on 6/16/2016.
 */
public class ViewCart_Adeptor extends RecyclerView.Adapter<ViewCart_Adeptor.ViewHolder> {

    public Context context;

    List<Actors> getDataAdapter;



    public ViewCart_Adeptor(List<Actors> getDataAdapter, Context context){


        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_view_row, parent, false);



        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, final int position) {

        final Actors getDataAdapter1 =  getDataAdapter.get(position);

        Viewholder.service_name.setText(getDataAdapter1.getService_name());
        Viewholder.service_amount.setText(getDataAdapter1.getService_amount());








        Viewholder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {













                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.225.37:8888/VehicleMechanic1/webservice/display_cart_delete.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                            }


                        }){

                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("user_email","ankit@gmail.com");
                        params.put("service_name",getDataAdapter1.getService_name());


                        return params;
                    }

                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue requestQueue = Volley.newRequestQueue(context);

                //Adding request to the queue
                requestQueue.add(stringRequest);
















            }
        });















    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView service_name,service_amount;
        public Button del;

        public ViewHolder(View itemView) {

            super(itemView);
            service_name = (TextView) itemView.findViewById(R.id.title);
            service_amount=(TextView)itemView.findViewById(R.id.amount);
            del=(Button)itemView.findViewById(R.id.del);
        }
    }
}
