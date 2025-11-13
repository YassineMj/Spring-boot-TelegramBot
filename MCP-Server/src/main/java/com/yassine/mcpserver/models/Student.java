package com.yassine.mcpserver.models;

import java.util.List;

public class Student {
    private String nom;
    private int age;
    private List<Module> modules;

    public Student(String nom, int age, List<Module> modules) {
        this.nom = nom;
        this.age = age;
        this.modules = modules;
    }

    public String getNom() { return nom; }
    public int getAge() { return age; }
    public List<Module> getModules() { return modules; }
}
