package com.example.geordano.xxxxxxx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 20161cmq.ads0254 on 14/02/2019.
 */

public class Pokemon implements Parcelable {
    @SerializedName(value = "name")
    String nome;
    int id;
    private double latitude;
    private double longitude;


    protected Pokemon(Parcel in) {
        nome = in.readString();
        id = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        img = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    Bitmap img;
    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeInt(id);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeParcelable(img, i);
    }
}
