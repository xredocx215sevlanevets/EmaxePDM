package com.marlonheraclito.emaxepdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ExameActivity extends AppCompatActivity {

    Button btnNovo, btnPresentes, btnFaltou, btnDesistiu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame);

        btnNovo = findViewById(R.id.btnNovo);
        btnPresentes = findViewById(R.id.btnPresentes);
        btnFaltou = findViewById(R.id.btnFaltou);
        btnDesistiu = findViewById(R.id.btnDesistiu);

        openNovo();
        openPresentes();
        openFaltou();
        openDesistiu();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_def_id:
                openDefinicoes();
                return true;

            case R.id.menu_sair_id:
                sair();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sair(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(ExameActivity.this);
        builder.setMessage("Deseja sair?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ExameActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // nao faz nada
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void openNovo(){
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExameActivity.this, NovoActivity.class);
                startActivity(intent);
                onPause();
            }
        });
    }

    public void openPresentes(){
        btnPresentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExameActivity.this, PresenteActivity.class);
                startActivity(intent);
                onPause();
            }
        });
    }

    public void openFaltou(){
        btnFaltou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExameActivity.this, FaltouActivity.class);
                startActivity(intent);
                onPause();
            }
        });
    }
    public void openDesistiu(){
        btnDesistiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExameActivity.this, DesistiuActivity.class);
                startActivity(intent);
                onPause();
            }
        });
    }

    public void openDefinicoes(){
        Intent in = new Intent(this, DefinicoesActivity.class);
        startActivity(in);
        onPause();
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
