package com.marlonheraclito.emaxepdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.marlonheraclito.emaxepdm.controle.DaoAluno;
import com.marlonheraclito.emaxepdm.modelo.Aluno;

public class NovoActivity extends AppCompatActivity {

    DaoAluno daoAluno;

    EditText edtNome, edtNumero, edtPC;
    RadioButton rB_Presente, rB_Faltou, rB_Desistiu;

    Button btnSalvar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo);

        setTitle("Novo");

        daoAluno = new DaoAluno(this);

        edtNome = findViewById(R.id.edtNewNome);
        edtNumero = findViewById(R.id.edtNewNumero);
        edtPC = findViewById(R.id.edtNewPc);

        rB_Presente = findViewById(R.id.NewRadioBPresente);
        rB_Desistiu = findViewById(R.id.NewRadioBDesistiu);
        rB_Faltou = findViewById(R.id.NewRadioBFaltou);

        btnCancelar = findViewById(R.id.btnNewCancelar);
        btnSalvar = findViewById(R.id.btnNewSalvar);

        cancelar();
        salvar();

    }

    public void cancelar(){
       btnCancelar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog alertDialog;
               AlertDialog.Builder builder = new AlertDialog.Builder(NovoActivity.this);
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

    public void salvar(){
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarHelper();
            }
        });
    }

    public void salvarHelper(){
        String nome = edtNome.getText().toString();
        String numero = edtNumero.getText().toString();
        String pc = edtPC.getText().toString();

        String presencia = "";

        if(nome.equals("") || numero.equals("") || pc.equals("")) {

            Toast.makeText(this, "Preenche todos os campos", Toast.LENGTH_SHORT).show();

        }  else if (!rB_Desistiu.isChecked() && !rB_Faltou.isChecked() && !rB_Presente.isChecked()) {

                Toast.makeText(this, "Escolhe uma categoria", Toast.LENGTH_SHORT).show();

            } else {

                if (rB_Presente.isChecked()) {
                    presencia = "P";
                }
                if (rB_Faltou.isChecked()) {
                    presencia = "F";
                }
                if (rB_Desistiu.isChecked()) {
                    presencia = "D";
                }

                Aluno aluno = new Aluno(nome, numero, pc, presencia);

                try {
                    long res = daoAluno.insert(aluno);
                    if (res > 0) {
                        Toast.makeText(this, "Salvo com Sucesso", Toast.LENGTH_SHORT).show();
                        limparCampos();
                    }
                } catch (Exception e) {
                    Log.i("ERRO", String.valueOf(e));
                }

            }


        }


    public void limparCampos() {
        edtNome.setText("");
        edtNumero.setText("");
        edtPC.setText("");
        rB_Presente.setChecked(false);
        rB_Desistiu.setChecked(false);
        rB_Faltou.setChecked(false);
    }
}
