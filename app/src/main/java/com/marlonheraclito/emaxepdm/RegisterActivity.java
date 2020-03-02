package com.marlonheraclito.emaxepdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marlonheraclito.emaxepdm.controle.DaoUser;
import com.marlonheraclito.emaxepdm.modelo.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Button btnCancel, btnRegister;
    EditText edtEmail;
    EditText edtPassword1, edtPassword2;

    DaoUser dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("");

        dao = new DaoUser(this);

        btnCancel = findViewById(R.id.btnCancelar);
        btnRegister = findViewById(R.id.btnRegister);

        edtEmail = findViewById(R.id.edtEmailRegister);
        edtPassword1 = findViewById(R.id.edtPasswordRegister1);
        edtPassword2 = findViewById(R.id.edtPasswordRegister2);

        cancelarRegister();
        registerUser();
    }

    public void cancelarRegister(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage("Deseja cancelar");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Nao faz nada
                    }
                });

                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void registerUser(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUserPost();

            }
        });
    }

    public void registerUserPost(){
        String email = edtEmail.getText().toString();
        String pass1 = edtPassword1.getText().toString();
        String pass2 = edtPassword2.getText().toString();

        if(email.equals("") || pass1.equals("") || pass2.equals("")) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();

        } else if(!pass1.equals(pass2)) {
            Toast.makeText(this, "Password incorreto", Toast.LENGTH_SHORT).show();

        } else {
            User user = new User(email, pass1);
            try {
                List<User> list = dao.select();
                for(int i = 0; i < list.size(); i ++){
                    if(email.equals(list.get(i).getEmail())){
                        Toast.makeText(this, "Este email ja existe", Toast.LENGTH_SHORT).show();
                    } else {
                        long res = dao.insert(user);
                        if (res > 0 ){
                            Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }

            } catch (Exception e) {
                Log.i("Erro", String.valueOf(e));
            }
        }


    }
}
