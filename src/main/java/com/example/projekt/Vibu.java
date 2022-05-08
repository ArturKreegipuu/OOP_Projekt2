package com.example.projekt;

public class Vibu extends Relvastus {
    private int nooled;

    public Vibu(int nooled) {
        this.nooled = nooled;
    }

    public void lisaMoona(int n) { // saab vibule nooli lisada
        this.nooled += n;
    }

    public int getMoona() {
        return nooled;
    }

    @Override
    public int ründa(int kaugus, boolean tabab) { // tagastab tehtud dmg
        if (nooled == 0) {
            System.out.println("Nooled on otsas!");
            return 0;
        }
        nooled -= 1;
        if (!tabab) {
            System.out.println("Lask läks mööda!");
            return 0;
        }
        if (kaugus < 100){
            int dmg =(int) ((Math.random() * (10 - 1)) + 1);
            System.out.println("Vastane kaotas " + dmg + " elu!");
            return dmg;
        }
        else if (kaugus >= 100 && kaugus < 200){
            int dmg = (int) (Math.random() * (8));
            System.out.println("Vastane kaotas " + dmg + " elu!");
            return dmg;
        }
        else if (kaugus >= 200 && kaugus < 300){
            int dmg = (int) ((Math.random() * (3)));
            System.out.println("Vastane kaotas " + dmg + " elu!");
            return dmg;
        }
        int dmg = (int) ((Math.random() * (1)));
        System.out.println("Vastane kaotas " + dmg + " elu!");
        return dmg;
    }

    @Override
    public boolean tabab(int kaugus) {
        double tabavus = Math.random();
        if (kaugus < 50) {
            if (tabavus > 0.2) return true;
        } else if (kaugus >= 50 && kaugus < 100) {
            if (tabavus > 0.5) return true;
        } else if (kaugus >= 100 && kaugus < 200) {
            if (tabavus > 0.75) return true;
        } else if (kaugus >= 200 && kaugus < 300) {
            if (tabavus > 0.87) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vibu (nooli:"+ nooled+")";
    }
}
