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

/**
 * Created by JUNED on 6/16/2016.
 */
public class My_Vehicle_Adeptor extends RecyclerView.Adapter<My_Vehicle_Adeptor.ViewHolder> {

    public Context context;

    List<Actors> getDataAdapter;


    public My_Vehicle_Adeptor(List<Actors> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_vehicle_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, final int position) {

        final Actors getDataAdapter1 =  getDataAdapter.get(position);











        Viewholder.brand.setText(getDataAdapter1.getBrand());
        Viewholder.model_no.setText(getDataAdapter1.getModel_no());
        Viewholder.reg_no.setText(getDataAdapter1.getReg_no());
        Viewholder.vehicle_name.setText(getDataAdapter1.getVehicle_name());
        Viewholder.usage_km.setText(getDataAdapter1.getKm());
        Viewholder.purchase_year.setText(getDataAdapter1.getPurchase_year());
        Viewholder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {













                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.225.37:8888/VehicleMechanic1/webservice/delete_vehicle.php",
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
                        params.put("vehicle_name",getDataAdapter1.getVehicle_name());


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

        public TextView brand,model_no,reg_no,vehicle_name,usage_km,purchase_year;
        public Button btn;

        public ViewHolder(View itemView) {

            super(itemView);

            brand = (TextView) itemView.findViewById(R.id.brand);
            model_no = (TextView) itemView.findViewById(R.id.model_no);
            reg_no = (TextView) itemView.findViewById(R.id.reg_no);
            vehicle_name = (TextView) itemView.findViewById(R.id.vehicle_name);
            usage_km = (TextView) itemView.findViewById(R.id.usage_km);
            purchase_year = (TextView) itemView.findViewById(R.id.purchase_year);
            btn=(Button)itemView.findViewById(R.id.btn);





        }
    }
}
