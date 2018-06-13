package com.example.ankit.vehicle_mechanic;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class SendFeedback extends AppCompatActivity {


    EditText FeedbackTitle, To, Description ;
    Button btn;
    ProgressDialog pdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_feedback);

        FeedbackTitle = (EditText) findViewById(R.id.edit1);
        To = (EditText) findViewById(R.id.edit2);
        Description = (EditText) findViewById(R.id.edit3);

        btn = (Button) findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String feedback_title = FeedbackTitle.getText().toString();
                final String to = To.getText().toString();
                final String description = Description.getText().toString();


                    pdialog=new ProgressDialog(SendFeedback.this);
                    pdialog.setMessage("Sending...");
                    pdialog.show();
                    //  Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,getString(R.string.url)+"sendfeedback.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(SendFeedback.this,response,Toast.LENGTH_LONG).show();


                                        pdialog.cancel();
                                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(i);




                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(SendFeedback.this,error.toString(),Toast.LENGTH_LONG).show();
                                }


                            }){

                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("title",feedback_title);
                            params.put("to",to);
                            params.put("description",description);
                            return params;
                        }

                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            0,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestQueue requestQueue = Volley.newRequestQueue(SendFeedback.this);

                    //Adding request to the queue
                    requestQueue.add(stringRequest);

            }

        });

    }


}



