package com.burhangok.mavisozluk;

import java.io.Serializable;
import java.security.PublicKey;

public class Sozluk implements Serializable {

    public int ID;
    public String English;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getTurkish() {
        return Turkish;
    }

    public void setTurkish(String turkish) {
        Turkish = turkish;
    }

    public String Turkish;

    public Sozluk(String english, String turkish) {
        English = english;
        Turkish = turkish;
    }


    public Sozluk () {

    }

}
