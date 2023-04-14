package com.example.a1_programacaomobile;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

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

        // initialise the layout
        WebView webView = findViewById(R.id.webView);

        // enable the javascript to load the url
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);

        webView.setWebViewClient(new WebViewClient());

        // define os parâmetros da altura da WebView
        ViewGroup.LayoutParams params = webView.getLayoutParams();


        // Julga se o Aluno passou
        if (valorMedia >= 6.0F) {
            txtViewResultadoPosAS.setText(String.format(("Parabéns, %s.\nVocê foi aprovado! \uD83E\uDD73\nSua nota: %.2f"), nomeAluno, valorMedia));

            ConstraintLayout constraintLayout = findViewById(R.id.parent_layout2);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.webView, ConstraintSet.TOP, R.id.buttonEncerrar,ConstraintSet.BOTTOM,50);
            constraintSet.applyTo(constraintLayout);
            params.height = 747*2;
            params.width = 747*2;

            webView.loadUrl("https://media.tenor.com/rdfk00lsVusAAAAC/thumbs-up-good-job.gif");

        } else {
            txtViewResultadoPosAS.setText(String.format(("Que pena, %s.\nVocê foi reprovado! \uD83D\uDE22\nSua nota: %.2f"), nomeAluno, valorMedia));

            ConstraintLayout constraintLayout = findViewById(R.id.parent_layout2);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.webView, ConstraintSet.TOP, R.id.buttonEncerrar,ConstraintSet.BOTTOM,50);
            constraintSet.applyTo(constraintLayout);
            params.height = 747*2;
            params.width = 747*2;

            // aplica os parâmetros atualizados na WebView
            webView.setLayoutParams(params);

            webView.loadUrl("https://media.tenor.com/N-893IGoGHsAAAAd/omg-what.gif");
        }



    }

    // Encerra o programa
    public void buttonEncerrarOnClick(View view) {
        finish();
        System.exit(0);
    }
}