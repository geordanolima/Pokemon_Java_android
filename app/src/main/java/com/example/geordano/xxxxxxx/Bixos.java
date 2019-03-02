package com.example.geordano.xxxxxxx;

import android.os.Parcel;
import android.os.Parcelable;

public class Bixos implements Parcelable {
    private String nome;
    private double latitude;
    private double longitude;
    private int img;

    public Bixos(String nome, double latitude, double longitude, int img) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.img = img;
    }

    protected Bixos(Parcel in) {
        nome = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        img = in.readInt();
    }

    public static final Creator<Bixos> CREATOR = new Creator<Bixos>() {
        @Override
        public Bixos createFromParcel(Parcel in) {
            return new Bixos(in);
        }

        @Override
        public Bixos[] newArray(int size) {
            return new Bixos[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeInt(img);
    }
}
