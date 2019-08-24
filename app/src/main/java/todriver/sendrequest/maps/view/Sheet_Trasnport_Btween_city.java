package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;

import todriver.sendrequest.D;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

public class Sheet_Trasnport_Btween_city {

    public static BottomSheet sheet_transport_btween_city;

    Activity activity;
    public static boolean isShow = false;

    public static boolean transprt_btween_city_50 = false;



    public Sheet_Trasnport_Btween_city(final Activity activity) {
        this.activity = activity;


        sheet_transport_btween_city = new BottomSheet((activity.findViewById(R.id.layout__sheet_transport_btween_city)));

        sheet_transport_btween_city.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });



        ((activity.findViewById(R.id.click_show_transprot_with_city_sheet))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                D.hide_all_sheet();

                show();

            }
        });




        ((activity.findViewById(R.id.transport_50_btween_city))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                D.hide_all_sheet();


                SheetEditTop.show();
                Btn_Send_request_To_Company.show();

                setTransprt_btween_city_50(true);


                MapsActivity.SERVICE_PRICE = "50 ريال";


            }
        });


    }


    public static void setTransprt_btween_city_50(boolean transprt_btween_city_50) {
        Sheet_Trasnport_Btween_city.transprt_btween_city_50 = transprt_btween_city_50;
    }

    public static boolean isTransprt_btween_city_50() {
        return transprt_btween_city_50;
    }


    public static boolean isTransprt_btween_city_50_false() {
        return !transprt_btween_city_50;
    }







    public static void show(){
        sheet_transport_btween_city.expandle();
    }

    public static void hide(){
        sheet_transport_btween_city.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
