package com.example.projekt;

public class Ragulka extends Relvastus {
    private int kivideArv;

    public Ragulka(int kivideArv) {
        this.kivideArv = kivideArv;
    }

    public int getMoona() {
        return kivideArv;
    }

    public void lisaMoona(int n) {
        this.kivideArv += n;
    }

    @Override
    public int ründa(int kaugus, boolean tabab) {
        if (kivideArv == 0) {
            System.out.println("Laskekivid on otsas!");
            return 0;
        }
        kivideArv -= 1;
        if (!tabab) {
            System.out.println("Lask läks mööda!");
            return 0;
        }
        if (kaugus < 25) {
            int dmg = (int) ((Math.random() * (5 - 1) + 1));
            System.out.println("Vastane kaotas " + dmg + " elu!");
            return dmg;
        }
        int dmg = (int) ((Math.random() * (3 - 1) + 1));
        System.out.println("Vastane kaotas " + dmg + " elu!");
        return dmg;
    }

    @Override
    public boolean tabab(int kaugus) {
        double tabavus = Math.random();
        if (kaugus < 25) {
            if (tabavus > 0.3) return true;
        }
        if (kaugus >= 25 && kaugus < 50) {
            if (tabavus > 0.6) return true;
        }
        return false; // Kaugemale kui 50m ei taba
    }

    @Override
    public String toString() {
        return "Ragulka (kive:" + kivideArv + ")";
    }
}
