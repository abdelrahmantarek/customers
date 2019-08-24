package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import todriver.sendrequest.D;
import todriver.sendrequest.Library.Clasess.EditAutoCompleteAddress;
import todriver.sendrequest.Library.Clasess.GetAddressClass;
import todriver.sendrequest.Library.Clasess.GetLocationClass;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.Listener.OnGet_Address_From_EditComplete;
import todriver.sendrequest.Library.Listener.OnGet_Address_From_Location;
import todriver.sendrequest.Library.Listener.OnGet_Location_And_Zoom_map;
import todriver.sendrequest.Library.Marker_My_Location;
import todriver.sendrequest.Library.ShowMapClass;
import todriver.sendrequest.Library.ShowMap_EventListener;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.NAV.PaymentActivity;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.Database;
import todriver.sendrequest.maps.MapsActivity;

import static todriver.sendrequest.maps.MapsActivity.LOCATION_A;
import static todriver.sendrequest.maps.MapsActivity.LOCATION_B;


public class Sheet_sat7a_hedrolek_details  implements AdapterView.OnItemSelectedListener{


    public static BottomSheet sheet_sat7a_details;
    Activity activity;


    public static boolean isShow = false;


    private ProgressBar progressBar_a;
    private ProgressBar progressBar_b;
    private AutoCompleteTextView edit_place_a;
    private AutoCompleteTextView edit_place_b;
    private TextView sat7a_price;
    private GoogleMap map;
    private EditAutoCompleteAddress editAutoCompleteA,editAutoCompleteB;

    private Marker_My_Location marker_my_location;
    private Database database;


    private List<Marker> MARKERS_CUSTOMER_ONLINE;
    private boolean driver_found;


    private Spinner spinner;

    private ArrayList<String> user_is_requests;

    public Sheet_sat7a_hedrolek_details(final Activity activity, final GoogleMap map, Database database, List<Marker> MARKERS_CUSTOMER_ONLINE,ArrayList<String> user_is_requests, boolean driver_found) {

        this.activity = activity;
        this.marker_my_location = marker_my_location;
        this.map = map;
        this.database = database;
        this.MARKERS_CUSTOMER_ONLINE = MARKERS_CUSTOMER_ONLINE;
        this.driver_found = driver_found;
        this.user_is_requests = user_is_requests;


        findViewById();


        Make_new_Sheet();



        EditAutoComplete_Place_A();


        EditAutoComplete_Place_B();



        spinner = activity.findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity,R.array.payment_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




        ((activity.findViewById(R.id.sat7a_call_the_driver_hedrolek))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (LOCATION_A==null){

                    F.message("من فضلك قم باختيار الاماكن",(activity.findViewById(R.id.drawer_layout)),activity,R.color.red_A700);

                    return;
                }

                if (LOCATION_B==null){

                    F.message("من فضلك قم باختيار الاماكن",(activity.findViewById(R.id.drawer_layout)),activity,R.color.red_A700);

                    return;
                }



                StartSendRequest();

            }
        });



        ((activity.findViewById(R.id.sat7a_image_edit_place_a_details_hedrolek))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressBar_a.setVisibility(View.VISIBLE);

                GetLocationClass getLocationClass = new GetLocationClass(activity,map);
                getLocationClass.setZoomAnimation(true);
                getLocationClass.setOn_Get_Location_And_Zoom_map(new OnGet_Location_And_Zoom_map() {
                    @Override
                    public void onFinishGettingLocation(LatLng latLng, Location location) {


                        GetAddressClass getAddressClass = new GetAddressClass(activity,location);
                        getAddressClass.setOn_Get_Address_From_Location(new OnGet_Address_From_Location() {
                            @Override
                            public void onAddressCome(String address, Location location) {


                                progressBar_a.setVisibility(View.GONE);
                                edit_place_a.setText(address);


                                MapsActivity.LOCATION_A = location;
                                MapsActivity.TEXT_FROM = address;


                                SetPrice();
                            }
                        });


                    }
                });
            }
        });

        ((activity.findViewById(R.id.sat7a_image_edit_place_b_details_hedrolek))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressBar_b.setVisibility(View.VISIBLE);

                GetLocationClass getLocationClass = new GetLocationClass(activity,map);
                getLocationClass.setZoomAnimation(true);
                getLocationClass.setOn_Get_Location_And_Zoom_map(new OnGet_Location_And_Zoom_map() {
                    @Override
                    public void onFinishGettingLocation(LatLng latLng, Location location) {


                        GetAddressClass getAddressClass = new GetAddressClass(activity,location);
                        getAddressClass.setOn_Get_Address_From_Location(new OnGet_Address_From_Location() {
                            @Override
                            public void onAddressCome(String address, Location location) {


                                progressBar_b.setVisibility(View.GONE);
                                edit_place_b.setText(address);



                                MapsActivity.LOCATION_B = location;
                                MapsActivity.TEXT_TO = address;

                                SetPrice();

                            }
                        });


                    }
                });
            }
        });


    }


    private void SendRequestWithData(final String user_id) {



        database.SendRequestWithData(user_id);




    }


    private void EditAutoComplete_Place_A() {

        editAutoCompleteA.setOn_Get_Address_From_EditComplete(edit_place_a, new OnGet_Address_From_EditComplete() {
            @Override
            public void onFinishGettingAddress(String address, Location location) {


                if (address.equals("")){
                    return;
                }

                edit_place_a.setText(address);


                if (location == null){
                    return;
                }




                MapsActivity.LOCATION_A = location;
                MapsActivity.TEXT_FROM = address;

                editAutoCompleteA.stop();

                SetPrice();

            }


            @Override
            public void onTextChange(String s) {

            }
        });

    }

    private void EditAutoComplete_Place_B() {


        editAutoCompleteB.setOn_Get_Address_From_EditComplete(edit_place_b, new OnGet_Address_From_EditComplete() {
            @Override
            public void onFinishGettingAddress(String address, Location location) {



                if (address.equals("")){
                    return;
                }

                edit_place_b.setText(address);


                if (location == null){
                    return;
                }



                MapsActivity.LOCATION_B = location;
                MapsActivity.TEXT_TO = address;

                editAutoCompleteB.stop();


                SetPrice();

            }

            @Override
            public void onTextChange(String s) {

            }
        });


    }


    private void Make_new_Sheet() {

        sheet_sat7a_details = new BottomSheet((activity.findViewById(R.id.layout_sheet_sat7a_details_hedrolek)));

        sheet_sat7a_details.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {


                isShow = true;



                getAddressPlaceAFristTime();
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });

    }


    private void getAddressPlaceAFristTime() {


        progressBar_a.setVisibility(View.VISIBLE);
        GetAddressClass getAddressClass = new GetAddressClass(activity, F.getLocation(map));
        getAddressClass.setOn_Get_Address_From_Location(new OnGet_Address_From_Location() {
            @Override
            public void onAddressCome(String address, Location location) {

                progressBar_a.setVisibility(View.GONE);
                edit_place_a.setText(address);


                MapsActivity.LOCATION_A = location;
                MapsActivity.TEXT_FROM = address;

                SetPrice();

            }
        });
    }

    private void findViewById() {



        editAutoCompleteA = new EditAutoCompleteAddress(activity);
        editAutoCompleteB = new EditAutoCompleteAddress(activity);

        progressBar_a = activity.findViewById(R.id.sat7a_progress_edit_place_a_details_hedrolek);
        progressBar_b = activity.findViewById(R.id.sat7a_progress_edit_place_b_details_hedrolek);
        edit_place_a = activity.findViewById(R.id.sat7a_edit_place_a_details_hedrolek);
        edit_place_b = activity.findViewById(R.id.sat7a_edit_place_b_details_hedrolek);
        sat7a_price = activity.findViewById(R.id.sat7a_the_price_hedrolek);
    }


    public static void show(){
        sheet_sat7a_details.expandle();
    }

    public static void hide(){
        sheet_sat7a_details.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }





    String price2;

    private void SetPrice() {




        if (MapsActivity.LOCATION_B !=null &&  MapsActivity.LOCATION_A!=null){


            int distance = F.getDistanceBeforKilo(MapsActivity.LOCATION_A.getLatitude(),MapsActivity.LOCATION_A.getLongitude(),
                    MapsActivity.LOCATION_B.getLatitude(),MapsActivity.LOCATION_B.getLongitude());

            int dis = distance;
            double mint = 0.99;

            int price = (int) (dis*mint+130);

            culculatPrice(price);


            MapsActivity.DISTANCE = price2;

            sat7a_price.setText(price2);


        }
    }

    private void culculatPrice(int price) {

        price2 = String.valueOf(price + "/" +" " +"ر.س ");

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();

        if (text.equals("Visa")){

            Intent connecsst = new Intent(activity, PaymentActivity.class);
            activity.startActivity(connecsst);


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    boolean key_request_found_after_loop = false;

    private void StartSendRequest() {


        if (!this.MARKERS_CUSTOMER_ONLINE.isEmpty()){

            for (Marker markerIt : MARKERS_CUSTOMER_ONLINE) {

                if (markerIt.getTag()!=null){

                    String key = (String) markerIt.getTag();

                    if (UserRegisterdRequests(key)){

                        Log.d("found_after_loop","user we have request him");

                    }else {

                        SendRequestWithData(key);

                        RegisterUsersRequests(key); // تسجيل المستخدم في قائمة اللذين تم الارسال اليهم

                        D.hide_all_sheet();

                        Log.d("found_after_loop","SendRequestWithData");


                    }


                }

            }

            if (If_User_Request_True()){

                return;
            }


            If_User_Request_false();



        }else {
            Toast.makeText(activity, "لا يوجد سائقين حول المنطفة", Toast.LENGTH_SHORT).show();
            //  F.message("لا يوجد سائقين حول الجوار الان",(activity.findViewById(R.id.drawer_layout)),activity,R.color.red_A700);

        }



    }

    private void If_User_Request_false() {

        if (!user_is_requests.isEmpty()){
            Log.d("found_after_loop","clear");
            user_is_requests.clear();
            StartSendRequest();
        }

    }

    private boolean If_User_Request_True() {

        if (key_request_found_after_loop){

            key_request_found_after_loop = false;

            return true;

        }

        return false;
    }

    private boolean UserRegisterdRequests(String key) {

        return user_is_requests.contains(key);
    }


    public void RegisterUsersRequests(String key){

        user_is_requests.add(key);
        key_request_found_after_loop = true;

    }

}
