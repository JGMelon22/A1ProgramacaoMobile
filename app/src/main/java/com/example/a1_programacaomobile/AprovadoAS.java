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
        String valorViewInfo2 = getIntent().getStringExtra("ChaveInfoAluno2");
        txtViewResultadoPosAS.setText(valorViewInfo2);
    }

    // Encerra o programa
    public void buttonEncerrarOnClick(View view) {
        finish();
        System.exit(0);
    }
}