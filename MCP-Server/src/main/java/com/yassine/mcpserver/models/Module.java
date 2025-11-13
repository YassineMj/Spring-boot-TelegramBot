package com.yassine.mcpserver.models;

public class Module {
    private String nom;
    private double note;

    public Module(String nom, double note) {
        this.nom = nom;
        this.note = note;
    }

    public String getNom() { return nom; }
    public double getNote() { return note; }
}