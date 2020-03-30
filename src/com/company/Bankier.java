package com.company;

public class Bankier {
    public static void dajPremie(Gracz gracz) {
        gracz.setSaldoKonta(gracz.getSaldoKonta() + 200);
    }

    public static void pobierzPodatek(Podatek podatek, Gracz gracz) {
        gracz.setSaldoKonta(gracz.getSaldoKonta() - podatek.getPodatek());
    }

    public static void sprzedajNieruchomoscGraczowi(Nieruchomosc nieruchomosc, Gracz gracz) {
        nieruchomosc.setIdPosiadacza(gracz.getId());
        if (nieruchomosc instanceof ElektrowniaWodociagi) gracz.setEW((ElektrowniaWodociagi) nieruchomosc);
        else if (nieruchomosc instanceof Kolej) gracz.setKolej((Kolej) nieruchomosc);
        else gracz.setUlica((Ulica) nieruchomosc);
        gracz.setSaldoKonta(gracz.getSaldoKonta() - nieruchomosc.getCena());
    }

    public static void kupNieruchomoscOdGracza(Nieruchomosc nieruchomosc, Gracz gracz) {
        nieruchomosc.setIdPosiadacza(0);
        if (nieruchomosc instanceof ElektrowniaWodociagi) gracz.usunEW((ElektrowniaWodociagi) nieruchomosc);
        else if (nieruchomosc instanceof Kolej) gracz.usunKolej((Kolej) nieruchomosc);
        else gracz.usunUlica((Ulica) nieruchomosc);
        gracz.setSaldoKonta(gracz.getSaldoKonta() + nieruchomosc.getCena());
    }

    public static void rozbudujUliceGraczowi(Ulica ulica, Gracz gracz) {
        ulica.setPoziomZabudowania(ulica.getPoziomZabudowania() + 1);
        ulica.setOplata(ulica.getZabudowaOplata());
        gracz.setSaldoKonta(gracz.getSaldoKonta() - ulica.getZabudowaCena());
    }

    public static void zmniejszUliceGraczowi(Ulica ulica, Gracz gracz) {
        gracz.setSaldoKonta(gracz.getSaldoKonta() + ulica.getZabudowaCena());
        ulica.setPoziomZabudowania(ulica.getPoziomZabudowania() - 1);
        ulica.setOplata(ulica.getZabudowaOplata());
    }
}
