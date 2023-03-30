package com.example.a1_programacaomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variáveis globais para manipular o contéudo recibido na View
    EditText txtNomeAluno, txtNotaA1, txtNotaA2, txtMediaAcMain;
    TextView txtNotaFinalAluno;

    float nota1, nota2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Modifica nome da top bar
        getSupportActionBar().setTitle("A1 Programação Mobile");
    }

    // Método para calcular a média inicial do aluno
    public void calcularMedia(View view) {

        txtNomeAluno = findViewById(R.id.plainTextNomeAluno);
        txtNotaA1 = findViewById(R.id.plainTextA1);
        txtNotaA2 = findViewById(R.id.plainTextA2);
        txtNotaFinalAluno = findViewById(R.id.textViewInfoMainAc);
        txtMediaAcMain = findViewById(R.id.editTextMedia);

        // Julga se nome do aluno foi informado ou não
        String nomeDoAluno = txtNomeAluno.getText().toString();
        if (nomeDoAluno.trim().isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, informe o nome do aluno!", Toast.LENGTH_SHORT).show();
            return;
        }

        nota1 = Float.parseFloat(txtNotaA1.getText().toString());
        nota2 = Float.parseFloat(txtNotaA2.getText().toString());


    }

    public void buttonCalcularMediaOnClick(View view) {
        try {
            calcularMedia(view);

            // Julga se nome do aluno foi informado ou não
            String nomeDoAluno = txtNomeAluno.getText().toString();
            if (nomeDoAluno.trim().isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, informe o nome do aluno!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Chama a classe para realizar os calculos
            Calculos calculos = new Calculos(nomeDoAluno, nota1, nota2);

            float notaAluno = calculos.calcularNota();
            // txtMediaAcMain.setText(String.valueOf(notaAluno));

            // Julga se o aluno passou ou não
            if (notaAluno >= 6.0) {
                // Metodo de calcular
                txtNotaFinalAluno.setText(String.format("Nota final do Aluno = %.2f\nParabéns, %s, você foi aprovado!\uD83E\uDD73", notaAluno, nomeDoAluno));

            } else {
                txtNotaFinalAluno.setText(String.format("Nota final do Aluno = %.2f\nInfelizmente, %s, você foi reprovado!\uD83D\uDE22", notaAluno, nomeDoAluno));
            }

            // Julga se as notas estão em um intervalo válido
            if (Float.parseFloat(txtNotaA1.getText().toString()) < 0 || (Float.parseFloat(txtNotaA1.getText().toString()) > 10) || Float.parseFloat(txtNotaA2.getText().toString()) < 0 || (Float.parseFloat(txtNotaA2.getText().toString()) > 10)) {
                Toast.makeText(MainActivity.this, "Por favor, insira uma nota válida!", Toast.LENGTH_SHORT).show();

            } else {
                // Pega informações do aluno (nome e nota final) e passa para a segunda tela
                Intent intent = new Intent(MainActivity.this, SituacaoAluno.class);
                intent.putExtra("ChaveInfoAluno", txtNotaFinalAluno.getText().toString());
                intent.putExtra("ChaveNotaA1Aluno", txtNotaA1.getText().toString());
                intent.putExtra("ChaveNotaA2Aluno", txtNotaA2.getText().toString());
                intent.putExtra("ChaveMediaAluno", txtMediaAcMain.getText().toString());
                startActivity(intent);
                finish(); // Ao abrir a nova activity, finaliza anterior para fins de otimização
            }
        } catch (
                Exception e) { // Em caso de erro, mostra mensagem para o usuário em uma toast
            Toast.makeText(MainActivity.this, "Por favor, certifique-se de que foi informado o nome do aluno, nota A1 e A2 para prosseguir!", Toast.LENGTH_SHORT).show();
        }
    }
}