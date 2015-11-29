package com.wordpress.afzaalahmadzeeshan.flashlight_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.turn_on_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(getApplicationContext(), FlashlightIntentService.class);
                if (button.getText().toString().equals(getString(R.string.turn_on_string))) {
                    // Turn on the flashlight
                    serviceIntent.setAction(FlashlightIntentService.ACTION_TURN_ON);
                    startService(serviceIntent);

                    button.setText(R.string.turn_off_string);
                } else {
                    // Turn off the flashlight
                    serviceIntent.setAction(FlashlightIntentService.ACTION_TURN_OFF);
                    startService(serviceIntent);

                    button.setText(R.string.turn_on_string);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onPause() {
        if(FlashLightWidget.lightOn) {
            // Light is on, turn it off.
            Intent intent = new Intent(this, FlashlightIntentService.class);
            intent.setAction(FlashlightIntentService.ACTION_TURN_OFF);
            startService(intent);
        }
        super.onPause();
    }

    /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    } */
}
