package com.example.a1_programacaomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SituacaoAluno extends AppCompatActivity {

    TextView textView;
    EditText txtNotaA1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_aluno);

        // Modifica nome da top bar
        getSupportActionBar().setTitle("A1 Programação Mobile");

        // Obtem dados da activity anterior
        textView = findViewById(R.id.txtViewNotaFinal2);
        String valor = getIntent().getStringExtra("ChaveInfoAluno");
        textView.setText(valor);

        // Julgar se aluno precisa/pode fazer AS baseado no valor obtido da activity 1
        txtNotaA1 = findViewById(R.id.editTextA1);
        String notaA1 = getIntent().getStringExtra("ChaveNotaA1Aluno");
        txtNotaA1.setText(notaA1);
    }
}