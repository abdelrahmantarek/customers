package todriver.sendrequest.Library;

import android.location.Location;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.zzp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import todriver.sendrequest.R;


public class Marker_My_Location {

    List<Marker> MyMarkerList = new ArrayList<Marker>();


   private Marker marker;



    public void Add(LatLng latLng,GoogleMap map){

        if (map!=null){

            for (Marker markerIt : MyMarkerList) {
                if (markerIt.getTag()!=null){
                    if (markerIt.getTag().equals(F.Uid())) {
                        Change(latLng);
                        return;
                    }
                }

            }

            marker = map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_person))
                    .position(new LatLng(latLng.latitude, latLng.longitude)).title("انت"));
            marker.setTag(FirebaseAuth.getInstance().getCurrentUser().getUid());
            MyMarkerList.add(marker);
        }
    }

    public void Change(LatLng latLng) {

        for (final Marker markerIt : MyMarkerList) {
            if (markerIt.getTag() != null) {
                if (markerIt.getTag().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                    Location location1 = new Location("s");
                    location1.setLatitude(latLng.latitude);
                    location1.setLongitude(latLng.longitude);
                    animateMarker(markerIt,location1);


                }

            }
        }
    }


    public void Remove(){

        for (Marker markerIt : MyMarkerList) {
            if (markerIt.getTag() != null) {
                if (markerIt.getTag().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    markerIt.remove();
                }
            }
        }
    }

    public static void animateMarker(final Marker marker, final Location location) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final LatLng startLatLng = marker.getPosition();
        final double startRotation = marker.getRotation();
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);

                double lng = t * location.getLongitude() + (1 - t)
                        * startLatLng.longitude;
                double lat = t * location.getLatitude() + (1 - t)
                        * startLatLng.latitude;



                marker.setPosition(new LatLng(lat, lng));
               // marker.setRotation(SensorClass.mRotation);

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

}
