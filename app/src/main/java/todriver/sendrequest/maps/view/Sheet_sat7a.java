package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;


import todriver.sendrequest.D;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class Sheet_sat7a {

    public static BottomSheet sheet_sat7a;
    Activity activity;


    public static boolean isShow = false;
    public static boolean sat7a_80 = false;
    public static boolean sat7a_130_hedrolek = false;



    public Sheet_sat7a(final Activity activity) {

        this.activity = activity;


        sheet_sat7a = new BottomSheet((activity.findViewById(R.id.layout_sheet_sat7a)));

        sheet_sat7a.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



        ((activity.findViewById(R.id.click_show_sat7a_sheet))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                D.hide_all_sheet();

                show();



            }
        });





        ((activity.findViewById(R.id.sat7a_80_3ady))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                D.hide_all_sheet();

                Sheet_sat7a_3ady_details.show();



                setSat7a_80(true);
                setSat7a_130_hedrolek(false);


                MapsActivity.SERVICE_PRICE = "80 ريال";


            }
        });




        ((activity.findViewById(R.id.sat7a_130_hedrolek))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                D.hide_all_sheet();

                Sheet_sat7a_hedrolek_details.show();


                setSat7a_130_hedrolek(true);
                setSat7a_80(false);


                MapsActivity.SERVICE_PRICE = "130 ريال";


            }
        });




    }


    public static boolean isSat7a_80() {
        return sat7a_80;
    }


    public static boolean isSat7a_80_false() {
        return !sat7a_80;
    }


    public static boolean isSat7a_130_hedrolek() {
        return sat7a_130_hedrolek;
    }
   public static boolean isSat7a_hedrolek_false() {
        return !sat7a_130_hedrolek;
    }

    public static void setSat7a_80(boolean sat7a_80) {
        Sheet_sat7a.sat7a_80 = sat7a_80;
    }

    public static void setSat7a_130_hedrolek(boolean sat7a_130_hedrolek) {
        Sheet_sat7a.sat7a_130_hedrolek = sat7a_130_hedrolek;
    }

    public static void show(){
        sheet_sat7a.expandle();
    }

    public static void hide(){
        sheet_sat7a.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
