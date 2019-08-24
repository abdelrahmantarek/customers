package todriver.sendrequest.NAV;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import todriver.sendrequest.Library.Clasess.SharePref;
import todriver.sendrequest.Library.F;
import todriver.sendrequest.Library.LocaleHelper;
import todriver.sendrequest.Login.LoginActivity;
import todriver.sendrequest.Login.WelcomeActivity;
import todriver.sendrequest.NAV.myOrder.MyOrderActivity;
import todriver.sendrequest.NAV.profile.ProfileActivity;
import todriver.sendrequest.R;
import todriver.sendrequest.maps.MapsActivity;
import todriver.sendrequest.model.Drivers;


public class NavigationDrawer  implements View.OnClickListener  {



    private static Activity activity;
    private View view;


    public NavigationDrawer(Activity activity) {
        this.activity = activity;
    }

    public void navigation_Drawer() {

        // 3 --- NavigationView




        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);





        view = navigationView.getHeaderView(0);
        LinearLayout profile = view.findViewById(R.id.map_go_profile);

        LinearLayout map_go_contact_us = view.findViewById(R.id.map_go_contact_us);
        LinearLayout map_go_law_ruls = view.findViewById(R.id.map_go_law_ruls);
        LinearLayout map_go_payment = view.findViewById(R.id.map_go_payment);
        LinearLayout map_go_rating_app = view.findViewById(R.id.map_go_rating_app);
        LinearLayout map_go_driver_app_store = view.findViewById(R.id.map_go_driver_app_store);
        LinearLayout map_go_my_my_order = view.findViewById(R.id.map_go_my_my_order);
        LinearLayout map_go_question = view.findViewById(R.id.map_go_question);
        LinearLayout map_go_share_app = view.findViewById(R.id.map_go_share_app);
        LinearLayout nav_change_language = view.findViewById(R.id.nav_change_language);


        ImageView facebook = view.findViewById(R.id.nav_facebook);
        ImageView whatsapp = view.findViewById(R.id.nav_whatsapp);
        ImageView twitter = view.findViewById(R.id.nav_twitter);


        LinearLayout logout = view.findViewById(R.id.map_go_logOut);


        profile.setOnClickListener(this);
        map_go_share_app.setOnClickListener(this);
        map_go_payment.setOnClickListener(this);
        map_go_contact_us.setOnClickListener(this);
        map_go_law_ruls.setOnClickListener(this);
        map_go_rating_app.setOnClickListener(this);
        map_go_driver_app_store.setOnClickListener(this);
        map_go_my_my_order.setOnClickListener(this);
        map_go_question.setOnClickListener(this);
        logout.setOnClickListener(this);
        facebook.setOnClickListener(this);
        whatsapp.setOnClickListener(this);
        twitter.setOnClickListener(this);
        nav_change_language.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {




            case R.id.map_go_profile:
                Intent intent = new Intent(activity, ProfileActivity.class);
                activity. startActivity(intent);
                break;

            case R.id.map_go_contact_us:
                Intent connect = new Intent(activity, ContactUsActivity.class);
                activity.startActivity(connect);
                break;

            case R.id.map_go_payment:
                Intent connecsst = new Intent(activity, PaymentActivity.class);
                activity.startActivity(connecsst);
                break;


            case R.id.map_go_law_ruls:
                Intent connecsstd = new Intent(activity, RulesActivity.class);
                activity.startActivity(connecsstd);
                break;


            case R.id.map_go_share_app:
                F.message("عند رفع التطبيق على المتجر",activity.findViewById(R.id.drawer_layout),activity,android.R.color.holo_green_dark);
                break;


            case R.id.map_go_rating_app:
                F.message("عند رفع التطبيق على المتجر",activity.findViewById(R.id.drawer_layout),activity,android.R.color.holo_green_dark);
                break;


            case R.id.map_go_driver_app_store:
                F.message("عند رفع التطبيق على المتجر",activity.findViewById(R.id.drawer_layout),activity,android.R.color.holo_green_dark);
                break;


            case R.id.map_go_my_my_order:
                Intent sdasdasd = new Intent(activity, MyOrderActivity.class);
                activity.startActivity(sdasdasd);

                break;


            case R.id.map_go_question:

                Intent dsdsd = new Intent(activity, QuestionActivity.class);
                activity.startActivity(dsdsd);

                break;



            case R.id.map_go_logOut:

                Intent out = new Intent(activity, WelcomeActivity.class);
                out.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(out);
                FirebaseAuth.getInstance().signOut();
                activity.finish();


                break;



            case R.id.nav_change_language:


                chengeLanguage();


                break;



            case R.id.nav_facebook:
                F.message("عند وجود رابط صفحة",activity.findViewById(R.id.drawer_layout),activity,android.R.color.holo_green_dark);

                break;

            case R.id.nav_whatsapp:
                F.message("عند وجود رقم جوال",activity.findViewById(R.id.drawer_layout),activity,android.R.color.holo_green_dark);

                break;

            case R.id.nav_twitter:
                F.message("عند وجود رابط صفحة",activity.findViewById(R.id.drawer_layout),activity,android.R.color.holo_green_dark);

                break;



        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void chengeLanguage() {


        //It is required to recreate the activity to reflect the change in UI.
        if (LocaleHelper.getLanguage(activity).equals("ar")){
            LocaleHelper.setLocale(activity,"en");
            Toast.makeText(activity, "to english", Toast.LENGTH_SHORT).show();
            activity.recreate();

            return;
        }


        if (LocaleHelper.getLanguage(activity).equals("en")){
            LocaleHelper.setLocale(activity,"ar");
            Toast.makeText(activity, "to arabic", Toast.LENGTH_SHORT).show();
            activity.recreate();

            return;
        }

        if (LocaleHelper.getLanguage(activity).equals("")){
            LocaleHelper.setLocale(activity,"en");
        }

        if (LocaleHelper.getLanguage(activity) == null){
            LocaleHelper.setLocale(activity,"en");
        }

        if (LocaleHelper.getLanguage(activity).equals("en")){
            LocaleHelper.setLocale(activity,"ar");
            Toast.makeText(activity, "to english", Toast.LENGTH_SHORT).show();
            activity.recreate();

        }

    }





    public void setMyData(DataSnapshot dataSnapshot) {


        final TextView myName = (TextView) view.findViewById(R.id.name_profile_map);
        final TextView myEmail = (TextView) view.findViewById(R.id.email_profile_map);
        final CircleImageView myImage = (CircleImageView) view.findViewById(R.id.image_profile_map);
		
		      if (!dataSnapshot.hasChild("name")){

                   
             Toast.makeText(activity, "اصلاحات من المبرمج حاليا يمكنك استخدام بريد اخر او التسجيل من جديد", Toast.LENGTH_SHORT).show();


                  Intent out = new Intent(activity, WelcomeActivity.class);
                  out.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  activity.startActivity(out);
                  FirebaseAuth.getInstance().signOut();
                  activity.finish();


                  return;
              }

        Drivers drivers = dataSnapshot.getValue(Drivers.class);

        if (drivers == null){
            return;
        }



        if (drivers.getName()!=null){

            myName.setText(drivers.getName());

        }

        if (drivers.getEmail()!=null){

            myEmail.setText(drivers.getEmail());
        }



        if (drivers.getImage()!=null){

            if (!drivers.getImage().equals("default")) {
                Glide.with(activity.getApplicationContext()).load(drivers.getImage()).into(myImage);
            }

        }




    }


}
