package sorindev.find;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class restaurante extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener, LocationListener {
    SeekBar seekbar;
    SwitchCompat fumatori;
    SwitchCompat romaneasca;
    SwitchCompat italiana;
    SwitchCompat chinezeasca;
    SwitchCompat livrareDomiciliu;
    protected LocationManager locationManager;

    int fumatori1 = 0;
    int romaneasca1 = 0;
    int italiana1 = 0;
    int chinezeasca1 = 0;
    int livrareDomiciliu1 = 0;
    int numarPersoane = 0;
    static double latitude, longitude;
    String request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurante);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new restaurante();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details
        }
        // ATRIBUIREA ELEMENTELOR INCEPE AICI
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        fumatori = (SwitchCompat) findViewById(R.id.fumatori);
        romaneasca=(SwitchCompat) findViewById(R.id.traditionalaRomaneasca);
        italiana = (SwitchCompat) findViewById(R.id.traditionalaItaliana);
        chinezeasca=(SwitchCompat) findViewById(R.id.traditionalaChinezeasca);
        livrareDomiciliu=(SwitchCompat) findViewById(R.id.livrareDomiciliu);


        //ATRIBUIREA ELEMENTELOR SE TERMINA AICI



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
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    public void createRequest(){
        // here we create the request for later usage

        try {
            request= URLEncoder.encode("fumatori","UTF-8")+"="+URLEncoder.encode(String.valueOf(fumatori1),"UTF-8")+"&"+URLEncoder.encode("romaneasca","UTF-8")+"="+URLEncoder.encode(String.valueOf(romaneasca1),"UTF-8")
            +"&"+URLEncoder.encode("italiana","UTF-8")+"="+URLEncoder.encode(String.valueOf(italiana1),"UTF-8")+"&"+URLEncoder.encode("chinezeasca","UTF-8")+"="+URLEncoder.encode(String.valueOf(chinezeasca1),"UTF-8")
            +"&"+URLEncoder.encode("livrareDomiciliu","UTF-8")+"="+URLEncoder.encode(String.valueOf(livrareDomiciliu1),"UTF-8")+"&"+URLEncoder.encode("numarPersoane","UTF-8")+"="+URLEncoder.encode(String.valueOf(numarPersoane),"UTF-8")+"&"+URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(latitude),"UTF-8")+"&"+URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(longitude),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("COORD", request);

    }


    public void onLogin(){

        String arg2 = "";
        String type="login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, arg2, request);

    }

}
