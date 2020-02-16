package edu.neu.madcourse.numad20s_rongyichen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Homework 4
 * To display the location latitude and longitude of the mobile device
 */

public class  LocationActivity extends Activity implements
        LocationListener {

    private LocationManager locationManager;
    private TextView latitudeValue;
    private TextView longitudeValue;
    private List<String> enabledProviders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        latitudeValue = findViewById(R.id.latitude);
        longitudeValue = findViewById(R.id.longitude);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume()
    {
        super.onResume();

        latitudeValue.setText(R.string.latitude_processing);
        longitudeValue.setText(R.string.longitude_processing);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        enabledProviders = locationManager.getProviders(criteria, true);

        if (enabledProviders.isEmpty())
        {
            latitudeValue.setText("Latitude: no result");
            longitudeValue.setText("Longitude: no result");
        }
        else
        {
            for (String enabledProvider : enabledProviders){
                try { locationManager.requestSingleUpdate(enabledProvider,
                        this,
                        null);
                } catch (SecurityException e){
                    latitudeValue.setText("Latitude: error...");
                    longitudeValue.setText("Longitude: error...");
                }
            }

        }

}

    @Override
    protected void onPause()
    {
        super.onPause();

        locationManager.removeUpdates(this);
    }

    /**
     * Updates the display with the new location information if new location
     * information is more accurate than the current location information.
     *
     * @param location The new location information
     *
     * @see android.location.LocationListener#onLocationChanged(android.location.Location)
     */
    @Override
    public void onLocationChanged(Location location)
    {
        StringBuilder latitudeResult = new StringBuilder("Latitude: ");
        StringBuilder longitudeResult = new StringBuilder("Longitude: ");
        latitudeResult.append(location.getLatitude());
        longitudeResult.append(location.getLongitude());
        latitudeValue.setText(latitudeResult.toString());
        longitudeValue.setText(longitudeResult.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
