package br.unicamp.ft.e196208_g173381.aula3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MailFragment extends Fragment {

    private View view;
    private EditText destinatario;
    private EditText mensagem;


    public MailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mail, container, false);
        }

        destinatario = view.findViewById(R.id.edtpara);
        mensagem = view.findViewById(R.id.edtmessage);


        Button enviar = view.findViewById(R.id.btnenviar);
        enviar.setOnClickListener(
                new View.OnClickListener() {


                    public void onClick(View view) {
                        String msg = MailFragment.this.mensagem.getText().toString();
                        ((MainActivity) getActivity()).doSomething(msg);
                    }

                }
        );
        return view;
    }

}
