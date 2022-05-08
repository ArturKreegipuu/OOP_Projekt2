package com.example.projekt;

public class VastaneKerge extends Vastane {
    public VastaneKerge(int elud, int kaugus) {
        super(elud, kaugus);
    }

    @Override
    public int ründa(int kaugus, boolean tabab) {
        if (!tabab) {
            System.out.println("Vastane ei tabanud sind!");
            return 0;
        }
        if (kaugus < 100) {
            int dmg = (int) ((Math.random() * (10 - 8) + 8));
            System.out.println("Vastane võttis sult " + dmg + " elu!");
            return dmg;
        } else if (kaugus >= 100 && kaugus < 200) {
            int dmg = (int) ((Math.random() * (11 - 6) + 6));
            System.out.println("Vastane võttis sult " + dmg + " elu!");
            return dmg;
        } else if (kaugus >= 200 && kaugus < 300) {
            int dmg = (int) ((Math.random() * (5 - 4) + 4));
            System.out.println("Vastane võttis sult " + dmg + " elu!");
            return dmg;
        }
        int dmg = (int) ((Math.random() * (3 - 2) + 2));
        System.out.println("Vastane võttis sult " + dmg + " elu!");
        return dmg;
    }

    @Override
    public boolean tabab(int kaugus) {
        double tabavus = Math.random();
        if (kaugus < 50) {
            if (tabavus > 0.5) return true;
        }
        if (kaugus >= 50 && kaugus < 100) {
            if (tabavus > 0.7) return true;
        }
        if (kaugus >= 100) {
            if (tabavus > 0.85) return true;
        }
        return false;
    }
}
