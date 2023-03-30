package com.example.a1_programacaomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SituacaoAluno extends AppCompatActivity {

    TextView textView;

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
    }

}