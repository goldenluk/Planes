package com.goldenluk.planes.presentation.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.goldenluk.planes.R
import com.goldenluk.planes.data.dto.CityDto
import com.goldenluk.planes.data.dto.LocationDto
import com.goldenluk.planes.utils.MapUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


private const val TO_CITY_KEY = "to_city"
private const val FROM_CITY_KEY = "from_city"
private const val ZOOM = 1F
private const val ANIMATION_DELAY = 300L

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var toCity: LocationDto
    private lateinit var fromCity: LocationDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        intent?.let {
            toCity = (it.getSerializableExtra(TO_CITY_KEY) as CityDto).location
            fromCity = (it.getSerializableExtra(FROM_CITY_KEY) as CityDto).location
        }
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val fromLatLng = LatLng(fromCity.lat, fromCity.lon)
        val toLatLng = LatLng(toCity.lat, toCity.lon)

        val marker = createMarker(fromLatLng)
        createPolyline(fromLatLng, toLatLng)

        val centerLatitude = (toCity.lat + fromCity.lat) / 2
        val centerLongitude = (toCity.lon + fromCity.lon) / 2

        map.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(centerLatitude, centerLongitude),
                ZOOM
            )
        )
        Handler().postDelayed({
            MapUtils.animateMarkerTo(marker, toLatLng)
        }, ANIMATION_DELAY)
    }

    private fun createMarker(fromLatLng: LatLng): Marker {
        return map.addMarker(
            MarkerOptions()
                .position(fromLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_plane))
        )
    }

    private fun createPolyline(fromLatLng: LatLng, toLatLng: LatLng) {
        map.addPolyline(
            PolylineOptions()
                .add(fromLatLng, toLatLng)
        )
    }
}
