package com.marlonheraclito.emaxepdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marlonheraclito.emaxepdm.controle.DaoUser;
import com.marlonheraclito.emaxepdm.modelo.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btnSingIn;
    Button btnLogin;

    EditText edtEmail, edtPass;

    DaoUser dao;

    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");

        dao = new DaoUser(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnSingIn = findViewById(R.id.btnSingIn);

        edtEmail = findViewById(R.id.idEmailLogin);
        edtPass = findViewById(R.id.idPasswordLogin);

        openSingIn();
        loginUser();

    }

    public void openSingIn() {
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                onPause();
            }
        });
    }

    public void loginUser(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        List<User> list = dao.select();
        email = edtEmail.getText().toString();
        password = edtPass.getText().toString();

        if(email.equals("") || password.equals("")) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (email.equals(list.get(i).getEmail()) && password.equals(list.get(i).getPassword())) {
                    openMain();
                } else {
                    Toast.makeText(this, "Login incorreto", Toast.LENGTH_SHORT).show();
                    edtEmail.setText("");
                    edtPass.setText("");
                }
            }
        }
        }


    public void openMain(){
        Intent intent = new Intent(this, ExameActivity.class);
        startActivity(intent);
        finish();
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
