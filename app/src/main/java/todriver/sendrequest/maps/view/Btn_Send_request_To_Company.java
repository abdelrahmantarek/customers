package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

import todriver.sendrequest.D;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;

import static todriver.sendrequest.maps.MapsActivity.LOCATION_A;
import static todriver.sendrequest.maps.MapsActivity.LOCATION_B;







public class Btn_Send_request_To_Company {

    public static BottomSheet btn_send_to_company;
    Activity activity;

    D d;


    public static boolean isShow = false;


    public Btn_Send_request_To_Company(final Activity activity) {
        this.activity = activity;




        btn_send_to_company = new BottomSheet((activity.findViewById(R.id.btn_sheet_send_company)));

        btn_send_to_company.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

                isShow = true;
            }

            @Override
            public void onSheetCollapsed() {

                isShow = false;
            }
        });




        ((activity.findViewById(R.id.company_click_to_comapny))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (d ==null){
                    d = new D();
                }


                send_request_data_to_company();

            }
        });

    }

    private void send_request_data_to_company() {


        if (LOCATION_B!=null && LOCATION_A!=null){


                    final Map<String,Object> hashMap2 = new HashMap<>();




                    String from_lat = "from_lat";
                    String from_lng = "from_lng";
                    String to_lat = "to_lat";
                    String to_lng = "to_lng";
                    String text_from = "text_from";
                    String text_to = "text_to";
                    String kindService = "service_kind";
                    String priceService = "service_price";
                    String note = "note";



                    hashMap2.put(from_lat, LOCATION_A.getLatitude());
                    hashMap2.put(from_lng, LOCATION_A.getLongitude());
                    hashMap2.put(to_lat, LOCATION_B.getLatitude());
                    hashMap2.put(to_lng, LOCATION_B.getLongitude());
                    hashMap2.put(text_from, MapsActivity.TEXT_FROM);
                    hashMap2.put(text_to,MapsActivity.TEXT_TO);
                    hashMap2.put(kindService,"kindService");
                    hashMap2.put(priceService,MapsActivity.SERVICE_PRICE);
                    hashMap2.put(note,"note");


                    F.reference().child("manager_requests").child(F.Uid()).updateChildren(hashMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            d.hide_all_sheet();
                            Btn_Show_Service.show();

                            F.message("لقد تم ارسال معلوماتك الى شركة الفرج من فضلك انتظر وسوف يتم الرد عليك",activity.findViewById(R.id.drawer_layout),activity,android.R.color.holo_green_dark);
                        }
                    });

        }else {

            F.message("من فضلك قم باختيار الاماكن",activity.findViewById(R.id.drawer_layout),activity,android.R.color.holo_red_dark);

        }
    }


    public static void show(){
        btn_send_to_company.expandle();
    }

    public static void hide(){
        btn_send_to_company.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }
}
