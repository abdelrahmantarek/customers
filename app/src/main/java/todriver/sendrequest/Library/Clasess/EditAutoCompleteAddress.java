package todriver.sendrequest.Library.Clasess;

import android.app.Activity;
import android.location.Location;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import todriver.sendrequest.Library.Listener.OnGet_Address_From_EditComplete;

import static android.content.ContentValues.TAG;


public class EditAutoCompleteAddress {

    private OnGet_Address_From_EditComplete getAdressAutoComplete;
    private GeoDataClient googleApiClient;
    private PlaceAutocompleteAdapter placesAutoCompleteAdapter_a;
    private AutoCompleteTextView autoCompleteTextView;
    private Activity activity;
    private OnGet_Address_From_EditComplete onGet_address_from_editComplete;


    public EditAutoCompleteAddress(Activity activity) {
        this.activity = activity;

        googleApiClient = Places.getGeoDataClient(activity, null);
        final LatLngBounds Bound = new LatLngBounds(new LatLng(-40,-168),new LatLng(71,136));
        placesAutoCompleteAdapter_a = new PlaceAutocompleteAdapter(activity,googleApiClient,Bound,null);

    }



    public void setOn_Get_Address_From_EditComplete(final AutoCompleteTextView autoCompleteTextView, final OnGet_Address_From_EditComplete onGet_address_from_editComplete){
        this.onGet_address_from_editComplete = onGet_address_from_editComplete;


        autoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (autoCompleteTextView.getAdapter() == null){

                    autoCompleteTextView.setOnItemClickListener(mAutocompleteClickListener_a);
                    autoCompleteTextView.setAdapter(placesAutoCompleteAdapter_a);
                }


                return false;
            }
        });


        this.getAdressAutoComplete = onGet_address_from_editComplete;

        this.autoCompleteTextView = autoCompleteTextView;


    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    private AdapterView.OnItemClickListener mAutocompleteClickListener_a = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
            final AutocompletePrediction item = placesAutoCompleteAdapter_a.getItem(position);

            if (item!=null){

                final String placeId = item.getPlaceId();
                final CharSequence primaryText = item.getPrimaryText(null);



                Log.i(TAG, "Autocomplete item selected: " + primaryText);
                Log.i(TAG, "OnItemClickListener " + "OnItemClickListener");

                hideKeyboard(activity);


            /*
             Issue a request to the Places Geo Data Client to retrieve a Place object with
             additional details about the place.
              */
                Task<PlaceBufferResponse> placeResult = googleApiClient.getPlaceById(placeId);
                placeResult.addOnCompleteListener(mUpdatePlaceDetailsCallback);

                Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
            }

        }
    };

    private  OnCompleteListener<PlaceBufferResponse> mUpdatePlaceDetailsCallback = new OnCompleteListener<PlaceBufferResponse>() {
        @Override
        public void onComplete(Task<PlaceBufferResponse> task) {
            try {
                PlaceBufferResponse places = task.getResult();

                // Get the Place object from the buffer.
                if (places!=null){

                    final Place place = places.get(0);

                    if (place == null){

                        return;
                    }


                    Log.i(TAG, "OnItemClickListener " + "mUpdatePlaceDetailsCallback");




                    Location location = new Location("a");
                    location.setLatitude(place.getLatLng().latitude);
                    location.setLongitude(place.getLatLng().longitude);


                    getAdressAutoComplete.onFinishGettingAddress(place.getName().toString(),location);




                    // Display the third party attributions if set.
                    final CharSequence thirdPartyAttribution = places.getAttributions();
                    if (thirdPartyAttribution == null) {

                    } else {

                    }

                    Log.i(TAG, "Place details received: " + place.getName());

                    places.release();



                }

            } catch (RuntimeRemoteException e) {
                // RequestClass did not complete successfully
                Log.e(TAG, "Place query did not complete.", e);
                return;
            }
        }
    };


    public void stop(){

        if (autoCompleteTextView!=null){
            autoCompleteTextView.setOnItemClickListener(null);
            autoCompleteTextView.setAdapter(null);
        }


    }

    public void start(){

        if (autoCompleteTextView!=null){
            autoCompleteTextView.setOnItemClickListener(mAutocompleteClickListener_a);
            autoCompleteTextView.setAdapter(placesAutoCompleteAdapter_a);
        }
    }


}
