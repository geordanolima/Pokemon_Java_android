package com.example.geordano.xxxxxxx;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Pokemon> bixos;
    private bd banco;
    private boolean iniciaLogado;
    private Jogador jogador;
    private int[] imagens = {R.drawable.p001,R.drawable.p002,R.drawable.p003,R.drawable.p004,R.drawable.p005,R.drawable.p006,R.drawable.p007,R.drawable.p008,R.drawable.p009,R.drawable.p010,R.drawable.p011,R.drawable.p012,R.drawable.p013,R.drawable.p014,R.drawable.p015,R.drawable.p016,R.drawable.p017,R.drawable.p018,R.drawable.p019,R.drawable.p020,R.drawable.p021,R.drawable.p022,R.drawable.p023,R.drawable.p024,R.drawable.p025,R.drawable.p026,R.drawable.p027,R.drawable.p028,R.drawable.p029,R.drawable.p030,R.drawable.p031,R.drawable.p032,R.drawable.p033,R.drawable.p034,R.drawable.p035,R.drawable.p036,R.drawable.p037,R.drawable.p038,R.drawable.p039,R.drawable.p040,R.drawable.p041,R.drawable.p042,R.drawable.p043,R.drawable.p044,R.drawable.p045,R.drawable.p046,R.drawable.p047,R.drawable.p048,R.drawable.p049,R.drawable.p050,R.drawable.p051,R.drawable.p052,R.drawable.p053,R.drawable.p054,R.drawable.p055,R.drawable.p056,R.drawable.p057,R.drawable.p058,R.drawable.p059,R.drawable.p060,R.drawable.p061,R.drawable.p062,R.drawable.p063,R.drawable.p064,R.drawable.p065,R.drawable.p066,R.drawable.p067,R.drawable.p068,R.drawable.p069,R.drawable.p070,R.drawable.p071,R.drawable.p072,R.drawable.p073,R.drawable.p074,R.drawable.p075,R.drawable.p076,R.drawable.p077,R.drawable.p078,R.drawable.p079,R.drawable.p080,R.drawable.p081,R.drawable.p082,R.drawable.p083,R.drawable.p084,R.drawable.p085,R.drawable.p086,R.drawable.p087,R.drawable.p088,R.drawable.p089,R.drawable.p090,R.drawable.p091,R.drawable.p092,R.drawable.p093,R.drawable.p094,R.drawable.p095,R.drawable.p096,R.drawable.p097,R.drawable.p098,R.drawable.p099,R.drawable.p100,R.drawable.p101,R.drawable.p102,R.drawable.p103,R.drawable.p104,R.drawable.p105,R.drawable.p106,R.drawable.p107,R.drawable.p108,R.drawable.p109,R.drawable.p110,R.drawable.p111,R.drawable.p112,R.drawable.p113,R.drawable.p114,R.drawable.p115,R.drawable.p116,R.drawable.p117,R.drawable.p118,R.drawable.p119,R.drawable.p120,R.drawable.p121,R.drawable.p122,R.drawable.p123,R.drawable.p124,R.drawable.p125,R.drawable.p126,R.drawable.p127,R.drawable.p128,R.drawable.p129,R.drawable.p130,R.drawable.p131,R.drawable.p132,R.drawable.p133,R.drawable.p134,R.drawable.p135,R.drawable.p136,R.drawable.p137,R.drawable.p138,R.drawable.p139,R.drawable.p140,R.drawable.p141,R.drawable.p142,R.drawable.p143,R.drawable.p144,R.drawable.p145,R.drawable.p146,R.drawable.p147,R.drawable.p148,R.drawable.p149,R.drawable.p150};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bixos = (ArrayList) getIntent().getParcelableArrayListExtra("bixo");
    }

    private void configuraMapinha() {
        try {
            LocationManager locationManager =
                    (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
        }
        catch (SecurityException ex) {
            Toast.makeText(this, "Deu erro nessa treta de gépis", Toast.LENGTH_LONG).show();
        }
    }
    private boolean calculaDistancia(double latitude1, double longitude1, double latitude2, double longitude2) {
        double raioDaTerraKm = 6371;
        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLng = Math.toRadians(longitude2 - longitude1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latitude1))
                * Math.cos(Math.toRadians(latitude2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = raioDaTerraKm * c;

        return (dist * 1000) < 20;
    }

    public Bitmap resizeMapIcons(int iconId, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), iconId);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double lati = location.getLatitude();
            double longi = location.getLongitude();

            LatLng ponto = new LatLng(lati, longi);
            mMap.clear();
            mMap.addMarker(new MarkerOptions()
                    .position(ponto)
                    .title("Vivente")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.vivente)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ponto));

            for (Pokemon animals : bixos) {
                if (calculaDistancia(lati,longi, animals.getLatitude(), animals.getLongitude())) {
                    LatLng Cusco = new LatLng(animals.getLatitude(), animals.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(Cusco)
                            .title(animals.getNome())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons(imagens[animals.getId()-1], 150 ,150))));
                }
            }

            mMap.addCircle(new CircleOptions()
                    .center(new LatLng(lati, longi))
                    .radius(20)
                    .strokeColor(Color.rgb(179, 217, 255))
                    .fillColor(Color.TRANSPARENT));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ponto, 19.0f));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
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
        configuraMapinha();
    }


}
