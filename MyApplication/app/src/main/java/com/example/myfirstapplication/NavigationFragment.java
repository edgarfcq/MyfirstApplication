package com.example.myfirstapplication;

import android.location.LocationListener;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.location.Criteria;
import android.text.TextUtils;


public class NavigationFragment extends Fragment {

    private LocationManager locationManager;
    private String provider;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private TextView addressTextView;
    private Geocoder geocoder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        // Initialize UI elements
        latitudeTextView = view.findViewById(R.id.textViewLatitude);
        longitudeTextView = view.findViewById(R.id.textViewLongitude);
        addressTextView = view.findViewById(R.id.textViewAddress);

        // Initialize Geocoder
        geocoder = new Geocoder(requireContext(), Locale.getDefault());

        // Initialize LocationManager
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);

        // Configure location updates
        provider = getLocationProvider();
        if (provider != null) {
            requestLocationUpdates();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }

    // Helper method to get the best location provider
    private String getLocationProvider() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        return locationManager.getBestProvider(criteria, true);
    }

    // Helper method to request location updates
    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Handle permissions here if not granted
            return;
        }

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocationUI(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Handle status changes if needed
            }

            @Override
            public void onProviderEnabled(String provider) {
                // Handle provider enabled
            }

            @Override
            public void onProviderDisabled(String provider) {
                // Handle provider disabled
            }
        };

        locationManager.requestLocationUpdates(provider, 15000, 10, locationListener);
    }

    // Helper method to stop location updates
    private void stopLocationUpdates() {
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Do nothing here
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Handle status changes if needed
            }

            @Override
            public void onProviderEnabled(String provider) {
                // Handle provider enabled
            }

            @Override
            public void onProviderDisabled(String provider) {
                // Handle provider disabled
            }
        };
        locationManager.removeUpdates(locationListener);
    }

    // Helper method to update UI with location information
    private void updateLocationUI(Location location) {
        if (location != null) {
            // Update UI elements
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            latitudeTextView.setText(String.format("Latitude: %f", latitude));
            longitudeTextView.setText(String.format("Longitude: %f", longitude));

            // Geocode coordinates to get the address
            geocodeCoordinates(latitude, longitude);
        }
    }

    // Helper method to geocode coordinates to get the address
    private void geocodeCoordinates(double latitude, double longitude) {
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);

                ArrayList<String> addressFragments = new ArrayList<>();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    addressFragments.add(address.getAddressLine(i));
                }

                String fullAddress = TextUtils.join(System.getProperty("line.separator"), addressFragments);
                addressTextView.setText(fullAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}