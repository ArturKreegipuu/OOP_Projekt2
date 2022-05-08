package com.example.projekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Peaklass {
    public static int relvaValik(String relv) { // Abimeetod, et teha kindlaks, millist relva mängija kasutada tahab. Tagastab indeksi, millega relva relvastuse listist kasutada saab.
        switch (relv) {
            case "vibu":
                return 0;
            case "javelin":
                return 1;
            case "ragulka":
                return 2;
            case "miinipilduja":
                return 3;
            case "sapöörilabidas":
                return 4;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Mängu eesmärk on vastane hävitada. Alguses saad valida mängu raskusastme. Mida raskem on tase, seda rohkem vastane sulle haiget teeb.");
        System.out.println("Enne iga käiku saad info vastase kauguse ja seisundi kohta, mille põhjal pead otsustama, millise relvaga teda rünnata tahad.");
        System.out.println("Pead arvestama ka sellega, palju sul moona alles on. Moona saad endale elude vastu juurde vahetada.");
        System.out.println("Võidab see, kes esimesena oma vastase hävitab.");
        System.out.println();
        MainLoop:
        while (true) {
            // Mängija nimi
            Scanner Nimi = new Scanner(System.in);
            System.out.print("Sisesta enda nimi: ");
            String mängijaNimi = Nimi.nextLine();
            Mängija mängija = new Mängija(mängijaNimi, 100);
            // Anname mängijale ka relvad
            List<Relvastus> relvastus = new ArrayList<Relvastus>();
            relvastus.add(new Vibu(15));
            relvastus.add(new Javelin(7));
            relvastus.add(new Ragulka(15));
            relvastus.add(new Miinipilduja(10));
            relvastus.add(new Sapöörilabidas());
            // Raskustaseme valimine
            Scanner tase = new Scanner(System.in);
            System.out.print("Vali tase(kerge/keskmine/raske): ");
            String manguTase = tase.nextLine().toLowerCase();
            int vastaseKaugus = (int) ((Math.random() * (275 - 1) + 1)); // vastase kaugus mängijast
            if (manguTase.equals("kerge")) { // Kerge tasemega vastane
                Vastane vastane = new VastaneKerge(100, vastaseKaugus);
                System.out.println(vastane.toString());
                RelvaValimine:
                while (true) {
                    System.out.println();
                    Scanner relvaValik = new Scanner(System.in);
                    System.out.println("Relvade valik: ");
                    for (Relvastus relvastus1 : relvastus) {
                        System.out.println(relvastus1.toString());
                    }
                    System.out.println();
                    System.out.println("Millise relvaga ründad? (kirjuta relva nimi) ");
                    String relv = tase.nextLine().toLowerCase();

                    if (relvaValik(relv) >= 0 && !(relvastus.size() == 4 && relv.equals("sapöörilabidas"))) {
                        Relvastus kasutatavRelv = relvastus.get(relvaValik(relv));
                        vastane.setElud(kasutatavRelv.ründa(vastaseKaugus, kasutatavRelv.tabab(vastaseKaugus))); // Mängija Ründab valitud relvaga vastast
                        System.out.println(vastane.toString());


                        if (vastane.getElud() <= 0) {
                            System.out.println("!Vastane on surnud! Sinu võit!");
                            break;
                        }
                        if (relvastus.get(relvaValik(relv)).getMoona() == 0) { // Kui kasutatud relva moon on otsas, on võimalus seda elude vastu juurde vahetada
                            MoonaTsükkel:
                            while (true) {
                                Scanner moonOtsas = new Scanner(System.in);
                                System.out.println("Relva " + relvastus.get(relvaValik(relv)).toString() + " moon on otsas. Kas soovite moona juurde lisada? Iga moona eest kaotad 3 elu. Sisestage lisatava moona kogus: ");
                                int moonaOtsus = Integer.parseInt(moonOtsas.nextLine().toLowerCase());
                                if (moonaOtsus != 0) {
                                    if (mängija.getElud() > moonaOtsus * 3) {
                                        mängija.setElud(moonaOtsus * 3); // võtab mängijalt elud
                                        relvastus.get(relvaValik(relv)).lisaMoona(moonaOtsus); // lisab moona
                                        break MoonaTsükkel;
                                    }
                                    System.out.println("Sinu elud ei võimalda nii palju moona lisada. Muuda lisatava moona kogust!");
                                    continue MoonaTsükkel;
                                }
                                break MoonaTsükkel;
                            }
                        }
                        if (vastaseKaugus > 5 && relvaValik(relv) == 4) {
                            System.out.println("Viskasid labida kaugele vastase poole ja kaotasid selle!");
                            relvastus.remove(4);

                        }
                        mängija.setElud(vastane.ründa(vastaseKaugus, vastane.tabab(vastaseKaugus)));
                        System.out.println(mängija.toString());
                        if (mängija.getElud() <= 0) {
                            System.out.println("Oled surnud! "+ mängija.getNimi()+", kaotasid kõige kergema taseme vastasele!");
                            break;
                        }
                    } else {
                        Scanner relvaError = new Scanner(System.in);
                        System.out.println("Sul ei ole sellist relva. Kas lõpetad mängimise? (jah/ei) ");
                        String kasLõpetab = relvaError.nextLine().toLowerCase();
                        if (kasLõpetab.equals("jah")) break MainLoop;
                    }
                }
                // Mäng sai läbi
                Scanner kasUuesti = new Scanner(System.in);
                System.out.println("Mängi uuesti? (jah/ei) ");
                String uuesti = kasUuesti.nextLine().toLowerCase();
                if (uuesti.equals("jah")) continue MainLoop;
                break;
            } else if (manguTase.equals("keskmine")) { // Tegu keskmise vastasega, aga tegevused samad, mis kerge või tugeva vastasega
                Vastane vastane = new VastaneKeskmine(100, vastaseKaugus);
                System.out.println(vastane.toString());
                RelvaValimine:
                while (true) {
                    System.out.println();
                    Scanner relvaValik = new Scanner(System.in);
                    System.out.println("Relvade valik: ");
                    for (Relvastus relvastus1 : relvastus) {
                        System.out.println(relvastus1.toString());
                    }
                    System.out.println();
                    System.out.println("Millise relvaga ründad? (kirjuta relva nimi) ");
                    String relv = tase.nextLine().toLowerCase();

                    if (relvaValik(relv) >= 0 && !(relvastus.size() == 4 && relv.equals("sapöörilabidas"))) {
                        Relvastus kasutatavRelv = relvastus.get(relvaValik(relv));
                        vastane.setElud(kasutatavRelv.ründa(vastaseKaugus, kasutatavRelv.tabab(vastaseKaugus))); // Mängija Ründab valitud relvaga vastast
                        System.out.println(vastane.toString());


                        if (vastane.getElud() <= 0) {
                            System.out.println("Vastane on surnud! Sinu võit!");
                            break;
                        }
                        if (relvastus.get(relvaValik(relv)).getMoona() == 0) { // Kui kasutatud relva moon on otsas, on võimalus seda elude vastu juurde vahetada
                            MoonaTsükkel:
                            while (true) {
                                Scanner moonOtsas = new Scanner(System.in);
                                System.out.println("Relva " + relvastus.get(relvaValik(relv)).toString() + " moon on otsas. Kas soovite moona juurde lisada? Iga moona eest kaotad 3 elu. Sisestage lisatava moona kogus: ");
                                int moonaOtsus = Integer.parseInt(moonOtsas.nextLine().toLowerCase());
                                if (moonaOtsus != 0) {
                                    if (mängija.getElud() > moonaOtsus * 3) {
                                        mängija.setElud(moonaOtsus * 3); // võtab mängijalt elud
                                        relvastus.get(relvaValik(relv)).lisaMoona(moonaOtsus); // lisab moona
                                        break MoonaTsükkel;
                                    }
                                    System.out.println("Sinu elud ei võimalda nii palju moona lisada. Muuda lisatava moona kogust!");
                                    continue MoonaTsükkel;
                                }
                                break MoonaTsükkel;
                            }
                        }
                        if (vastaseKaugus > 5 && relvaValik(relv) == 4) {
                            System.out.println("Viskasid labida kaugele vastase poole ja kaotasid selle!");
                            relvastus.remove(4);

                        }
                        mängija.setElud(vastane.ründa(vastaseKaugus, vastane.tabab(vastaseKaugus)));
                        System.out.println(mängija.toString());
                        if (mängija.getElud() <= 0) {
                            System.out.println("Oled surnud! "+ mängija.getNimi()+", kaotasid keskmise taseme vastasele!");
                            break;
                        }
                    } else {
                        Scanner relvaError = new Scanner(System.in);
                        System.out.println("Sul ei ole sellist relva. Kas lõpetad mängimise? (jah/ei) ");
                        String kasLõpetab = relvaError.nextLine().toLowerCase();
                        if (kasLõpetab.equals("jah")) break MainLoop;
                    }
                }
                // Mäng sai läbi
                Scanner kasUuesti = new Scanner(System.in);
                System.out.println("Mängi uuesti? (jah/ei) ");
                String uuesti = kasUuesti.nextLine().toLowerCase();
                if (uuesti.equals("jah")) continue MainLoop;
                break;
            } else if (manguTase.equals("raske")) { // Tegu raske vastasega, aga tegevused samad, mis kerge või keskmise vastasega
                Vastane vastane = new VastaneRaske(100, vastaseKaugus);
                System.out.println(vastane.toString());
                RelvaValimine:
                while (true) {
                    System.out.println();
                    Scanner relvaValik = new Scanner(System.in);
                    System.out.println("Relvade valik: ");
                    for (Relvastus relvastus1 : relvastus) {
                        System.out.println(relvastus1.toString());
                    }
                    System.out.println();
                    System.out.println("Millise relvaga ründad? (kirjuta relva nimi) ");
                    String relv = tase.nextLine().toLowerCase();

                    if (relvaValik(relv) >= 0 && !(relvastus.size() == 4 && relv.equals("sapöörilabidas"))) {
                        Relvastus kasutatavRelv = relvastus.get(relvaValik(relv));
                        vastane.setElud(kasutatavRelv.ründa(vastaseKaugus, kasutatavRelv.tabab(vastaseKaugus))); // Mängija Ründab valitud relvaga vastast
                        System.out.println(vastane.toString());


                        if (vastane.getElud() <= 0) {
                            System.out.println("Vastane on surnud! Sinu võit!");
                            break;
                        }
                        if (relvastus.get(relvaValik(relv)).getMoona() == 0) { // Kui kasutatud relva moon on otsas, on võimalus seda elude vastu juurde vahetada
                            MoonaTsükkel:
                            while (true) {
                                Scanner moonOtsas = new Scanner(System.in);
                                System.out.println("Relva " + relvastus.get(relvaValik(relv)).toString() + " moon on otsas. Kas soovite moona juurde lisada? Iga moona eest kaotad 3 elu. Sisestage lisatava moona kogus: ");
                                int moonaOtsus = Integer.parseInt(moonOtsas.nextLine().toLowerCase());
                                if (moonaOtsus != 0) {
                                    if (mängija.getElud() > moonaOtsus * 3) {
                                        mängija.setElud(moonaOtsus * 3); // võtab mängijalt elud
                                        relvastus.get(relvaValik(relv)).lisaMoona(moonaOtsus); // lisab moona
                                        break MoonaTsükkel;
                                    }
                                    System.out.println("Sinu elud ei võimalda nii palju moona lisada. Muuda lisatava moona kogust!");
                                    continue MoonaTsükkel;
                                }
                                break MoonaTsükkel;
                            }
                        }
                        if (vastaseKaugus > 5 && relvaValik(relv) == 4) {
                            System.out.println("Viskasid labida kaugele vastase poole ja kaotasid selle!");
                            relvastus.remove(4);

                        }
                        mängija.setElud(vastane.ründa(vastaseKaugus, vastane.tabab(vastaseKaugus)));
                        System.out.println(mängija.toString());
                        if (mängija.getElud() <= 0) {
                            System.out.println("Oled surnud! "+ mängija.getNimi()+", kaotasid kõige tugevama taseme vastasele!");
                            break;
                        }
                    } else {
                        Scanner relvaError = new Scanner(System.in);
                        System.out.println("Sul ei ole sellist relva. Kas lõpetad mängimise? (jah/ei) ");
                        String kasLõpetab = relvaError.nextLine().toLowerCase();
                        if (kasLõpetab.equals("jah")) break MainLoop;
                    }
                }
                // Mäng sai läbi
                Scanner kasUuesti = new Scanner(System.in);
                System.out.println("Mängi uuesti? (jah/ei) ");
                String uuesti = kasUuesti.nextLine().toLowerCase();
                if (uuesti.equals("jah")) continue MainLoop;
                break;
            } else { // Kui raskustase on ebakorrektselt sisestatud, on võimalik mäng lõpetada või tase uuesti sisestada
                Scanner tasemeError = new Scanner(System.in);
                System.out.println("Sellist taset meie mängul veel ei ole! Kas lõpetad mängimise? (jah/ei) ");
                String kasLõpetab = tasemeError.nextLine().toLowerCase();
                if (kasLõpetab.equals("jah")) break MainLoop;
            }
        }
    }
}
