package com.marlonheraclito.emaxepdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.marlonheraclito.emaxepdm.adapter.AdapterWorld;
import com.marlonheraclito.emaxepdm.controle.DaoAluno;
import com.marlonheraclito.emaxepdm.modelo.Aluno;
import com.marlonheraclito.emaxepdm.utils.Common;

import java.util.ArrayList;
import java.util.List;

public class PresenteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterWorld adapterWorld;
    DaoAluno dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presente);

        setTitle("Lista Presente");

        dao = new DaoAluno(this);
        List<Aluno> alunoList = dao.select();
        Common.listaAluno = alunoList;


        recyclerView = findViewById(R.id.recycleView);

        adapterWorld = new AdapterWorld(this, Common.listaAluno);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterWorld);

        adapterWorld.notifyDataSetChanged();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
