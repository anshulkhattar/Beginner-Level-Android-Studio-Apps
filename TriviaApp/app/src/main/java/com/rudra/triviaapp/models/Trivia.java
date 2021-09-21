package com.rudra.triviaapp.models;

public class Trivia {
    String id, name, a1, a2, time;

    public Trivia(String id, String name, String a1, String a2, String time) {
        this.id = id;
        this.name = name;
        this.a1 = a1;
        this.a2 = a2;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
