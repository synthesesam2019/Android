package com.example.piano;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button Do = (Button) findViewById(R.id.doo);
        final Button Re = (Button) findViewById(R.id.re);
        final Button Mi = (Button) findViewById(R.id.mi);
        final Button Fa = (Button) findViewById(R.id.fa);
        final Button Sol = (Button) findViewById(R.id.sol);
        final Button La = (Button) findViewById(R.id.la);
        final Button Si = (Button) findViewById(R.id.si);


        Do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Do.setBackgroundColor(Color.GRAY);

            }
        });

        Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Mi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        La.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
