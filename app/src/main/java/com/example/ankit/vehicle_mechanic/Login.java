package com.example.ankit.vehicle_mechanic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;



public class Login extends Activity {
    EditText edt_name,edt_password;
    Button btn_submit;
    TextView TextView1,TextView2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edt_name = (EditText)findViewById(R.id.edit1);
        edt_password=(EditText)findViewById(R.id.edit2);

        btn_submit = (Button)findViewById(R.id.btn1);

        TextView1=(TextView)findViewById(R.id.textview1);
        TextView2=(TextView)findViewById(R.id.textview2);

        TextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });

        TextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String email = edt_name.getText().toString();
                final  String password = edt_password.getText().toString();



                final ProgressDialog pdialog=new ProgressDialog(Login.this);
                pdialog.setMessage("Login...");
                pdialog.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.url)+"login.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //  Toast.makeText(getApplicationContext(), "Response"+response, Toast.LENGTH_LONG).show();
                                if (response.trim().equals("failure")) {


                                    pdialog.cancel();
                                    Toast.makeText(getApplicationContext(), "Emailid and Password was wrong..", Toast.LENGTH_LONG).show();



                                } else {
                                   SharedPreferences sharedpreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();


                                    editor.putString("email_id" , email);



                                    editor.commit();

                                    pdialog.cancel();
                                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);


                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("email", email);
                        map.put("password", password);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);











            }
        });




    }
}
