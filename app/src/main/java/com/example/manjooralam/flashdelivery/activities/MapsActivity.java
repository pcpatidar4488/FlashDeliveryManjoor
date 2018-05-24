package com.example.manjooralam.flashdelivery.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.interfaces.AsyncResponse;
import com.example.manjooralam.flashdelivery.utilities.AppUtils;
import com.example.manjooralam.flashdelivery.utilities.DownloadTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, LocationListener, AsyncResponse, View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 123;
    private GoogleMap mMap;
    ArrayList<LatLng> markerPoints = new ArrayList();
    LocationManager locationManager;
    private TextView tvStoreList, tvStoreLocation, tvUserLocation, tvNearestStore, tvToolbarTitle;
    private ImageView ivBack;
    private RelativeLayout rlRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        initViews();
       initialPageSetup();
    }

    private void initViews() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_title);
        tvNearestStore = (TextView) findViewById(R.id.tv_nearest_store);
        tvStoreList = (TextView) findViewById(R.id.tv_store_list);
        rlRootLayout = (RelativeLayout) findViewById(R.id.rl_rootlayout);
        tvStoreLocation = (TextView) findViewById(R.id.tv_store_location);
        tvUserLocation = (TextView) findViewById(R.id.tv_user_location);

        //------registering listener---
        tvNearestStore.setOnClickListener(this);
        tvStoreList.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    private void initialPageSetup() {

        tvToolbarTitle.setText(getResources().getString(R.string.s_store_locator));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            askForLocationPermission();
        }else {
            if(locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
                requestLocationUpdates();
            }else {
                enableGps();
            }
        }
    }

    private void requestLocationUpdates() {

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 5, this);
    }

    private void askForLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_FINE_LOCATION);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng sydney = new LatLng(27.63234, 76.37263);
        markerPoints.add(sydney);
        mMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8));
        tvStoreLocation.setText(getCompleteAddressString(sydney.latitude, sydney.longitude));
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strAdd;
    }
    @Override
    public void onLocationChanged(Location location) {
        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
        if (markerPoints.size() > 1) {
            markerPoints.remove(markerPoints.size() - 1);
            markerPoints.add(currentLocation);
        }else {
            markerPoints.add(currentLocation);
        }

        tvUserLocation.setText(getCompleteAddressString(currentLocation.latitude, currentLocation.longitude));
       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));
        // Checks, whether start and end locations are captured
        if (markerPoints.size() == 2) {
            LatLng origin = (LatLng) markerPoints.get(0);
            LatLng dest = (LatLng) markerPoints.get(1);

            // Getting URL to the Google Directions API
            String url = getDirectionsUrl(origin, dest);

            DownloadTask downloadTask = new DownloadTask(this);

            // Start downloading json data from Google Directions API
            downloadTask.execute(url);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }







    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if(locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
                        requestLocationUpdates();
                    }else {
                        enableGps();
                    }

                } else {

                    askForLocationPermission();
                }
                return;
            }
        }

    }

    private void enableGps() {
        Intent openGpsIntent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(openGpsIntent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 101:
                if(locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )){
                    requestLocationUpdates();
                }else {
                    askForLocationPermission();
                }
        }
    }

    @Override
    public void processFinish(PolylineOptions lineOptions) {

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(markerPoints.get(0)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions().position(markerPoints.get(1)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addPolyline(lineOptions);
    }


    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.tv_store_list:
                AppUtils.getInstance().showSnackBar(getString(R.string.s_under_development), rlRootLayout);
            break;

            case R.id.tv_nearest_store:
                AppUtils.getInstance().showSnackBar(getString(R.string.s_under_development), rlRootLayout);

                break;
            case R.id.iv_back:
                  finish();
                break;
        }
    }
}