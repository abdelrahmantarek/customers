package todriver.sendrequest.maps.Listener;

import com.google.firebase.database.DataSnapshot;

public interface On_Request_Or_Service {

    void onRequest(String user_id, DataSnapshot dataSnapshot);
    void onService(String user_id, DataSnapshot dataSnapshot);


    void onRequestRemove(DataSnapshot dataSnapshot);
    void onServiceRemove(DataSnapshot dataSnapshot);


    void onCheckNo(DataSnapshot dataSnapshot);


}
