package com.marlonheraclito.emaxepdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.marlonheraclito.emaxepdm.adapter.AdapterWorld;
import com.marlonheraclito.emaxepdm.controle.DaoAluno;
import com.marlonheraclito.emaxepdm.modelo.Aluno;
import com.marlonheraclito.emaxepdm.utils.Common;

import java.util.List;

public class DesistiuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterWorld adapterWorld;
    DaoAluno dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desistiu);

        setTitle("Lista Desistiu");

        dao = new DaoAluno(this);
        List<Aluno> alunoList = dao.selectDesistiu();
        Common.listaAluno = alunoList;

        recyclerView = findViewById(R.id.recycleViewDesistiu);

        adapterWorld = new AdapterWorld(this, Common.listaAluno);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterWorld);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
