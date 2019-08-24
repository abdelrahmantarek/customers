package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import todriver.sendrequest.Library.ArrayHelp;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.Marker_My_Location;
import todriver.sendrequest.Library.ShowMapClass;
import todriver.sendrequest.Library.ShowMap_EventListener;
import todriver.sendrequest.Library.ShowMap_No_Class;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class ShowMap {



    private GoogleMap map;
    Activity activity;
    boolean driver_found;
    private ShowMap_No_Class showMapClass;


    private int drawable;

    private List<Marker> MARKERS_CUSTOMER_ONLINE;


    public ShowMap(GoogleMap map, Activity activity,List<Marker> MARKERS_CUSTOMER_ONLINE, boolean driver_found) {
        this.map = map;
        this.activity = activity;
        this.driver_found = driver_found;
        this.MARKERS_CUSTOMER_ONLINE = MARKERS_CUSTOMER_ONLINE;


    }

    int ss = 0;
    public void ShowAll(Location myLocation) {



        if (showMapClass == null){
            showMapClass = new ShowMap_No_Class();
        }

        showMapClass.setMARKERS_CUSTOMER_ONLINE(MARKERS_CUSTOMER_ONLINE);
        showMapClass.hide();
        MapsActivity.d.map_clear(activity,map);
        showMapClass.setMap(map);
        showMapClass.setTitleMarker("مقدم خدمة");
        showMapClass.setReduis(8000);
        showMapClass.setDatabaseReference(F.OnlineDrivers());
        showMapClass.setResourseMarkerDrawAbe(R.drawable.car_map);
        showMapClass.setSearch(true);
        showMapClass.setLocation(myLocation);
        showMapClass.addShowMapEventListener(new ShowMap_EventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {


                driver_found = true;
                Toast.makeText(activity, "لقد دخل سائق", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onKeyExited(String key,int index) {

                Toast.makeText(activity, "لقد خرج سائق", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });

    }


}
