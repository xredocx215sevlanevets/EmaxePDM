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

public class FaltouActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterWorld adapterWorld;
    DaoAluno dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faltou);

        setTitle("Lista Faltou");

        dao = new DaoAluno(this);
        List<Aluno> alunoList = dao.selectFaltou();
        Common.listaAluno = alunoList;

        recyclerView = findViewById(R.id.recycleViewFaltou);

        adapterWorld = new AdapterWorld(this, Common.listaAluno);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterWorld);

        adapterWorld.notifyDataSetChanged();
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
