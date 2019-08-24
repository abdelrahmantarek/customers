package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;

import todriver.sendrequest.D;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class Sheet_Taxi {


    public static BottomSheet sheet_taxi;

    Activity activity;
    public static boolean isShow = false;


    public static boolean taxi10 = false;


    public Sheet_Taxi(final Activity activity) {
        this.activity = activity;


        sheet_taxi = new BottomSheet((activity.findViewById(R.id.layout_sheet_taxi)));

        sheet_taxi.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



        ((activity.findViewById(R.id.click_show_taxi))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                D.hide_all_sheet();

                show();



            }
        });





        ((activity.findViewById(R.id.winch_10_taxi))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                D.hide_all_sheet();


                SheetEditTop.show();
                Btn_Send_request_To_Company.show();

                setTaxi10(true);

                MapsActivity.SERVICE_PRICE = "10 ريال";



            }
        });


    }


    public static boolean isTaxi10() {
        return taxi10;
    }


    public static void setTaxi10(boolean taxi10) {
        taxi10 = taxi10;
    }

    public static void show(){
        sheet_taxi.expandle();
    }

    public static void hide(){
        sheet_taxi.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
