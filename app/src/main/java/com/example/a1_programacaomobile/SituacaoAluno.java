package com.example.a1_programacaomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SituacaoAluno extends AppCompatActivity {

    TextView txtViewInfo, txtViewMedia, txtViewAS;
    EditText txtNotaAS, txtNotaA1, txtNotaA2;
    Button btnEnviarAS;
    float notaAS, notaA1, notaA2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_aluno);

        // Modifica nome da top bar
        getSupportActionBar().setTitle("A1 Programação Mobile");

        // UI Components
        btnEnviarAS = findViewById(R.id.buttonEnviar);
        txtNotaAS = findViewById(R.id.editTextAS);
        txtViewAS = findViewById(R.id.textViewAS);

        // Instancia objeto grafico e obtem dados da activity anterior
        txtViewInfo = findViewById(R.id.textViewInfo);
        String valorViewInfo = getIntent().getStringExtra("ChaveInfoAluno");
        txtViewInfo.setText(valorViewInfo);

        txtNotaA1 = findViewById(R.id.editTextA1);
        String valorNotaA1 = getIntent().getStringExtra("ChaveNotaA1Aluno");
        txtNotaA1.setText(valorNotaA1);

        txtNotaA2 = findViewById(R.id.editTextA2);
        String valorNotaA2 = getIntent().getStringExtra("ChaveNotaA2Aluno");
        txtNotaA2.setText(valorNotaA2);

        txtViewMedia = findViewById(R.id.textViewMedia);
        String valorMedia = getIntent().getStringExtra("ChaveMediaAluno");
        txtViewMedia.setText(String.valueOf(valorMedia));

        // Julga se o Aluno pode/deve fazer AS
        if (Float.parseFloat(txtViewMedia.getText().toString()) >= 6 || Float.parseFloat(txtViewMedia.getText().toString()) < 4) {
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaAS.setVisibility(View.INVISIBLE);
            txtViewAS.setVisibility(View.INVISIBLE);
        } else {
            btnEnviarAS.setVisibility(View.VISIBLE);
            txtNotaAS.setVisibility(View.VISIBLE);
            txtViewAS.setVisibility(View.VISIBLE);
        }
    }
}