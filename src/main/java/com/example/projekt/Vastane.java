package com.example.projekt;

public abstract class Vastane {
    private int elud;
    private int kaugus;
    private String[] vastasteNimed;
    private int indeksNimi;

    public Vastane(int elud, int kaugus) {
        this.elud = elud;
        this.kaugus = kaugus; // Kaugus mängijast
        this.vastasteNimed = new String[]{"Artur", "Karl", "Christjan", "Reamees Orav", "Tšaikin", "Ülemiste Vana", "Suur koletis", "Sipsik", "Diskreetne matemaatika"};
        this.indeksNimi = (int) ((Math.random() * (vastasteNimed.length)));
    }

    public int getKaugus() {
        return kaugus;
    }

    public void setElud(int dmg) {
        this.elud -= dmg;
    }

    public int getElud() {
        return elud;
    }

    public abstract int ründa(int kaugus, boolean tabab); // Kui suurt kahju vastane, mängijale teeb

    public abstract boolean tabab(int kaugus); // Kas vastane tabab oma rünnakuga mängijat

    @Override
    public String toString() {
        return "Vastane " + vastasteNimed[this.indeksNimi] + " asub " + kaugus + "m kaugusel ja tal on " + elud + " elu.";


    }
}
