package com.company;

import java.util.ArrayList;

public class Plansza {
    public Plansza(ArrayList<Pole> plansza) {
        plansza.add(new ZwyklePole(1, "Start"));
        int[] zabudowyOplaty = {10,30,90,160,250};
        plansza.add(new Ulica(2, "Brązowa 1", 60, 2, zabudowyOplaty, 50));
        plansza.add(new ZwyklePole(3, "Zwykłe pole 1"));
        int[] zabudowyOplaty2 = {20,60,180,320,450};
        plansza.add(new Ulica(4, "Brązowa 2", 60, 4, zabudowyOplaty2, 50));
        plansza.add(new Podatek(5, "Podatek dochodowy", 200));
        plansza.add(new Kolej(6, "Kolej południowa", 200, 25));
        int[] zabudowyOplaty3 = {30,90,270,400,550};
        plansza.add(new Ulica(7, "Błękitna 1", 100, 6, zabudowyOplaty3, 50));
        plansza.add(new ZwyklePole(8, "Zwykłe pole 2"));
        plansza.add(new Ulica(9, "Błękitna 2", 100, 6, zabudowyOplaty3, 50));
        int[] zabudowyOplaty4 = {40,100,300,450,600};
        plansza.add(new Ulica(10, "Błękitna 3", 120, 8, zabudowyOplaty4, 50));
        plansza.add(new ZwyklePole(11, "Zwykłe pole 3"));
        int[] zabudowyOplaty5 = {50,150,450,625,750};
        plansza.add(new Ulica(12, "Różowa 1", 140, 10, zabudowyOplaty5, 100));
        plansza.add(new ElektrowniaWodociagi(13, "Elektrownia", 150, 75));
        plansza.add(new Ulica(14, "Różowa 2", 140, 10, zabudowyOplaty5, 100));
        int[] zabudowyOplaty6 = {60,80,500,700,900};
        plansza.add(new Ulica(15, "Różowa 3", 160, 12, zabudowyOplaty6, 100));
        plansza.add(new Kolej(16, "Kolej zachodnia", 200, 25));
        int[] zabudowyOplaty7 = {70,200,550,750,950};
        plansza.add(new Ulica(17, "Pomarańczowa 1", 180, 14, zabudowyOplaty7, 100));
        plansza.add(new ZwyklePole(18, "Zwykłe pole 4"));
        plansza.add(new Ulica(19, "Pomarańczowa 2", 180, 14, zabudowyOplaty7, 100));
        int[] zabudowyOplaty8 = {80,220,600,800,1000};
        plansza.add(new Ulica(20, "Pomarańczowa 3", 200, 16, zabudowyOplaty8, 100));
        plansza.add(new ZwyklePole(21, "Darmowy parking"));
        int[] zabudowyOplaty9 = {90,250,700,875,1050};
        plansza.add(new Ulica(22, "Czerwona 1", 220, 18, zabudowyOplaty9, 150));
        plansza.add(new ZwyklePole(23, "Zwykłe pole 5"));
        plansza.add(new Ulica(24, "Czerwona 2", 220, 18, zabudowyOplaty9, 150));
        int[] zabudowyOplaty10 = {100,300,750,925,1100};
        plansza.add(new Ulica(25, "Czerwona 3", 240, 20, zabudowyOplaty10, 150));
        plansza.add(new Kolej(26, "Kolej północna", 200, 25));
        int[] zabudowyOplaty11 = {110,330,800,975,1150};
        plansza.add(new Ulica(27, "Żółta 1", 260, 22, zabudowyOplaty11, 150));
        plansza.add(new Ulica(28, "Żółta 2", 260, 22, zabudowyOplaty11, 150));
        plansza.add(new ElektrowniaWodociagi(29, "Wodociągi", 150, 75));
        int[] zabudowyOplaty12 = {120,360,850,1025,1200};
        plansza.add(new Ulica(30, "Żółta 3", 280, 24, zabudowyOplaty12, 150));
        plansza.add(new ZwyklePole(31, "Zwykłe pole 6"));
        int[] zabudowyOplaty13 = {130,390,900,1100,1275};
        plansza.add(new Ulica(32, "Zielona 1", 300, 26, zabudowyOplaty13, 200));
        plansza.add(new Ulica(33, "Zielona 2", 300, 26, zabudowyOplaty13, 200));
        plansza.add(new ZwyklePole(34, "Zwykłe pole 7"));
        int[] zabudowyOplaty14 = {150,450,1000,1200,1400};
        plansza.add(new Ulica(35, "Zielona 3", 320, 28, zabudowyOplaty14, 200));
        plansza.add(new Kolej(36, "Kolej wschodnia", 200, 25));
        plansza.add(new ZwyklePole(37, "Zwykłe pole 8"));
        int[] zabudowyOplaty15 = {175,500,1100,1300,1500};
        plansza.add(new Ulica(38, "Niebieska 1", 350, 35, zabudowyOplaty15, 200));
        plansza.add(new Podatek(39, "Podatek od nieruchomości", 100));
        int[] zabudowyOplaty16 = {200,600,1400,1700,2000};
        plansza.add(new Ulica(40, "Niebieska 2", 400, 50, zabudowyOplaty16, 200));
    }
}
