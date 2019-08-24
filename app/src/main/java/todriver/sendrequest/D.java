package todriver.sendrequest;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.view.View;
import android.widget.ProgressBar;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import todriver.sendrequest.Library.Clasess.GetLocationClass;
import todriver.sendrequest.Library.Listener.OnGet_Location_And_Zoom_map;
import todriver.sendrequest.Library.Marker_My_Location;
import todriver.sendrequest.maps.helper.LatLngBound_Tow_point;
import todriver.sendrequest.maps.view.BottomSheetService;
import todriver.sendrequest.maps.view.Btn_Send_request_To_Company;
import todriver.sendrequest.maps.view.Btn_Show_Service;
import todriver.sendrequest.maps.view.RequestClass;
import todriver.sendrequest.maps.view.Btn_send_request_driver;
import todriver.sendrequest.maps.view.SheetEditTop;
import todriver.sendrequest.maps.view.Sheet_All_Service;
import todriver.sendrequest.maps.view.Sheet_Bettrey;
import todriver.sendrequest.maps.view.Sheet_Taxi;
import todriver.sendrequest.maps.view.Sheet_Trasnport_Btween_city;
import todriver.sendrequest.maps.view.Sheet_bancher;
import todriver.sendrequest.maps.view.Sheet_banzen;
import todriver.sendrequest.maps.view.Sheet_key;
import todriver.sendrequest.maps.view.Sheet_sat7a;
import todriver.sendrequest.maps.view.Sheet_sat7a_3ady_details;
import todriver.sendrequest.maps.view.Sheet_sat7a_hedrolek_details;
import todriver.sendrequest.maps.view.Sheet_winsh;
import todriver.sendrequest.maps.view.ToolBarSheet;

public class D {

    private Marker_My_Location marker_my_location;
    private  GetLocationClass getLocationClass;



    public static void hide_all_sheet(){

        RequestClass.hide();

        Sheet_All_Service.hide();

        Sheet_bancher.hide();

        Sheet_banzen.hide();

        Sheet_Bettrey.hide();

        Sheet_key.hide();

        Sheet_sat7a.hide();

        Sheet_sat7a_3ady_details.hide();

        Sheet_sat7a_hedrolek_details.hide();

        Sheet_winsh.hide();

        Btn_send_request_driver.hide();

        SheetEditTop.hide();

        ToolBarSheet.hide();

        BottomSheetService.hide();

        Btn_Send_request_To_Company.hide();

        Sheet_Taxi.hide();

        Sheet_Trasnport_Btween_city.hide();

        Btn_Show_Service.hide();

    }

    public void map_clear(Activity activity, final GoogleMap map){

        if (map!=null){
            map.clear();

            if (marker_my_location!=null){
                marker_my_location.Remove();
            }
        }

        if (getLocationClass == null){

            getLocationClass = new GetLocationClass(activity,map);
        }



        getLocationClass.setZoomAnimation(false);
        getLocationClass.setOn_Get_Location_And_Zoom_map(new OnGet_Location_And_Zoom_map() {
            @Override
            public void onFinishGettingLocation(LatLng latLng, Location location) {

                if (marker_my_location== null){
                    marker_my_location = new Marker_My_Location();
                }

                marker_my_location.Add(latLng,map);

            }
        });


    }


    public void marker_location_update(LatLng latLng, final GoogleMap map){

        if (marker_my_location== null){
            marker_my_location = new Marker_My_Location();
        }

        marker_my_location.Add(latLng,map);

    }

    public void show_no_Sheet(){

        Btn_Show_Service.show();
        ToolBarSheet.show();
    }


    public void hide_progress(ProgressBar progressBar){

        if (progressBar!=null){
            progressBar.setVisibility(View.GONE);
        }
    }


    public void hide_floationg(FloatingActionMenu floatingActionMenu_1,FloatingActionMenu floatingActionMenu_2){

        if (floatingActionMenu_1!=null && floatingActionMenu_2!=null){

            floatingActionMenu_1.setVisibility(View.GONE);
            floatingActionMenu_2.setVisibility(View.VISIBLE);
        }

    }


    public void show_ToolBar_Sheet(){

        ToolBarSheet.show();
    }





    public void addPolylineAndMarker(LatLng latLng_1,LatLng latLng_2,String text_1,String text_2,GoogleMap map){


        addMarker(latLng_1,latLng_2,text_1,text_2,map);

        ArrayList<LatLng> arrayList = new ArrayList<>();
        arrayList.add(latLng_1);
        arrayList.add(latLng_2);

        for (int i = 0; i<arrayList.size() ; i++){


            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.color(Color.BLUE);
            polylineOptions.width(20+i*3);
            polylineOptions.addAll(arrayList);
            // polylineOptions.addAll(arrayList.get(i).getPoints());

            Polyline polyline = map.addPolyline(polylineOptions);
            // poliLines.add(polyline);
        }




    }


    Marker marker;

    public void addMarker(LatLng Location_a, LatLng location_b, String textView_from,String textView_to,GoogleMap map){

        LatLng latLng_aa = new LatLng(Location_a.latitude,Location_a.longitude);
        LatLng Location_bb = new LatLng(location_b.latitude,location_b.longitude);



        MarkerOptions marker_a = new MarkerOptions();
        marker_a.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_person))
                .position(latLng_aa);

        marker_a.title("من");
        marker_a.snippet(textView_from);


        MarkerOptions marker_b = new MarkerOptions();
        marker_b.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_to))
                .position(Location_bb);

        marker_b.title("الي");
        marker_b.snippet(textView_to);

        marker = map.addMarker(marker_a);

        marker = map.addMarker(marker_b);


        LatLngBound_Tow_point latLngBound_tow_point = new LatLngBound_Tow_point(Location_a,location_b,map);
        latLngBound_tow_point.setOnLatLngBound(new LatLngBound_Tow_point.OnLatLngBound() {
            @Override
            public void onFinish() {

            }
        });
    }


    public void showServiceSheet() {


    }
}
