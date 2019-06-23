package com.example.piano;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BT_Activity extends AppCompatActivity {

    // GUI Components
    private TextView mBluetoothStatus;
    private TextView mReadBuffer;
    private Button mOnBtn;
    private Button mOffBtn;
    private Button mListPairedDevicesBtn;
    private Button mDiscoverBtn;
    private Button send;
    private EditText message;
    private BluetoothAdapter mBTAdapter;
    private Set<BluetoothDevice> mPairedDevices;
    private ArrayAdapter<String> mBTArrayAdapter;
    private ListView mDevicesListView;

    private Handler maj = new Handler();
    // private testbt serviceBT = serviceBT = testbt.get();;
    private ConnectionBT serviceBT = serviceBT = ConnectionBT.getInstance();
    // #defines for identifying shared types between calling functions
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    private final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_);


        final Button start = (Button) findViewById(R.id.goActivity);
        final Intent intent = new Intent(this, PianoKey.class);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
                //  Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
      //  View view = inflater.inflate(R.layout.fragment_bt__fragrement, container, false);
      //  send = (Button) findViewById(R.id.send);
        message = (EditText) findViewById(R.id.message);

        mBluetoothStatus = (TextView) findViewById(R.id.bluetoothStatus);
        mReadBuffer = (TextView) findViewById(R.id.readBuffer);
        mOnBtn = (Button) findViewById(R.id.on);
        mOffBtn = (Button) findViewById(R.id.off);
        mDiscoverBtn = (Button) findViewById(R.id.discover);
        mListPairedDevicesBtn = (Button) findViewById(R.id.PairedBtn);

        if (savedInstanceState==null) {
            mBTArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        }
        else
        {
            ArrayList<String> itemsList = new ArrayList<String> ( ) ;
            itemsList = savedInstanceState.getStringArrayList("list");
            mBTArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, itemsList);
        }
        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio
        mDevicesListView = (ListView) findViewById(R.id.devicesListView);
        mDevicesListView.setAdapter(mBTArrayAdapter); // assign model to view
        mDevicesListView.setOnItemClickListener(mDeviceClickListener);



        if (mBTArrayAdapter == null) {
            // Device does not support Bluetooth
            mBluetoothStatus.setText("Status: Bluetooth not found");
            Toast.makeText(getApplicationContext(), "Bluetooth device not found!", Toast.LENGTH_SHORT).show();
        } else {
/*
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serviceBT.envoieData(message.getText().toString());
                }
            });
*/

            mOnBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bluetoothOn(v);
                }
            });

            mOffBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bluetoothOff(v);
                }
            });

            mListPairedDevicesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPairedDevices(v);
                }
            });

            mDiscoverBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    discover(v);
                }
            });
        }

    }


    private void bluetoothOn(View view){
        if (!mBTAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

            Toast.makeText(getApplicationContext(),"Bluetooth turned on",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(),"Bluetooth is already on", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent Data){

        if (requestCode == REQUEST_ENABLE_BT) {

            if (resultCode == RESULT_OK) {


//                mBluetoothStatus.setText("Enabled");
            }
            else
                mBluetoothStatus.setText("Disabled");
        }
    }

    private void bluetoothOff(View view){
        mBTAdapter.disable(); // turn off

        Toast.makeText(getApplicationContext(),"Bluetooth turned Off", Toast.LENGTH_SHORT).show();
    }

    private void discover(View view){


        if(mBTAdapter.isDiscovering()){
            mBTAdapter.cancelDiscovery();
            Toast.makeText(getApplicationContext(),"Discovery stopped",Toast.LENGTH_SHORT).show();
        }
        else{

            if(mBTAdapter.isEnabled()) {
                mBTArrayAdapter.clear(); // clear items
                mBTAdapter.startDiscovery();
                Toast.makeText(getApplicationContext(), "Discovery started", Toast.LENGTH_SHORT).show();

                getApplicationContext().registerReceiver(blReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            }

            else{
                Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
            }
        }
    }

    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                boolean verificationSiPresentDansLaListe = false;
                for(int i = 0; i < mDevicesListView.getCount(); i++){

                    if (((device.getName() + "\n" + device.getAddress()).equals(mDevicesListView.getAdapter().getItem(i)))== true)
                    {
                        verificationSiPresentDansLaListe = true;
                    }
                }
                if (verificationSiPresentDansLaListe== false)
                {
                    mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                    mBTArrayAdapter.notifyDataSetChanged();
                }

            }
        }
    };

    private void listPairedDevices(View view){
        mPairedDevices = mBTAdapter.getBondedDevices();
        if(mBTAdapter.isEnabled()) {

            mBTArrayAdapter.clear(); // clear items
            for (BluetoothDevice device : mPairedDevices)
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

            Toast.makeText(getApplicationContext(), "Show Paired Devices", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Bluetooth pas activé", Toast.LENGTH_SHORT).show();
    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            if (!mBTAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
                return;
            }

            String info = ((TextView) v).getText().toString();
            final String address = info.substring(info.length() - 17);
            final String mName = info.substring(0, info.length() - 17);

            BluetoothDevice device = mBTAdapter.getRemoteDevice(address);
            serviceBT.connect(device, mName);
        }

    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
      //  Log.d(TAG, "onSaveInstanceState: ");
        // Sauvegarde des données du contexte utilisateur
//        outState.putString("statut",mBluetoothStatus.getText().toString());
        //outState.putParcelableArrayList(LIST, messages);
        ArrayList<String> itemsList = new ArrayList<String> ( ) ;
        for(int i = 0; i < mBTArrayAdapter.getCount(); i++){
            itemsList.add(mBTArrayAdapter.getItem(i));
        }
        outState.putStringArrayList("list", itemsList );
        super.onSaveInstanceState(outState);

    }

}
