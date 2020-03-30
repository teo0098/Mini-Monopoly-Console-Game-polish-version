package com.company;

public class Ulica extends Nieruchomosc {
    private int poziom_zabudowania; //0 - brak, 1 - jeden domek, 2 - dwa domki... 5 - hotel
    private int[] zabudowyOplaty;
    private int zabudowyCena;

    public Ulica(int nr_pola, String nazwa_pola, int cena, int oplata, int[] zabudowyOplaty, int zabudowyCena) {
        super(nr_pola, nazwa_pola, cena, oplata);
        poziom_zabudowania = 0;
        this.zabudowyOplaty = new int [zabudowyOplaty.length];
        for (int i = 0; i < zabudowyOplaty.length; i++) {
            this.zabudowyOplaty[i] = zabudowyOplaty[i];
        }
        this.zabudowyCena = zabudowyCena;
    }

    public void setPoziomZabudowania(int poziom_zabudowania) {
        this.poziom_zabudowania = poziom_zabudowania;
    }

    public int getPoziomZabudowania() {
        return poziom_zabudowania;
    }

    public int getZabudowaOplata() {
        return zabudowyOplaty[poziom_zabudowania - 1];
    }

    public int getZabudowaCena() {
        return zabudowyCena;
    }
}
