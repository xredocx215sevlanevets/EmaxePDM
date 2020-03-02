package com.marlonheraclito.emaxepdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.marlonheraclito.emaxepdm.controle.DaoAluno;
import com.marlonheraclito.emaxepdm.modelo.Aluno;
import com.marlonheraclito.emaxepdm.utils.Common;

public class AtualizarActivity extends AppCompatActivity {

    DaoAluno dao;

    Button btnCancelarUp, btnSalvarUp;
    EditText edtNomeUp, edtNumeroUp, edtPcUp;
    RadioButton rb_PresenteUp, rb_FaltouUp, rb_DesistiuUp;

    int id;
    String id_bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        setTitle("Atualizar");

        dao = new DaoAluno(this);

        btnCancelarUp = findViewById(R.id.btnCancelarUp);
        btnSalvarUp = findViewById(R.id.btnSalvarUp);

        edtNomeUp = findViewById(R.id.edtNomeUpdate);
        edtNumeroUp = findViewById(R.id.edtNumeroUpdate);
        edtPcUp = findViewById(R.id.edtPcUpdate);

        rb_PresenteUp = findViewById(R.id.RadioBPresenteUp);
        rb_FaltouUp = findViewById(R.id.RadioBFaltouUp);
        rb_DesistiuUp = findViewById(R.id.RadioBDesistiuUp);

        Intent in = getIntent();
        id = in.getIntExtra("posicao", 0);

        Aluno aluno = Common.listaAluno.get(id);

        edtNomeUp.setText(aluno.getNome());
        edtNumeroUp.setText(aluno.getNumero());
        edtPcUp.setText(aluno.getPc());

        id_bd = aluno.getId();

        String presencia = aluno.getPresencia();

     if(presencia.equals("P")) {
         rb_PresenteUp.setChecked(true);
     } else if (presencia.equals("F")){
         rb_FaltouUp.setChecked(true);
     } else {
         rb_DesistiuUp.setChecked(true);
     }

        cancelar();
        atualizar();

    }

    public void cancelar(){
        btnCancelarUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(AtualizarActivity.this);
                builder.setTitle("Deseja cancelar ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }); builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nao faz nada
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void atualizar(){
        btnSalvarUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(AtualizarActivity.this);
                builder.setTitle("Tem certeza que quer atualizar ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        atualizarHelper();
                    }
                });
                builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void atualizarHelper(){
        String nome = edtNomeUp.getText().toString();
        String numero = edtNumeroUp.getText().toString();
        String pc = edtPcUp.getText().toString();

        String presencia = "";

        if(rb_PresenteUp.isChecked()) {
            presencia = "P";
        } if (rb_FaltouUp.isChecked()) {
            presencia = "F";
        } if (rb_DesistiuUp.isChecked()) {
            presencia = "D";
        }

        Aluno aluno = new Aluno(id_bd, nome, numero, pc, presencia);
        try {
            Boolean resposta = dao.update(aluno);
            if(resposta) {
                Toast.makeText(this, nome + " Atualizado", Toast.LENGTH_SHORT).show();
                if(rb_PresenteUp.isChecked()) {
                 Intent in = new Intent(this, PresenteActivity.class);
                 startActivity(in);
                } if (rb_FaltouUp.isChecked()) {
                    Intent in = new Intent(this, FaltouActivity.class);
                    startActivity(in);
                } if (rb_DesistiuUp.isChecked()) {
                    Intent in = new Intent(this, DesistiuActivity.class);
                    startActivity(in);
                }

                finish();
            }
        } catch (Exception e) {

        }
    }
}
