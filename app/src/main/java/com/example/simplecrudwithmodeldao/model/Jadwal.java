package com.example.simplecrudwithmodeldao.model;

public class Jadwal {
    private int id;
    private String hari;
    private String matkul;
    private String jam;
    private String ruang;
    private String dosen;

    public Jadwal() {
    }

    public Jadwal(int id, String hari, String matkul, String jam, String ruang, String dosen) {
        this.id = id;
        this.hari = hari;
        this.matkul = matkul;
        this.jam = jam;
        this.ruang = ruang;
        this.dosen = dosen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }
}
