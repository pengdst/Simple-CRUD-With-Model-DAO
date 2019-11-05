package com.example.simplecrudwithmodeldao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.simplecrudwithmodeldao.adapter.JadwalAdapter;
import com.example.simplecrudwithmodeldao.dao.JadwalDAO;
import com.example.simplecrudwithmodeldao.model.Jadwal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvJadwal;
    FloatingActionButton fabAdd;
    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    JadwalAdapter adapter;
    JadwalDAO dao = new JadwalDAO();
    List<Jadwal> jadwals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        setSupportActionBar(toolbar);

        jadwals = dao.selectAll();

        adapter = new JadwalAdapter(this, jadwals);

        rvJadwal.setAdapter(adapter);
        rvJadwal.setLayoutManager(new LinearLayoutManager(this));


    }

    private void bindView() {
        rvJadwal = findViewById(R.id.rv_jadwal);
        fabAdd = findViewById(R.id.fab_add);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        toolbar = findViewById(R.id.toolbar);
    }
}
