package br.unicamp.ft.e196208_g173381.aula3;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.unicamp.ft.e196208_g173381.aula3.alunos.Aluno;
import br.unicamp.ft.e196208_g173381.aula3.alunos.AlunoBanco;
import br.unicamp.ft.e196208_g173381.aula3.alunos.Alunos;
import br.unicamp.ft.e196208_g173381.aula3.database.DatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class DesempenhoFragment extends Fragment {

    private View view;
    private TextView pessoaNaoSabeNome;
    private TextView nomeFazErrar;
    private TextView porcentagemErro;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private ArrayList<AlunoBanco> listAlunoBanco = new ArrayList<>();
    private List<Aluno> listAlunos = Arrays.asList(Alunos.alunos);

    public DesempenhoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_desempenho, container, false);
        }
        pessoaNaoSabeNome = view.findViewById(R.id.pessoaNaoSabeNome);
        nomeFazErrar = view.findViewById(R.id.nomeFazJogadorErrar);
        porcentagemErro = view.findViewById(R.id.porcentagemErros);
        return view;
    }

    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        onSelecionar();
        preencherDesempenho();
    }

    public void preencherDesempenho() {
        int maiorErro = 0;
        int maiorTentativaGlobal = 0;
        int totalErro = 0;
        int totalTentativa = 0;
        String nomeErro = null;
        String nomeErrar = null;

        for (AlunoBanco alunoBanco : listAlunoBanco) {
            totalErro += alunoBanco.getErro();
            totalTentativa += alunoBanco.getTentativaSelf();
            if (alunoBanco.getErro() > maiorErro) {
                maiorErro = alunoBanco.getErro();
                nomeErro = alunoBanco.getNome();
            }
            if (alunoBanco.getTentativaGlobal() > maiorTentativaGlobal) {
                maiorTentativaGlobal = alunoBanco.getTentativaGlobal();
                nomeErrar = alunoBanco.getNome();
            }

        }
        DecimalFormat df = new DecimalFormat("#,###.00");
        pessoaNaoSabeNome.setText("Pessoar que menos sabe o nome é " + nomeErro);
        nomeFazErrar.setText("O nome que mais faz os jogadores errar é: " + nomeErrar);
        if(totalTentativa<=0){
            porcentagemErro.setText("0");
        }else{
            porcentagemErro.setText("Porcentagem de erros: "+(df.format((totalErro*100)/totalTentativa))+"%");
        }

    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }


    public void onInserir(AlunoBanco alunoBanco) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", alunoBanco.getId());
        contentValues.put("nome", alunoBanco.getNome());
        contentValues.put("tentativaGlobal", alunoBanco.getTentativaGlobal());
        contentValues.put("TentativaSelf", alunoBanco.getTentativaSelf());
        contentValues.put("acerto", alunoBanco.getAcerto());
        contentValues.put("erro", alunoBanco.getErro());

        sqLiteDatabase.insert("alunos", null, contentValues);

    }

    public void onSelecionar() {
        String sql = "Select * from alunos";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                AlunoBanco alunoBanco = new AlunoBanco();
                alunoBanco.setId(cursor.getInt(0));
                alunoBanco.setNome(cursor.getString(1));
                alunoBanco.setTentativaGlobal(cursor.getInt(2));
                alunoBanco.setTentativaSelf(cursor.getInt(3));
                alunoBanco.setAcerto(cursor.getInt(4));
                alunoBanco.setErro(cursor.getInt(5));
                listAlunoBanco.add(alunoBanco);
            } while (cursor.moveToNext());
        } else {

            for (Aluno a : listAlunos) {
                AlunoBanco alunoBanco = new AlunoBanco();
                alunoBanco.setId(a.getFoto());
                alunoBanco.setNome(a.getNome().split(" ")[0].toLowerCase());
                alunoBanco.setAcerto(0);
                alunoBanco.setErro(0);
                alunoBanco.setTentativaGlobal(0);
                alunoBanco.setTentativaSelf(0);
                onInserir(alunoBanco);
                listAlunoBanco.add(alunoBanco);
            }
        }
        cursor.close();

    }
}
