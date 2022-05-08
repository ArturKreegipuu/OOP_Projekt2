package com.example.projekt;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class Kasutajaliides extends Application {
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
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        StackPane paigutus = new StackPane();
        Scene stseen = new Scene(paigutus, 1200, 600);
        Text tutvustus = new Text();
        tutvustus.setFont(new Font(15));
        tutvustus.setTextAlignment(TextAlignment.LEFT);
        paigutus.setAlignment(tutvustus, Pos.CENTER);
            tutvustus.setText("Mängu eesmärk on vastane hävitada. Alguses saad valida mängu raskusastme. Mida raskem on tase, seda rohkem vastane sulle haiget teeb. " + "\n" +
                    "Enne iga käiku saad info vastase kauguse ja seisundi kohta, mille põhjal pead otsustama, millise relvaga teda rünnata tahad. " + "\n" +
                    "Pead arvestama ka sellega, palju sul moona alles on. Moona saad endale elude vastu juurde vahetada. " + "\n" +
                    "Võidab see, kes esimesena oma vastase hävitab.");
            Button nuppMängi = new Button("Mängi!");
            paigutus.setAlignment(nuppMängi, Pos.BOTTOM_CENTER);
            nuppMängi.setOnMouseClicked(event -> {
                tutvustus.setVisible(false);
                nuppMängi.setVisible(false);
                // Mängija nimi
                while (true) {
                    Text nimi = new Text("Sisestage oma nimi: ");
                    nimi.setFont(new Font(15));
                    nimi.setTextAlignment(TextAlignment.CENTER);
                    paigutus.setAlignment(nimi, Pos.TOP_LEFT);
                    TextField nimeRida = new TextField();
                    nimeRida.setMinWidth(300);
                    paigutus.setAlignment(nimeRida, Pos.CENTER_LEFT);
                    paigutus.getChildren().addAll(nimeRida, nimi);
                    paigutus.setOnKeyPressed(event1 -> {
                        String mängijaNimi = nimeRida.getText();
                        nimeRida.setVisible(false);
                        nimi.setVisible(false);
                        Mängija mängija = new Mängija(mängijaNimi, 100);
                        // Anname mängijale ka relvad
                        List<Relvastus> relvastus = new ArrayList<Relvastus>();
                        relvastus.add(new Vibu(1));
                        relvastus.add(new Javelin(7));
                        relvastus.add(new Ragulka(15));
                        relvastus.add(new Miinipilduja(10));
                        relvastus.add(new Sapöörilabidas());
                        // Raskustaseme valimine
                        Text tase = new Text("Vali tase!");
                        tase.setFont(new Font(15));
                        tase.setTextAlignment(TextAlignment.CENTER);
                        paigutus.getChildren().add(tase);
                        paigutus.setAlignment(tase, Pos.TOP_LEFT);
                        Button nuppKerge = new Button("Kerge");
                        Button nuppKeskmine = new Button("Keskmine");
                        Button nuppRaske = new Button("Raske");
                        VBox nuppudePaigutus = new VBox();
                        nuppudePaigutus.setAlignment(Pos.CENTER);
                        nuppudePaigutus.setSpacing(10);
                        nuppudePaigutus.getChildren().addAll(nuppKerge, nuppKeskmine, nuppRaske);
                        paigutus.getChildren().add(nuppudePaigutus);
                        Button[] buttons = {nuppKerge, nuppKeskmine, nuppRaske};
                        for (Button button : buttons) {
                            button.setOnMouseClicked(event2 -> {
                                String manguTase = button.getText().toLowerCase();
                                for (Button button1 : buttons) {
                                    button1.setVisible(false);
                                }
                                tase.setVisible(false);
                                int vastaseKaugus = (int) ((Math.random() * (275 - 1) + 1)); // vastase kaugus mängijast
                                if (manguTase.equals("kerge")) { // Kerge tasemega vastane
                                    Vastane vastane = new VastaneKerge(100, vastaseKaugus);
                                    VBox vastaneRelvaValik = new VBox();
                                    Text vastaseInfo = new Text(vastane.toString());
                                    vastaseInfo.setFont(new Font(15));
                                    vastaseInfo.setTextAlignment(TextAlignment.LEFT);
                                    vastaneRelvaValik.getChildren().add(vastaseInfo);
                                    Button[] nuppRelvad = new Button[relvastus.size()];
                                    int indeks = 0;
                                    for (Relvastus relvastus1 : relvastus) {
                                        nuppRelvad[indeks++] = new Button(relvastus1.toString());
                                    }
                                    for (Button button1 : nuppRelvad) {
                                        vastaneRelvaValik.getChildren().add(button1);
                                    }
                                    paigutus.getChildren().add(vastaneRelvaValik);
                                    paigutus.setAlignment(vastaneRelvaValik, Pos.TOP_LEFT);
                                    for (Button button1 : nuppRelvad) {
                                        button1.setOnMouseClicked(event3 -> {
                                            String relv = button1.getText().split(" ")[0].toLowerCase();
                                            if (relvaValik(relv) >= 0 && !(relvastus.size() == 4 && relv.equals("sapöörilabidas"))) {
                                                vastaseInfo.setVisible(false);
                                                for (Button button2 : nuppRelvad) {
                                                    button2.setVisible(false);
                                                }
                                                Relvastus kasutatavRelv = relvastus.get(relvaValik(relv));
                                                vastane.setElud(kasutatavRelv.ründa(vastaseKaugus, kasutatavRelv.tabab(vastaseKaugus))); // Mängija Ründab valitud relvaga vastast
                                                vastaseInfo.setText(vastane.toString());
                                                vastaseInfo.setVisible(true);
                                                if (vastane.getElud() <= 0) {
                                                    vastaseInfo.setText("Vastane on surnud! Sinu võit!");
                                                }
                                                if (relvastus.get(relvaValik(relv)).getMoona() == 0) { // Kui kasutatud relva moon on otsas, on võimalus seda elude vastu juurde vahetada
                                                    vastaseInfo.setVisible(false);
                                                    Text moonaInfo = new Text(vastane.toString() + "\nRelva " + relvastus.get(relvaValik(relv)).toString() + " moon on otsas. Kas soovite moona juurde lisada? Iga moona eest kaotad 3 elu. Sisestage lisatava moona kogus: ");
                                                    moonaInfo.setFont(new Font(15));
                                                    moonaInfo.setTextAlignment(TextAlignment.LEFT);
                                                    paigutus.setAlignment(moonaInfo, Pos.TOP_LEFT);
                                                    paigutus.getChildren().add(moonaInfo);
                                                    TextField moonaLisamine = new TextField();
                                                    moonaLisamine.setMinWidth(300);
                                                    paigutus.setAlignment(moonaLisamine, Pos.CENTER_LEFT);
                                                    paigutus.getChildren().add(moonaLisamine);
                                                    moonaLisamine.setOnKeyPressed(event4 -> {
                                                        int moonaOtsus = Integer.parseInt(moonaInfo.getText());
                                                        if (moonaOtsus != 0) {
                                                            if (mängija.getElud() > moonaOtsus * 3) {
                                                                mängija.setElud(moonaOtsus * 3); // võtab mängijalt elud
                                                                relvastus.get(relvaValik(relv)).lisaMoona(moonaOtsus); // lisab moona
                                                                //break MoonaTsükkel;
                                                            }
                                                            moonaInfo.setText("Sinu elud ei võimalda nii palju moona lisada. Muuda lisatava moona kogust!");
                                                            //continue MoonaTsükkel;
                                                        }
                                                        //break MoonaTsükkel;
                                                    });
                                                }

                                            }
                                        });
                                    }
                                }
                            });
                            break;
                        }

                    });
                    break;
                }
            });
            paigutus.getChildren().add(nuppMängi);
            paigutus.getChildren().add(tutvustus);
            stage.setScene(stseen);
            stage.setTitle("Mäng");
            stage.show();

        /*MainLoop:
        while (true) {

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
                            System.out.println("Oled surnud! " + mängija.getNimi() + ", kaotasid kõige kergema taseme vastasele!");
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
                            System.out.println("Oled surnud! " + mängija.getNimi() + ", kaotasid keskmise taseme vastasele!");
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
                            System.out.println("Oled surnud! " + mängija.getNimi() + ", kaotasid kõige tugevama taseme vastasele!");
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
        }*/

    }
}
