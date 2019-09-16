package br.unicamp.ft.e196208_g173381.aula3.alunos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.e196208_g173381.aula3.MainActivity;
import br.unicamp.ft.e196208_g173381.aula3.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;
    private MyFirstAdapter adapter;



    public AlunosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_alunos, container, false);
        }

        mRecyclerView = view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));

        adapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));

        adapter.setMyOnItemClickListener(new MyFirstAdapter.MyOnItemClickListener() {
            @Override
            public void onMyItemClick(String name) {
                Toast.makeText(getActivity(), name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMyItemLongClick(int position) {
                ((MainActivity) getActivity()).showBiografia(position);
            }
        });


        mRecyclerView.setAdapter(adapter);

        return view;
    }

}
