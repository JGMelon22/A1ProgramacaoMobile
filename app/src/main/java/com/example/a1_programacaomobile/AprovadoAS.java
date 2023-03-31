package com.example.a1_programacaomobile;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AprovadoAS extends AppCompatActivity {
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovado_as);

        // Modifica nome da top bar
        getSupportActionBar().setTitle("A1 Programação Mobile");

        // Instancia componentes gráficos
        txtView = findViewById(R.id.txtViewResultadoPosAS);

        // Obtem dados da activity anterior
        String valor = getIntent().getStringExtra("infoAS");
        txtView.setText(valor);
    }

    // Encerra o programa
    public void buttonEncerrarOnClick(View view) {
        finish();
    }
}