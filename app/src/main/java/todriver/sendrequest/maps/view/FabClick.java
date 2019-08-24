package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;

import todriver.sendrequest.Library.Clasess.GetLocationClass;
import todriver.sendrequest.Library.Clasess.MapStyle;
import todriver.sendrequest.Library.Listener.OnGet_Location_And_Zoom_map;
import todriver.sendrequest.Library.MasterPermission;
import todriver.sendrequest.R;

import todriver.sendrequest.model.Drivers;


public class FabClick {


    private FloatingActionButton getLocation,changeColor;
    private FloatingActionMenu floating_action_menu;

    private Activity activity;
    private GoogleMap map;

    private FloatingActionButton fab_getLocation,fab_show_details,fab_location_navigation_to_client;
    private FloatingActionMenu service_map_floating_action_menu;



    private GetLocationClass getLocationClass;

    private MapStyle mapStyle;

    private MasterPermission masterPermission;





    public FabClick(final Activity activity, final GoogleMap map) {
        this.activity = activity;

        this.map = map;


        service_map_floating_action_menu = activity.findViewById(R.id.service_map_floating_action_menu);
        fab_getLocation = activity.findViewById(R.id.service_map_fab_get_location_map);
        fab_show_details = activity.findViewById(R.id.service_map_fab_show_details);
        fab_location_navigation_to_client = activity.findViewById(R.id.service_map_fab_location_the_client);


        floating_action_menu = activity.findViewById(R.id.no_map_floating_action_menu);
        getLocation = activity.findViewById(R.id.no_map_fab_get_location_map);
        changeColor = activity.findViewById(R.id.no_map_fab_change_color_map);








        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getLocationClass == null){

                    getLocationClass  = new GetLocationClass(activity,map);
                }
                getLocationClass.setZoomAnimation(true);
                getLocationClass.setOn_Get_Location_And_Zoom_map(new OnGet_Location_And_Zoom_map() {
                    @Override
                    public void onFinishGettingLocation(LatLng latLng, Location location) {


                    }
                });


                floating_action_menu.close(true);

            }
        });



        if (mapStyle == null){
            mapStyle = new MapStyle(activity,map);
        }

        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapStyle.changeColor();
            }
        });







    }









    public void on_Click_get_location_client(final Location location) {

        fab_location_navigation_to_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CameraPosition cameraUpdate = new CameraPosition
                        .Builder()
                        .bearing(15f)
                        .tilt(16f)
                        .zoom(15.15f)
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .build();

                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraUpdate), 1000,null);



            }
        });
    }

    private void on_Click_get_location(DataSnapshot dataSnapshot, String user_id) {

        fab_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocation();

            }
        });
    }

    private void getLocation() {

        service_map_floating_action_menu.close(true);

        if (getLocationClass == null){
            getLocationClass = new GetLocationClass(activity,map);
        }
        getLocationClass.setZoomAnimation(true);
        getLocationClass.setOn_Get_Location_And_Zoom_map(new OnGet_Location_And_Zoom_map() {
            @Override
            public void onFinishGettingLocation(LatLng latLng, Location location) {


            }
        });
    }

    private void on_Click_Show_details(DataSnapshot dataSnapshot, String user_id) {


        service_map_floating_action_menu.close(true);
        fab_show_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetService.show();

            }
        });
    }



    public void GoGoogleMap(LatLng latLng){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/maps?daddr="+ latLng.latitude+","+latLng.longitude));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }


    public void fabService(DataSnapshot dataSnapshot, String user_id) {


        on_Click_Show_details(dataSnapshot,user_id);

        on_Click_get_location(dataSnapshot,user_id);


        on_Click_get_Call_driver(dataSnapshot,user_id);


    }

    private void on_Click_get_Call_driver(final DataSnapshot dataSnapshot, String user_id) {


        ((activity.findViewById(R.id.service_map_fab_call_the_client))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                masterPermission = new MasterPermission(activity);

                if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    masterPermission.addPermissionCallPhone();
                    return;
                }


                Drivers drivers = dataSnapshot.getValue(Drivers.class);

                if (drivers.getPhone().length() > 9) {

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + drivers.getPhone().length()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                }


            }
        });

    }
}
