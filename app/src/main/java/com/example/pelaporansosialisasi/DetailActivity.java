package com.example.pelaporansosialisasi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView tvPelapord, tvTanggald, tvLokasid, tvKelurahand, tvKecamatand, tvPendamping, tvPenyelenggara, tvJmlPeserta, tvKetPeserta, tvAcara, tvJam1, tvJam2, tvNotulensi, tvKendala, tvJmlDownloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvPelapord = findViewById(R.id.tvpel);
        tvPendamping = findViewById(R.id.tvpen);
        tvPenyelenggara = findViewById(R.id.tvpeny);
        tvJmlPeserta = findViewById(R.id.tvjml);
        tvKetPeserta = findViewById(R.id.tvket);

        tvAcara = findViewById(R.id.tva);
        tvLokasid = findViewById(R.id.tvl);
        tvKelurahand = findViewById(R.id.tvk2);
        tvKecamatand = findViewById(R.id.tvk1);
        tvTanggald = findViewById(R.id.tvt);
        tvJam1 = findViewById(R.id.tvj1);
        tvJam2 = findViewById(R.id.tvj2);

        tvNotulensi = findViewById(R.id.tvn);
        tvKendala = findViewById(R.id.tvk3);
        tvJmlDownloader = findViewById(R.id.tvjml1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Halaman Detail");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        String Pelapor = getIntent().getStringExtra("pelapor");
        String Pendamping = getIntent().getStringExtra("pendamping");
        String Penyelenggara = getIntent().getStringExtra("penyelenggara");
        String JmlPeserta = getIntent().getStringExtra("jmlpeserta");
        String KetPeserta = getIntent().getStringExtra("ketpeserta");

        String Acara = getIntent().getStringExtra("acara");
        String Lokasi = getIntent().getStringExtra("lokasi");
        String Kelurahan = getIntent().getStringExtra("kelurahan");
        String Kecamatan = getIntent().getStringExtra("kecamatan");
        String Tanggal = getIntent().getStringExtra("tanggal");
        String Jam1 = getIntent().getStringExtra("jam1");
        String Jam2 = getIntent().getStringExtra("jam2");

        String Notulensi = getIntent().getStringExtra("notulensi");
        String Kendala = getIntent().getStringExtra("kendala");
        String JmlDownloader = getIntent().getStringExtra("jmldownloader");

        tvPelapord.setText(Pelapor);
        tvPendamping.setText(Pendamping);
        tvPenyelenggara.setText(Penyelenggara);
        tvJmlPeserta.setText(JmlPeserta);
        tvKetPeserta.setText(KetPeserta);

        tvAcara.setText(Acara);
        tvLokasid.setText(Lokasi);
        tvKelurahand.setText(Kelurahan);
        tvKecamatand.setText(Kecamatan);
        tvTanggald.setText(Tanggal);
        tvJam1.setText(Jam1);
        tvJam2.setText(Jam2);

        tvNotulensi.setText(Notulensi);
        tvKendala.setText(Kendala);
        tvJmlDownloader.setText(JmlDownloader);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
