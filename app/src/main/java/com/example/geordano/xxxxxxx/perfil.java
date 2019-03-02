package com.example.geordano.xxxxxxx;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class perfil extends AppCompatActivity {

    private Button mapa;
    protected LatLng ponto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Intent intent = getIntent();
        mapa = findViewById(R.id.butaoDoPokemao);
        mapa.setOnClickListener(ChamaMapinha);
        mapa.setVisibility(View.GONE);
        configuraMapinha();
        iniciaframes();
    }

    public void iniciaframes(){
        Jogador jogador = getIntent().getParcelableExtra("Jogador");

        View view = LayoutInflater.from(this).inflate(R.layout.fragment_dados_usuario, null);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DadosUsuario fragment = DadosUsuario.newInstance(jogador);

        AtributosUsuarios fragment2 = AtributosUsuarios.newInstance(jogador);

        fragmentTransaction.add(R.id.id_frame1, fragment); // usuario
        fragmentTransaction.add(R.id.id_frame2, fragment2); // atributo
        fragmentTransaction.commit();
    }

    public void solicitaPerimssao(){
        int permissaoFineCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissaoFineCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                          String permissions[], int[] grantResults){
        if (grantResults.length >0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent mapa = new Intent(perfil.this, MapsActivity.class);
            startActivity(mapa);
        } else {
            finish();
        }
    }

    public View.OnClickListener ChamaMapinha = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            solicitaPerimssao();

            ArrayList<Pokemon> bixaradas = new ArrayList();
            HttpService eita = new HttpService(perfil.this, bixaradas, 5, ponto);
            eita.execute();
        }
    };

    private void configuraMapinha() {
        try {
            LocationManager locationManager =
                    (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
        }
        catch (SecurityException ex) {
        }
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mapa.setVisibility(View.VISIBLE);
            double lati = location.getLatitude();
            double longi = location.getLongitude();
            ponto = new LatLng(lati, longi);
            Toast.makeText(getApplicationContext(),"Aguarde, iniciando mapa0", Toast.LENGTH_SHORT).show();
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
}
