package com.example.geordano.xxxxxxx;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


public class    HttpService extends AsyncTask<Void, Integer, Void> {

    private Context context;
    private int quantidade;
    private LatLng ponto;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }



    private ArrayList<Pokemon> dados = new ArrayList();

    public HttpService(Context context, ArrayList<Pokemon> dados, int quantidade, LatLng ponto) {
        this.dados = dados;
        this.context = context;
        this.quantidade = quantidade;
        this.ponto = ponto;
    }

    @Override
    protected void onPreExecute(){
//        textoCarregando.setText("Procurando...");
    }

    @Override
    protected Void doInBackground(Void... params) {
        int c = 1;
        pausa(5000);

        while (ponto == null ){
            try {
                c++;
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        c = 1;
        while(c <= quantidade){
            pausa(1000);
            dados.add(carregaPokemon(randomizapokemon()));
            c++;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        textoCarregando.setText(values[0] + " itens caregados...");
    }

    private int randomizapokemon(){
        Random valor = new Random();
        int resposta = valor.nextInt(149);
        return resposta+1;
    }

    protected void onPostExecute(Void value) {
        Intent mapa = new Intent(context, MapsActivity.class);

        for (Pokemon bixo: dados) {
            bixo.setLatitude(Randomizaposicao() + ponto.latitude);
            bixo.setLongitude(Randomizaposicao() + ponto.longitude);
        }
        mapa.putParcelableArrayListExtra("bixo", dados);
        context.startActivity(mapa);
    }

    private double Randomizaposicao(){
        Random valor = new Random();
        int resposta = valor.nextInt(5);
        Double valorfinal;
        if (resposta % 2 == 0){
            valorfinal = resposta * 0.0001;
        } else {
            valorfinal = resposta * -0.0001;
        }
        return valorfinal;
    }



    public void pausa(int tempo) {
        try{
            Thread.sleep(tempo);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public Pokemon carregaPokemon(int id) {
        Pokemon pokemon = null;
        HttpURLConnection urlConexao = null;
        BufferedReader leitor = null;
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + id);
            urlConexao = (HttpURLConnection) url.openConnection();
            urlConexao.setRequestMethod("GET");
            urlConexao.connect();
            InputStream inputStream = urlConexao.getInputStream();
            leitor = new BufferedReader(new InputStreamReader(inputStream));

            Gson gson = new Gson();
            pokemon = gson.fromJson(leitor, Pokemon.class);
            pokemon.setId(id);

//            pokemon.setImg(carregaImagem(id));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemon;
    }

//    public Bitmap carregaImagem(int id) {
//        String url = "https://pokeres.bastionbot.org/images/pokemon/"+id+".png";
//        InputStream in = null;
//        try {
//            in = new URL(url).openStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Bitmap bmp = BitmapDescriptorFactory.fromResource(R.drawable.vivente).;
//        return bmp;
//    }
}