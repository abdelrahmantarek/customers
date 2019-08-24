package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.Listener.BottomSheetEvent;
import todriver.sendrequest.Library.helper.BottomSheet;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.Database;
import todriver.sendrequest.model.Drivers;
import todriver.sendrequest.model.Service;


public class BottomSheetService {

    public static BottomSheet bottomSheet;
    private View layou_sheet;
    private Activity activity;
    private Database database;


    public static boolean isShow = false;

    public BottomSheetService(Activity activity) {
        this.layou_sheet = layou_sheet;
        this.activity = activity;
        this.database = database;


        bottomSheet = new BottomSheet((activity.findViewById(R.id.sheet_layout_service)));
        bottomSheet.addBottomSheetEvent(new BottomSheetEvent() {
            @Override
            public void onSheetExpandle() {

            }

            @Override
            public void onSheetCollapsed() {

            }
        });

    }


    public static void show(){
        bottomSheet.expandle();
    }

    public static void hide(){
        bottomSheet.collapsed();
    }


    public static boolean isShow(){
        return isShow;
    }

    public static boolean isHide(){

        return !isShow;
    }









    public void set_Data(Service service, final String user_id_request, ArrayList<String> requestList, final ArrayList<String> request_list) {



        if (service == null){
            return;
        }


        F.Drivers().child(user_id_request).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

       Drivers drivers = dataSnapshot.getValue(Drivers.class);

				
		if(drivers.getKind_car()!=null){
			
		TextView service_car_number = activity.findViewById(R.id.service_car_number);
        service_car_number.setText(drivers.getKind_car());

		}
	    if(drivers.getNumber_car()!=null){
			
		TextView service_car_kind = activity.findViewById(R.id.service_car_kind);
            service_car_kind.setText(drivers.getNumber_car());

		}

				

                TextView service_name_customer = activity.findViewById(R.id.service_name_customer);
                service_name_customer.setText(drivers.getName());


                CircleImageView service_image_customer = activity.findViewById(R.id.service_image_customer);

                Glide.with(activity.getApplicationContext()).load(drivers.getImage()).into(service_image_customer);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




       TextView service_kind = activity.findViewById(R.id.service_kind);
       service_kind.setText(service.getService_kind());


       TextView service_from = activity.findViewById(R.id.service_from);
        service_from.setText(service.getText_from());


       TextView service_to = activity.findViewById(R.id.service_to);
        service_to.setText(service.getText_to());


     TextView service_price = activity.findViewById(R.id.service_price);
        service_price.setText(service.getService_price());





        ((activity.findViewById(R.id.service_delete_service))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (database == null){
                    database = new Database(activity);
                }

                database.deleteService(user_id_request);

            }
        });




    }





}
