package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;


import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class Sheet_Bettrey {

    public static BottomSheet sheet_bettry;
    Activity activity;


    public static boolean isShow = false;
    public static boolean battry_10 = false;
    public static boolean battry_50 = false;




    public Sheet_Bettrey(final Activity activity) {

        this.activity = activity;


        sheet_bettry = new BottomSheet((activity.findViewById(R.id.layout_sheet_better)));

        sheet_bettry.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



        ((activity.findViewById(R.id.click_show_bettery_sheet))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sheet_All_Service.hide();
                Sheet_banzen.hide();
                Sheet_sat7a.hide();
                Sheet_winsh.hide();
                Sheet_bancher.hide();
                show();


            }
        });





        ((activity.findViewById(R.id.bettry_10))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sheet_All_Service.hide();
                Sheet_bancher.hide();
                Sheet_banzen.hide();
                Sheet_Bettrey.hide();
                Sheet_sat7a.hide();
                Sheet_winsh.hide();

                SheetEditTop.show();
                Btn_Send_request_To_Company.show();




                setBattry_10(true);
                setBattry_50(false);


                MapsActivity.SERVICE_PRICE = "10 ريال";

            }
        });





        ((activity.findViewById(R.id.bettry_50))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sheet_All_Service.hide();
                Sheet_bancher.hide();
                Sheet_banzen.hide();
                Sheet_Bettrey.hide();
                Sheet_sat7a.hide();
                Sheet_winsh.hide();

                SheetEditTop.show();
                Btn_Send_request_To_Company.show();



                setBattry_10(false);
                setBattry_50(true);

                MapsActivity.SERVICE_PRICE = "50 ريال";


            }
        });


    }


    public static boolean isBattry_10() {
        return battry_10;
    }

    public static boolean isBattry_50_false() {
        return !battry_50;
    }

    public static boolean isBattry_50() {
        return battry_50;
    }

    public static boolean isBattry_10_false() {
        return !battry_50;
    }

    public static void setBattry_10(boolean battry_10) {
        Sheet_Bettrey.battry_10 = battry_10;
    }



    public static void setBattry_50(boolean battry_50) {
        Sheet_Bettrey.battry_50 = battry_50;
    }

    public static void show(){
        sheet_bettry.expandle();
    }

    public static void hide(){
        sheet_bettry.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
