package sorindev.find;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.support.v7.appcompat.R.styleable.CompoundButton;


public class restaurante extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener {
    SeekBar seekbar;
    SwitchCompat fumatori;
    SwitchCompat romaneasca;
    SwitchCompat italiana;
    SwitchCompat chinezeasca;
    SwitchCompat livrareDomiciliu;
    int fumatori1 = 0;
    int romaneasca1 = 0;
    int italiana1 = 0;
    int chinezeasca1 = 0;
    int livrareDomiciliu1 = 0;
    int numarPersoane = 0;
    String request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurante);
        // ATRIBUIREA ELEMENTELOR INCEPE AICI

        fumatori = (SwitchCompat) findViewById(R.id.fumatori);
        romaneasca=(SwitchCompat) findViewById(R.id.traditionalaRomaneasca);
        italiana = (SwitchCompat) findViewById(R.id.traditionalaItaliana);
        chinezeasca=(SwitchCompat) findViewById(R.id.traditionalaChinezeasca);
        livrareDomiciliu=(SwitchCompat) findViewById(R.id.livrareDomiciliu);


        //ATRIBUIREA ELEMENTELOR SE TERMINA AICI

        //CEREREA PERMISIUNII DE LOCATIE INCEPE AICI

        int permissionCheck1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
        if (permissionCheck1
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Acces locatie")
                        .setMessage("Aplicatia are nevoie de locatia dvs. pentru a afisa rezultate relevante.")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions((Activity) getApplicationContext(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }


        }
//
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
//        locationManager.getLastKnownLocation();







        //CEREREA PERMISIUNII DE LOCATIE SE TERMINA AICI

        final TextView textView = (TextView)findViewById(R.id.count);
        seekbar = (SeekBar)findViewById(R.id.line3);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                textView.setText(String.valueOf(progress));
                numarPersoane = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ImageView act = (ImageView)findViewById(R.id.lupa);
        act.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getData();
                        createRequest();
                        onLogin();
                    }
                }
        );
    }
    public void getData(){
        if (fumatori != null) {
            fumatori.setOnCheckedChangeListener(this);
            if(fumatori.isChecked()){
                fumatori1 = 1;
            }
        }
        if (romaneasca != null) {
            romaneasca.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(romaneasca.isChecked()){
                romaneasca1 = 1;
            }
        }
        if (italiana != null) {
            italiana.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(italiana.isChecked()){
                italiana1 = 1;
            }
        }
        if (chinezeasca != null) {
            chinezeasca.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(chinezeasca.isChecked()){
                chinezeasca1 = 1;
            }
        }
        if (livrareDomiciliu != null) {
            livrareDomiciliu.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(livrareDomiciliu.isChecked()){
                livrareDomiciliu1 = 1;
            }
        }
    }

    public void createRequest(){
        // here we create the request for later usage

        try {
            request= URLEncoder.encode("fumatori","UTF-8")+"="+URLEncoder.encode(String.valueOf(fumatori1),"UTF-8")+"&"+URLEncoder.encode("romaneasca","UTF-8")+"="+URLEncoder.encode(String.valueOf(romaneasca1),"UTF-8")
            +"&"+URLEncoder.encode("italiana","UTF-8")+"="+URLEncoder.encode(String.valueOf(italiana1),"UTF-8")+"&"+URLEncoder.encode("chinezeasca","UTF-8")+"="+URLEncoder.encode(String.valueOf(chinezeasca1),"UTF-8")
            +"&"+URLEncoder.encode("livrareDomiciliu","UTF-8")+"="+URLEncoder.encode(String.valueOf(livrareDomiciliu1),"UTF-8")+"&"+URLEncoder.encode("numarPersoane","UTF-8")+"="+URLEncoder.encode(String.valueOf(numarPersoane),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public void onLogin(){

        String arg2 = "";
        String type="login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, arg2, request);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
