package com.example.geordano.xxxxxxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    bd banco = new bd(this,"banco", 2 );
    private TextView nome;
    private TextView senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


            Button cadastrar;
            cadastrar = findViewById(R.id.cadastrar);
            cadastrar.setOnClickListener(Cadastrar);
            Button logar;
            logar = findViewById(R.id.logar);
            logar.setOnClickListener(Logar);


    }

    private View.OnClickListener Logar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (false){
                Intent logando = new Intent(login.this, perfil.class);
                startActivity(logando);
            } else {
                nome = findViewById(R.id.edtlogin);
                senha = findViewById(R.id.edtsenha);
                Jogador jogador;
                try {
                    jogador = banco.lerJogador(nome.getText().toString(), senha.getText().toString());
                } catch (Exception E){
                    jogador = null;
                }
                if (jogador == null) {
                    Toast.makeText(getApplicationContext(), "Não pode logar", Toast.LENGTH_SHORT).show();
                } else{
                    Intent perfil = new Intent(login.this, perfil.class);
                    perfil.putExtra("Jogador", jogador);
                    startActivity(perfil);
                }
            }
        }
    };
    private View.OnClickListener Cadastrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent cadastrando = new Intent(login.this, cadastro.class);
            startActivity(cadastrando);
        }
    };
}
