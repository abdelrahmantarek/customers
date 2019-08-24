package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.Database;
import todriver.sendrequest.model.Drivers;
import todriver.sendrequest.model.Service;

public class RequestClass {



    public static BottomSheet bottomSheet;
    private View layou_sheet;
    private Activity activity;
    private String user_id;
    private Database database;

    public static boolean isShow = false;
    public boolean driver_found;


    private ArrayList<String> driver;

    public RequestClass(Activity activity, GoogleMap map, Database database) {
        this.activity = activity;
        this.user_id = user_id;
        this.database = database;
        this.driver_found = driver_found;
        this.driver = driver;


        bottomSheet = new BottomSheet((activity.findViewById(R.id.layout_sheet_request)));
        bottomSheet.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;

            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



    }









    public static void show(){
        bottomSheet.expandle();
    }

    public static void hide(){
        bottomSheet.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }




    public void setData(Drivers service, DataSnapshot dataSnapshot, final String user_id, final ArrayList<String> requestList) {


        F.Drivers().child(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Service service1 = dataSnapshot.getValue(Service.class);

                TextView textname = activity.findViewById(R.id.request_name_user);
                textname.setText(service1.getName());


                CircleImageView circleImageView = activity.findViewById(R.id.request_image_user);

                Glide.with(activity.getApplicationContext()).load(service1.getImage()).into(circleImageView);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        activity.findViewById(R.id.request_cancel_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database.CancelRequest(user_id);

                removeUser_request(user_id,requestList);
            }
        });

    }

    public void removeUser_request(String user_id_request, ArrayList<String> requestList){

        int index = 0;

        for (String user_id : requestList) {
            if (user_id !=null){
                if (user_id.equals(user_id_request)) {
                    requestList.remove(index);
                    return;
                }else {
                    index++;
                }

            }
        }
    }

}
