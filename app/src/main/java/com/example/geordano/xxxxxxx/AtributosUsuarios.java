package com.example.geordano.xxxxxxx;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

public class AtributosUsuarios extends Fragment {

    private Context context;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Jogador jogador;

    public AtributosUsuarios() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AtributosUsuarios newInstance(Jogador jogador) {
        AtributosUsuarios fragment = new AtributosUsuarios();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, jogador);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jogador = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dados_usuario, container, false);
        if (jogador != null){
            TextView intel = v.findViewById(R.id.txtvalInt);
            intel.setText(Integer.toString(jogador.getInteligencia()));

            TextView sorte = v.findViewById(R.id.txtValSorte);
            sorte.setText(Integer.toString(jogador.getSorte()));

            TextView treta = v.findViewById(R.id.txtValTreta);
            treta.setText(Integer.toString(jogador.getTreta()));

            Button sair = v.findViewById(R.id.sair_treta);
//            Log.i(">>>>>>>>>>>",sair.toString());

            sair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inicio = new Intent(context, login.class);
                    context.startActivity(inicio);
                }
            });
        }
        return v;
        //return inflater.inflate(R.layout.fragment_edicao, container, false);
    }

}
