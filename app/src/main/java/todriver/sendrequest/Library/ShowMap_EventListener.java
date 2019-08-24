package todriver.sendrequest.Library;

import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DatabaseError;

public interface ShowMap_EventListener {

    public void onKeyEntered(final String key, GeoLocation location);
    public void onKeyExited(String key,int index);
    public void onKeyMoved(String key, GeoLocation location);
    public void onGeoQueryReady();
    public void onGeoQueryError(DatabaseError error);
}
