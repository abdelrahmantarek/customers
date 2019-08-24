package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;


import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class Sheet_winsh {

    public static BottomSheet sheet_winch;
    Activity activity;
    public static boolean isShow = false;


    public static boolean winsh10 = false;



    public Sheet_winsh(final Activity activity) {

        this.activity = activity;



        sheet_winch = new BottomSheet((activity.findViewById(R.id.layout_sheet_winch)));

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



        ((activity.findViewById(R.id.click_show_wich_sheet))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sheet_All_Service.hide();
                Sheet_banzen.hide();
                Sheet_sat7a.hide();
                Sheet_Bettrey.hide();
                Sheet_bancher.hide();
                show();

            }
        });


        ((activity.findViewById(R.id.winch_10_hedrolek))).setOnClickListener(new View.OnClickListener() {
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


                setWinsh10(true);


                MapsActivity.SERVICE_PRICE = "10 ريال";


            }
        });










    }


    public static boolean isWinsh10() {
        return winsh10;
    }

    public static boolean isWinsh10_false() {
        return !winsh10;
    }







    public static void setWinsh10(boolean winsh10) {
        Sheet_winsh.winsh10 = winsh10;
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
