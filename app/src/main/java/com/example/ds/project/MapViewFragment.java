package com.example.ds.project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay.OnStateChangeListener;

public class MapViewFragment extends NMapFragment implements NMapView.OnMapStateChangeListener, NMapPOIdataOverlay.OnStateChangeListener {
    NMapView mapView;
    NMapController mapController;
    NMapViewerResourceProvider mapViewerResourceProvider;
    NMapOverlayManager mapOverlayManager;
    NMapPOIdata poidata;
    int markerId = NMapPOIflagType.PIN;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_view_fragment, container, false);
        mapView = (NMapView) v.findViewById(R.id.map_view);
        mapView.setClientId("SRQ8fQ4AjnbYFQuTXNDu");
        mapView.setClickable(true);
        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.setBuiltInZoomControls(true, null);
        mapView.setOnMapStateChangeListener(this);
        mapController = mapView.getMapController();
        mapViewerResourceProvider = new NMapViewerResourceProvider(getActivity());
        mapOverlayManager = new NMapOverlayManager(getActivity(), mapView, mapViewerResourceProvider);
        moveMapCenter();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null) {
            moveMapCenter();
        } else {
            Log.e("map init error", nMapError.message);
        }
        
    }

    private void moveMapCenter() {
        NGeoPoint currentPoint = new NGeoPoint(127.0630205, 37.5091300);
        mapController.setMapCenter(currentPoint);

        poidata = new NMapPOIdata(1, mapViewerResourceProvider);
        poidata.beginPOIdata(1);
        poidata.addPOIitem(127.0630205, 37.5091300, "Pizza 777-111", markerId, 0);
        poidata.endPOIdata();

        NMapPOIdataOverlay poiDataOverlay = mapOverlayManager.createPOIdataOverlay(poidata, null);
        
    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

    }

    @Override
    public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

    }

    @Override
    public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

    }
}
