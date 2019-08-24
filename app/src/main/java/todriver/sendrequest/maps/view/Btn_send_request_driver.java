package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.Database;
import todriver.sendrequest.maps.MapsActivity;

import static todriver.sendrequest.maps.MapsActivity.LOCATION_A;
import static todriver.sendrequest.maps.MapsActivity.LOCATION_B;


public class Btn_send_request_driver {


    public static BottomSheet sheet_winch;
    Activity activity;
    public static boolean isShow = false;

    private ArrayList<String> driver;
    private boolean driver_found;
    private Database database;




    private boolean driver_requests = false;

    private String last_request= "";

    public Btn_send_request_driver(final Activity activity, boolean driver_found, final ArrayList<String> driver, Database database, final GoogleMap map) {
        this.activity = activity;
        this.driver_found = driver_found;
        this.driver = driver;
        this.database = database;



        sheet_winch = new BottomSheet((activity.findViewById(R.id.layout_sheet_edit_bottom)));

        sheet_winch.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



        ((activity.findViewById(R.id.click_get_driver))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (LOCATION_B!=null && LOCATION_A!=null){

                    F.message("من فضلك قم باختيار الاماكن",(activity.findViewById(R.id.drawer_layout)),activity,R.color.red_A700);

                    return;
                }



                if (driver.size()>0){

                    map.setOnCameraMoveListener(null);
                    map.setOnCameraIdleListener(null);


                    for (String user_id : driver){



                        last_request = user_id;



                        Sheet_All_Service.hide();
                        Sheet_bancher.hide();
                        Sheet_banzen.hide();
                        Sheet_Bettrey.hide();
                        Sheet_sat7a.hide();
                        SheetEditTop.hide();
                        Sheet_winsh.hide();
                        Btn_Show_Service.hide();
                        ToolBarSheet.hide();
                        hide();

                        Toast.makeText(activity, user_id+"", Toast.LENGTH_SHORT).show();

                          SendRequestWithData(user_id);


                        return;
                    }

                }else {

                    F.message("لا يوجد سائقين حول الجوار الان",(activity.findViewById(R.id.drawer_layout)),activity,R.color.red_A700);

                }




            }
        });
    }



    private void SendRequestWithData(final String user_id) {

        database.SendRequestWithData(user_id);

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
}
