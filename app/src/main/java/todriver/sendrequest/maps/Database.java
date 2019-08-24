package todriver.sendrequest.maps;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;


import todriver.sendrequest.Library.F;

import todriver.sendrequest.Library.StateClass;
import todriver.sendrequest.maps.Listener.On_Request_Or_Service;

import static todriver.sendrequest.Library.Clasess.GetLocationClass.MY_LOCATION;
import static todriver.sendrequest.maps.MapsActivity.LOCATION_A;
import static todriver.sendrequest.maps.MapsActivity.LOCATION_B;


public class Database {




    public Activity activity;

    private On_Request_Or_Service on_request_or_service;

    private ChildEventListener childEventListener;

    private DatabaseReference databaseReference;


    public  final String REQUEST_REMOVE = "REQUEST_REMOVE";
    public  final String SERVICE_REMOVE = "SERVICE_REMOVE";
    public  final String SERVICE = "SERVICE";
    public  final String REQUEST = "REQUEST";



    public void setOn_request_or_service(On_Request_Or_Service on_request_or_service) {
        this.on_request_or_service = on_request_or_service;

        Check_Service_Request();
    }

    public Database(Activity activity) {
        this.activity = activity;
    }


    public void Check_Service_Request(){

        Log.d("UID",F.Uid() +"");

        StateClass stateClass = new StateClass();
        stateClass.setOnGetStateEventListener(new StateClass.onGetStateEventListener() {
            @Override
            public void onNo(DataSnapshot your_data_colunm_Users) {

                on_request_or_service.onCheckNo(your_data_colunm_Users);


            }

            @Override
            public void onService(DataSnapshot dataSnapshot) {

                String user_id = dataSnapshot.child("user_id").getValue(String.class);

                on_request_or_service.onService(user_id,dataSnapshot);

            }

            @Override
            public void onRequest(DataSnapshot dataSnapshot) {

                String user_id = dataSnapshot.child("user_id").getValue(String.class);

                on_request_or_service.onRequest(user_id,dataSnapshot);


            }

            @Override
            public void onRequestRemove(DataSnapshot dataSnapshot) {

                on_request_or_service.onRequestRemove(dataSnapshot);


            }

            @Override
            public void onServiceRemove(DataSnapshot dataSnapshot) {

                on_request_or_service.onServiceRemove(dataSnapshot);
            }
        });


    }




    public void onStop() {


    }

    public void onResume() {

    }

    public void onStart() {


    }

    public void onDestroy() {

    }

    public void onPause() {

    }





    public  void Add_MyLocation(){

        if (MY_LOCATION==null){

            return;
        }

        if (F.firebaseUser() == null){
            return;
        }

        GeoFire geoFire;
        geoFire = new GeoFire(F.Users().child(F.Uid()).child("location"));
        geoFire.setLocation(F.Uid(), new GeoLocation(MY_LOCATION.getLatitude(), MY_LOCATION.getLongitude()), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                //   Snackbar.make(getlayout,"انت الان نشط تنتطر عميلا", Snackbar.LENGTH_LONG).show();

            }
        });

    }


    public void CancelRequest(final String user_id) {


        String state_user_id = "state/"+user_id;
        String state_uid = "state/"+ F.Uid();


        final Map<String,Object> hashMap = new HashMap<>();

        hashMap.put(state_user_id,null);
        hashMap.put(state_uid,null);

        F.reference().updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {



            }
        });
    }


    public void SendRequestWithData(final String user_id) {



        if (LOCATION_B!=null && LOCATION_A!=null){


            String state_uid = "state/"+F.Uid();
            String state_user_id = "state/"+user_id;

            final Map<String,Object> hashMap = new HashMap<>();

            hashMap.put(state_user_id,null);
            hashMap.put(state_uid,null);
            hashMap.put("notifications/" +  "/requests" + "/" + F.Uid() + "/" + user_id + "/state", null);



            F.reference().updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    final Map<String,Object> hash_main = new HashMap<>();


                    String from_lat = F.Uid()+"/from_lat";
                    String from_lng = F.Uid()+"/from_lng";
                    String to_lat = F.Uid()+"/to_lat";
                    String to_lng = F.Uid()+"/to_lng";
                    String text_from = F.Uid()+"/text_from";
                    String text_to = F.Uid()+"/text_to";
                    String kindService = F.Uid()+"/service_kind";
                    String priceService = F.Uid()+"/service_price";
                    String note = F.Uid()+"/note";
                    String set_uid_inside_another_user = user_id+"/user_id";
                    String state_you = F.Uid()+"/state";



                    String from_lat_another = user_id+"/from_lat";
                    String from_lng_another = user_id+"/from_lng";
                    String to_lat_another = user_id+"/to_lat";
                    String to_lng_another = user_id+"/to_lng";
                    String text_from_another = user_id+"/text_from";
                    String text_to_another = user_id+"/text_to";
                    String kindService_another = user_id+"/service_kind";
                    String priceService_another = user_id+"/service_price";
                    String note_another = user_id+"/note";
                    String state_another = user_id+"/state";
                    String set_user_id_inside_you = F.Uid()+"/user_id";






                    hash_main.put("state/"+from_lat, LOCATION_A.getLatitude());
                    hash_main.put("state/"+from_lng, LOCATION_A.getLongitude());
                    hash_main.put("state/"+to_lat, LOCATION_B.getLatitude());
                    hash_main.put("state/"+to_lng, LOCATION_B.getLongitude());
                    hash_main.put("state/"+text_from, MapsActivity.TEXT_FROM);
                    hash_main.put("state/"+text_to,MapsActivity.TEXT_TO);
                    hash_main.put("state/"+kindService,"kindService");
                    hash_main.put("state/"+priceService,MapsActivity.SERVICE_PRICE);
                    hash_main.put("state/"+note,"note");
                    hash_main.put("state/"+set_user_id_inside_you,user_id);
                    hash_main.put("state/"+state_you,"request");



                    hash_main.put("state/"+from_lat_another, LOCATION_A.getLatitude());
                    hash_main.put("state/"+from_lng_another, LOCATION_A.getLongitude());
                    hash_main.put("state/"+to_lat_another, LOCATION_B.getLatitude());
                    hash_main.put("state/"+to_lng_another, LOCATION_B.getLongitude());
                    hash_main.put("state/"+text_from_another, MapsActivity.TEXT_FROM);
                    hash_main.put("state/"+text_to_another,MapsActivity.TEXT_TO);
                    hash_main.put("state/"+kindService_another,"kindService");
                    hash_main.put("state/"+priceService_another,MapsActivity.SERVICE_PRICE);
                    hash_main.put("state/"+note_another,"note");
                    hash_main.put("state/"+set_uid_inside_another_user,F.Uid());
                    hash_main.put("state/"+state_another,"request");

                    hash_main.put("notifications/" +  "/requests" + "/" + F.Uid() + "/" + user_id + "/state", "request");


                    F.reference().updateChildren(hash_main).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {




                        }
                    });
                }
            });

        }else {
            Toast.makeText(activity, "من فضلك قم باختيار الاماكن", Toast.LENGTH_SHORT).show();
        }

    }




    public void deleteService(String user_id) {

        String state_user_id = "state/"+F.Uid();
        String state_uid = "state/"+ user_id;

        final Map<String,Object> hashMap = new HashMap<>();

        hashMap.put(state_user_id,null);
        hashMap.put(state_uid,null);

        F.reference().updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


            }
        });

    }

}
