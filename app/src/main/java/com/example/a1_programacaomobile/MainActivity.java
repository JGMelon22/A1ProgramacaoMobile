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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Variáveis globais para manipular o contéudo recibido na View
    EditText txtNomeAluno, txtNotaA1, txtNotaA2;

    Float nota1, nota2, mediaA1A2;

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
        txtNotaA1 = findViewById(R.id.plainTextA1);
        txtNotaA2 = findViewById(R.id.plainTextA2);

        // Julga se nome do aluno foi informado ou não
        String nomeDoAluno = txtNomeAluno.getText().toString();
        if (nomeDoAluno.trim().isEmpty()) {
            txtNomeAluno.setError("Por favor, informe o nome do aluno!");
            Toast.makeText(MainActivity.this, "Por favor, informe o nome do aluno!", Toast.LENGTH_SHORT).show();
            return;
        }

//        nota1 = Float.parseFloat(txtNotaA1.getText().toString());
//        nota2 = Float.parseFloat(txtNotaA2.getText().toString());
//        nota1 = Float.parseFloat(txtNotaA1.getText().toString());
//        nota2 = Float.parseFloat(txtNotaA2.getText().toString());

        if (TextUtils.isEmpty(txtNotaA1.getText())) {
            txtNotaA1.setError("Por favor, informe um número valido!");
            Toast.makeText(MainActivity.this, "Por favor, informe a nota A1!", Toast.LENGTH_SHORT).show();
            nota1 = null;
            return;
        } else {
            nota1 = Float.parseFloat(txtNotaA1.getText().toString());
        }

        if (TextUtils.isEmpty(txtNotaA2.getText())) {
            txtNotaA2.setError("Por favor, informe um número valido!");
            Toast.makeText(MainActivity.this, "Por favor, informe a nota A2!", Toast.LENGTH_SHORT).show();
            nota2 = null;
            return;
        } else {
            nota2 = Float.parseFloat(txtNotaA2.getText().toString());
        }
        //
        Calculos calculos = new Calculos(nomeDoAluno, nota1, nota2);

        mediaA1A2 = calculos.calcularNota();

        // Pega informações do aluno (nome e nota final) e passa para a segunda tela
        Intent intent = new Intent(getApplicationContext(), SituacaoAluno.class);
        intent.putExtra("ChaveNomeAluno", calculos.getNomeAluno());
        intent.putExtra("ChaveNotaA1Aluno", Float.parseFloat(txtNotaA1.getText().toString()));
        intent.putExtra("ChaveNotaA2Aluno", Float.parseFloat(txtNotaA2.getText().toString()));
        intent.putExtra("ChaveMedia", mediaA1A2);
        startActivity(intent);
        finish(); // Ao abrir a nova activity, finaliza anterior para fins de otimização
    }

    // Chama o método de calcular a média da A1 + A2 ao clicar no botão
    public void buttonCalcularMediaOnClick(View view) {
        try {
            calcularMedia(view);

            // Julga se nome do aluno foi informado ou não
            String nomeDoAluno = txtNomeAluno.getText().toString();
            if (nomeDoAluno.trim().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, informe o nome do aluno!", Toast.LENGTH_SHORT).show();
            }
        } catch (
                Exception e) { // Em caso de erro, mostra mensagem para o usuário em uma toast
            Toast.makeText(getApplicationContext(), "Por favor, certifique-se de que foi informado o nome do aluno, nota A1 e A2 para prosseguir!", Toast.LENGTH_SHORT).show();
        }
    }

    // Esconde o Soft Keyboard ao clicar fora de um campo de input
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
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