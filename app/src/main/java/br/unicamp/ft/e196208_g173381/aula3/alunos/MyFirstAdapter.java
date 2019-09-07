package br.unicamp.ft.e196208_g173381.aula3.alunos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.unicamp.ft.e196208_g173381.aula3.R;

public class MyFirstAdapter extends RecyclerView.Adapter {

    private ArrayList<Aluno> alunos;


    public MyFirstAdapter(ArrayList arrayListAluno) {
        this.alunos = arrayListAluno;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_layout, viewGroup, false);

        MyFirstNewHolder holder = new MyFirstNewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Aluno aluno = alunos.get(i);
        ((MyFirstNewHolder) viewHolder).onBind(aluno);

    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public class MyFirstNewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private ImageView imagem;
        private TextView descricao;

        public MyFirstNewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.txtviewNome);
            imagem = itemView.findViewById(R.id.imagem);
            descricao = itemView.findViewById(R.id.txtviewDescricao);
        }

        public void onBind(Aluno aluno) {
            nome.setText(aluno.getNome());
            imagem.setImageAlpha(aluno.getFoto());
            descricao.setText(aluno.getDescricao());


        }
    }
}


