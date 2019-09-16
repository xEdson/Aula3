package br.unicamp.ft.e196208_g173381.aula3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.e196208_g173381.aula3.alunos.Aluno;
import br.unicamp.ft.e196208_g173381.aula3.alunos.Alunos;
import br.unicamp.ft.e196208_g173381.aula3.alunos.MyFirstAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class BiografiasFragment extends Fragment {

    private TextView txtBiografia;
    private TextView txtBiografia2;
    private ImageView imagem;
    private Button next;
    private Button previous;
    private int position = 0;

    private View view;


    public BiografiasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_biografias, container, false);
        }

        txtBiografia = view.findViewById(R.id.txtBiografia);
        txtBiografia2 = view.findViewById(R.id.txtBiografia2);
        imagem = view.findViewById(R.id.imagemBiografia);
        next = view.findViewById(R.id.btnext);
        previous = view.findViewById(R.id.btprevious);

        txtBiografia.setText(Alunos.alunos[position].getNome());
        imagem.setImageResource(Alunos.alunos[position].getFoto());
        txtBiografia2.setText(Alunos.alunos[position].getDescricao());


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicNext();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPrevious();
            }
        });


        return view;
    }


    private void clicNext() {


        position = (position+=1)%Alunos.alunos.length;
        System.out.println(position);
            txtBiografia.setText(Alunos.alunos[position].getNome());
            imagem.setImageResource(Alunos.alunos[position].getFoto());
            txtBiografia2.setText(Alunos.alunos[position].getDescricao());
    }


    private void clickPrevious() {

        if (position >0) {
            position--;
            txtBiografia.setText(Alunos.alunos[position].getNome());
            imagem.setImageResource(Alunos.alunos[position].getFoto());
            txtBiografia2.setText(Alunos.alunos[position].getDescricao());
        }else if(position<=0){
            position = Alunos.alunos.length-1;
            txtBiografia.setText(Alunos.alunos[position].getNome());
            imagem.setImageResource(Alunos.alunos[position].getFoto());
            txtBiografia2.setText(Alunos.alunos[position].getDescricao());
        }

    }

    public void setPosition(int position){
        this.position = position;
    }

}
