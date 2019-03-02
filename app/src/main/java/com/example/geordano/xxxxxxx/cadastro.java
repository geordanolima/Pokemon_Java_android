package com.example.geordano.xxxxxxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class cadastro extends AppCompatActivity {

    Jogador jogador = new Jogador();
    bd banco = new bd(this,"banco", 2 );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button treta;
        treta = findViewById(R.id.cadastrar);
        treta.setOnClickListener(finalizar);
    }

    private View.OnClickListener finalizar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            EditText nome = findViewById(R.id.nomeVivente);
            EditText apelido = findViewById(R.id.apelidoVivente);
            EditText email = findViewById(R.id.emailVivente);
            EditText senha = findViewById(R.id.senhaVivente);
            EditText confSenha = findViewById(R.id.confSenha);
            if ((nome.getText().toString().equals("")) ||
                (apelido.getText().toString().equals("")) ||
                (email.getText().toString().equals("")) ||
                (senha.getText().toString().equals("")) ||
                (confSenha.getText().toString().equals(""))) {
                Toast.makeText(getApplicationContext(),"Falta tretas pra preencher", Toast.LENGTH_SHORT).show();
            } else {
                if (senha.getText().toString().equals(confSenha.getText().toString())) {
                    jogador.setNome(nome.getText().toString());
                    jogador.setApelido(apelido.getText().toString());
                    jogador.setEmail(email.getText().toString());
                    jogador.setSenha(senha.getText().toString());

                    setContentView(R.layout.finalizar);
                    Button treta;
                    treta = findViewById(R.id.acabatreta);
                    treta.setOnClickListener(perfil);
                } else {
                    Toast.makeText(getApplicationContext(),"Dá um confere na senha, tá errada!", Toast.LENGTH_SHORT).show();
                }
            }


        }
    };

    private View.OnClickListener perfil = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent perfil = new Intent(cadastro.this, perfil.class);
            SeekBar intel = findViewById(R.id.seekIntel);
            SeekBar sorte = findViewById(R.id.seekSorte);
            SeekBar treta = findViewById(R.id.seekTreta);
            jogador.setInteligencia(intel.getProgress());
            jogador.setSorte(sorte.getProgress());
            jogador.setTreta(treta.getProgress());
            banco.gravarJogador(jogador);
            perfil.putExtra("Jogador", jogador);
            startActivity(perfil);
        }
    };


}
