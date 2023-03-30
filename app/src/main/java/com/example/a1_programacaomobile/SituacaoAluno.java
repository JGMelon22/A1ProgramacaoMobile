package com.example.a1_programacaomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SituacaoAluno extends AppCompatActivity {

    TextView textView;
    EditText txtNotaA1;
    Button btnEnviarAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_aluno);

        // Modifica nome da top bar
        getSupportActionBar().setTitle("A1 Programação Mobile");

        // Instancia componentes gráficos
        textView = findViewById(R.id.txtViewNotaFinal2);
        btnEnviarAS = findViewById(R.id.buttonEnviar);
        txtNotaA1 = findViewById(R.id.editTextA1);

        // Obtem dados da activity anterior
        String valor = getIntent().getStringExtra("ChaveInfoAluno");
        String notaA1 = getIntent().getStringExtra("ChaveNotaA1Aluno");
        textView.setText(valor);
        txtNotaA1.setText(notaA1);

        // Julgar se aluno precisa/pode fazer AS baseado no valor obtido da activity 1
        if (Float.parseFloat(txtNotaA1.getText().toString()) > 6) {
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaA1.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
        } else if (Float.parseFloat(txtNotaA1.getText().toString()) < 6 && Float.parseFloat(txtNotaA1.getText().toString()) > 4) {
            btnEnviarAS.setVisibility(View.VISIBLE);
            txtNotaA1.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        } else {
            btnEnviarAS.setVisibility(View.VISIBLE);
            txtNotaA1.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
        }
    }
}