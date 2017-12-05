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


public class pubs extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener, LocationListener {
    SeekBar seekbar;
    SwitchCompat fumatori;
    SwitchCompat biliard;
    SwitchCompat darts;
    SwitchCompat karaoke;
    SwitchCompat concerte;
    SwitchCompat standup;
    SwitchCompat narghilea;
    protected LocationManager locationManager;

    int fumatori1 = 0;
    int biliard1 = 0;
    int darts1 = 0;
    int karaoke1 = 0;
    int concerte1 = 0;
    int standup1 = 0;
    int numarPersoane = 0;
    int narghilea1 = 0;
    static double latitude, longitude;
    String request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubs);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new pubs();
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
        biliard=(SwitchCompat) findViewById(R.id.biliard);
        darts = (SwitchCompat) findViewById(R.id.darts);
        karaoke=(SwitchCompat) findViewById(R.id.karaoke);
        concerte = (SwitchCompat) findViewById(R.id.concerte);
        standup=(SwitchCompat) findViewById(R.id.standup);
        narghilea=(SwitchCompat) findViewById(R.id.narghilea);

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
        if (biliard != null) {
            biliard.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(biliard.isChecked()){
                biliard1 = 1;
            }
        }
        if (darts != null) {
            darts.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(darts.isChecked()){
                darts1 = 1;
            }
        }
        if (karaoke != null) {
            karaoke.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(karaoke.isChecked()){
                karaoke1 = 1;
            }
        }
        if (concerte != null) {
            concerte.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(concerte.isChecked()){
                concerte1 = 1;
            }
        }
        if (standup != null) {
            standup.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(standup.isChecked()){
                standup1 = 1;
            }
        }
        if (narghilea != null) {
            narghilea.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
            if(narghilea.isChecked()){
                narghilea1 = 1;
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
            request= URLEncoder.encode("fumatori","UTF-8")+"="+URLEncoder.encode(String.valueOf(fumatori1),"UTF-8")+"&"+URLEncoder.encode("biliard","UTF-8")+"="+URLEncoder.encode(String.valueOf(biliard1),"UTF-8")
                    +"&"+URLEncoder.encode("darts","UTF-8")+"="+URLEncoder.encode(String.valueOf(darts1),"UTF-8")+"&"+URLEncoder.encode("karaoke","UTF-8")+"="+URLEncoder.encode(String.valueOf(karaoke1),"UTF-8")
                    +"&"+URLEncoder.encode("concerte","UTF-8")+"="+URLEncoder.encode(String.valueOf(concerte1),"UTF-8")+"&"+URLEncoder.encode("standup","UTF-8")+"="+URLEncoder.encode(String.valueOf(standup1),"UTF-8")+"&"+URLEncoder.encode("narghilea","UTF-8")+"="+URLEncoder.encode(String.valueOf(narghilea1),"UTF-8")
                    +"&"+URLEncoder.encode("numarPersoane","UTF-8")+"="+URLEncoder.encode(String.valueOf(numarPersoane),"UTF-8")+"&"+URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(latitude),"UTF-8")+"&"+URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(longitude),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("COORD", request);

    }


    public void onLogin(){

        String arg2 = "";
        String type="login";

        BackgroundWorkerPubs backgroundWorkerPubs = new BackgroundWorkerPubs(this);
        backgroundWorkerPubs.execute(type, arg2, request);

    }


}
