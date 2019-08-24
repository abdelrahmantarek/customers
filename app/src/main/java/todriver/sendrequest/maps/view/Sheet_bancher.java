package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;


import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class Sheet_bancher {


    public static BottomSheet sheet_bacnher;
    Activity activity;
    public static boolean isShow = false;

    public static boolean bancher_30 = false;
    public static boolean bancher_50 = false;
    public static boolean bancher_100 = false;



    public Sheet_bancher(final Activity activity) {
        this.activity = activity;


        sheet_bacnher = new BottomSheet((activity.findViewById(R.id.layout_sheet_bancher)));

        sheet_bacnher.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



        ((activity.findViewById(R.id.click_show_change_bancher))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sheet_All_Service.hide();
                Sheet_banzen.hide();
                Sheet_sat7a.hide();
                Sheet_Bettrey.hide();
                Sheet_winsh.hide();

                show();



            }
        });




        ((activity.findViewById(R.id.bancher_50))).setOnClickListener(new View.OnClickListener() {
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

                setBancher_50(true);
                setBancher_100(false);
                setBancher_30(false);

                MapsActivity.SERVICE_PRICE = "50 ريال";


            }
        });





        ((activity.findViewById(R.id.bancher_100))).setOnClickListener(new View.OnClickListener() {
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

                setBancher_50(false);
                setBancher_100(true);
                setBancher_30(false);

                MapsActivity.SERVICE_PRICE = "100 ريال";


            }
        });





        ((activity.findViewById(R.id.bancher_30))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // hide all sheet



                Sheet_All_Service.hide();
                Sheet_bancher.hide();
                Sheet_banzen.hide();
                Sheet_Bettrey.hide();
                Sheet_sat7a.hide();
                Sheet_winsh.hide();

                SheetEditTop.show();
                Btn_Send_request_To_Company.show();


                // false other state


                setBancher_50(false);
                setBancher_100(false);
                setBancher_30(true);


                MapsActivity.SERVICE_PRICE = "30 ريال";




            }
        });


    }

    public static boolean isBancher_30() {
        return bancher_30;
    }


    public static boolean isBancher_30_false() {
        return !bancher_30;
    }


    public static boolean isBancher_50() {
        return bancher_50;
    }


    public static boolean isBancher_50_false() {
        return !bancher_50;
    }

    public static boolean isBancher_100() {
        return bancher_100;
    }


    public static boolean isBancher_100_false() {
        return !bancher_100;
    }

    public static void setBancher_30(boolean bancher_30) {
        Sheet_bancher.bancher_30 = bancher_30;
    }

    public static void setBancher_50(boolean bancher_50) {
        Sheet_bancher.bancher_50 = bancher_50;
    }

    public static void setBancher_100(boolean bancher_100) {
        Sheet_bancher.bancher_100 = bancher_100;
    }

    public static void show(){
        sheet_bacnher.expandle();
    }

    public static void hide(){
        sheet_bacnher.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
