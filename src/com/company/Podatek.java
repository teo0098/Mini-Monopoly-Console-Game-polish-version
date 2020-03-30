package com.company;

public class Podatek extends Pole {
    private int podatek;

    public Podatek(int nr_pola, String nazwa_pola, int podatek) {
        super(nr_pola, nazwa_pola);
        this.podatek = podatek;
    }

    public int getPodatek() {
        return podatek;
    }
}
