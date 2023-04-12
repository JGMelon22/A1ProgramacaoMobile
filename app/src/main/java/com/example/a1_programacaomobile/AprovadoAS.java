package com.example.a1_programacaomobile;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AprovadoAS extends AppCompatActivity {
    TextView txtViewResultadoPosAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovado_as);

        // Modifica nome da top bar
        getSupportActionBar().setTitle("A1 Programação Mobile");

        // Instancia componentes gráficos
        txtViewResultadoPosAS = findViewById(R.id.textViewResultadoPosAS);

        // Instancia objeto grafico e obtem dados da activity anterior
        Bundle dados = getIntent().getExtras();
        String nomeAluno = dados.getString("ChaveNomeAluno");
        Float valorMedia = dados.getFloat("ChaveNotaPosAS");

        // Julga se o Aluno passou
        if (valorMedia >= 6.0F) {
            txtViewResultadoPosAS.setText(String.format(("Parabéns, %s.\nVocê foi aprovado! \uD83E\uDD73\nSua nota: %.2f"), nomeAluno, valorMedia));
        } else {
            txtViewResultadoPosAS.setText(String.format(("Que pena, %s.\nVocê foi reprovado! \uD83D\uDE22\nSua nota: %.2f"), nomeAluno, valorMedia));
        }
    }

    // Encerra o programa
    public void buttonEncerrarOnClick(View view) {
        finish();
        System.exit(0);
    }
}