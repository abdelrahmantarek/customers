package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;

import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;

public class Btn_Show_Service {



    public static BottomSheet btn_show_sheet_service;
    Activity activity;
    Sheet_All_Service sheet_all_service_;

    public static boolean isShow = false;


    public Btn_Show_Service(final Sheet_All_Service sheet_all_service_, final Activity activity) {
        this.activity = activity;
        this.sheet_all_service_ = sheet_all_service_;


        isShow = true;

        btn_show_sheet_service = new BottomSheet((activity.findViewById(R.id.layout_btn_show_all_service)));

        btn_show_sheet_service.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



        ((activity.findViewById(R.id.layout_btn_show_all_service))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hide();
                ToolBarSheet.hide();
                sheet_all_service_.show();

            }
        });

    }


    public static void show(){
        btn_show_sheet_service.expandle();
    }

    public static void hide(){
        btn_show_sheet_service.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
