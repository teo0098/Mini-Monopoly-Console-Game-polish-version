package com.company;

public abstract class Pole {
    private int nr_pola;
    private String nazwa_pola;

    public Pole(int nr_pola, String nazwa_pola) {
        this.nr_pola = nr_pola;
        this.nazwa_pola = nazwa_pola;
    }

    public int getNrPola() {
        return nr_pola;
    }

    public String getNazwaPola() {
        return nazwa_pola;
    }
}
