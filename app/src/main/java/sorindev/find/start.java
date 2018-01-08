package sorindev.find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ImageView acasa = (ImageView)findViewById(R.id.acasa);
        acasa.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Thread myThread = new Thread(){
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);

                            }
                        };
                        myThread.start();
                    }
                }
        );
    }
}
