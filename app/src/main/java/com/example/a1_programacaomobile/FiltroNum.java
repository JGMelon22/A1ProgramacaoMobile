package com.example.a1_programacaomobile;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class FiltroNum {

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
                    if (str.equals(max+".")) {
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
                }
                catch(NumberFormatException nfe) {

                }
            }
        });
    }

}
