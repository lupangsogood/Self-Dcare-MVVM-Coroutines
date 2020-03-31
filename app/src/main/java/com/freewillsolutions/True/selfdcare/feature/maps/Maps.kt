package com.freewillsolutions.True.selfdcare.feature.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Maps : BaseScreen(),OnMapReadyCallback{

    lateinit var rootView:View
    private lateinit var mMap : GoogleMap
    var lat: Double = 0.0
    var lng: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.screen_maps,container,false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment= childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }
    override fun onMapReady(p0: GoogleMap?) {
        p0?.let {
            mMap = it
            val sydney = LatLng(-34.0, 151.0)
            val marker = MarkerOptions().position(sydney).title("Marker in Sydney")
            mMap.addMarker(marker)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }

}