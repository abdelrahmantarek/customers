package todriver.sendrequest.Library.Clasess;


import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;

import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.Listener.OnLocation_Update;


public class LocationUpdate {


    private Activity activity;

    private OnLocation_Update locationUpdateEventListener;

    private  LocationRequest locationRequest;
    private  FusedLocationProviderClient fusedLocationProviderClient;

    private LocationManager locationManager;

    private int FastestInterval = 4000;
    private int setInterval = 5000;

    public void setFastestInterval(int fastestInterval) {
        FastestInterval = fastestInterval;
    }

    public void setSetInterval(int setInterval) {
        this.setInterval = setInterval;
    }

    public LocationUpdate(Activity activity) {
        this.activity = activity;
    }

    public void setOn_Location_Update(OnLocation_Update locationUpdateEventListener){

        this.locationUpdateEventListener = locationUpdateEventListener;



        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);


        //////////////////////////////////////////

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);


        StartUpdateLocation();

    }


    public void StartUpdateLocation() {

        if (fusedLocationProviderClient!=null){

            if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

                return;
            }
        }


    }

    public void StopLocationUpdate() {

        if (fusedLocationProviderClient != null) {

            fusedLocationProviderClient.removeLocationUpdates(locationCallback);

            locationUpdateEventListener.onLocationStop();

        }
    }

    public  LocationCallback  locationCallback = new LocationCallback(){

        @Override
        public void onLocationResult(LocationResult locationResult) {

            for (final Location location : locationResult.getLocations()){

                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

                if (location !=null){

                    if (F.firebaseUser() == null){
                        return;
                    }

                    locationUpdateEventListener.onLocationChanged(location,latLng);

                }else {

                    fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                }
            }
        }

        @Override
        public void onLocationAvailability(LocationAvailability locationAvailability) {
            super.onLocationAvailability(locationAvailability);


        }
    };


}
