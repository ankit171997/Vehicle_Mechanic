package com.example.ankit.vehicle_mechanic;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddVehicle extends AppCompatActivity {


    EditText Brand, Model_No, Name , Purchase_Year , Usage_Km, Reg_No;
    Button btn;
    ProgressDialog pdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);


        SharedPreferences sharedpreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        final String user_email = sharedpreferences.getString("email_id", "");

        Brand = (EditText) findViewById(R.id.edit1);
        Model_No = (EditText) findViewById(R.id.edit2);
        Name = (EditText) findViewById(R.id.edit3);
        Purchase_Year = (EditText) findViewById(R.id.edit4);
        Usage_Km = (EditText) findViewById(R.id.edit5);
        Reg_No = (EditText) findViewById(R.id.edit);


        btn = (Button) findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String brand = Brand.getText().toString();
                final String model_no = Model_No.getText().toString();
                final String name = Name.getText().toString();
                final String purchase_year = Purchase_Year.getText().toString();
                final String usage_km = Usage_Km.getText().toString();
                final String reg_no = Reg_No.getText().toString();



                pdialog=new ProgressDialog(AddVehicle.this);
                pdialog.setMessage("Adding...");
                pdialog.show();
                //  Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,getString(R.string.url)+"addvehicle.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(AddVehicle.this,response,Toast.LENGTH_LONG).show();


                                pdialog.cancel();
                                Intent i = new Intent(getApplicationContext(),MyVehicles.class);
                                startActivity(i);


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(AddVehicle.this,error.toString(),Toast.LENGTH_LONG).show();
                            }


                        }){

                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("brand",brand);
                        params.put("name",name);
                        params.put("model_no",model_no);
                        params.put("reg_no",reg_no);
                        params.put("purchase_year",purchase_year);
                        params.put("usage_km",usage_km);
                        params.put("user_email",user_email);

                        return params;
                    }

                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue requestQueue = Volley.newRequestQueue(AddVehicle.this);

                //Adding request to the queue
                requestQueue.add(stringRequest);

            }

        });

    }


}



