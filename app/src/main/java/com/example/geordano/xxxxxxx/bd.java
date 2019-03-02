package com.example.geordano.xxxxxxx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class bd extends SQLiteOpenHelper {
    private final String TABELAJOGADORES = "jogadores";
    private final String COLUNA_ID = "_id";
    private final String COLUNA_NOME = "nome";
    private final String COLUNA_APELIDO = "apelido";
    private final String COLUNA_SENHA = "senha";
    private final String COLUNA_EMAIL = "email";
    private final String COLUNA_INTEL = "INTELIGENCIA";
    private final String COLUNA_TRETA = "TRETA";
    private final String COLUNA_SORTE = "SORTE";



    public bd(Context context, String nome, int versao) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELAJOGADORES + " ("
                + COLUNA_ID + " INTEGER PRIMARY KEY autoincrement,"
                + COLUNA_NOME + " varchar(50) NOT NULL,"
                + COLUNA_APELIDO + " varchar(20) NOT NULL ,"
                + COLUNA_SENHA + " varchar(20) NOT NULL ,"
                + COLUNA_EMAIL + " varchar(50) NOT NULL,"
                + COLUNA_INTEL + " varchar(50) NOT NULL,"
                + COLUNA_TRETA + " varchar(50) NOT NULL,"
                + COLUNA_SORTE + " varchar(50) NOT NULL"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoAntiga, int versaoNova) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABELAJOGADORES);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Jogador> lerJogadores(){
        Cursor cursor = this.getWritableDatabase().query(TABELAJOGADORES,
                new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_APELIDO, COLUNA_EMAIL, COLUNA_SENHA},
                null, null, null, null, null);

        ArrayList<Jogador> jogadores = new ArrayList<>();
        for (int c =0; c<cursor.getCount(); c++){
            cursor.moveToNext();
            Jogador jogador = new Jogador();
            jogador.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            jogador.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            jogador.setApelido(cursor.getString(cursor.getColumnIndexOrThrow("apelido")));
            jogador.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            jogador.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
            jogadores.add(jogador);
        }
        cursor.close();
        return jogadores;
    }

    public Jogador lerJogador(String nome, String senha){
        Cursor cursor= this.getWritableDatabase().query(TABELAJOGADORES,
                new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_APELIDO, COLUNA_EMAIL, COLUNA_SENHA, COLUNA_INTEL, COLUNA_TRETA, COLUNA_SORTE},
                "nome = '"+nome+"' and senha = '"+senha+"'",null,null,null,null);
        Jogador jogador = null;
        if (cursor.getCount() >= 1){
            cursor.moveToFirst();
            jogador =new Jogador();
            jogador.setId(cursor.getInt(0));
            jogador.setNome(cursor.getString(1));
            jogador.setApelido(cursor.getString(2));
            jogador.setEmail(cursor.getString(3));
            jogador.setSenha(cursor.getString(4));
            jogador.setInteligencia(cursor.getInt(5));
            jogador.setTreta(cursor.getInt(6));
            jogador.setSorte(cursor.getInt(7));
        }
        cursor.close();
        return jogador;
    }

    public void gravarJogador(Jogador jogador){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",jogador.getNome());
        contentValues.put("apelido",jogador.getApelido());
        contentValues.put("email",jogador.getEmail());
        contentValues.put("senha",jogador.getSenha());
        contentValues.put("treta",jogador.getTreta());
        contentValues.put("inteligencia",jogador.getInteligencia());
        contentValues.put("sorte",jogador.getSorte());
        this.getWritableDatabase().insert(TABELAJOGADORES, null, contentValues);
    }

    public void atualizarJogador(Jogador jogador){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",jogador.getNome());
        contentValues.put("apelido",jogador.getApelido());
        contentValues.put("email",jogador.getEmail());
        contentValues.put("senha",jogador.getSenha());
        this.getWritableDatabase().update(TABELAJOGADORES, contentValues, "_id = "+jogador.getId(),null);
    }

    public void deletarJogador(Jogador jogador){
        String where = "_id ="+ jogador.getId();
        this.getWritableDatabase().delete(TABELAJOGADORES, where, null);
        this.close();
    }

}
