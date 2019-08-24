package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import todriver.sendrequest.Library.Clasess.CameraMoveAddress;
import todriver.sendrequest.Library.Clasess.EditAutoCompleteAddress;
import todriver.sendrequest.Library.Listener.OnGet_Address_From_Camera;
import todriver.sendrequest.Library.Listener.OnGet_Address_From_EditComplete;
import todriver.sendrequest.Library.Listener.TopSheetEvent;
import todriver.sendrequest.Library.helper.TopSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class SheetEditTop {




    public static TopSheet sheet_winch;
    Activity activity;
    public static boolean isShow = false;



    private CameraMoveAddress cameraMoveAddress;
    private GoogleMap map;

    private EditAutoCompleteAddress editAutoCompleteA,editAutoCompleteB;


    private AutoCompleteTextView edit_place_a,edit_place_b;
    private ImageView add_camera_edit_place_a,add_camera_edit_place_b;




    private ImageView image_marker_place_map;


    private ProgressBar prgress_camera_edit_place_b,prgress_camera_edit_place_a;

    public SheetEditTop(final Activity activity,final CameraMoveAddress cameraMoveAddress, final GoogleMap map) {
        this.activity = activity;
        this.cameraMoveAddress = cameraMoveAddress;
        this.map = map;

        edit_place_a = activity.findViewById(R.id.edit_place_a);
        edit_place_b = activity.findViewById(R.id.edit_place_b);
        add_camera_edit_place_a = activity.findViewById(R.id.add_camera_edit_place_a);
        add_camera_edit_place_b = activity.findViewById(R.id.add_camera_edit_place_b);
        prgress_camera_edit_place_b = activity.findViewById(R.id.progress_edit_place_b);
        prgress_camera_edit_place_a = activity.findViewById(R.id.progress_edit_place_a);
        image_marker_place_map = activity.findViewById(R.id.image_marker_place_map);






        add_camera_edit_place_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editAutoCompleteA!=null){
                    editAutoCompleteA.stop();
                }

                if (editAutoCompleteB!=null){
                    editAutoCompleteB.stop();
                }
                cameraMoveAddress.stop();
                startCameraChoiceA();



            }
        });



        add_camera_edit_place_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editAutoCompleteA!=null){
                    editAutoCompleteA.stop();
                }

                if (editAutoCompleteB!=null){
                    editAutoCompleteB.stop();
                }
                cameraMoveAddress.stop();
                startCameraChoiceB();
            }
        });


        editAutoCompleteA = new EditAutoCompleteAddress(activity);
        editAutoCompleteB = new EditAutoCompleteAddress(activity);



        editAutoCompleteA.setOn_Get_Address_From_EditComplete(edit_place_a, new OnGet_Address_From_EditComplete() {
            @Override
            public void onFinishGettingAddress(String address, Location location) {




                Log.d("LastAddddd",address +"");



                if (address.equals("")){
                    return;
                }

                edit_place_a.setText(address);


                if (location == null){
                    Log.d("LastAddddd","LatLang == null" +"");
                    return;
                }




                MapsActivity.LOCATION_A = location;
                MapsActivity.TEXT_FROM = address;

                editAutoCompleteA.stop();

            }

            @Override
            public void onTextChange(String s) {

            }
        });

        editAutoCompleteB.setOn_Get_Address_From_EditComplete(edit_place_b, new OnGet_Address_From_EditComplete() {
            @Override
            public void onFinishGettingAddress(String address, Location location) {


                if (location == null){
                    Log.d("LastAddddd","LatLang == null" +"");
                    return;
                }




                MapsActivity.LOCATION_B = location;


                if (address.equals("")){
                    return;
                }

                edit_place_b.setText(address);
                MapsActivity.TEXT_TO = address;

                editAutoCompleteB.stop();
            }

            @Override
            public void onTextChange(String s) {


            }
        });




        // image_marker_place_map
        // edit_place_a
        // edit_place_b
        // add_camera_edit_place_a
        // add_camera_edit_place_b
        // progress_edit_place_a
        // progress_edit_place_b


        sheet_winch = new TopSheet((activity.findViewById(R.id.layout_sheet_edit_top)));

        sheet_winch.addBottomSheetEvent(new TopSheetEvent() {
            @Override
            public void onSheetExpandle() {

                image_marker_place_map.setVisibility(View.VISIBLE);

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
                image_marker_place_map.setVisibility(View.GONE);
            }
        });






    }

    private void startCameraChoiceB() {

        prgress_camera_edit_place_b.setVisibility(View.VISIBLE);
        edit_place_b.setVisibility(View.GONE);

        cameraMoveAddress.setOn_Get_Address_From_Camera(new OnGet_Address_From_Camera() {

            @Override
            public void onCameraMove() {


                prgress_camera_edit_place_b.setVisibility(View.VISIBLE);
                edit_place_b.setVisibility(View.GONE);

            }

            @Override
            public void onCameraGetAddress(String Address,Location location) {

                if (Address.equals("")){
                    return;
                }

                Log.d("LastAddddd",Address +"");



                prgress_camera_edit_place_b.setVisibility(View.GONE);
                edit_place_b.setVisibility(View.VISIBLE);




                MapsActivity.LOCATION_B = location;
                MapsActivity.TEXT_TO = Address;

                edit_place_b.setText(Address);

                editAutoCompleteB.start();

            }
        });

    }

    private void startCameraChoiceA() {

        prgress_camera_edit_place_a.setVisibility(View.VISIBLE);
        edit_place_a.setVisibility(View.GONE);

        cameraMoveAddress.setOn_Get_Address_From_Camera(new OnGet_Address_From_Camera() {


            @Override
            public void onCameraMove() {


                prgress_camera_edit_place_a.setVisibility(View.VISIBLE);
                edit_place_a.setVisibility(View.GONE);

            }


            @Override
            public void onCameraGetAddress(String Address,Location location) {


                if (Address.equals("")){
                    return;
                }


                Log.d("LastAddddd",Address +"");

                edit_place_a.setText(Address);

                prgress_camera_edit_place_a.setVisibility(View.GONE);
                edit_place_a.setVisibility(View.VISIBLE);

                MapsActivity.LOCATION_A = location;
                MapsActivity.TEXT_FROM = Address;

                editAutoCompleteA.start();

            }


        });
    }


    public static void show(){
        sheet_winch.expandle();
    }

    public static void hide(){
        sheet_winch.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }



   public void onBackPress(){


    }

    public void onRequestStart() {


    }
}
