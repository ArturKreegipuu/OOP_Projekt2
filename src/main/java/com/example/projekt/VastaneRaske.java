package com.example.projekt;

public class VastaneRaske extends Vastane {
    public VastaneRaske(int elud, int kaugus) {
        super(elud, kaugus);
    }

    @Override
    public int ründa(int kaugus, boolean tabab) {
        if (!tabab) {
            System.out.println("Vastane ei tabanud sind!");
            return 0;
        } else if (kaugus >= 100 && kaugus < 200) {
            int dmg = (int) ((Math.random() * (35 - 25) + 25));
            System.out.println("Vastane võttis sult " + dmg + " elu!");
            return dmg;
        } else if (kaugus >= 200 && kaugus < 300) {
            int dmg = (int) ((Math.random() * (25 - 18) + 18));
            System.out.println("Vastane võttis sult " + dmg + " elu!");
            return dmg;
        }
        int dmg = (int) (Math.random() * (15 - 8) + 8);
        System.out.println("Vastane võttis sult " + dmg + " elu!");
        return dmg;
    }

    @Override
    public boolean tabab(int kaugus) {
        double tabavus = Math.random();
        if (kaugus < 50) {
            if (tabavus > 0.1) return true;
        }
        if (kaugus >= 50 && kaugus < 100) {
            if (tabavus > 0.3) return true;
        }
        if (kaugus >= 100) {
            if (tabavus > 0.5) return true;
        }
        return false;
    }
}
