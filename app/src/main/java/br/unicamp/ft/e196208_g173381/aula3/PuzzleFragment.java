package br.unicamp.ft.e196208_g173381.aula3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class PuzzleFragment extends Fragment {

    private LinearLayout view;
    private AbstractPuzzle abstractPuzzle;
    private Spinner mySpinner;
    private String itemSelected;
    private Button button;
    private LinearLayout newLinearLayout;


    public PuzzleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        List<String> listTela = new ArrayList<>();
        listTela.add("Gislaine");
        listTela.add("Rodrigo");
        if (view == null) {
            // Inflate the layout for this fragment
            view = (LinearLayout) inflater.inflate(R.layout.fragment_puzzle, container, false);
        }
        mySpinner = view.findViewById(R.id.spinnerId);
        button = view.findViewById(R.id.reiniciar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, listTela);
        mySpinner.setAdapter(adapter);

        if (newLinearLayout == null) {
            newLinearLayout = view.findViewById(R.id.newLinearLayout);
        }
        startPuzzle(0, newLinearLayout);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelected = mySpinner.getSelectedItem().toString();
                if (itemSelected.equals("Gislaine")) {
                    startPuzzle(0, newLinearLayout);
                } else {
                    startPuzzle(1, newLinearLayout);
                }

            }
        });


        return view;
    }


    private void startPuzzle(int puzzle, LinearLayout view) {
        view.removeAllViews();
        Board board = Boards.getPuzzle(puzzle);
        ArrayList<ImageView> imageViews = new ArrayList();



        for (int i = 0; i < board.getNumLines(); i++) {
            LinearLayout row = new LinearLayout(getContext());
            // Configurando os par^ametros
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
            for (int j = 0; j < board.getNumColumns(); j++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);


                System.out.println(i + j);


                // Configurando os parametros
                imageView.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                board.getWidth(),
                                board.getHeight()));
                imageViews.add(imageView);
                row.addView(imageView);

            }

            view.addView(row);


        }
        FreeBlockPuzzle freeBlockPuzzle = new FreeBlockPuzzle(board, imageViews);


    }

}
