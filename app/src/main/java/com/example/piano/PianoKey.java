package com.example.piano;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.WHITE;

public class PianoKey extends AppCompatActivity {

    int octave = 1;
    int SRC;
    int DST;
    int ID;
    String notes = "";
    String DATA;
    String APPUIE;
    int coordX;
    int coordY;
    int compteurTouches = 0;
    int not;
    int i=0;

    List<Note> listNotes;

    ConnectionBT bt = ConnectionBT.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano_key);

        listNotes = new ArrayList<>();

        final Button Do = (Button) findViewById(R.id.doo);
        final Button Re = (Button) findViewById(R.id.re);
        final Button Mi = (Button) findViewById(R.id.mi);
        final Button Fa = (Button) findViewById(R.id.fa);
        final Button Sol = (Button) findViewById(R.id.sol);
        final Button La = (Button) findViewById(R.id.la);
        final Button Si = (Button) findViewById(R.id.si);
        final Button Moins = (Button) findViewById(R.id.bouton_moins);
        final Button Plus = (Button) findViewById(R.id.bouton_plus);
        final Button DoD = (Button) findViewById(R.id.doD);
        final Button ReD = (Button) findViewById(R.id.reD);
        final Button FaD = (Button) findViewById(R.id.faD);
        final Button SolD = (Button) findViewById(R.id.solD);
        final Button LaD = (Button) findViewById(R.id.laD);
        final TextView nbreOctave = (TextView) findViewById(R.id.nbreOctave);
        final ImageView[] tabNote;


        nbreOctave.setText(Integer.toString(octave));

        coordX = 170;

        Moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (octave > 1)
                    octave--;
                nbreOctave.setText(Integer.toString(octave));

            }
        });

        Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (octave < 7)
                    octave++;
                nbreOctave.setText(Integer.toString(octave));


            }
        });

       Do.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               notes = "Do";
               not = 0 + +12 + 12 * octave;
               DATA = "" + not + "\r\n";


               if (bt == null) {
                   bt = ConnectionBT.getInstance();
               }

               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   Do.setBackgroundColor(GRAY);
                   compteurTouches++;
                   System.out.println("COMPTEUR " + compteurTouches);
                   bt.envoieData("A" + DATA);
                   System.out.println("A" + DATA);
                   listNotes.add(new Note());
                   System.out.println("DATAAAAAA"+DATA);


                   LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                   l.removeAllViews();
                   for (int i=0; i<listNotes.size();i++)
                   {
                       Note  n = listNotes.get(i);
                       l.addView(n.noteObjet);

                   }

               }

               else if (event.getAction()== MotionEvent.ACTION_UP)
               {
                   bt.envoieData("R"+DATA);
                   System.out.println("R" + DATA);
                   Do.setBackgroundColor(WHITE);

               }

               return false;
           }
       });

        Re.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "Ré";
                not = 2 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Re.setBackgroundColor(GRAY);
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);
                    Re.setBackgroundColor(WHITE);

                }

                return false;
            }
        });

        Mi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "Mi";
                not = 4 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Mi.setBackgroundColor(GRAY);
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);
                    Mi.setBackgroundColor(WHITE);

                }

                return false;
            }
        });

        Fa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "Fa";
                not = 5 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Fa.setBackgroundColor(GRAY);
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);
                    Fa.setBackgroundColor(WHITE);

                }

                return false;
            }
        });

        Sol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "Sol";
                not = 7 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Sol.setBackgroundColor(GRAY);
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);
                    Sol.setBackgroundColor(WHITE);

                }

                return false;
            }
        });

        La.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "La";
                not = 9 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    La.setBackgroundColor(GRAY);
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);
                    La.setBackgroundColor(WHITE);

                }

                return false;
            }
        });

        Si.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "Si";
                not = 11 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Si.setBackgroundColor(GRAY);
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);
                    Si.setBackgroundColor(WHITE);

                }

                return false;
            }
        });

        DoD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "DoD";
                not = 1 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);


                }

                return false;
            }
        });

        ReD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "ReD";
                not = 3 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);

                }

                return false;
            }
        });

        FaD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "FaD";
                not = 6 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);

                }

                return false;
            }
        });

        SolD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "SolD";
                not = 8 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);

                }

                return false;
            }
        });

        LaD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                notes = "LaD";
                not = 10 + 12 + 12 * octave;
                DATA = "" + not + "\r\n";

                if (bt == null) {
                    bt = ConnectionBT.getInstance();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    compteurTouches++;
                    System.out.println("COMPTEUR " + compteurTouches);
                    bt.envoieData("A" + DATA);
                    System.out.println("A" + DATA);
                    listNotes.add(new Note());

                    LinearLayout l = (LinearLayout) findViewById(R.id.partitionLayout);
                    l.removeAllViews();
                    for (int i=0; i<listNotes.size();i++)
                    {
                        Note  n = listNotes.get(i);
                        l.addView(n.noteObjet);

                    }
                }

                else if (event.getAction()== MotionEvent.ACTION_UP)
                {
                    bt.envoieData("R"+DATA);
                    System.out.println("R" + DATA);

                }

                return false;
            }
        });

    }


    public class Note{

        android.widget.ImageView noteObjet;



        public Note() {

            noteObjet = new ImageView(getApplicationContext());
            noteObjet.setImageResource(R.drawable.key1);


            if (compteurTouches > 8) {
                compteurTouches = 1;
                coordX = 170;
                coordY = 200;
                listNotes.clear();
            }

            switch (compteurTouches) {
                case 1:
                    coordX = 170;
                    break;

                case 2:
                    coordX = 274;
                    break;

                case 3:
                    coordX = 378;
                    break;

                case 4:
                    coordX = 482;
                    break;

                case 5:
                    coordX = 586;
                    break;

                case 6:
                    coordX = 690;
                    break;

                case 7:
                    coordX = 794;
                    break;

                case 8:
                    coordX = 898;
                    break;
            }

            switch (notes) {
                case "Do":

                    coordY = 255;
                    break;

                case "DoD":

                    coordY = 255;
                    break;

                case "Ré":
                    coordY = 250;
                    break;

                case "RéD":
                    coordY = 250;
                    break;

                case "Mi":
                    coordY = 240;
                    break;

                case "Fa":
                    coordY = 225;
                    break;

                case "FaD":
                    coordY = 225;
                    break;

                case "Sol":
                    coordY = 215;
                    break;

                case "SolD":
                    coordY = 215;
                    break;

                case "La":
                    coordY = 199;
                    break;

                case "LaD":
                    coordY = 199;
                    break;

                case "Si":
                    coordY = 190;
                    break;

            }

            noteObjet.setX(coordX);
            noteObjet.setY(coordY);
            noteObjet.setVisibility(View.VISIBLE);



            System.out.println("X " + coordX + " Y " + coordY + " COMPTEUR " + compteurTouches);
            System.out.println("GETX " + noteObjet.getX() + " GETY " + noteObjet.getY());

        }


    }

}