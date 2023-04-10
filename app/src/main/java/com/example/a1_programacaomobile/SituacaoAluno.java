package com.example.a1_programacaomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SituacaoAluno extends AppCompatActivity {

    TextView txtViewInfo, txtViewMedia, txtViewAS, txtViewInfo2;
    EditText txtNotaAS, txtNotaA1, txtNotaA2;
    Button btnEnviarAS;
    float notaAS;

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
        txtViewInfo2 = findViewById(R.id.textViewInfo2);

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

    public void calcularMediaAS(View view) {
        notaAS = Float.parseFloat(txtNotaAS.getText().toString());

        // Critica se nota é vazia e dentro de intervalo
        if (Float.parseFloat(txtNotaAS.getText().toString()) < 0 || (Float.parseFloat(txtNotaAS.getText().toString()) > 10)) {
            Toast.makeText(getApplicationContext(), "Por favor, insira uma nota válida!", Toast.LENGTH_SHORT).show();
        }
        // Critica se nota é vazia e dentro de intervalo
        if (Float.parseFloat(txtNotaAS.getText().toString()) < 0 || (Float.parseFloat(txtNotaAS.getText().toString()) > 10)) {
            Toast.makeText(getApplicationContext(), "Por favor, insira uma nota válida!", Toast.LENGTH_SHORT).show();
        } else {
            // Chama a classe para realizar os calculos
            Calculos calculos = new Calculos(Float.parseFloat(txtNotaA1.getText().toString()), Float.parseFloat(txtNotaA2.getText().toString()), notaAS);

            float notaAlunoPosAS = calculos.calcularNotaRecuperacao();

            // Julga se o aluno passou ou não
            if (notaAlunoPosAS >= 6.0) {
                // Metodo de calcular
                txtViewInfo2.setText(String.format("Nota final do Aluno = %.2f\nParabéns, você foi aprovado!\uD83E\uDD73", notaAlunoPosAS));

            } else {
                txtViewInfo2.setText(String.format("Nota final do Aluno = %.2f\nInfelizmente você foi reprovado!\uD83D\uDE22", notaAlunoPosAS));
            }

            if (Float.parseFloat(txtNotaAS.getText().toString()) < 0 || (Float.parseFloat(txtNotaA1.getText().toString()) > 10) || Float.parseFloat(txtNotaA2.getText().toString()) < 0 || (Float.parseFloat(txtNotaA2.getText().toString()) > 10)) {
                Toast.makeText(getApplicationContext(), "Por favor, insira uma nota válida!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), AprovadoAS.class);
                intent.putExtra("ChaveInfoAluno2", txtViewInfo2.getText());
                startActivity(intent);
                finish();
            }
        }
    }


    // Calcular AS
    public void buttonEnviarOnClick(View view) {
        try {
            calcularMediaAS(view);

        } catch (
                Exception e) { // Em caso de erro, mostra mensagem para o usuário em uma toast
            Toast.makeText(getApplicationContext(), "Por favor, certifique-se de que foi informado a nota AS para prosseguir!", Toast.LENGTH_SHORT).show();
        }
    }
}