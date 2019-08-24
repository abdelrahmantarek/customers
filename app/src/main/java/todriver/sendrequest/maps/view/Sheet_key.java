package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;

import todriver.sendrequest.D;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class Sheet_key {


    public static BottomSheet sheet_key;

    Activity activity;
    public static boolean isShow = false;


    public static boolean key_50 = false;
    public static boolean key_100 = false;



    public Sheet_key(final Activity activity) {
        this.activity = activity;


        sheet_key = new BottomSheet((activity.findViewById(R.id.layout_sheet_key)));

        sheet_key.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });




        ((activity.findViewById(R.id.click_show_open_key))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sheet_All_Service.hide();
                Sheet_bancher.hide();
                Sheet_banzen.hide();
                Sheet_Bettrey.hide();
                Sheet_sat7a.hide();
                SheetEditTop.hide();
                Sheet_winsh.hide();
                Btn_Show_Service.hide();
                ToolBarSheet.hide();

                show();
            }
        });





        ((activity.findViewById(R.id.key_50))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                D.hide_all_sheet();

                SheetEditTop.show();
                Btn_Send_request_To_Company.show();


                setKey_50(true);
                setKey_100(false);

                MapsActivity.SERVICE_PRICE = "50 ريال";

            }
        });





        ((activity.findViewById(R.id.key_100))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                D.hide_all_sheet();

                SheetEditTop.show();
                Btn_Send_request_To_Company.show();


                setKey_50(false);
                setKey_100(true);


                MapsActivity.SERVICE_PRICE = "100 ريال";
            }
        });



    }


    public static boolean isKey_50() {
        return key_50;
    }

    public static boolean isKey_100() {
        return key_100;
    }
    public static boolean isKey_50_false() {
        return !key_50;
    }

    public static boolean isKey_100_false() {
        return !key_50;
    }

    public static void setKey_50(boolean key_50) {
        Sheet_key.key_50 = key_50;
    }



    public static void setKey_100(boolean key_100) {
        Sheet_key.key_100 = key_100;
    }

    public static void show(){
        sheet_key.expandle();
    }

    public static void hide(){
        sheet_key.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
