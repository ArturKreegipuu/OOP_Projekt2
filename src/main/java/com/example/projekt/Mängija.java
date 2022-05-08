package com.example.projekt;

public class Mängija {
    private String nimi;
    private int elud;

    public Mängija(String nimi, int elud) {
        this.nimi = nimi;
        this.elud = elud;
    }

    public String getNimi() {
        return nimi;
    }

    public int getElud() {
        return elud;
    }

    public void setElud(int dmg) {
        this.elud -= dmg;
    }

    @Override
    public String toString() {
        return "Sul on " + elud + " elu.";
    }
}
