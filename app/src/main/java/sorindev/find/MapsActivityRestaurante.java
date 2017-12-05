package sorindev.find;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MapsActivityRestaurante extends FragmentActivity implements OnMapReadyCallback {
    //cod perntru lista
    private ArrayAdapter<String> adaptNume;
    private ArrayAdapter<String> adaptMeniu;
    private ArrayAdapter<Integer> adaptmeniulZileiA;
    private ArrayAdapter<String> adaptMeniulZilei;
    private ArrayAdapter<String> adaptDateContact;
    private ArrayAdapter<Integer> adaptNonStop;
    private ArrayAdapter<Float> adaptLat;
    private ArrayAdapter<Float> adaptLong;
    ListView listView;

    Gson gson = new Gson();
    Address[] address = gson.fromJson(BackgroundWorker.result, Address[].class);
    //cod pentru harta

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_restaurante);
        //cod pentru lista
        listView = (ListView)findViewById(R.id.listv);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(new LatLng(-34, 151)).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));

        String message1="";
        int x=0;
        ArrayList<String> arrayNume = new ArrayList<>();
        ArrayList<String> arrayMeniu = new ArrayList<>();
        ArrayList<Integer> arrayMeniulZileiA = new ArrayList<>();
        ArrayList<String> arrayMeniulZilei = new ArrayList<>();
        ArrayList<String> arrayDateContact = new ArrayList<>();
        ArrayList<Integer> arrayNonStop = new ArrayList<>();
        ArrayList<Float> arrayLat = new ArrayList<>();
        ArrayList<Float> arrayLong = new ArrayList<>();
        ArrayList<Integer> arrayStatus = new ArrayList<>();
        adaptNume = new ArrayAdapter<String>(this, R.layout.list_item,R.id.numeitem, arrayNume);
        listView.setAdapter(adaptNume);
        if(address!=null)
            x=address.length;
        for(int i=0; i<x; i++){
            arrayNume.add(address[i].nume);
            arrayMeniu.add(address[i].meniu);
            arrayMeniulZileiA.add(address[i].meniul_zileiA);
            arrayMeniulZilei.add(address[i].meniul_zilei);
            arrayDateContact.add(address[i].date_contact);
            arrayNonStop.add(address[i].non_stop);
            arrayLat.add(address[i].latit);
            arrayLong.add(address[i].longit);
            arrayStatus.add(address[i].status);
            adaptNume.notifyDataSetChanged();
        }

        AlertDialog alertDialog = new AlertDialog.Builder(MapsActivityRestaurante.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message1);
        alertDialog.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            BackgroundWorker.result="";
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
