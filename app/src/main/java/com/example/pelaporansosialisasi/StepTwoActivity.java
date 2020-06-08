package com.example.pelaporansosialisasi;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.kofigyan.stateprogressbar.StateProgressBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static java.util.Calendar.*;


public class StepTwoActivity extends FormBaseActivity implements FetchAddressTask.OnTaskCompleted {
    Spinner sKecamatan, sKelurahan;
    DatePickerDialog datePickerDialog;
    EditText date, txt_jam, txt_jam2, etAcara;
    ArrayAdapter adapterKelurahan;

    // Constants
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String TRACKING_LOCATION_KEY = "tracking_location";

    // Views
    private Button mLocationButton;
    private EditText mLocationTextView;

    // Location classes
    private boolean mTrackingLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two);
        injectBackView();
        stateprogressbar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
        etAcara = findViewById(R.id.etAcara);
        date = findViewById(R.id.etTanggal);
        txt_jam = findViewById(R.id.etJam1);
        txt_jam2 = findViewById(R.id.etJam2);
        sKecamatan = findViewById(R.id.etKecamatan);
        sKelurahan = findViewById(R.id.etKelurahan);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(StepTwoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"-"+(month + 1)+"-"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        txt_jam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = getInstance();
                int hour = mcurrentTime.get(HOUR_OF_DAY);
                int minute = mcurrentTime.get(MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(StepTwoActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_jam.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        txt_jam2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = getInstance();
                int hour = mcurrentTime.get(HOUR_OF_DAY);
                int minute = mcurrentTime.get(MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(StepTwoActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_jam2.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        ArrayList<String> items = getNama("kecamatan.json");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sKecamatan.setAdapter(adapter);

        final ArrayList<String> kelurahan_dn = getKelurahan("kelurahan_dn.json");
        final ArrayList<String> kelurahan_gk = getKelurahan("kelurahan_gk.json");
        final ArrayList<String> kelurahan_gm = getKelurahan("kelurahan_gm.json");
        final ArrayList<String> kelurahan_gt = getKelurahan("kelurahan_gt.json");
        final ArrayList<String> kelurahan_jt = getKelurahan("kelurahan_jt.json");
        final ArrayList<String> kelurahan_kg = getKelurahan("kelurahan_kg.json");
        final ArrayList<String> kelurahan_kr = getKelurahan("kelurahan_kr.json");
        final ArrayList<String> kelurahan_mg = getKelurahan("kelurahan_mg.json");
        final ArrayList<String> kelurahan_mj = getKelurahan("kelurahan_mj.json");
        final ArrayList<String> kelurahan_ng = getKelurahan("kelurahan_ng.json");
        final ArrayList<String> kelurahan_pa = getKelurahan("kelurahan_pa.json");
        final ArrayList<String> kelurahan_tr = getKelurahan("kelurahan_tr.json");
        final ArrayList<String> kelurahan_uh = getKelurahan("kelurahan_uh.json");
        final ArrayList<String> kelurahan_wb = getKelurahan("kelurahan_wb.json");

        sKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = sKecamatan.getSelectedItem().toString();
                if (selectedItem.contentEquals("DANUREJAN")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_dn);
                    adapterKelurahan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }
                if (selectedItem.contentEquals("GONDOKUSUMAN")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_gk);
                }
                if (selectedItem.contentEquals("GONDOMANAN")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_gm);
                }
                if (selectedItem.contentEquals("GEDONGTENGEN")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_gt);
                }
                if (selectedItem.contentEquals("JETIS")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_jt);
                }
                if (selectedItem.contentEquals("KOTAGEDE")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_kg);
                }
                if (selectedItem.contentEquals("KRATON")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_kr);
                }
                if (selectedItem.contentEquals("MERGANGSAN")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_mg);
                }
                if (selectedItem.contentEquals("MANTRIJERON")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_mj);
                }
                if (selectedItem.contentEquals("NGAMPILAN")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_ng);
                }
                if (selectedItem.contentEquals("PAKUALAMAN")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_pa);
                }
                if (selectedItem.contentEquals("TEGALREJO")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_tr);
                }
                if (selectedItem.contentEquals("UMBULHARJO")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_uh);
                }
                if (selectedItem.contentEquals("WIROBRAJAN")) {
                    adapterKelurahan = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kelurahan_wb);
                }
                adapterKelurahan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapterKelurahan.notifyDataSetChanged();
                sKelurahan.setAdapter(adapterKelurahan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mLocationButton = findViewById(R.id.bt_ppicker);
        mLocationTextView = findViewById(R.id.tv_gps);


        // Initialize the FusedLocationClient.
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        // Restore the state if the activity is recreated.
        if (savedInstanceState != null) {
            mTrackingLocation = savedInstanceState.getBoolean(
                    TRACKING_LOCATION_KEY);
        }

        // Set the listener for the location button.
        mLocationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!mTrackingLocation) {
                    startTrackingLocation();
                } else {
                    stopTrackingLocation();
                }
            }
        });

        // Initialize the location callbacks.
        mLocationCallback = new LocationCallback() {
            /**
             * This is the callback that is triggered when the
             * FusedLocationClient updates your location.
             * @param locationResult The result containing the device location.
             */
            @Override
            public void onLocationResult(LocationResult locationResult) {
                // If tracking is turned on, reverse geocode into an address
                if (mTrackingLocation) {
                    new FetchAddressTask(StepTwoActivity.this, StepTwoActivity.this).execute(locationResult.getLastLocation());
                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                Intent intent = new Intent(getApplicationContext(), StepThreeActivity.class);
                String spelapor = getIntent().getStringExtra("pelapor");
                String spenyelenggara = getIntent().getStringExtra("penyelenggara");
                String sjmlpeserta = getIntent().getStringExtra("jmlpeserta");
                String sketpeserta = getIntent().getStringExtra("ketpeserta");
                String sspinner1 = getIntent().getStringExtra("pendamping1");

                String sacara = etAcara.getText().toString();
                String slokasi = mLocationTextView.getText().toString();
                String skecamatan = sKecamatan.getSelectedItem().toString();
                String skelurahan = sKelurahan.getSelectedItem().toString();
                String stanggal = date.getText().toString();
                String sjam1 = txt_jam.getText().toString();
                String sjam2 = txt_jam2.getText().toString();

                intent.putExtra("pelapor", spelapor);
                intent.putExtra("penyelenggara", spenyelenggara);
                intent.putExtra("jmlpeserta", sjmlpeserta);
                intent.putExtra("ketpeserta", sketpeserta);
                intent.putExtra("pendamping1", sspinner1);
                intent.putExtra("pelapor", spelapor);

                intent.putExtra("acara", sacara);
                intent.putExtra("lokasi", slokasi);
                intent.putExtra("kecamatan", skecamatan);
                intent.putExtra("kelurahan", skelurahan);
                intent.putExtra("tanggal", stanggal);
                intent.putExtra("jam1", sjam1);
                intent.putExtra("jam2", sjam2);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.btnBack:
                finish();
                break;
        }
    }


    private ArrayList<String> getNama(String fileName) {
        JSONArray jsonArray;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("nama"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }

        return cList;
    }

    private ArrayList<String> getKelurahan(String fileName) {
        JSONArray jsonArray;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("nama"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }

        return cList;
    }


    private void startTrackingLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates
                    (getLocationRequest(),
                            mLocationCallback,
                            null /* Looper */);

            // Set a loading text while you wait for the address to be
            // returned
            mLocationTextView.setText(getString(R.string.address_text, getString(R.string.loading), System.currentTimeMillis()));
            mLocationButton.setText(R.string.stop_tracking_location);
            //mRotateAnim.start();
        }
    }


    /**
     * Stops tracking the device. Removes the location
     * updates, stops the animation, and resets the UI.
     */
    private void stopTrackingLocation() {
        if (mTrackingLocation) {
            mTrackingLocation = false;
            mLocationButton.setText(R.string.start_tracking_location);
            mLocationTextView.setText(R.string.textview_hint);
            //mRotateAnim.end();
        }
    }


    /**
     * Sets up the location request.
     *
     * @return The LocationRequest object containing the desired parameters.
     */
    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }


    /**
     * Saves the last location on configuration change
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(TRACKING_LOCATION_KEY, mTrackingLocation);
        super.onSaveInstanceState(outState);
    }

    /**
     * Callback that is invoked when the user responds to the permissions
     * dialog.
     *
     * @param requestCode  Request code representing the permission request
     *                     issued by the app.
     * @param permissions  An array that contains the permissions that were
     *                     requested.
     * @param grantResults An array with the results of the request for each
     *                     permission requested.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:

                // If the permission is granted, get the location, otherwise,
                // show a Toast
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    startTrackingLocation();
                } else {
                    Toast.makeText(this,
                            R.string.location_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onTaskCompleted(String result) {
        if (mTrackingLocation) {
            // Update the UI
            mLocationTextView.setText(getString(R.string.address_text,
                    result, System.currentTimeMillis()));
        }
    }

    @Override
    protected void onPause() {
        if (mTrackingLocation) {
            stopTrackingLocation();
            mTrackingLocation = true;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mTrackingLocation) {
            startTrackingLocation();
        }
        super.onResume();
    }

}

