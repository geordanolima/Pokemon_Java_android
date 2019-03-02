package com.example.geordano.xxxxxxx;

import android.os.Parcel;
import android.os.Parcelable;

public class Jogador implements Parcelable{

    private int id;
    private String nome;
    private String apelido;
    private String email;
    private String senha;
    private int inteligencia;
    private int sorte;
    private int treta;


    protected Jogador(Parcel in) {
        nome = in.readString();
        apelido = in.readString();
        email = in.readString();
        inteligencia = in.readInt();
        sorte = in.readInt();
        treta = in.readInt();
    }

    public static final Creator<Jogador> CREATOR = new Creator<Jogador>() {
        @Override
        public Jogador createFromParcel(Parcel in) {
            return new Jogador(in);
        }

        @Override
        public Jogador[] newArray(int size) {
            return new Jogador[size];
        }
    };

    public Jogador() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getSorte() {
        return sorte;
    }

    public void setSorte(int sorte) {
        this.sorte = sorte;
    }

    public int getTreta() {
        return treta;
    }

    public void setTreta(int treta) {
        this.treta = treta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Creator<Jogador> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(apelido);
        parcel.writeString(email);
        parcel.writeInt(inteligencia);
        parcel.writeInt(sorte);
        parcel.writeInt(treta);
    }
}
