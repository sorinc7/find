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


public class spa extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener, LocationListener {
    SeekBar seekbar;
    SwitchCompat interioara;
    SwitchCompat exterioara;
    SwitchCompat relaxare;
    SwitchCompat celulita;
    SwitchCompat coafura;
    SwitchCompat manichiura;
    SwitchCompat pedichiura;
    SwitchCompat machiaj;
    SwitchCompat solar;
    protected LocationManager locationManager;

    int interioara1 = 0;
    int exterioara1 = 0;
    int relaxare1 = 0;
    int celulita1 = 0;
    int coafura1 = 0;
    int manichiura1 = 0;
    int numarPersoane = 0;
    int pedichiura1 = 0;
    int machiaj1 = 0;
    int solar1 = 0;
    static double latitude, longitude;
    String request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new spa();
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
        interioara = (SwitchCompat) findViewById(R.id.interioara);
        exterioara=(SwitchCompat) findViewById(R.id.exterioara);
        relaxare = (SwitchCompat) findViewById(R.id.relaxare);
        celulita=(SwitchCompat) findViewById(R.id.celulita);
        coafura = (SwitchCompat) findViewById(R.id.coafura);
        manichiura=(SwitchCompat) findViewById(R.id.manichiura);
        pedichiura=(SwitchCompat) findViewById(R.id.pedichiura);
        machiaj=(SwitchCompat) findViewById(R.id.machiaj);
        solar=(SwitchCompat) findViewById(R.id.solar);

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
        if (interioara != null) {
            interioara.setOnCheckedChangeListener(this);
            if(interioara.isChecked()){
                interioara1 = 1;
            }
        }
        if (exterioara != null) {
            exterioara.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(exterioara.isChecked()){
                exterioara1 = 1;
            }
        }
        if (relaxare != null) {
            relaxare.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(relaxare.isChecked()){
                relaxare1 = 1;
            }
        }
        if (celulita != null) {
            celulita.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(celulita.isChecked()){
                celulita1 = 1;
            }
        }
        if (coafura != null) {
            coafura.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(coafura.isChecked()){
                coafura1 = 1;
            }
        }
        if (manichiura != null) {
            manichiura.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(manichiura.isChecked()){
                manichiura1 = 1;
            }
        }
        if (pedichiura != null) {
            pedichiura.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(pedichiura.isChecked()){
                pedichiura1 = 1;
            }
        }
        if (machiaj != null) {
            machiaj.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(machiaj.isChecked()){
                machiaj1 = 1;
            }
        }
        if (solar != null) {
            solar.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(solar.isChecked()){
                solar1 = 1;
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
            request= URLEncoder.encode("interioara","UTF-8")+"="+URLEncoder.encode(String.valueOf(interioara1),"UTF-8")+"&"+URLEncoder.encode("exterioara","UTF-8")+"="+URLEncoder.encode(String.valueOf(exterioara1),"UTF-8")
                    +"&"+URLEncoder.encode("relaxare","UTF-8")+"="+URLEncoder.encode(String.valueOf(relaxare1),"UTF-8")+"&"+URLEncoder.encode("celulita","UTF-8")+"="+URLEncoder.encode(String.valueOf(celulita1),"UTF-8")
                    +"&"+URLEncoder.encode("coafura","UTF-8")+"="+URLEncoder.encode(String.valueOf(coafura1),"UTF-8")+"&"+URLEncoder.encode("manichiura","UTF-8")+"="+URLEncoder.encode(String.valueOf(manichiura1),"UTF-8")+"&"+URLEncoder.encode("pedichiura","UTF-8")+"="+URLEncoder.encode(String.valueOf(pedichiura1),"UTF-8")+"&"+URLEncoder.encode("machiaj","UTF-8")+"="+URLEncoder.encode(String.valueOf(machiaj1),"UTF-8")+"&"+URLEncoder.encode("solar","UTF-8")+"="+URLEncoder.encode(String.valueOf(solar1),"UTF-8")
                    +"&"+URLEncoder.encode("numarPersoane","UTF-8")+"="+URLEncoder.encode(String.valueOf(numarPersoane),"UTF-8")+"&"+URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(latitude),"UTF-8")+"&"+URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(longitude),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("COORD", request);

    }


    public void onLogin(){

        String arg2 = "";
        String type="login";

        BackgroundWorkerSpa backgroundWorkerSpa = new BackgroundWorkerSpa(this);
        backgroundWorkerSpa.execute(type, arg2, request);

    }


}
