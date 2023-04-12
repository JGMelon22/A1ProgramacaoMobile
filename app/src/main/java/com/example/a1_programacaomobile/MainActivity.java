package com.example.a1_programacaomobile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Variáveis globais para manipular o contéudo recibido na View
    EditText txtNomeAluno, txtNotaA1, txtNotaA2;
    TextView txtNotaFinalAluno, txtViewMediaAc;
    Float nota1, nota2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Modifica nome da top bar
        getSupportActionBar().setTitle("A1 Programação Mobile");

        txtNotaA1 = findViewById(R.id.plainTextA1);
        txtNotaA2 = findViewById(R.id.plainTextA2);

        // Filtra números inválidos em tempo real
        FiltroNum.limitEditText(txtNotaA1, 0, 10, this);
        FiltroNum.limitEditText(txtNotaA2, 0, 10, this);
    }

    // Método para calcular a média inicial do aluno
    public void calcularMedia(View view) {

        txtNomeAluno = findViewById(R.id.plainTextNomeAluno);
      
        txtNotaFinalAluno = findViewById(R.id.textViewInfoMainAc);
        txtViewMediaAc = findViewById(R.id.textViewMediaAc);

        // Julga se nome do aluno foi informado ou não
        String nomeDoAluno = txtNomeAluno.getText().toString();
        if (nomeDoAluno.trim().isEmpty()) {
            txtNomeAluno.setError("Por favor, informe o nome do aluno!");
            Toast.makeText(MainActivity.this, "Por favor, informe o nome do aluno!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(txtNotaA1.getText())) {
            txtNotaA1.setError("Por favor, informe um número valido!");
            Toast.makeText(MainActivity.this, "Por favor, informe o n!", Toast.LENGTH_SHORT).show();
            nota1 = null;
            return;
        } else {
            nota1 = Float.parseFloat(txtNotaA1.getText().toString());
        }

        if (TextUtils.isEmpty(txtNotaA2.getText())) {
            txtNotaA2.setError("Por favor, informe um número valido!");
            Toast.makeText(MainActivity.this, "Por favor, informe o n!", Toast.LENGTH_SHORT).show();
            nota2 = null;
            return;
        } else {
            nota2 = Float.parseFloat(txtNotaA2.getText().toString());
        }
    }

    // Chama o método de calcular a média da A1 + A2 ao clicar no botão
    public void buttonCalcularMediaOnClick(View view) {
        try {
            calcularMedia(view);

            // Julga se nome do aluno foi informado ou não
            String nomeDoAluno = txtNomeAluno.getText().toString();
            if (nomeDoAluno.trim().isEmpty()) {
                txtNomeAluno.setError("Por favor, informe o nome do aluno!");
                Toast.makeText(MainActivity.this, "Por favor, informe o nome do aluno!", Toast.LENGTH_SHORT).show();
                return;
            }


            // Chama a classe para realizar os calculos
            Calculos calculos = new Calculos(nomeDoAluno, nota1, nota2);

            float notaAluno = calculos.calcularNota();

            // Julga se o aluno passou ou não
            if (notaAluno >= 6.0) {
                // Metodo de calcular
                txtNotaFinalAluno.setText(String.format("Nota final do Aluno = %.2f\nParabéns, %s, você foi aprovado!\uD83E\uDD73", notaAluno, nomeDoAluno));
                txtViewMediaAc.setText(String.valueOf(notaAluno));

            } else {
                txtNotaFinalAluno.setText(String.format("Nota final do Aluno = %.2f\nInfelizmente, %s, você foi reprovado!\uD83D\uDE22", notaAluno, nomeDoAluno));
                txtViewMediaAc.setText(String.valueOf(notaAluno));
            }

            if (nota1 != null && nota2 != null){
                // Pega informações do aluno (nome e nota final) e passa para a segunda tela
                Intent intent = new Intent(MainActivity.this, SituacaoAluno.class);
                intent.putExtra("ChaveInfoAluno", txtNotaFinalAluno.getText().toString());
                intent.putExtra("ChaveNotaA1Aluno", txtNotaA1.getText().toString());
                intent.putExtra("ChaveNotaA2Aluno", txtNotaA2.getText().toString());
                intent.putExtra("ChaveMediaAluno", txtViewMediaAc.getText().toString());
                startActivity(intent);
                finish(); // Ao abrir a nova activity, finaliza anterior para fins de otimização
            }

        } catch (Exception e) { // Em caso de erro, mostra mensagem para o usuário em uma toast
            Toast.makeText(MainActivity.this, "Por favor, certifique-se de que foi informado o nome do aluno, nota A1 e A2 para prosseguir!", Toast.LENGTH_SHORT).show();
        }
        return super.dispatchTouchEvent(event);
    }

    //Esconde o Soft Keyboard ao clicar fora de um campo de input
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText ) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    //Esconde o teclado
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}