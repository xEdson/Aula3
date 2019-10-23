package br.unicamp.ft.e196208_g173381.aula3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

import br.unicamp.ft.e196208_g173381.aula3.internet.MyViaCepAsyncTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Jogo3Fragment extends Fragment {

    private View view;
    private ResponseJogo3 responseJogo3;
    private TextView frase;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private Button enviar;
    private Button atualizar;
    private MyJogo3AsyncTask myJogo3AsyncTask;


    public Jogo3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_jogo3, container, false);
        }

        frase = view.findViewById(R.id.frase);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.radio1);
        radioButton2 = view.findViewById(R.id.radio2);
        radioButton3 = view.findViewById(R.id.radio3);
        radioButton4 = view.findViewById(R.id.radio4);
        radioButton5 = view.findViewById(R.id.radio5);
        enviar = view.findViewById(R.id.jogo3Enviar);
        atualizar = view.findViewById(R.id.jogo3Atualizar);
        new MyJogo3AsyncTask(this).execute();



        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator();

            }
        });

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizar();

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void validator() {
        int selectId = radioGroup.getCheckedRadioButtonId();


        if (selectId != -1) {
            RadioButton selectedRadioButton = view.findViewById(selectId);

            if (selectedRadioButton.getText().toString().equals(responseJogo3.getNome())) {
                Toast toast = Toast.makeText(view.getContext(), "Acertou Miseravel", Toast.LENGTH_SHORT);
                toast.show();


            } else {
                Toast toast = Toast.makeText(view.getContext(), "Uh Rapaz, Errou", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        atualizar();
        radioGroup.clearCheck();
    }

    public void atualizar(){
        new MyJogo3AsyncTask(this).execute();
    }

    public void preencher(ResponseJogo3 responseJogo3) {
        this.responseJogo3 = responseJogo3;
        Collections.shuffle(responseJogo3.getOutros());
        frase.setText(responseJogo3.getFrase());
        radioButton1.setText(responseJogo3.getOutros().get(0));
        radioButton2.setText(responseJogo3.getOutros().get(1));
        radioButton3.setText(responseJogo3.getOutros().get(2));
        radioButton4.setText(responseJogo3.getOutros().get(3));
        radioButton5.setText(responseJogo3.getOutros().get(4));

    }

}
