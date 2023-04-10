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

    TextView txtViewInfo, txtViewAS;
    EditText txtNotaAS;
    Button btnEnviarAS;
    float notaAS, notaAlunoPosAS;
    String nomeAluno;

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
        txtViewInfo = findViewById(R.id.textViewInfo);

        // Obtem valores da actvity anterior
        Bundle dados = getIntent().getExtras();
        nomeAluno = dados.getString("ChaveNomeAluno");
        Float valorMedia = dados.getFloat("ChaveMedia");

        // Julga se o Aluno pode/deve fazer AS
        if (valorMedia >= 6.0F) {
            txtViewInfo.setText(String.format(("Parabéns, %s. Você foi aprovado \uD83E\uDD73! Sua nota: %.2f"), nomeAluno, valorMedia));
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaAS.setVisibility(View.INVISIBLE);
            txtViewAS.setVisibility(View.INVISIBLE);
        } else if (valorMedia < 6 && valorMedia >= 4.0F) {
            txtViewInfo.setText(String.format(("Que pena, %s. Você foi reprovado \uD83D\uDE22, mas poderá fazer a AS. Sua nota: %.2f"), nomeAluno, valorMedia));
            btnEnviarAS.setVisibility(View.VISIBLE);
            txtNotaAS.setVisibility(View.VISIBLE);
            txtViewAS.setVisibility(View.VISIBLE);
        } else {
            txtViewInfo.setText(String.format(("Que pena, %s. Você foi reprovado \uD83D\uDE22, e não poderá fazer a AS\nSua nota: %.2f"), nomeAluno, valorMedia));
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaAS.setVisibility(View.INVISIBLE);
            txtViewAS.setVisibility(View.INVISIBLE);
        }
    }

    public void calcularMediaAS(View view) {

        notaAS = Float.parseFloat(txtNotaAS.getText().toString());

        // Critica se nota é vazia e dentro de intervalo
        if (Float.parseFloat(txtNotaAS.getText().toString()) < 0 || (Float.parseFloat(txtNotaAS.getText().toString()) > 10)) {
            Toast.makeText(getApplicationContext(), "Por favor, insira uma nota válida!", Toast.LENGTH_SHORT).show();
        } else {

            Bundle dados = getIntent().getExtras();
            Float notaA1 = dados.getFloat("ChaveNotaA1Aluno");
            Float notaA2 = dados.getFloat("ChaveNotaA2Aluno");

            // Chama a classe para realizar os calculos
            Calculos calculos = new Calculos(notaA1, notaA2, notaAS);

            notaAlunoPosAS = calculos.calcularNotaRecuperacao();

            // Julga a nota AS é valida
            if (Float.parseFloat(txtNotaAS.getText().toString()) < 0 || Float.parseFloat(txtNotaAS.getText().toString()) > 10) {
                Toast.makeText(getApplicationContext(), "Por favor, insira uma nota válida!", Toast.LENGTH_SHORT).show();
            }

            // Intent para passar valores para a nova acitvity
            Intent intent = new Intent(getApplicationContext(), AprovadoAS.class);
            intent.putExtra("ChaveNotaPosAS", notaAlunoPosAS);
            intent.putExtra("ChaveNomeAluno", nomeAluno);
            startActivity(intent);
            finish();
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