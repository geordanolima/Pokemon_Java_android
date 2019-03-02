package com.example.geordano.xxxxxxx;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DadosUsuario extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Jogador jogador;


    public DadosUsuario() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DadosUsuario newInstance(Jogador jogador) {
        DadosUsuario fragment = new DadosUsuario();
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
        View frag = inflater.inflate(R.layout.fragment_edicao,container,false);
        if (jogador != null){
            TextView ap = frag.findViewById(R.id.apelido);
            ap.setText(jogador.getApelido());

            TextView no = frag.findViewById(R.id.nomex);
            no.setText(jogador.getNome());
        }

        return frag;
    }

}
