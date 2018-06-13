package com.example.ankit.vehicle_mechanic;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Priyanka Wagh on 26-01-2017.
 */
public class Splash_screen extends Activity {
    Intent i;

    public static final String MyPREFERENCES = "MyPrefs" ;


    //Permision code that will be checked in the method onRequestPermissionsResult
    private int STORAGE_PERMISSION_CODE = 23;
    public static final String KEY_EMAIL = "email_id";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //  if(isReadStorageAllowed()){
        //If permission is already having then showing the toast
        //  Toast.makeText(Splashscreen.this,"You already have the permission", Toast.LENGTH_LONG).show();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

//                        Intent intent = new Intent(getApplicationContext(),Login.class);
//
//                        startActivity(intent);


                    sharedpreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                    if (sharedpreferences.contains("email_id")) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                        startActivity(intent);
                    }
                    else{
                        Intent intent1 = new Intent(getApplicationContext(),Login.class);

                        startActivity(intent1);
                    }
                }
            }
        };
        timerThread.start();

        //Existing the method with return
        return;
    }

    //If the app has not the permission then asking for the permission
    //  requestStoragePermission();






    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

//    //We are calling this method to check the permission status
//    private boolean isReadStorageAllowed() {
//        //Getting the permission status
//
//        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        //If permission is granted returning true
//        if (result == PackageManager.PERMISSION_GRANTED)
//            return true;
//
//        //If permission is not granted returning false
//        return false;
//    }
//
//    //Requesting permission
//    private void requestStoragePermission(){
//
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
//            //If the user has denied the permission previously your code will come to this block
//            //Here you can explain why you need this permission
//            //Explain here why you need this permission
//        }
//
//        //And finally ask for the permission
//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
//    }
//
//    //This method will be called when the user will tap on allow or deny
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        //Checking the request code of our request
//        if(requestCode == STORAGE_PERMISSION_CODE){
//
//            //If permission is granted
//            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//                //Displaying a toast
//                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
//            }else{
//                //Displaying another toast if permission is not granted
//                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
//            }
//        }
//    }
}

