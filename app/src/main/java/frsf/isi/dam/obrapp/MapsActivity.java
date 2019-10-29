package frsf.isi.dam.obrapp;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import frsf.isi.dam.obrapp.modelo.Material;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("clic corto")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                );
            }
        });
        final Map<Integer,MarkerOptions> marcadores = new HashMap<>();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            Integer i =1;
            @Override
            public void onMapLongClick(LatLng latLng) {
                MarkerOptions m = new MarkerOptions()
                        .position(latLng)
                        .title("clic largo")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                mMap.addMarker(m);
                marcadores.put(i,m);
            }
        });
        // marcadores.get(iOcultar).visible(false);
        List<LatLng> lista = this.generarPuntos(3);
        List<Marker> listaMarkers = new ArrayList<>();
        LatLng ultimoPunto = null;
        int i = 1;
//        for(LatLng punto: lista ){
//            listaMarkers .add(mMap.addMarker(new MarkerOptions().position(punto)
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
//                    .title("Punto "+i)
//                    .snippet("Detalle punto"+i)));
//            i++;
//            ultimoPunto = punto;
//        }

        PolygonOptions linea = new PolygonOptions();
        for(LatLng punto: lista ){
            linea.add(punto).fillColor(Color.RED).strokeColor(Color.BLUE);
            ultimoPunto = punto;
        }
        googleMap.addPolygon(linea);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ultimoPunto)
                .zoom(15)
                .build();     // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                3000,null);
    }


    private void mostrarCrai(){
        LatLng crai= new LatLng(-31.620816,-60.747582);
        Marker QLSG = mMap.addMarker(new MarkerOptions().position(crai)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.club))
                .title("CRAI")
                .snippet("Club de rugby ateneo inmaculada"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(crai)
                .zoom(17)
                .bearing(90)
                .tilt(30)
                .build();     // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                    3000,null);
    }


    private List<LatLng> generarPuntos(Integer n){
        List<LatLng> lista = new ArrayList<>();
        double norte = -31.614339;
        double oeste = -60.702693;
        double sur =-31.644426;
        double este = -60.687270;
        double lonDiff = Math.abs(oeste-este);
        double latDiff = Math.abs(sur-norte);
        LatLng punto1 = new LatLng(norte,oeste);
        LatLng punto2 = new LatLng(sur,este);
        lista.add(punto1);
        lista.add(punto2);
        Random r = new Random();

        for(int i =0; i<n;i++){
            double  l1 = r.nextDouble()/1000.0;
            double  l2 = r.nextDouble()/1000.0;
            lista.add(new LatLng(norte-l1,oeste-l2));
          //  l1 = r.nextDouble()/100.0;
          //  l2 = r.nextDouble()/100.0;
          //  lista.add(new LatLng(norte-l1,oeste-l2));
        }
        return lista;
    }


}
