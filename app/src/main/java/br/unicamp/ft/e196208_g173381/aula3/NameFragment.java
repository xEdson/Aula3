package br.unicamp.ft.e196208_g173381.aula3;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.unicamp.ft.e196208_g173381.aula3.alunos.Aluno;
import br.unicamp.ft.e196208_g173381.aula3.alunos.AlunoBanco;
import br.unicamp.ft.e196208_g173381.aula3.alunos.Alunos;
import br.unicamp.ft.e196208_g173381.aula3.database.DatabaseHelper;


public class NameFragment extends Fragment {

    private View lview;

    private Random random = new Random();
    private String nomeCorreto;
    private int idCorreto;
    private int positionAluno;
    private int numTentativas;

    private ImageView imageView;
    private TextView txtTentativas;
    private TextView txtFeedback;
    private ArrayList<Button> arrayListButton;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private List<Aluno> listAlunos = Arrays.asList(Alunos.alunos);
    private ArrayList<AlunoBanco> listAlunoBanco = new ArrayList<>();



    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_name, container, false);
        }

        imageView = lview.findViewById(R.id.imageFoto);
        txtTentativas = lview.findViewById(R.id.txtTentativas);
        txtFeedback = lview.findViewById(R.id.txtFeedback);

        arrayListButton = new ArrayList<>();
        arrayListButton.add((Button) lview.findViewById(R.id.button1));
        arrayListButton.add((Button) lview.findViewById(R.id.button2));
        arrayListButton.add((Button) lview.findViewById(R.id.button3));
        arrayListButton.add((Button) lview.findViewById(R.id.button4));
        arrayListButton.add((Button) lview.findViewById(R.id.button5));
        arrayListButton.add((Button) lview.findViewById(R.id.button6));
        arrayListButton.add((Button) lview.findViewById(R.id.button7));
        arrayListButton.add((Button) lview.findViewById(R.id.button8));
        arrayListButton.add((Button) lview.findViewById(R.id.button9));

        startGame();

        View.OnClickListener guessButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeEscolhido = ((Button) v).getText().toString();
                if (nomeEscolhido.equals( nomeCorreto) ){
                    txtFeedback.setText("Correto!!");
                    new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    startGame();
                                }
                            }, 2000);
                } else {
                    txtFeedback.setText("Incorreto!!");
                    numTentativas--;
                    txtTentativas.setText("Tentativas: " + numTentativas);

                    if (numTentativas == 0) {
                        txtFeedback.setText("Você Perdeu!!");

                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        startGame();
                                    }
                                }, 2000);

//                        new Handler().postDelayed(
//                                new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        if (((MainActivity) getActivity()) != null) {
//                                            ((MainActivity) getActivity()).showBiografia(positionAluno);
//                                        }
//                                    }
//                                }, 2000);


                    }
                }
            }
        };

        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setOnClickListener(guessButtonListener);
        }


        return lview;
    }



    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        onSelecionar();
    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    public void onInserir(ArrayList<AlunoBanco> alunoBancos) {

        for (AlunoBanco alunoBanco: alunoBancos){
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", alunoBanco.getId());
            contentValues.put("nome", alunoBanco.getNome());
            contentValues.put("tentativaGlobal", alunoBanco.getTentativaGlobal());
            contentValues.put("tentativaSelf", alunoBanco.getTentativaSelf());
            contentValues.put("acerto", alunoBanco.getAcerto());
            contentValues.put("erro", alunoBanco.getErro());

            sqLiteDatabase.insert("alunos", null, contentValues);
        }
    }

    private void startGame() {
        int guess = random.nextInt(Alunos.alunos.length);
        positionAluno = guess;
        Aluno aluno = Alunos.alunos[guess];
        nomeCorreto = aluno.getNome().split(" ")[0].toLowerCase();
        idCorreto = aluno.getFoto();
        imageView.setImageResource(aluno.getFoto());
        numTentativas = 3;
        txtTentativas.setText("Tentativas: " + numTentativas);
        txtFeedback.setText("");

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            Aluno candidate = Alunos.alunos[(guess + i) % Alunos.alunos.length];
            arrayList.add(candidate.getNome().split(" ")[0].toLowerCase());
        }
        Collections.shuffle(arrayList);
        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setText(arrayList.get(i));
        }
    }

//    public void onAtualizar(int id) {
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("erro", 2);
//
//        String whereClause = "_id = ?";
//        String[] whereArgs = new String[]{Integer.toString(id)};
//
//        sqLiteDatabase.update("tabela", contentValues, whereClause, whereArgs);
//    }

    public void onSelecionar() {
        String sql = "Select * from alunos";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            AlunoBanco alunoBanco = new AlunoBanco();
            do {
                alunoBanco.setId(cursor.getInt(0));
                alunoBanco.setNome(cursor.getString(1));
                alunoBanco.setAcerto(cursor.getInt(2));
                alunoBanco.setErro(cursor.getInt(3));
                alunoBanco.setTentativaGlobal(cursor.getInt(4));
                alunoBanco.setTentativaSelf(cursor.getInt(5));
                listAlunoBanco.add(alunoBanco);

            } while (cursor.moveToNext());
        }else{

            for (Aluno a : listAlunos){
                AlunoBanco alunoBanco = new AlunoBanco();
                alunoBanco.setId(a.getFoto());
                alunoBanco.setNome(a.getNome().split(" ")[0].toLowerCase());
                alunoBanco.setAcerto(0);
                alunoBanco.setErro(0);
                alunoBanco.setTentativaGlobal(0);
                alunoBanco.setTentativaSelf(0);
                listAlunoBanco.add(alunoBanco);
            }
            onInserir(listAlunoBanco);
        }
        cursor.close();

    }

}
