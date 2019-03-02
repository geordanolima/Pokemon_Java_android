package com.example.geordano.xxxxxxx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private bd banco;
    private boolean iniciaLogado;
    private Jogador jogador;
    SharedPreferences login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = getSharedPreferences("arquivo", Context.MODE_PRIVATE);
        iniciaLogado = login.getBoolean("logado", false);
        banco = new bd(this,"banco", 2 );

        int i = 1;
        while (i<= 150){
            i++;
        }

        IniciarLogado();

        Button butao;
        butao = findViewById(R.id.jogar);
        butao.setOnClickListener(Login);

    }

    private void IniciarLogado(){
        if (iniciaLogado == true) {
            Toast.makeText(getApplicationContext(), "True", Toast.LENGTH_SHORT).show();
            String usuario = login.getString("usuario", "sem usuario");
            String password = login.getString("senha", "sem senha");
            String apelido = login.getString("apelido", "sem apelido");
            String email = login.getString("email", "email");
            jogador=banco.lerJogador(usuario, password);
            perfil p = new perfil();
            setContentView(R.layout.activity_perfil);
        }
    }

    private View.OnClickListener Login = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent logar = new Intent(MainActivity.this, login.class);
            startActivity(logar);
        }
    };
}
