package com.example.felipe.bartapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void onClickEnter(View view) throws Exception {

        Intent intent = new Intent(this, SecondScreen.class);


        EditText txtUser = (EditText) findViewById(R.id.editTextUsuario);
        EditText txtPassword = (EditText) findViewById(R.id.editTextSenha);
        TextView resultArea = (TextView) findViewById(R.id.Resultado);

        String username = String.valueOf(txtUser.getText());
        String password = String.valueOf(txtPassword.getText());

        Connection connection = new Connection();

        JSONObject jj = connection.login(username,password);
        if (!jj.has("status")) {
            intent.putExtra(EXTRA_MESSAGE, jj.getString("userName"));
        }
        else
            resultArea.setText("Usuário Inválido");

        if (!jj.has("status")) startActivity(intent);

        }

    }

