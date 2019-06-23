package com.example.piano;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.io.IOException;
import java.util.UUID;

import static android.webkit.WebSettings.PluginState.ON;

public class Menu extends AppCompatActivity {



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button bpENter = (Button) findViewById(R.id.bpEnter);
        final Intent intent = new Intent(this, BT_Activity.class);
      //  final Intent intent = new Intent(this, BT_Fragrement.class);
        final ImageView fleche1 = (ImageView) findViewById(R.id.imageView7);
        final ImageView fleche2 = (ImageView) findViewById(R.id.imageView8);

        Animation animation = new AlphaAnimation((float) 1, 0);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        fleche1.startAnimation(animation);
        fleche2.startAnimation(animation);

        bpENter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
                System.out.println("COMMENCER A JOUER");

            }
        });

    }

}

