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
        import android.widget.TextView;
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

public class Register extends AppCompatActivity {


    EditText UserName, Password, C_Password , Email, Address, Phone;
    Button btn;
    ProgressDialog pdialog;
    private EditText B_Date;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog fromDatePickerDialog;

    TextView TextView1,TextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        UserName = (EditText) findViewById(R.id.edit1);
        Email = (EditText) findViewById(R.id.edit2);
        Password = (EditText) findViewById(R.id.edit3);
        C_Password = (EditText) findViewById(R.id.edit4);
        Phone = (EditText) findViewById(R.id.edit5);
        Address = (EditText) findViewById(R.id.edit6);
        btn = (Button) findViewById(R.id.btn1);
        TextView1=(TextView)findViewById(R.id.textview1);
        TextView2=(TextView)findViewById(R.id.textview2);

        TextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String user_name = UserName.getText().toString();
                final String c_password = C_Password.getText().toString();
                final String phone = Phone.getText().toString();
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                final String address = Address.getText().toString();




                if (!isValidUserName(user_name)) {
                    UserName.setError("Invalid FirstName");
                }
                else if (!isValidEmail(email)) {
                    Email.setError("Invalid email id");
                }

                else if (!isValidPassword(password)) {
                    Password.setError("Invalid Password");
                }
                else if (!isValidCPassword(c_password,password)){
                    C_Password.setError("Password Does not match !!! ");
                }
                else if (!isValidNumber(phone)) {
                    Phone.setError("Invalid Mobile Number");
                } else if (!isValidAddress(address)) {
                    Address.setError("Invalid Address");
                }  else {

                    pdialog=new ProgressDialog(Register.this);
                    pdialog.setMessage("Login...");
                    pdialog.show();
                    //  Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,getString(R.string.url)+"registation.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                     Toast.makeText(Register.this,response,Toast.LENGTH_LONG).show();

                                    pdialog.cancel();
                                    Intent i = new Intent(getApplicationContext(),Login.class);
                                    startActivity(i);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();
                                }


                            }){

                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("user_name",user_name);
                            params.put("email",email);
                            params.put("password",password);
                            params.put("number",phone);
                            params.put("address",address);
                            return params;
                        }

                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            0,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestQueue requestQueue = Volley.newRequestQueue(Register.this);

                    //Adding request to the queue
                    requestQueue.add(stringRequest);

                }




            }
            // validating Name

            private boolean isValidUserName(String name) {
                if (name != null && name.length() > 3) {
                    return true;
                }
                return false;
            }



            // validating Password
            private boolean isValidPassword(String password) {
                if (password != null && password.length() >= 7) {
                    return true;
                }
                return false;
            }

            private boolean isValidCPassword(String c_password, String password){
                if (c_password.equals(password)) {
                    return true;
                }
                return false;

            }

            // validating email id
            private boolean isValidEmail(String email) {
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }


            // validating Number
            private boolean isValidNumber(String number) {
                if (number != null && number.length() == 10) {
                    return true;
                }
                return false;
            }


            // validating Address
            private boolean isValidAddress(String address) {
                if (address != null && address.length() > 3) {
                    return true;
                }
                return false;
            }

            // validating city name
            private boolean isValidCity(String city) {
                if (city != null && city.length() > 3) {
                    return true;
                }
                return false;
            }

            // validating State name
            private boolean isValidState(String state) {
                if (state != null && state.length() > 3) {
                    return true;
                }
                return false;
            }

            // validating Country name
            private boolean isValidCountry(String country) {
                if (country != null && country.length() > 3) {
                    return true;
                }
                return false;
            }

            // validating postal Number
            private boolean isValidPin_code(String pin_code) {
                if (pin_code != null && pin_code.length() == 6) {
                    return true;
                }
                return false;
            }



        });






    }








}



