package sorindev.find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView fastfood = (ImageView)findViewById(R.id.restaurantImg);
        fastfood.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Thread myThread = new Thread(){
                            @Override
                            public void run() {
                                    Intent intent = new Intent(getApplicationContext(),restaurante.class);
                                    startActivity(intent);
                                    finish();
                            }
                        };
                        myThread.start();
                    }
                }
        );
    }
}
