package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;

import todriver.sendrequest.Library.Listener.TopSheetEvent;
import todriver.sendrequest.Library.helper.TopSheet;
import todriver.sendrequest.R;

public class ToolBarSheet {


    public static TopSheet tool_bar_sheet;
    Activity activity;
    Sheet_All_Service sheet_all_service_;

    public static boolean isShow = false;


    public ToolBarSheet(final Activity activity) {
        this.activity = activity;


        isShow = true;

        tool_bar_sheet = new TopSheet((activity.findViewById(R.id.layout_sheet_tool_bar)));

        tool_bar_sheet.addBottomSheetEvent(new TopSheetEvent() {
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
        tool_bar_sheet.expandle();
    }

    public static void hide(){
        tool_bar_sheet.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
