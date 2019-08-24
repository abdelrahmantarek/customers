package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;


import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;

public class Sheet_All_Service {


    public static BottomSheet sheet_all_service;
    Activity activity;

    private static boolean isShow = false;

    public Sheet_All_Service(Activity activity) {
        this.activity = activity;

        sheet_all_service = new BottomSheet((activity.findViewById(R.id.layout_sheet_all_service)));

        sheet_all_service.addBottomSheetEvent(new BottomSheetEvent() {
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


    public static  void show(){
        sheet_all_service.expandle();
    }

    public static void hide(){
        sheet_all_service.collapsed();
    }
    public static boolean isShow(){

        return isShow;
    }

    public static boolean isHide(){
        return !isShow;
    }
}
