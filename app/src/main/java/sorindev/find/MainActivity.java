package sorindev.find;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
//                new AlertDialog.Builder(this)
//                        .setTitle("Acces locatie")
//                        .setMessage("Aplicatia are nevoie de locatia dvs. pentru a afisa rezultate relevante.")
//                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                //Prompt the user once explanation has been shown
//                                ActivityCompat.requestPermissions((Activity) getApplicationContext(),
//                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                                        MY_PERMISSIONS_REQUEST_LOCATION);
//                            }
//                        })
//                        .create()
//                        .show();

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

        //CEREREA PERMISIUNII DE LOCATIE SE TERMINA AICI


        ImageView restaurante = (ImageView)findViewById(R.id.restaurantImg);
        restaurante.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Thread myThread = new Thread(){
                            @Override
                            public void run() {
                                    Intent intent = new Intent(getApplicationContext(),restaurante.class);
                                    startActivity(intent);

                            }
                        };
                        myThread.start();
                    }
                }
        );
        ImageView pizzerii = (ImageView)findViewById(R.id.pizzaImg);
        pizzerii.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Thread myThread = new Thread(){
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(),pizzerii.class);
                                startActivity(intent);

                            }
                        };
                        myThread.start();
                    }
                }
        );
        ImageView fastfood = (ImageView)findViewById(R.id.fastfoodImg);
        fastfood.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Thread myThread = new Thread(){
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(),fastfood.class);
                                startActivity(intent);

                            }
                        };
                        myThread.start();
                    }
                }
        );

        ImageView baruri = (ImageView)findViewById(R.id.baruriImg);
        baruri.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Thread myThread = new Thread(){
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(),baruri.class);
                                startActivity(intent);

                            }
                        };
                        myThread.start();
                    }
                }
        );
        ImageView pubs = (ImageView)findViewById(R.id.pubImg);
        pubs.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Thread myThread = new Thread(){
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(),pubs.class);
                                startActivity(intent);

                            }
                        };
                        myThread.start();
                    }
                }
        );
        ImageView spa = (ImageView)findViewById(R.id.spaImg);
        spa.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Thread myThread = new Thread(){
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(),spa.class);
                                startActivity(intent);

                            }
                        };
                        myThread.start();
                    }
                }
        );
    }
}
