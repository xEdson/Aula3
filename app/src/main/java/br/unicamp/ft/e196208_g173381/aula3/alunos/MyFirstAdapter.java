package br.unicamp.ft.e196208_g173381.aula3.alunos;

import android.annotation.SuppressLint;
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

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    private ArrayList<Aluno> alunos;
    private MyOnItemClickListener myOnItemClickListener;

    public MyFirstAdapter(ArrayList arrayListAluno) {
        this.alunos = arrayListAluno;
    }

    public void removeItem(int position){
        alunos.remove(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_layout, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOnItemClickListener != null) {
                    TextView txt = view.findViewById(R.id.txtviewNome);
                    myOnItemClickListener.onMyItemClick(
                            txt.getText().toString()
                    );
                }
            }

        });



        final MyFirstNewHolder holder = new MyFirstNewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        final Aluno aluno = alunos.get(i);
        ((MyFirstNewHolder) viewHolder).onBind(aluno);

       viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               if (myOnItemClickListener != null) {
                   myOnItemClickListener.onMyItemLongClick(i);

                   return true;
               }
               return true;
           }
       });
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
            imagem.setImageResource(aluno.getFoto());
            descricao.setText(aluno.getDescricao());
        }
    }

    public interface MyOnItemClickListener {
        void onMyItemClick(String name);
        void onMyItemLongClick(int position);
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}


