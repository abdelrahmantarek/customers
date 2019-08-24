package todriver.sendrequest.maps;


import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.geofire.GeoLocation;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import todriver.sendrequest.D;
import todriver.sendrequest.Library.BoomAnimation;
import todriver.sendrequest.Library.Clasess.CameraMoveAddress;
import todriver.sendrequest.Library.Clasess.GetLocationClass;
import todriver.sendrequest.Library.Clasess.LocationUpdate;
import todriver.sendrequest.Library.Clasess.MapStyle;
import todriver.sendrequest.Library.Clasess.SharePref;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.Listener.OnGet_Location_And_Zoom_map;
import todriver.sendrequest.Library.Listener.OnLocation_Update;
import todriver.sendrequest.Library.OnBoomAnimation;
import todriver.sendrequest.Library.ShowMapClass;
import todriver.sendrequest.Library.ShowMap_EventListener;
import todriver.sendrequest.Library.Tools;
import todriver.sendrequest.Login.LoginActivity;
import todriver.sendrequest.Login.WelcomeActivity;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.view.BottomSheetService;
import todriver.sendrequest.maps.Listener.On_Request_Or_Service;
import todriver.sendrequest.maps.helper.helper;
import todriver.sendrequest.maps.view.Btn_Send_request_To_Company;
import todriver.sendrequest.maps.view.Btn_Show_Service;
import todriver.sendrequest.maps.view.FabClick;
import todriver.sendrequest.NAV.NavigationDrawer;
import todriver.sendrequest.maps.view.OnBackPress;
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
import todriver.sendrequest.maps.view.ShowMap;
import todriver.sendrequest.maps.view.ToolBarSheet;
import todriver.sendrequest.model.Drivers;
import todriver.sendrequest.model.Service;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,On_Request_Or_Service {


    public List<Marker> MARKERS_CUSTOMER_ONLINE = new ArrayList<Marker>();
    public ArrayList<String> user_is_requests = new ArrayList<>();


    @Override
    public void onCheckNo(DataSnapshot dataSnapshot) {

        Request = false;
        Service = false;

        start_no_frist_time(dataSnapshot);
    }

    @Override
    public void onRequestRemove(DataSnapshot dataSnapshot) {

        Request = false;

        if (d==null){
            d = new D();
        }
        d.hide_all_sheet();

        start_no_frist_time(dataSnapshot);


    }
    @Override
    public void onServiceRemove(DataSnapshot dataSnapshot) {

        Service = false;
        Request = false;

        if (d==null){
            d = new D();
        }
        d.hide_all_sheet();


        start_no_frist_time(dataSnapshot);

    }

    @Override
    public void onService(String user_id, DataSnapshot dataSnapshot) {

        Service = true;

        start_Service(dataSnapshot,user_id);

    }

    private void start_Service(DataSnapshot dataSnapshot, final String user_id){

        onServiceStart(dataSnapshot,user_id);

    }

    @Override
    public void onRequest(final String user_id, DataSnapshot dataSnapshot) {


        Request = true;
        user_id_request = user_id;

        start_Request(dataSnapshot,user_id);


    }


    private void start_Request(final DataSnapshot dataSnapshot_user_id, final String user_id){


        onRequestStart(dataSnapshot_user_id,user_id);
    }




    public static String DISTANCE;
    private ArrayList<String> driver = new ArrayList<>();
    private ArrayList<String> driverlist2 = new ArrayList<>();
    private boolean driver_found = false;


    private String user_id_request;

    private boolean Request = false;
    private boolean Service = false;
    private DrawerLayout drawer_layout;


    private Sheet_All_Service sheet_all_service;
    private Btn_Show_Service btn_show_service;
    private ToolBarSheet toolBarSheetSheet;


    public void removeUser_request(String user_id_request){

        int index = 0;

        for (String user_id : request_list) {
            if (user_id !=null){
                if (user_id.equals(user_id_request)) {
                    request_list.remove(index);
                    return;
                }else {
                    index++;
                }

            }
        }
    }


    private LinearLayout click_show_open_key;


    private Sheet_bancher sheet_bancher;
    private Sheet_banzen sheet_banzen;
    private Sheet_Bettrey sheet_bettrey;
    private Sheet_sat7a sheet_sat7a;
    private Sheet_sat7a_3ady_details sheet_sat7A_3ady_details;
    private Sheet_sat7a_hedrolek_details sheet_sat7a_hedrolek_details;
    private Sheet_key sheet_key;
    private Sheet_winsh sheet_winsh;
    private SheetEditTop sheetEditTop;
    private Btn_send_request_driver btnsendrequestdriver;
    private Btn_Send_request_To_Company btn_send_request_to_company;
    private Sheet_Trasnport_Btween_city sheet_trasnport_btween_city;
    private Sheet_Taxi sheet_taxi;

    private BottomSheetService bottomSheetService;



    private CameraMoveAddress cameraMoveAddress;



    public static Location LOCATION_A;
    public static Location LOCATION_B;


    public static String TEXT_FROM;
    public static String TEXT_TO;


    public static String NOTE;

    public static String SERVICE_KIND;

    public static String SERVICE_PRICE;



    LocationUpdate locationUpdate;
    NavigationDrawer navigationDrawer;
    Database database;
    GetLocationClass location;
    GoogleMap map;



    RequestClass requestClass;
    Toolbar toolbar;
    FabClick fabClick;
    ShowMap showMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_maps_activity);
		
		setTitle(R.string.app_name);
		
		ChangeColorTopLineBar();
		
		setSizeScreen();
		
        IntentAnimation();



        if (F.firebaseAuth().getCurrentUser() == null){
            return;
        }

        initMapFragment();		


      




    }
	
		
	public void ChangeColorTopLineBar(){
		Tools.setSystemBarColor(this, R.color.colorPrimaryDark);
	}
	public void setSizeScreen(){
		  helper.screen(this);
	}
    private void IntentAnimation() {


        BoomAnimation boomAnimation = new BoomAnimation(((findViewById(R.id.boom_animation_layout))),this);
        boomAnimation.setOnBoomAnimation(new OnBoomAnimation() {
            @Override
            public void onFinish() {

                if (F.firebaseAuth().getCurrentUser() == null){

                    IntentToWelcomeActivity();
                    return;
                }


                SharePref sharePref = new SharePref(MapsActivity.this);


                if (!sharePref.exist(sharePref.database_name)){

                    IntentToWelcomeActivity();
                    return;
                }


		 (findViewById(R.id.boom_animation_layout)).setVisibility(View.GONE);
          (findViewById(R.id.boom_animation_layout_image)).setVisibility(View.GONE);   
		      

            }
        });


    }
	public void IntentToWelcomeActivity(){
	     // Intent mainIntent = new Intent(getContext(), LoginActivity.class);
                    Intent mainIntent = new Intent(MapsActivity.this, WelcomeActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
	}

	public void initMapFragment(){
		
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.no_map);
        mapFragment.getMapAsync(this);
	}






    @Override
    protected void onStart() {
        super.onStart();
        if (F.firebaseAuth().getCurrentUser() == null){
            return;
        }

        if (database!=null){
            database.onStart();
        }
    }



    @Override
    protected void onStop() {
        super.onStop();
        if (F.firebaseAuth().getCurrentUser() == null){
            return;
        }

        if (database!=null){
            database.onStop();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (F.firebaseAuth().getCurrentUser() == null){
            return;
        }

        if (database!=null){
            database.onPause();
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (F.firebaseAuth().getCurrentUser() == null){
            return;
        }

        if (database!=null){
            database.onResume();
        }



    }

    OnBackPress onBackPress;
    @Override
    public void onBackPressed() {
        if (F.firebaseAuth().getCurrentUser() == null){
            return;
        }



        if (onBackPress.backPressState(this,Request,Service)){
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (F.firebaseAuth().getCurrentUser() == null){
            return;
        }
        if (database!=null){
            database.onDestroy();
        }

    }










    public static D d;



    LatLng LatLng_A;
    LatLng LatLng_B;
    private ShowMapClass showMapClass;
    ArrayList<String> request_list = new ArrayList<>();
    MapStyle mapStyle;








    // loadMyData when Service
    private void loadMyData() {
        F.Users().child(F.Uid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                navigationDrawer.setMyData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    // onMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        if (F.firebaseAuth().getCurrentUser() == null){
            return;
        }


        new_For_All_Class();


        Get_Location_And_Zoom();


    }





    // new For All Classes
    private void new_For_All_Class() {

         drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
         navigationDrawer = new NavigationDrawer(this);
         setSupportActionBar(toolbar);

         ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                 this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer_layout.addDrawerListener(toggle);
         toggle.syncState();
         toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
         fabClick = new FabClick(MapsActivity.this,map);
        mapStyle = new MapStyle(this,map);
        mapStyle.checkMapStyle();
        location = new GetLocationClass(this,map);
        database = new Database(this);
        click_show_open_key = findViewById(R.id.click_show_open_key);
        sheet_all_service = new Sheet_All_Service(this);
        btn_show_service = new Btn_Show_Service(sheet_all_service,this);
        toolBarSheetSheet = new ToolBarSheet(this);
        onBackPress = new OnBackPress(btn_show_service, sheet_all_service,database,user_id_request);
        sheet_bancher = new Sheet_bancher(this);
        bottomSheetService = new BottomSheetService(this);
        sheet_banzen = new Sheet_banzen(this);
        sheet_bettrey = new Sheet_Bettrey(this);
        sheet_sat7a = new Sheet_sat7a(this);
        sheet_sat7A_3ady_details = new Sheet_sat7a_3ady_details(this,map,database,MARKERS_CUSTOMER_ONLINE,user_is_requests,driver_found);
        sheet_sat7a_hedrolek_details = new Sheet_sat7a_hedrolek_details(this,map,database,MARKERS_CUSTOMER_ONLINE,user_is_requests,driver_found);
        sheet_key = new Sheet_key(this);
        sheet_winsh = new Sheet_winsh(this);
        sheet_taxi = new Sheet_Taxi(this);
        sheet_trasnport_btween_city = new Sheet_Trasnport_Btween_city(this);
        cameraMoveAddress = new CameraMoveAddress(map,MapsActivity.this);
        sheetEditTop = new SheetEditTop(this,cameraMoveAddress,map);
        btnsendrequestdriver = new Btn_send_request_driver(this,driver_found,driver,database,map);
        btn_send_request_to_company = new Btn_Send_request_To_Company(this);
        locationUpdate = new LocationUpdate(this);
        requestClass = new RequestClass(MapsActivity.this,map,database);
        navigationDrawer.navigation_Drawer();

        Btn_Show_Service.show();

    }
	

    // get Location Zoom
	private void Get_Location_And_Zoom() {
        location.setZoomAnimation(true);
        location.setOn_Get_Location_And_Zoom_map(new OnGet_Location_And_Zoom_map() {
            @Override
            public void onFinishGettingLocation(LatLng latLng, Location location) {
			


            }
        });

        Check_Request_Or_Service();
    }
	

    // location update
    private void onLocationUpdate() {

        locationUpdate.setOn_Location_Update(new OnLocation_Update() {
            @Override
            public void onLocationChanged(Location location, LatLng latLng) {

                if (F.firebaseUser() == null){
                    return;
                }

                if (d!=null){

                    d.marker_location_update(latLng,map);
                }

                database.Add_MyLocation();

            }

            @Override
            public void onLocationStop() {

            }
        });
    }


    // check state database after location zoom
    private void Check_Request_Or_Service() {

        database.setOn_request_or_service(this);

    }


    // show driver service
    private void show_driver_service(String user_id_service) {

        if (showMapClass == null){
            showMapClass = new ShowMapClass();
        }


        MapsActivity.d.map_clear(this,map);
        showMapClass.setMap(map);
        showMapClass.setTitleMarker("مقدم خدمتك");
        showMapClass.setReduis(8000);
        showMapClass.setDatabaseReference(F.Drivers().child(user_id_service).child("location"));
        showMapClass.setResourseMarkerDrawAbe(R.drawable.car_map);
        showMapClass.setSearch(false);
        showMapClass.setLocation(GetLocationClass.MY_LOCATION);
        showMapClass.addShowMapEventListener(new ShowMap_EventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {

                showMapClass.driverFound = true;





                Location location1 = new Location("s");
                location1.setLatitude(location.latitude);
                location1.setLongitude(location.longitude);

                // enable whem location available
                fabClick.on_Click_get_location_client(location1);


                request_list.add(key);

            }

            @Override
            public void onKeyExited(String key,int index) {

                //  driver.remove(key);

                removeUser_request(key);
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


    // show driver request


    private void show_driver_request(String user_id_request) {

        if (showMapClass == null){
            showMapClass = new ShowMapClass();
        }


        showMapClass.hide();
        MapsActivity.d.map_clear(this,map);
        showMapClass.setMap(map);
        showMapClass.setTitleMarker("في انتظار رد مقدم الخدمة");
        showMapClass.setReduis(8000);
        showMapClass.setDatabaseReference(F.Drivers().child(user_id_request).child("location"));
        showMapClass.setResourseMarkerDrawAbe(R.drawable.car_map);
        showMapClass.setSearch(false);
        showMapClass.setLocation(GetLocationClass.MY_LOCATION);
        showMapClass.addShowMapEventListener(new ShowMap_EventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {



            }

            @Override
            public void onKeyExited(String key,int index) {


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




    // start Service
    private void onServiceStart(DataSnapshot dataSnapshot_service,String user_id) {


        if (d==null){
            d = new D();
        }


        Service service = dataSnapshot_service.getValue(Service.class);

        LatLng_A = new LatLng(service.getFrom_lat(),service.getFrom_lng());
        LatLng_B = new LatLng(service.getTo_lat(),service.getTo_lng());

        d.hide_all_sheet();
        show_driver_service(user_id);
        d.map_clear(MapsActivity.this,map);
        d.hide_floationg(((FloatingActionMenu)findViewById(R.id.no_map_floating_action_menu))
                ,((FloatingActionMenu)findViewById(R.id.service_map_floating_action_menu)));



        d.addPolylineAndMarker(LatLng_A,LatLng_B,service.getText_from(),service.getText_to(),map);

        fabClick.fabService(dataSnapshot_service,user_id);

        bottomSheetService.set_Data(service,user_id, request_list, request_list);

        loadMyData();
        location.setZoomAnimation(true);
        location.setOn_Get_Location_And_Zoom_map(new OnGet_Location_And_Zoom_map() {
            @Override
            public void onFinishGettingLocation(LatLng latLng, Location location) {

              onLocationUpdate();


            }
        });




    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, "onConfigurationChanged", Toast.LENGTH_SHORT).show();
        navigationDrawer.chengeLanguage();
    }

    // start Request
    private void onRequestStart(DataSnapshot dataSnapshot_service, String user_id) {


        if (d==null){
            d = new D();
        }

        Log.d("Error", dataSnapshot_service.child("user_id").getValue(String.class) + "");

        try {

            d.hide_all_sheet();

            d.map_clear(MapsActivity.this,map);

            Service service = dataSnapshot_service.getValue(Service.class);
            LatLng_A = new LatLng(service.getFrom_lat(),service.getFrom_lng());
            LatLng_B = new LatLng(service.getTo_lat(),service.getTo_lng());


            Drivers drivers = dataSnapshot_service.getValue(Drivers.class);

            requestClass.setData(drivers,dataSnapshot_service,user_id,request_list);

            show_driver_request(user_id);

            d.addPolylineAndMarker(LatLng_A,LatLng_B,service.getText_from(),service.getText_to(),map);

            RequestClass.show();

            sheetEditTop.onRequestStart();


            loadMyData();

        }catch (Exception e){

            Log.d("Exception", e + "");
        }


    }





    private void start_no_frist_time(DataSnapshot dataSnapshot) {

        if (d==null){
            d = new D();
        }


        d.map_clear(this,map);
        d.show_no_Sheet();
        d.hide_floationg(((FloatingActionMenu)findViewById(R.id.service_map_floating_action_menu))
                ,((FloatingActionMenu)findViewById(R.id.no_map_floating_action_menu)));




        loadMyData();
        location.setZoomAnimation(true);
        location.setOn_Get_Location_And_Zoom_map(new OnGet_Location_And_Zoom_map() {
            @Override
            public void onFinishGettingLocation(LatLng latLng, Location location) {

                showMap = new ShowMap(map,MapsActivity.this,MARKERS_CUSTOMER_ONLINE,driver_found);
                showMap.ShowAll(location);
                onLocationUpdate();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        location.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}


