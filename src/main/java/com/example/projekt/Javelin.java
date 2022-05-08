package com.example.projekt;

public class Javelin extends Relvastus{
    private int moona;

    public Javelin(int moona) {
        this.moona = moona;
    }

    public int getMoona() {
        return moona;
    }

    public void lisaMoona(int n) {
        this.moona += n;
    }

    @Override
    public int ründa(int kaugus, boolean tabab) {
        if (moona == 0){
            System.out.println("Moon on otsas!");
            return 0;
        }
        moona-=1;
        if (!tabab) {
            System.out.println("Lask läks mööda!");
            return 0;
        }
        int dmg = (int) ((Math.random() * (25 - 15) + 15));
        System.out.println("Vastane kaotas " + dmg + " elu!");
        return dmg;
    }

    @Override
    public boolean tabab(int kaugus) {
        double tabavus = Math.random();
        if (kaugus < 250){
            if (tabavus > 0.4) return true;
        }
        if (tabavus > 0.75) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Javelin (moona:"+ moona + ")";
    }
}
