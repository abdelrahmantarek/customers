package todriver.sendrequest.maps.helper;

import android.app.Activity;
import android.util.DisplayMetrics;

import todriver.sendrequest.Library.F;

public class helper {

    public static void screen(Activity activity){


        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        F.setScreenHieght(height);
        F.setScreenWidth(width);
    }
}
