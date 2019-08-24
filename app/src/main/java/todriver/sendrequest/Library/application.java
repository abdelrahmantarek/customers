package todriver.sendrequest.Library;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;


public class application extends Application{

    public static Context context;

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

    public static Context getContext(){

        return application.context;
    }

     @Override
    protected void attachBaseContext(Context base) {


        super.attachBaseContext(LocaleHelper.setLocale(base,LocaleHelper.getLanguage(base)));

    }


    @Override
    public void onCreate() {
        super.onCreate();


        application.context = getApplicationContext();
        if (LocaleHelper.getLanguage(context) == null){
            LocaleHelper.setLocale(context,"ar");
            Toast.makeText(context, "first lang == arabic", Toast.LENGTH_SHORT).show();
        }

        if (LocaleHelper.getLanguage(context).equals("")){
            LocaleHelper.setLocale(context,"ar");
            Toast.makeText(context, "first lang == arabic", Toast.LENGTH_SHORT).show();
        }
    }
}
