package com.example.projekt;

public class Miinipilduja extends Relvastus {
    private int miinideKogus;

    public Miinipilduja(int miinideKogus) {
        this.miinideKogus = miinideKogus;
    }

    public int getMoona() {
        return miinideKogus;
    }

    public void lisaMoona(int n) {
        this.miinideKogus += n;
    }

    @Override
    public int ründa(int kaugus, boolean tabab) {
        if (miinideKogus == 0) {
            System.out.println("Miinid on otsas!");
            return 0;
        }
        miinideKogus -= 1;
        if (!tabab) {
            System.out.println("Lask läks mööda!");
            return 0;
        }
        int dmg = (int) ((Math.random() * (20 - 8) + 8));
        System.out.println("Vastane kaotas " + dmg + " elu!");
        return dmg;
    }

    @Override
    public boolean tabab(int kaugus) {
        double tabavus = Math.random();
        if (kaugus < 100) {
            if (tabavus > 0.8) return true;
        }
        if (kaugus >= 100 && kaugus < 250) {
            if (tabavus > 0.5) return true;
        }
        if (kaugus > 250) {
            if (tabavus > 0.1) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Miinipilduja (miine:" + miinideKogus + ")";
    }
}
