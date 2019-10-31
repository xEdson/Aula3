package br.unicamp.ft.e196208_g173381.aula3.jogo3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.unicamp.ft.e196208_g173381.aula3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusJogador extends Fragment {

    private View view;

    public StatusJogador() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_status_jogador, container, false);
        }
        new MyFirstWebServiceJogo3Status(this).execute();
        return view;
    }

    public void put(String val){
        ((TextView) view.findViewById(R.id.listaJogador)).setText(val);

    }

}
