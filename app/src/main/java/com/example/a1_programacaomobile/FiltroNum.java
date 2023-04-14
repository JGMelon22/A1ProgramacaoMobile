package com.example.a1_programacaomobile;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class FiltroNum {

    //Este é um código escrito em linguagem Java para limitar o número máximo e mínimo que pode ser digitado em um objeto EditText em um aplicativo Android.
    //
    //A classe FiltroNum contém um método estático limitEditText, que recebe três parâmetros: o objeto EditText que precisa ser limitado, o valor mínimo permitido e o valor máximo permitido. Esses parâmetros são usados ​​para instanciar uma classe MinMaxTextWatcher anônima, que é passada para o método addTextChangedListener do objeto EditText.
    //
    //A classe MinMaxTextWatcher é uma classe abstrata que implementa a interface TextWatcher. Ela contém três métodos: beforeTextChanged, onTextChanged e afterTextChanged. Esses métodos são chamados em diferentes pontos durante a edição do texto em um EditText. No entanto, como a classe é abstrata, ela não pode ser instanciada diretamente. Em vez disso, a classe anônima é criada dentro do método limitEditText.
    //
    //Dentro do método afterTextChanged, o valor do texto inserido no EditText é convertido em um número de ponto flutuante usando o método parseFloat da classe Float. Em seguida, o código verifica se o número é maior que o valor máximo permitido. Se sim, o número é ajustado para o valor máximo e uma mensagem de toast é exibida para o usuário informando o limite máximo. Se o usuário digitar um ponto após o valor máximo, o número é ajustado para o valor máximo e uma mensagem de toast é exibida. Além disso, se o usuário digitar zeros à esquerda do número, eles são removidos.
    //
    //O objetivo geral do código é limitar o número que pode ser digitado em um EditText e fornecer feedback visual para o usuário quando o limite é alcançado.

    public static abstract class MinMaxTextWatcher implements TextWatcher {
        int min, max;
        Context ctx;

        public MinMaxTextWatcher(int min, int max, Context ctx) {
            super();
            this.min = min;
            this.max = max;
            this.ctx = ctx;
        }
    }
    public static void limitEditText(final EditText ed, int min, int max, Context ctx) {

        ed.addTextChangedListener(new FiltroNum.MinMaxTextWatcher(min, max, ctx) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                float n = 0;
                try {
                    n = Float.parseFloat(str);
                    if (str.equals(max + ".")) {
                        ed.setText(Integer.toString(max));
                        Toast.makeText(ctx, "Número máximo permitido é " + max, Toast.LENGTH_SHORT).show();
                        ed.setSelection(2);

                    } else if (n > max) {
                        ed.setText(Integer.toString(max));
                        Toast.makeText(ctx, "Número máximo permitido é " + max, Toast.LENGTH_SHORT).show();
                        ed.setSelection(Integer.toString(max).length());

                    } else if (s.charAt(0) == '0' && n >= 1) { // remover 0`s à esquerda
                        ed.setText(Float.toString(n));
                        ed.setSelection(Float.toString(n).length());
                    }
                } catch (NumberFormatException nfe) {

                }
            }
        });
    }

}