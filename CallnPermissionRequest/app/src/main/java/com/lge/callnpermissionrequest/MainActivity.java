package com.lge.callnpermissionrequest;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.telephony.PhoneStateListener;

import java.lang.reflect.InvocationTargetException;


public class MainActivity extends AppCompatActivity {


    static final Integer CALL = 0x1;

    static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lge.callnpermissionrequest.R.layout.activity_main);

        Button button = (Button)findViewById(com.lge.callnpermissionrequest.R.id.turnon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    askForPermission(Manifest.permission.CALL_PHONE,CALL);
                }else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "9059057059"));
                    startActivity(callIntent);
                }


            }
        });//Button onClick Ends Here


        TelephonyManager telephonyManager =
                (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        telephonyManager.listen(callStateListener,PhoneStateListener.LISTEN_CALL_STATE);

    }  // OnCreate ENDS HERE




//CHAKRADHAR CODE [START HERE]=======================================================================================================================



    //TELEPHONY CALL STATES HANDLED HERE [START ]============================================================



    PhoneStateListener callStateListener = new PhoneStateListener() {
        public void onCallStateChanged(int state, String incomingNumber)
        {
            if(state==TelephonyManager.CALL_STATE_RINGING){
                Toast.makeText(getApplicationContext(),"Phone Is Riging",
                        Toast.LENGTH_LONG).show();
                TelephonyManager telephonyManager =
                        (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    telephonyManager.getClass().getMethod("answerRingingCall").invoke(telephonyManager);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            if(state==TelephonyManager.CALL_STATE_OFFHOOK){
                Toast.makeText(getApplicationContext(),"Phone is Currently in A call",
                        Toast.LENGTH_LONG).show();
            }

            if(state==TelephonyManager.CALL_STATE_IDLE){
                Toast.makeText(getApplicationContext(),"phone is neither ringing nor in a call",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };



    //TELEPHONY CALL STATES HANDLED HERE [END ]============================================================

    //Code for askingPermissions and Callback After Requesting Permission [Start Here]============================================================
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
//            switch (requestCode) {
//
//                case 1:
//                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    callIntent.setData(Uri.parse("tel:" + "9059057059"));
////                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        startActivity(callIntent);
////                    }
//                    break;
//            }

            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }
    //Code for askingPermissions and Callback After Requesting Permission [END Here]============================================================



//CHAKRADHAR CODE [END HERE]=======================================================================================================================

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}