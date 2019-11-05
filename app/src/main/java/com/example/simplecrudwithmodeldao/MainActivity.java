package com.example.simplecrudwithmodeldao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.simplecrudwithmodeldao.adapter.JadwalAdapter;
import com.example.simplecrudwithmodeldao.dao.JadwalDAO;
import com.example.simplecrudwithmodeldao.helper.JadwalItemTouchHelper;
import com.example.simplecrudwithmodeldao.helper.JadwalItemTouchHelperListener;
import com.example.simplecrudwithmodeldao.helper.OnItemClickCallback;
import com.example.simplecrudwithmodeldao.model.Jadwal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnItemClickCallback, JadwalItemTouchHelperListener {
    RecyclerView rvJadwal;
    FloatingActionButton fabAdd;
    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    JadwalAdapter adapter;
    JadwalDAO dao = new JadwalDAO();
    List<Jadwal> jadwals = new ArrayList<>();
    private int itemID;

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

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new JadwalItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvJadwal);

        initListener();
    }

    private void initListener() {
        adapter.setOnItemClickCallback(this);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdActivity.class);
                startActivityForResult(intent, 81);
            }
        });
    }

    private void bindView() {
        rvJadwal = findViewById(R.id.rv_jadwal);
        fabAdd = findViewById(R.id.fab_add);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void onItemClick(int id, Jadwal jadwal) {
        Intent intent = new Intent(getApplicationContext(), UpdActivity.class);
        itemID = id;
        intent.putExtra("id", id);
        intent.putExtra("Hari", jadwal.getHari());
        intent.putExtra("Matkul", jadwal.getMatkul());
        intent.putExtra("Jam", jadwal.getJam());
        intent.putExtra("Ruang", jadwal.getRuang());
        intent.putExtra("Dosen", jadwal.getDosen());
        startActivityForResult(intent, 80);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            String matkul = data.getStringExtra("Matkul");
            String jam = data.getStringExtra("Jam");
            String ruang = data.getStringExtra("Ruang");
            String dosen = data.getStringExtra("Dosen");
            String hari = data.getStringExtra("Hari");

            Jadwal jadwal = new Jadwal();
            jadwal.setHari(hari);
            jadwal.setMatkul(matkul);
            jadwal.setJam(jam);
            jadwal.setRuang(ruang);
            jadwal.setDosen(dosen);


            if (requestCode == 80 && resultCode == RESULT_OK){

                Toast.makeText(getApplicationContext(), " = "+data.getStringExtra("Hari"), Toast.LENGTH_LONG).show();

                dao.update(itemID, jadwal);
                adapter.notifyDataSetChanged();
            }
            else if (requestCode == 81 && resultCode == RESULT_OK){
                dao.insert(jadwal);
                adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            Log.e("error", Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof JadwalAdapter.ViewHolder) {
            final Jadwal bckUpJadwal = dao.select(position);
            String matkul = bckUpJadwal.getMatkul();
            final int deletedIndex = position;

            dao.delete(deletedIndex);
            adapter.notifyItemRemoved(deletedIndex);

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, " Undo delete for item "+matkul+ " ?", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dao.insert(deletedIndex, bckUpJadwal);
                    adapter.notifyItemInserted(deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
