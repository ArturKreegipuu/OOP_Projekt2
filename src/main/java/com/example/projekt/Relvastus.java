package com.example.projekt;

import java.util.ArrayList;
import java.util.List;

public abstract class Relvastus {
    private List<Relvastus> relvastuse = new ArrayList<Relvastus>();

    public abstract int ründa(int kaugus, boolean tabab); // Vastavalt relvale, tabavusele ja vastase kaugusele saab ründamise tagajärjel vastasele haiget teha

    public abstract boolean tabab(int kaugus); // Näitab, kui efektiivselt relv mingi vahemaa tagant vastasele pihta saab

    public abstract void lisaMoona(int n); // Saab moona lisada

    public abstract int getMoona(); // Saab vaadata, palju antud relval moona on
}
