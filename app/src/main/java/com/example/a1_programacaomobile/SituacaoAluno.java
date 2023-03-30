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
    TextView textView, textViewFinalAS;
    EditText txtNotaA1, txtNotaA2, txtMedia, txtNotaAS;
    Button btnEnviarAS;
    float notaAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_aluno);

        // Modifica nome da top bar
        getSupportActionBar().setTitle("A1 Programação Mobile");

        // Valores provenientes da A1 da activity passada
        txtNotaA1 = findViewById(R.id.editTextA1);
        String notaA1 = getIntent().getStringExtra("ChaveNotaA1Aluno");
        txtNotaA1.setText(notaA1);

        // Recebe valor nota A2 vindo da tela anterior
        txtNotaA2 = findViewById(R.id.editTextA2);
        String notaA2 = getIntent().getStringExtra("ChaveNotaA2Aluno");
        txtNotaA2.setText(notaA2);

        // Recebe média vindo da tela anterior
        txtMedia = findViewById(R.id.editTextMedia);
        String media = getIntent().getStringExtra("ChaveMedia");
        txtMedia.setText(media);

        // Mostra componentes dependendo da nota anterior
        // Instancia componentes gráficos
        textView = findViewById(R.id.txtViewNotaFinal2);
        btnEnviarAS = findViewById(R.id.buttonEnviar);
        txtNotaA1 = findViewById(R.id.editTextA1);
        txtNotaAS = findViewById(R.id.editTextAS);
        textViewFinalAS = findViewById(R.id.txtViewNotaFinalAS);

        // Obtem dados da activity anterior
        String valor = getIntent().getStringExtra("ChaveInfoAluno");
        textView.setText(valor);

        // Julgar se aluno precisa/pode fazer AS baseado no valor obtido da activity 1
        if (Float.parseFloat(txtMedia.getText().toString()) >= 6) {
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaAS.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        } else if (Float.parseFloat(txtMedia.getText().toString()) >= 4) {
            btnEnviarAS.setVisibility(View.VISIBLE);
            txtMedia.setVisibility(View.INVISIBLE);
        } else {
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaAS.setVisibility(View.INVISIBLE);
        }
    }

    public void calcularAsAluno(View view) {

        // Captura nota AS aluno
        notaAS = Float.parseFloat(txtNotaAS.getText().toString());

        // Perfomar calculo A1 + AS
        Calculos calculos = new Calculos(notaAS);

        float notaFinal = calculos.calcularNotaRecuperacao();

        // Passou ou nao?
        if (notaFinal >= 6.0) {
            textViewFinalAS.setText(String.format("Nota final do Aluno = %.2f\nParabéns após realizar a AS, você foi aprovado!\uD83E\uDD73", notaAS));
        } else {
            textViewFinalAS.setText(String.format("Nota final do Aluno = %.2f\nInfelizmente você foi reprovado!\uD83D\uDE22", notaAS));
        }
    }

    public void buttonEnviarOnClick(View view) {
        try {
            calcularAsAluno(view);
            // Julga se as notas estão em um intervalo válido

            if (Float.parseFloat(txtNotaA1.getText().toString()) < 0 || (Float.parseFloat(txtNotaA1.getText().toString()) > 10)) {
                Toast.makeText(SituacaoAluno.this, "Por favor, insira uma nota A1 válida!", Toast.LENGTH_SHORT).show();
            } else {
                //
                String notaAsAuxiliar = textViewFinalAS.getText().toString();

                // Pega nota AS e passar para a próxima tela
                Intent intent = new Intent(SituacaoAluno.this, AprovadoAS.class);
                intent.putExtra("ChaveNotaAS", notaAsAuxiliar);
                intent.putExtra("ChaveNotaA1Aluno", txtNotaA1.getText().toString());
                startActivity(intent);
                finish(); // Ao abrir a nova activity, finaliza anterior para fins de otimização
            }

        } catch (Exception e) {
            Toast.makeText(SituacaoAluno.this, "Por favor, certifique-se de que foi informado uma nota AS válida para prosseguir!", Toast.LENGTH_SHORT).show();
        }
    }
}