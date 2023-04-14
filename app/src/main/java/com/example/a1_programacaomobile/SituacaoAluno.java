package com.example.a1_programacaomobile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class SituacaoAluno extends AppCompatActivity {

    TextView txtViewInfo, txtViewAS;
    EditText txtNotaAS;
    Button btnEnviarAS;
    Float notaAS, notaAlunoPosAS;
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


        // initialise the layout
        WebView webView = findViewById(R.id.Wb2);

        // enable the javascript to load the url
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);

        webView.setWebViewClient(new WebViewClient());

        // define os parâmetros da altura da WebView
        ViewGroup.LayoutParams params = webView.getLayoutParams();


        // Julga se o Aluno pode/deve fazer AS
        if (valorMedia >= 6.0F) {
            txtViewInfo.setText(String.format(("Parabéns, %s. Você foi aprovado! \uD83E\uDD73 Sua nota: %.2f"), nomeAluno, valorMedia));
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaAS.setVisibility(View.INVISIBLE);
            txtViewAS.setVisibility(View.INVISIBLE);

            ConstraintLayout constraintLayout = findViewById(R.id.parent_layout);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.Wb2, ConstraintSet.TOP, R.id.textViewInfo,ConstraintSet.BOTTOM,200);
            constraintSet.applyTo(constraintLayout);

            params.height = 650*2;

            //https://media.tenor.com/IVh7YxGaB_4AAAAC/nerd-emoji.gif
            webView.loadUrl("https://media.tenor.com/IVh7YxGaB_4AAAAC/nerd-emoji.gif");

        } else if (valorMedia < 6.0F && valorMedia >= 4.0F) {
            txtViewInfo.setText(String.format(("Que pena, %s.\nVocê foi reprovado, mas poderá fazer a AS \uD83D\uDE22\nSua nota: %.2f"), nomeAluno, valorMedia));
            btnEnviarAS.setVisibility(View.VISIBLE);
            txtNotaAS.setVisibility(View.VISIBLE);
            txtViewAS.setVisibility(View.VISIBLE);
            FiltroNum.limitEditText(txtNotaAS, 0, 10, this);

            // https://media.tenor.com/SBIEIe8f1MgAAAAC/emoji-emojis.gif
            ConstraintLayout constraintLayout = findViewById(R.id.parent_layout);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.Wb2, ConstraintSet.TOP, R.id.buttonEnviar,ConstraintSet.BOTTOM,50);
            constraintSet.applyTo(constraintLayout);
            params.height = 747;
            params.width = 747;

            webView.loadUrl("https://media.tenor.com/QJOxesycuHEAAAAC/chuqui-chuquiluki.gif");

        } else {
            txtViewInfo.setText(String.format(("Que pena, %s.\nVocê foi reprovado e não poderá fazer a AS \uD83D\uDE2D\nSua nota: %.2f"), nomeAluno, valorMedia));
            btnEnviarAS.setVisibility(View.INVISIBLE);
            txtNotaAS.setVisibility(View.INVISIBLE);
            txtViewAS.setVisibility(View.INVISIBLE);

            ConstraintLayout constraintLayout = findViewById(R.id.parent_layout);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.Wb2, ConstraintSet.TOP, R.id.textViewInfo,ConstraintSet.BOTTOM,50);
            constraintSet.applyTo(constraintLayout);
            params.height = 747*2;
            params.width = 747*2;

            // aplica os parâmetros atualizados na WebView
            webView.setLayoutParams(params);

            // add the url of gif
            webView.loadUrl("https://media.tenor.com/9PTGVf4BLwYAAAAC/crying-emoji-dies.gif");

        }
    }

    // Método para calcular média da Avaliação Substitutiva
    public void calcularMediaAS(View view) {

        notaAS = Float.parseFloat(txtNotaAS.getText().toString());

        Bundle dados = getIntent().getExtras();
        Float notaA1 = dados.getFloat("ChaveNotaA1Aluno");
        Float notaA2 = dados.getFloat("ChaveNotaA2Aluno");

        // Chama a classe para realizar os calculos
        Calculos calculos = new Calculos(notaA1, notaA2, notaAS);

        notaAlunoPosAS = calculos.calcularNotaRecuperacao();

        // Intent para passar valores para a nova acitvity
        Intent intent = new Intent(getApplicationContext(), AprovadoAS.class);
        intent.putExtra("ChaveNotaPosAS", notaAlunoPosAS);
        intent.putExtra("ChaveNomeAluno", nomeAluno);
        startActivity(intent);
        finish();
    }

    // Chama o método de calcular média da Avaliação Substitutiva
    public void buttonEnviarOnClick(View view) {
        try {
            calcularMediaAS(view);

        } catch (
                Exception e) { // Em caso de erro, mostra mensagem para o usuário em uma toast
            
            Toast.makeText(getApplicationContext(), "Por favor, certifique-se de que foi informado a nota AS para prosseguir!", Toast.LENGTH_SHORT).show();
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