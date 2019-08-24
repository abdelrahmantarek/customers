package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;


import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class Sheet_banzen {

    public static BottomSheet sheet_banzen;
    Activity activity;


    public static boolean isShow = false;
    public static boolean banzen_10 = false;
    public static boolean banzen_50 = false;




    public Sheet_banzen(final Activity activity) {
        this.activity = activity;


        sheet_banzen = new BottomSheet((activity.findViewById(R.id.layout_sheet_banzen)));

        sheet_banzen.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



        ((activity.findViewById(R.id.click_show_banzen_sheet))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sheet_All_Service.hide();
                Sheet_sat7a.hide();
                Sheet_Bettrey.hide();
                Sheet_winsh.hide();
                Sheet_bancher.hide();

                show();

            }
        });





        ((activity.findViewById(R.id.banzen_10))).setOnClickListener(new View.OnClickListener() {
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

                setBanzen_10(true);
                setBanzen_50(false);

                MapsActivity.SERVICE_PRICE = "10 ريال";



            }
        });



        ((activity.findViewById(R.id.banzen_50))).setOnClickListener(new View.OnClickListener() {
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

                setBanzen_10(false);
                setBanzen_50(true);

                MapsActivity.SERVICE_PRICE = "50 ريال";



            }
        });




    }


    public static boolean isBanzen_10() {
        return banzen_10;
    }

    public static boolean isBanzen_10_false() {
        return !banzen_10;
    }

    public static boolean isBanzen_50() {
        return banzen_50;
    }

    public static boolean isBanzen_50_false() {
        return !banzen_50;
    }

    public static void setBanzen_10(boolean banzen_10) {
        Sheet_banzen.banzen_10 = banzen_10;
    }

    public static void setBanzen_50(boolean banzen_50) {
        Sheet_banzen.banzen_50 = banzen_50;
    }




    public static void show(){
        sheet_banzen.expandle();
    }

    public static void hide(){
        sheet_banzen.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
