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


public class pizzerii extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener, LocationListener {
    SeekBar seekbar;
    SwitchCompat fumatori;
    SwitchCompat vegetariana;
    SwitchCompat paste;
    SwitchCompat alcool;
    SwitchCompat livrareDomiciliu;
    protected LocationManager locationManager;

    int fumatori1 = 0;
    int vegetariana1 = 0;
    int paste1 = 0;
    int alcool1 = 0;
    int livrareDomiciliu1 = 0;
    int numarPersoane = 0;
    static double latitude, longitude;
    String request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzerii);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new pizzerii();
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
        vegetariana=(SwitchCompat) findViewById(R.id.vegetariana);
        paste = (SwitchCompat) findViewById(R.id.paste);
        alcool=(SwitchCompat) findViewById(R.id.alcool);
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
        if (vegetariana != null) {
            vegetariana.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(vegetariana.isChecked()){
                vegetariana1 = 1;
            }
        }
        if (paste != null) {
            paste.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(paste.isChecked()){
                paste1 = 1;
            }
        }
        if (alcool != null) {
            alcool.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(alcool.isChecked()){
                alcool1 = 1;
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
            request= URLEncoder.encode("fumatori","UTF-8")+"="+URLEncoder.encode(String.valueOf(fumatori1),"UTF-8")+"&"+URLEncoder.encode("vegetariana","UTF-8")+"="+URLEncoder.encode(String.valueOf(vegetariana1),"UTF-8")
                    +"&"+URLEncoder.encode("paste","UTF-8")+"="+URLEncoder.encode(String.valueOf(paste1),"UTF-8")+"&"+URLEncoder.encode("alcool","UTF-8")+"="+URLEncoder.encode(String.valueOf(alcool1),"UTF-8")
                    +"&"+URLEncoder.encode("livrareDomiciliu","UTF-8")+"="+URLEncoder.encode(String.valueOf(livrareDomiciliu1),"UTF-8")+"&"+URLEncoder.encode("numarPersoane","UTF-8")+"="+URLEncoder.encode(String.valueOf(numarPersoane),"UTF-8")+"&"+URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(latitude),"UTF-8")+"&"+URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(longitude),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("COORD", request);

    }


    public void onLogin(){

        String arg2 = "";
        String type="login";

        BackgroundWorkerPizza backgroundWorkerPizza = new BackgroundWorkerPizza(this);
        backgroundWorkerPizza.execute(type, arg2, request);

    }


}
