package todriver.sendrequest.Library;


import android.content.Context;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;


public class F {

    public static int ScreenHieght;
    public static int ScreenWidth;

    public static int getScreenWidth() {
        return ScreenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        ScreenWidth = screenWidth;
    }

    public static int getScreenHieght() {
        return ScreenHieght;
    }

    public static void setScreenHieght(int screenHieght) {
        ScreenHieght = screenHieght;
    }


    public static DatabaseReference Users(){
        return reference().child("Users");
    }

    public static DatabaseReference Drivers(){
        return reference().child("Drivers");
    }


    public static DatabaseReference reference(){
        return FirebaseDatabase.getInstance().getReference();
    }


    public static DatabaseReference data_and_state(){
        return FirebaseDatabase.getInstance().getReference().child("data_and_state").child(F.Uid());
    }

    public static FirebaseAuth firebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    public static FirebaseUser firebaseUser(){
        return firebaseAuth().getCurrentUser();
    }

    public static String Uid(){
        return mAuth().getUid();
    }

    public static FirebaseUser mAuth(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }


    public static String getDaviceToken(){
        return FirebaseInstanceId.getInstance().getToken();
    }


    public static DatabaseReference OnlineDrivers() {
        return reference().child("Online_Drivers");
    }

    public static void message(String message, View view, Context context, int color) {

        Snackbar snackbar = Snackbar.make(view, message+"", Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, color));
        snackbar.show();
    }

    public static int getDistanceBeforKilo(double startLat, double startLong, double endLat, double endLong) {

        Location sourceLoc = new Location("");

        sourceLoc.setLatitude(startLat);
        sourceLoc.setLongitude(startLong);

        Location destLoc = new Location("");

        destLoc.setLatitude(endLat);
        destLoc.setLongitude(endLong);

        return  (int) sourceLoc.distanceTo(destLoc);
    }

    public static Location getLocation(GoogleMap map){

        Location location = new Location("");
        location.setLatitude(map.getCameraPosition().target.latitude);
        location.setLongitude(map.getCameraPosition().target.longitude);

        return location;
    }



}
