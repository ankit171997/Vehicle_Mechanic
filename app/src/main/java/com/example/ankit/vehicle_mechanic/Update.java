package com.example.ankit.vehicle_mechanic;


        import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;

/**
 * Created by ankit on 25/01/18.
 */

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;
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
 * Created by swapnil on 7/5/2017.
 */

public class Update extends Activity {

    // private static final String RATING ="http://qrworld.kstechnologies.co/webservice.asmx/Filters";
    ProgressDialog pdialog;
    RequestQueue requestQueue;
    List<Actors> GetDataAdapter1;

    Integer qtySum = 0;
    int qtyNum;
    RecyclerView recyclerView;
    TextView textView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        textView=(TextView)findViewById(R.id.textView2);
        SharedPreferences sharedpreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        final String user_email = sharedpreferences.getString("email_id", "");
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.recycle2);



        GetDataAdapter1 = new ArrayList<>();

        pdialog= new ProgressDialog(Update.this);
        pdialog.setMessage("Loading...");
        pdialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.url)+"display_progress.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //    Toast.makeText(View_Cart.this, "Welcome to QRWORLD"+response, Toast.LENGTH_LONG).show();
                        try{

                            JSONObject obj = new JSONObject(response);
                            final JSONArray user1 = obj.getJSONArray("data");
                            for (int j = 0; j < user1.length(); j++) {


                                Actors GetDataAdapter2 = new Actors();
                                JSONObject c = user1.getJSONObject(j);
                                GetDataAdapter2.setService_name(c.getString("service_name"));
                                GetDataAdapter2.setCompleted(c.getString("Completed"));
                                GetDataAdapter2.setVehicle_name(c.getString("vehicle_name"));
                                GetDataAdapter2.setGarage_id(c.getString("estimated_time"));


                                // Toast.makeText(CityHotelList.this, "imsge"+c.getString("img1"), Toast.LENGTH_LONG).show();
                                GetDataAdapter1.add(GetDataAdapter2);

                                recyclerViewadapter = new Update_Adeptor(GetDataAdapter1, getApplicationContext());
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
