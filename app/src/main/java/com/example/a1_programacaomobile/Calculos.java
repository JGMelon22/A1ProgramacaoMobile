package com.example.a1_programacaomobile;

public class Calculos {

    private String nomeAluno;
    private float a1, a2, as;

    // Getters
    public String getNomeAluno() {
        return nomeAluno;
    }

    public float getA1() {
        return a1;
    }

    public float getA2() {
        return a2;
    }

    public float getAs() {
        return as;
    }

    // CTOR
    public Calculos(float as) {
        this.as = as;
    }

    public Calculos(String nomeAluno, float a1, float a2) {
        this.nomeAluno = nomeAluno;
        this.a1 = a1;
        this.a2 = a2;
    }

    public float calcularNota() {
        return (a1 + a2) / 2.0F;
    }

    public float calcularNotaRecuperacao() {
        return a1 + (a2 + as) / 2.0F;
    }
}
