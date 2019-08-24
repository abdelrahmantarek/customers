package todriver.sendrequest.Library;

import android.provider.Contacts;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ArrayHelp {


    ArrayList<String> arrayList;

    public ArrayHelp(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public List<Marker> MARKERS_CUSTOMER_ONLINE;

    public ArrayHelp(List<Marker> MARKERS_CUSTOMER_ONLINE) {
        this.MARKERS_CUSTOMER_ONLINE = MARKERS_CUSTOMER_ONLINE;
    }

    public void remove(String key) {

        int index = 0;

        if (key != null) {

            if (arrayList.isEmpty()) {
                return;
            }


            for (ListIterator<String> iterator = arrayList.listIterator(); iterator.hasNext(); ) {//In this Line getting error
                String user_id = iterator.next();
                if (user_id.equals(key)) {
                    arrayList.remove(index);
                }else {
                    index++;
                }
            }
        }
    }

    public void remove_marker(String key) {

        int index = 0;

        for (ListIterator<Marker> iterator = MARKERS_CUSTOMER_ONLINE.listIterator(); iterator.hasNext(); ) {//In this Line getting error
            Marker markerIt = iterator.next();
            if (markerIt.getTag() != null) {
                if (markerIt.getTag().equals(key)) {
                    markerIt.remove();
                    Log.d("remover",key+"");
                }
            }
        }
    }

}
