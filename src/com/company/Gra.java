package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Gra {
    private ArrayList<Pole> plansza = new ArrayList<Pole>();
    private ArrayList<Gracz> gracze = new ArrayList<Gracz>();

    public Gra() {
        new Plansza(plansza);
    }

    private boolean stworzGraczy() {
        String iluGraczy = "";
        iluGraczy = JOptionPane.showInputDialog(null, "Ilu graczy uczestniczy w rozgrywce? Zakres graczy od 2 do 6");
        try {
            if (Integer.parseInt(iluGraczy) < 2 || Integer.parseInt(iluGraczy) > 6) throw new NumberFormatException();
            for (int i = 0; i < Integer.parseInt(iluGraczy); i++) {
                String nazwa = "";
                nazwa = JOptionPane.showInputDialog(null, "Podaj nazwę " + (i + 1) + " gracza:");
                gracze.add(new Gracz(nazwa, (i + 1)));
            }
            return true;
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ups...chyba nie jest to odpowiednia liczba");
            return false;
        }
    }

    private void kontynuacjaGry(int aktualnyGracz) {
        int kontynuacja = 10;
        if (gracze.get(aktualnyGracz).getBankrut() == false) kontynuacja = JOptionPane.showConfirmDialog(null, "Czy chcesz kontynuować grę " + gracze.get(aktualnyGracz).getNazwa() + "?");
        if (kontynuacja != JOptionPane.YES_OPTION) {
            gracze.get(aktualnyGracz).setBankrut(true);
            JOptionPane.showMessageDialog(null, "Co za strata... żegnaj " + gracze.get(aktualnyGracz).getNazwa() + "...");
        }
    }

    private void uzbierajPieniadzeZGraczaNieruchomosci(Gracz gracz, int oplata) {
        do {
            while (gracz.getUlicaIlosc() > 0 || gracz.getSaldoKonta() < oplata) {
                if (gracz.getUlica(0).getPoziomZabudowania() > 0) Bankier.zmniejszUliceGraczowi(gracz.getUlica(0), gracz);
                else Bankier.kupNieruchomoscOdGracza(gracz.getUlica(0), gracz);
            }
            if (gracz.getSaldoKonta() >= oplata) break;
            while (gracz.getKolejIlosc() > 0 || gracz.getSaldoKonta() < oplata) {
                Bankier.kupNieruchomoscOdGracza(gracz.getKolej(0), gracz);
            }
            if (gracz.getSaldoKonta() >= oplata) break;
            while (gracz.getEWIlosc() > 0 || gracz.getSaldoKonta() < oplata) {
                Bankier.kupNieruchomoscOdGracza(gracz.getEW(0), gracz);
            }
            if (gracz.getSaldoKonta() >= oplata) break;
            else {
                gracz.setBankrut(true);
                JOptionPane.showMessageDialog(null, "BANKRUCTWO!!! Niestety, wartość posiadanych przez Ciebie nieruchomości jest zbyt mała aby spłacić opłatę. Dopadło Cię bankructwo i jednocześnie koniec gry " + gracz.getNazwa());
                break;
            }
        } while (true);
    }

    private void ruchGracza(int kolejka, int aktualnyGracz) {
        Gracz gracz = gracze.get(aktualnyGracz);
        if (kolejka > 1) JOptionPane.showMessageDialog(null, "Twoja kolej " + gracz.getNazwa() + "!");
        JOptionPane.showMessageDialog(null, "Jesteś na polu: " + plansza.get(gracz.getPozycja()).getNazwaPola());
        gracz.wykonajRuch();
        Pole pole = plansza.get(gracz.getPozycja());
        JOptionPane.showMessageDialog(null, "Przechodzisz na pole: " + pole.getNazwaPola());
        if (pole instanceof Podatek) {
            if (gracz.getSaldoKonta() >= ((Podatek) pole).getPodatek()) {
                Bankier.pobierzPodatek((Podatek) pole, gracz);
                JOptionPane.showMessageDialog(null, "Ups... płacisz podatek w wysokości: " + ((Podatek) pole).getPodatek() + ". Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
            }
            else {
                JOptionPane.showMessageDialog(null, "OOOO nieee! Wygląda na to, że nie posiadasz odpowiedniej ilości pieniędzy do przeprowadzenia opłaty " + gracz.getNazwa() + ". Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta() + "... Sprawdźmy, czy bank może coś zarekwirować aby uiścić opłatę");
                if (gracz.getEWIlosc() == 0 && gracz.getKolejIlosc() == 0 && gracz.getUlicaIlosc() == 0) {
                    gracz.setBankrut(true);
                    JOptionPane.showMessageDialog(null, "BANKRUCTWO!!! Niestety, nie posiadasz nic co bank mógłby zarekwirować. Dopadło Cię bankructwo i jednocześnie koniec gry " + gracz.getNazwa());
                } else {
                    uzbierajPieniadzeZGraczaNieruchomosci(gracz, ((Podatek) pole).getPodatek());
                    if (gracz.getSaldoKonta() >= ((Podatek) pole).getPodatek()) {
                        Bankier.pobierzPodatek((Podatek) pole, gracz);
                        JOptionPane.showMessageDialog(null, "Ufff...jakoś udało się spłacić opłatę za postój na polu u " + pole.getNazwaPola() +". Niestety Bank był zmuszony zabrać przy tym część twoich nieruchomości aby uiścić opłatę. Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
                    }
                }
            }
        }
        else if (pole instanceof Nieruchomosc) {
            if (((Nieruchomosc) pole).getIdPosiadacza() == 0) {
                if (gracz.getSaldoKonta() >= ((Nieruchomosc) pole).getCena()) {
                    int zakup = JOptionPane.showConfirmDialog(null, gracz.getNazwa() + ", czy chcesz dokonać zakupu " + pole.getNazwaPola() + " za kwote " + ((Nieruchomosc) pole).getCena() + "?");
                    if (zakup == JOptionPane.YES_OPTION) {
                        Bankier.sprzedajNieruchomoscGraczowi((Nieruchomosc) pole, gracz);
                        JOptionPane.showMessageDialog(null, "Dokonałaś/Dokonałeś zakupu " + pole.getNazwaPola() + " za kwote " + ((Nieruchomosc) pole).getCena() + ". Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
                    }
                    else JOptionPane.showMessageDialog(null, "Ahhh... taka okazja przeszła koło nosa... Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
                }
                else JOptionPane.showMessageDialog(null, "Ups... niestety, chwilowo nie stać Cię na zakup " + pole.getNazwaPola() + ". Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
            }
            else {
                if (((Nieruchomosc) pole).getIdPosiadacza() != gracz.getId()) {
                    Gracz wlasciciel = null;
                    for (int i = 0; i < gracze.size(); i++) {
                        if (gracze.get(i).getId() == ((Nieruchomosc) pole).getIdPosiadacza()) {
                            wlasciciel = gracze.get(i);
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Oho... " + gracz.getNazwa() + ", wygląda na to, że weszłaś/wszedłeś na pole, którego właścicielem jest " + wlasciciel.getNazwa() + ". Niestety, musisz jej/mu zapłacić opłate za postój na tym polu");
                    JOptionPane.showMessageDialog(null, "Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
                    int oplata;
                    if (pole instanceof ElektrowniaWodociagi) oplata = wlasciciel.getEWIlosc() * 75;
                    else if (pole instanceof Kolej) oplata = wlasciciel.getKolejIlosc() * 25;
                    else {
                        if (((Ulica)((Nieruchomosc) pole)).getPoziomZabudowania() == 0) oplata = ((Nieruchomosc) pole).getOplata();
                        else oplata = ((Ulica)((Nieruchomosc) pole)).getZabudowaOplata();
                    }
                    JOptionPane.showMessageDialog(null, "Opłata za postój wyniesię Cię: " + oplata);
                    if (gracz.getSaldoKonta() >= oplata) {
                        gracz.setSaldoKonta(gracz.getSaldoKonta() - oplata);
                        wlasciciel.setSaldoKonta(wlasciciel.getSaldoKonta() + oplata);
                        JOptionPane.showMessageDialog(null, "Dokonano opłaty za postój. Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "OOOO nieee! Wygląda na to, że nie posiadasz odpowiedniej ilości pieniędzy do przeprowadzenia opłaty " + gracz.getNazwa() + ". Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta() + "... Sprawdźmy, czy bank może coś zarekwirować aby uiścić opłatę");
                        if (gracz.getEWIlosc() == 0 && gracz.getKolejIlosc() == 0 && gracz.getUlicaIlosc() == 0) {
                            gracz.setBankrut(true);
                            JOptionPane.showMessageDialog(null, "BANKRUCTWO!!! Niestety, nie posiadasz nic co bank mógłby zarekwirować. Dopadło Cię bankructwo i jednocześnie koniec gry " + gracz.getNazwa());
                        }
                        else {
                            uzbierajPieniadzeZGraczaNieruchomosci(gracz, oplata);
                            if (gracz.getSaldoKonta() >= oplata) {
                                wlasciciel.setSaldoKonta(wlasciciel.getSaldoKonta() + oplata);
                                gracz.setSaldoKonta(gracz.getSaldoKonta() - oplata);
                                JOptionPane.showMessageDialog(null, "Ufff...jakoś udało się spłacić opłatę za postój na polu u " + wlasciciel.getNazwa() + ". Niestety Bank był zmuszony zabrać przy tym część twoich nieruchomości aby uiścić opłatę. Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
                            }
                        }
                    }
                }
                else {
                    Ulica ulica = ((Ulica)((Nieruchomosc) pole));
                    int rozbudowa = 10;
                    if (gracz.getSaldoKonta() >= ulica.getZabudowaCena()) {
                        if (ulica.getPoziomZabudowania() < 5) rozbudowa = JOptionPane.showConfirmDialog(null, "Czy chcesz rozbudować swoją ulicę o domek?");
                        if (rozbudowa == JOptionPane.YES_OPTION) {
                            Bankier.rozbudujUliceGraczowi(ulica, gracz);
                            JOptionPane.showMessageDialog(null, "BRAWO! Rozbudowałeś " + ulica.getNazwaPola() + ". Teraz opłata za postój będzie wynosić: " + ulica.getZabudowaOplata() + ". Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
                        } else {
                            JOptionPane.showMessageDialog(null, "Trzeba rozbudowywać miasto...no co ty...");
                        }
                    }
                    if (rozbudowa != JOptionPane.YES_OPTION) {
                        if (ulica.getPoziomZabudowania() > 0) {
                            rozbudowa = JOptionPane.showConfirmDialog(null, "Czy chcesz zmniejszyć swoją ulicę?");
                            if (rozbudowa == JOptionPane.YES_OPTION) {
                                Bankier.zmniejszUliceGraczowi(ulica, gracz);
                                JOptionPane.showMessageDialog(null, "Jak możesz degradować miasto!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Tak jest! Miasto ma być duże!");
                            }
                        }
                        else {
                            rozbudowa = JOptionPane.showConfirmDialog(null, "Czy chcesz sprzedać ulicę?");
                            if (rozbudowa == JOptionPane.YES_OPTION) {
                                Bankier.kupNieruchomoscOdGracza(ulica, gracz);
                                JOptionPane.showMessageDialog(null, "Pozbywasz się świetnej nieruchomości...PRZEDSIĘBIORCO...");
                            }
                            else JOptionPane.showMessageDialog(null, "Słusznie! Niech pieniądze lecą strumienień!");
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Twoje saldo konta " + gracz.getNazwa() + " wynosi: " + gracz.getSaldoKonta());
            }
        }
    }

    private boolean wygrana() {
        int wygranyGracz = 0;
        for (int i = 0; i < gracze.size(); i++) {
            if (gracze.get(i).getBankrut() == false) wygranyGracz++;
        }
        if (wygranyGracz == 1) return true;
        return false;
    }

    private int ktoWygral() {
        for (int i = 0; i < gracze.size(); i++) {
            if (gracze.get(i).getBankrut() == false) return i;
        }
        return -1;
    }

    public void rozgrywka() {
        if (stworzGraczy()) {
            int aktualnyGracz = new Random().nextInt(gracze.size()), kolejka = 1;
            JOptionPane.showMessageDialog(null, "Zaczyna " + gracze.get(aktualnyGracz).getNazwa() + "!");
            while (true) {
                kontynuacjaGry(aktualnyGracz);
                if (gracze.get(aktualnyGracz).getBankrut() == false) ruchGracza(kolejka, aktualnyGracz);
                if (aktualnyGracz == gracze.size() - 1) aktualnyGracz = 0;
                else aktualnyGracz++;
                kolejka++;
                if (wygrana()) {
                    JOptionPane.showMessageDialog(null, "Wygrał gracz: " + gracze.get(ktoWygral()).getNazwa() + "!");
                    break;
                }
            }
        }
    }
}
