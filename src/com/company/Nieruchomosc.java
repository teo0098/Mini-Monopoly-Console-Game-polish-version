package com.company;

public abstract class Nieruchomosc extends Pole {
    private int cena;
    private int oplata;
    private int id_posiadacza;

    public Nieruchomosc(int nr_pola, String nazwa_pola, int cena, int oplata) {
        super(nr_pola, nazwa_pola);
        this.cena = cena;
        this.oplata = oplata;
        id_posiadacza = 0;
    }

    public int getCena() {
        return cena;
    }

    public int getOplata() {
        return oplata;
    }

    public int getIdPosiadacza() {
        return id_posiadacza;
    }

    public void setIdPosiadacza(int id_posiadacza) {
        this.id_posiadacza = id_posiadacza;
    }

    public void setOplata(int oplata) {
        this.oplata = oplata;
    }
}
