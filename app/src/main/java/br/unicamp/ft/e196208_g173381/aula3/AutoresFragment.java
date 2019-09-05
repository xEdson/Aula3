package br.unicamp.ft.e196208_g173381.aula3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutoresFragment extends Fragment {

    private View view;
    private String mensagem;
    private TextView textView;


    public AutoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_autores, container, false);
        }

        textView = view.findViewById(R.id.lblmessage);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        textView.setText(mensagem);
    }

    public void setText(String msg) {
        this.mensagem = msg;
    }

}
