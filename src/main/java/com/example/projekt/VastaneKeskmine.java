package com.example.projekt;

public class VastaneKeskmine extends Vastane {
    public VastaneKeskmine(int elud, int kaugus) {
        super(elud, kaugus);
    }

    @Override
    public int ründa(int kaugus, boolean tabab) {
        if (!tabab) {
            System.out.println("Vastane ei tabanud sind!");
            return 0;
        } else if (kaugus >= 100 && kaugus < 200) {
            int dmg = (int) (Math.random() * (23 - 17) + 17);
            System.out.println("Vastane võttis sult " + dmg + " elu!");
            return dmg;
        } else if (kaugus >= 200 && kaugus < 300) {
            int dmg = (int) ((Math.random() * (13 - 10) + 10));
            System.out.println("Vastane võttis sult " + dmg + " elu!");
            return dmg;
        }
        int dmg = (int) (Math.random() * (7 - 5) + 5);
        System.out.println("Vastane võttis sult " + dmg + " elu!");
        return dmg;
    }

    @Override
    public boolean tabab(int kaugus) {
        double tabavus = Math.random();
        if (kaugus < 50) {
            if (tabavus > 0.4) return true;
        }
        if (kaugus >= 50 && kaugus < 100) {
            if (tabavus > 0.6) return true;
        }
        if (kaugus >= 100) {
            if (tabavus > 0.8) return true;
        }
        return false;
    }
}
