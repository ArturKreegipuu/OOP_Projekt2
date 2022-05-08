package com.example.projekt;

public class Sapöörilabidas extends Relvastus {
    @Override
    public int ründa(int kaugus, boolean tabab) {
        if (!tabab) {
            System.out.println("Sa ei tabanud labidaga vastast!");
            return 0;
        }
        System.out.println("Vastane kaotas 35 elu!");
        return 35;
    }

    @Override
    public boolean tabab(int kaugus) {
        double tabavus = Math.random();
        if (kaugus <= 5) return true;
        if (kaugus > 5 && kaugus < 30) { // Viskab labidat
            if (tabavus > 0.4) return true;
        }
        return false;
    }

    @Override
    public void lisaMoona(int n) { // Moona lisada või uut labidat lisada ei saa

    }

    @Override
    public int getMoona() { // Kui labidas on veel alles, on 'moona kogus' 1
        return 1;
    }

    @Override
    public String toString() {
        return "Sapöörilabidas";
    }
}
