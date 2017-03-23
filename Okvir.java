package com.example.android.slagalika;

class Okvir {

    private boolean prazno = true;
    private String slika;
    private Integer id;

    void setIme(String a) {
        slika = a;
    }
    String getIme(){
        return  slika;
    }
    void setID(int id) {
        this.id = id;
    }
    Integer  getID(){
        return id;
    }
    void setPrazno(boolean a) {
        prazno = a;
    }

    boolean getPrazno() {
        return prazno;
    }
}
