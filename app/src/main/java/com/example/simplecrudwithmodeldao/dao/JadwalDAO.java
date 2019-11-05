package com.example.simplecrudwithmodeldao.dao;

import com.example.simplecrudwithmodeldao.model.Jadwal;

import java.util.ArrayList;
import java.util.List;

public class JadwalDAO {

    private List<Jadwal> jadwals = new ArrayList<>();

    public JadwalDAO() {
        insert(new Jadwal(0, "Senin", "Mobile", "7.00", "712", "Dosen"));
        insert(new Jadwal(1, "Selasa", "Web", "7.00", "712", "Dosen"));
        insert(new Jadwal(2, "Rabu", "Design", "7.00", "712", "Dosen"));
        insert(new Jadwal(3, "Kamis", "UI/UX", "7.00", "712", "Dosen"));
        insert(new Jadwal(4, "Jumat", "Database", "7.00", "712", "Dosen"));
        insert(new Jadwal(5, "Sabtu", "Mobile", "7.00", "712", "Dosen"));
    }

    public List<Jadwal> selectAll() {
        return jadwals;
    }
    public Jadwal select(int id) {
        return jadwals.get(id);
    }

    public void insert(Jadwal jadwal) {
        jadwals.add(jadwal);
    }
    public void insert(int id, Jadwal jadwal) {
        jadwals.add(id, jadwal);
    }

    public void delete(int id) {
        jadwals.remove(id);
    }

    public void update(int id, Jadwal newJadwal) {
        Jadwal jadwal = jadwals.get(id);
        jadwal.setHari(newJadwal.getHari());
        jadwal.setMatkul(newJadwal.getMatkul());
        jadwal.setJam(newJadwal.getJam());
        jadwal.setRuang(newJadwal.getRuang());
        jadwal.setDosen(newJadwal.getDosen());

        jadwals.remove(id);
        jadwals.add(id, jadwal);
    }
}
