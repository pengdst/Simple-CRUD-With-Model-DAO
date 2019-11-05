package com.example.simplecrudwithmodeldao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class UpdActivity extends AppCompatActivity {
    EditText etHari, etMatkul, etJam, etRuang, etDosen;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd);

        bindView();

        try {
            setView();
        } catch (Exception e) {
            Log.e("error", Objects.requireNonNull(e.getMessage()));
        }

        bindListener();
    }

    private void bindView() {
        etHari = findViewById(R.id.etHari);
        etMatkul = findViewById(R.id.etMatkul);
        etJam = findViewById(R.id.etJam);
        etRuang = findViewById(R.id.etRuang);
        etDosen = findViewById(R.id.etDosen);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 3);
        String hari = intent.getStringExtra("Hari");
        String matkul = intent.getStringExtra("Matkul");
        String jam = intent.getStringExtra("Jam");
        String ruang = intent.getStringExtra("Ruang");
        String dosen = intent.getStringExtra("Dosen");

        etHari.setText(hari);
        etMatkul.setText(matkul);
        etJam.setText(jam);
        etRuang.setText(ruang);
        etDosen.setText(dosen);
        if (hari != null) btnSubmit.setText("Update");
    }

    private void bindListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent result = new Intent();
                result.putExtra("Hari", etHari.getText().toString());
                result.putExtra("Matkul", etMatkul.getText().toString());
                result.putExtra("Jam", etJam.getText().toString());
                result.putExtra("Ruang", etRuang.getText().toString());
                result.putExtra("Dosen", etDosen.getText().toString());

                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
