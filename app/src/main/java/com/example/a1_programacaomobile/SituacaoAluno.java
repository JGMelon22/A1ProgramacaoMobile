package com.example.a1_programacaomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
            txtViewInfo.setText(String.format(("Parabéns, %s. Você foi aprovado! \uD83E\uDD73 Sua nota: %.2f"), nomeAluno, valorMedia));
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaAS.setVisibility(View.INVISIBLE);
            txtViewAS.setVisibility(View.INVISIBLE);
        } else if (valorMedia < 6.0F && valorMedia >= 4.0F) {
            txtViewInfo.setText(String.format(("Que pena, %s.\nVocê foi reprovado, mas poderá fazer a AS \uD83D\uDE22\nSua nota: %.2f"), nomeAluno, valorMedia));
            btnEnviarAS.setVisibility(View.VISIBLE);
            txtNotaAS.setVisibility(View.VISIBLE);
            txtViewAS.setVisibility(View.VISIBLE);
            FiltroNum.limitEditText(txtNotaAS, 0, 10, this);
        }
    }

    // Método para calcular média da Avaliação Substitutiva
    public void calcularMediaAS(View view) {

        notaAS = Float.parseFloat(txtNotaAS.getText().toString());

        // Critica se nota é vazia e dentro de intervalo
        if (Float.parseFloat(txtNotaAS.getText().toString()) < 0 || (Float.parseFloat(txtNotaAS.getText().toString()) > 10)) {
            txtNotaAS.setError("Por favor, insira uma nota válida!");
            Toast.makeText(getApplicationContext(), "Por favor, insira uma nota válida!", Toast.LENGTH_SHORT).show();
        }
    }

    // Chama o método de calcular média da Avaliação Substitutiva
    public void buttonEnviarOnClick(View view) {
        try {
            calcularMediaAS(view);
          
            // Critica se nota é vazia e dentro de intervalo
            if (Float.parseFloat(txtNotaAS.getText().toString()) < 0 || (Float.parseFloat(txtNotaAS.getText().toString()) > 10)) {
                txtNotaAS.setError("Por favor, insira uma nota válida!");
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
                    txtNotaAS.setError("Por favor, insira uma nota válida!");
                    Toast.makeText(getApplicationContext(), "Por favor, insira uma nota válida!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), AprovadoAS.class);
                    intent.putExtra("ChaveInfoAluno2", txtViewInfo2.getText());
                    startActivity(intent);
                    finish();
                }
            }
        } catch (Exception e) { // Em caso de erro, mostra mensagem para o usuário em uma toast
            Toast.makeText(getApplicationContext(), "Por favor, certifique-se de que foi informado a nota AS para prosseguir!", Toast.LENGTH_SHORT).show();
        }
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