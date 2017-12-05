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


public class fastfood extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener, LocationListener {
    SeekBar seekbar;
    SwitchCompat vegetarian;
    SwitchCompat racoritoare;
    SwitchCompat cafea;
    SwitchCompat servireMasa;
    protected LocationManager locationManager;

    int vegetarian1 = 0;
    int racoritoare1 = 0;
    int cafea1 = 0;
    int servireMasa1 = 0;
    int numarPersoane = 0;
    static double latitude, longitude;
    String request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastfood);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new fastfood();
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
        vegetarian=(SwitchCompat) findViewById(R.id.vegetarian);
        racoritoare = (SwitchCompat) findViewById(R.id.racoritoare);
        cafea=(SwitchCompat) findViewById(R.id.cafea);
        servireMasa=(SwitchCompat) findViewById(R.id.servireMasa);


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

        if (vegetarian != null) {
            vegetarian.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(vegetarian.isChecked()){
                vegetarian1 = 1;
            }
        }
        if (racoritoare != null) {
            racoritoare.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(racoritoare.isChecked()){
                racoritoare1 = 1;
            }
        }
        if (cafea != null) {
            cafea.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(cafea.isChecked()){
                cafea1 = 1;
            }
        }
        if (servireMasa != null) {
            servireMasa.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(servireMasa.isChecked()){
                servireMasa1 = 1;
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
            request= URLEncoder.encode("vegetarian","UTF-8")+"="+URLEncoder.encode(String.valueOf(vegetarian1),"UTF-8")
                    +"&"+URLEncoder.encode("racoritoare","UTF-8")+"="+URLEncoder.encode(String.valueOf(racoritoare1),"UTF-8")+"&"+URLEncoder.encode("cafea","UTF-8")+"="+URLEncoder.encode(String.valueOf(cafea1),"UTF-8")
                    +"&"+URLEncoder.encode("servireMasa","UTF-8")+"="+URLEncoder.encode(String.valueOf(servireMasa1),"UTF-8")+"&"+URLEncoder.encode("numarPersoane","UTF-8")+"="+URLEncoder.encode(String.valueOf(numarPersoane),"UTF-8")+"&"+URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(latitude),"UTF-8")+"&"+URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(longitude),"UTF-8");
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
