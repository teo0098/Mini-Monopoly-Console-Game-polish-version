package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Gracz {
    private String nazwa;
    private int id;
    private int saldo_konta;
    private int pozycja;
    private boolean bankrut;
    private ArrayList<ElektrowniaWodociagi> posiadane_EW;
    private ArrayList<Kolej> posiadane_koleje;
    private ArrayList<Ulica> posiadane_ulice;

    public Gracz(String nazwa, int id) {
        this.nazwa = nazwa;
        this.id = id;
        saldo_konta = 1500;
        pozycja = 0;
        bankrut = false;
        posiadane_EW = new ArrayList<ElektrowniaWodociagi>();
        posiadane_koleje = new ArrayList<Kolej>();
        posiadane_ulice = new ArrayList<Ulica>();
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getId() {
        return id;
    }

    public int getSaldoKonta() {
        return saldo_konta;
    }

    public int getPozycja() {
        return pozycja;
    }

    public boolean getBankrut() {
        return bankrut;
    }

    public ElektrowniaWodociagi getEW(int nr) {
        return posiadane_EW.get(nr);
    }

    public Kolej getKolej(int nr) {
        return posiadane_koleje.get(nr);
    }

    public Ulica getUlica(int nr) {
        return posiadane_ulice.get(nr);
    }

    public int getEWIlosc() {
        return posiadane_EW.size();
    }

    public int getKolejIlosc() {
        return posiadane_koleje.size();
    }

    public int getUlicaIlosc() {
        return posiadane_ulice.size();
    }

    public void setSaldoKonta(int saldo_konta) {
        this.saldo_konta = saldo_konta;
    }

    /*
    public void setPozycja(int pozycja) {
        this.pozycja = pozycja;
    }
    */

    public void setBankrut(boolean bankrut) {
        this.bankrut = bankrut;
    }

    public void setEW(ElektrowniaWodociagi ew) {
        posiadane_EW.add(ew);
    }

    public void setKolej(Kolej kolej) {
        posiadane_koleje.add(kolej);
    }

    public void setUlica(Ulica ulica) {
        posiadane_ulice.add(ulica);
    }

    public void usunEW(ElektrowniaWodociagi ew) {
        for (int i = 0; i < posiadane_EW.size(); i++) {
            if (posiadane_EW.get(i).getNrPola() == ew.getNrPola()) posiadane_EW.remove(i);
        }
    }

    public void usunKolej(Kolej kolej) {
        for (int i = 0; i < posiadane_koleje.size(); i++) {
            if (posiadane_koleje.get(i).getNrPola() == kolej.getNrPola()) posiadane_koleje.remove(i);
        }
    }

    public void usunUlica(Ulica ulica) {
        for (int i = 0; i < posiadane_ulice.size(); i++) {
            if (posiadane_ulice.get(i).getNrPola() == ulica.getNrPola()) posiadane_ulice.remove(i);
        }
    }

    public void wykonajRuch() {
        int rand = new Random().nextInt(6) + 1;
        pozycja += rand;
        if (pozycja > 39) {
            pozycja -= 40;
            Bankier.dajPremie(this);
            JOptionPane.showMessageDialog(null, "Przeszłaś/Przeszedłeś przez pole Start, dostajesz premię w wysokości: 200. Twoje saldo konta " + nazwa + " wynosi: " + saldo_konta);
        }
        JOptionPane.showMessageDialog(null, "Wyrzucona liczba oczek: " + rand);
    }
}
