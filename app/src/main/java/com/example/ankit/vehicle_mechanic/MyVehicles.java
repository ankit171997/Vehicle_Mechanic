package com.example.ankit.vehicle_mechanic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ankit on 25/01/18.
 */

public class MyVehicles extends AppCompatActivity {

    Button button1;
    ProgressDialog pdialog;
    RequestQueue requestQueue;
    List<Actors> GetDataAdapter1;
    RecyclerView.Adapter recyclerViewadapter;


    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_vehicles);

        SharedPreferences sharedpreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        final String user_email = sharedpreferences.getString("email_id", "");


        button1=(Button)findViewById(R.id.btn1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyVehicles.this,AddVehicle.class);
                startActivity(i);
            }
        });


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.recycle);


        GetDataAdapter1 = new ArrayList<>();

        pdialog= new ProgressDialog(MyVehicles.this);
        pdialog.setMessage("Loading...");
        pdialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.url)+"display_vehicle.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   Toast.makeText(CityHotelList.this, "Welcome to QRWORLD"+response, Toast.LENGTH_LONG).show();
                        try{

                            JSONObject obj = new JSONObject(response);
                            final JSONArray user1 = obj.getJSONArray("data");
                            for (int j = 0; j < user1.length(); j++) {


                                Actors GetDataAdapter2 = new Actors();
                                JSONObject c = user1.getJSONObject(j);
                                GetDataAdapter2.setBrand(c.getString("brand"));
                                GetDataAdapter2.setModel_no(c.getString("model_no"));
                                GetDataAdapter2.setReg_no(c.getString("reg_no"));
                                GetDataAdapter2.setKm(c.getString("usage_km"));
                                GetDataAdapter2.setPurchase_year(c.getString("purchase_year"));
                                GetDataAdapter2.setVehicle_name(c.getString("vehicle_name"));



                                // Toast.makeText(CityHotelList.this, "imsge"+c.getString("img1"), Toast.LENGTH_LONG).show();
                                GetDataAdapter1.add(GetDataAdapter2);


                                recyclerViewadapter = new My_Vehicle_Adeptor(GetDataAdapter1, getApplicationContext());
                                recyclerView.setAdapter(recyclerViewadapter);

                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            }

                        }catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();

                        }


                        pdialog.cancel();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();


                map.put("user_email", user_email);
                return map;
            }

        };

        requestQueue.add(stringRequest);


    }




}
