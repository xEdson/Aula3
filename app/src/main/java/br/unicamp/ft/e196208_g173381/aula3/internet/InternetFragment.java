package br.unicamp.ft.e196208_g173381.aula3.internet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import br.unicamp.ft.e196208_g173381.aula3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InternetFragment extends Fragment {

    private View lview;


    public InternetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_internet, container, false);
        }

        final TextView textView = lview.findViewById(R.id.textView);
        final EditText editText = lview.findViewById(R.id.editText);
        final Spinner spinner = lview.findViewById(R.id.restSpinner);

        lview.findViewById(R.id.btnViaCep).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url =
                                "https://projetoandroid-94ff7.firebaseio.com/"+editText.getText().toString() + "/.json";


                         String comando = spinner.getSelectedItem().toString();

                         Log.i("InternetFragment", url);
                         Log.i("InternetFragment", comando);

                         String json = "{\"ulissesdias\": {\"Nıvel\":\"MS\",\"estado civil\" : \"casado\",\"instituto\" :\"FT\",\"name\" : \"Ulisses Martins Dias\",\"titula¸c~ao\":\"doutorado\"}}";

                         new MyFirstWebService(textView).execute(url, comando,  json);
                    }
                }
        );


        return lview;
    }

}
