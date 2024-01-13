package com.example.myeee.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.example.myeee.R
import com.google.android.gms.location.LocationCallback


class stationFragment : Fragment(), OnMapReadyCallback{

    private val LIVE_LOCATION_UPDATE_INTERVAL: Long = 5000 // Update interval in milliseconds
    private lateinit var locationCallback: LocationCallback

    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_station, container, false)

        mapView = view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Fetch EV charging spot locations (replace with your logic)
        val chargingSpots = getChargingSpots()

        // Add markers for each charging spot
        for (chargingSpot in chargingSpots) {
            val markerOptions = MarkerOptions()
                .position(LatLng(chargingSpot.latitude, chargingSpot.longitude))
                .title(chargingSpot.name)
                .snippet(chargingSpot.address)
            mMap.addMarker(markerOptions)
        }

        // Set camera position to the first charging spot
        if (chargingSpots.isNotEmpty()) {
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(chargingSpots[0].latitude, chargingSpots[0].longitude),
                    12.0f
                )
            )
        }

    }

    private fun getChargingSpots(): List<ChargingSpot> {
        // Replace this with your logic to fetch charging spots from a data source
        // Example: You can fetch charging spots from a server or a local database
        return listOf(
            ChargingSpot("Spot 1", "Address 1", 37.7749, -122.4194),
            ChargingSpot("Spot 2", "Address 2", 37.7755, -122.4182),
            ChargingSpot("Spot 3", "Address 3", 37.7737, -122.4156)
            // Add more charging spots as needed
        )
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    // Data class to represent charging spot information
    data class ChargingSpot(
        val name: String,
        val address: String,
        val latitude: Double,
        val longitude: Double
    )

}