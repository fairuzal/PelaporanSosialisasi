package com.example.pelaporansosialisasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaporansosialisasi.model.LaporanModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    String id;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        floatingActionButton = findViewById(R.id.button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StepOneActivity.class);
                startActivity(i);
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference().child("form");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<LaporanModel> options =
                new FirebaseRecyclerOptions.Builder<LaporanModel>()
                        .setQuery(databaseReference, LaporanModel.class)
                        .build();

        FirebaseRecyclerAdapter<LaporanModel, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<LaporanModel, ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ViewHolder holder, int position, @NonNull final LaporanModel model) {
                        holder.setData(getApplicationContext(), model.getPelapor(), model.getTanggal(), model.getPhotoUrl(), model.getLokasi(),
                                model.getKelurahan(), model.getKecamatan());
                        holder.setOnClickListener(new ViewHolder.Clicklistener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView tvPelapor = view.findViewById(R.id.tvPelapor);
                                TextView tvTanggal = view.findViewById(R.id.tanggal);
                                TextView tvLokasi = view.findViewById(R.id.lokasi1);
                                TextView tvKelurahan = view.findViewById(R.id.lokasi2);
                                TextView tvKecamatan = view.findViewById(R.id.lokasi3);
                                final ImageView imageView = view.findViewById(R.id.fotolaporan);
                                String Pelapor = tvPelapor.getText().toString();
                                String Tanggal = tvTanggal.getText().toString();
                                String Lokasi = tvLokasi.getText().toString();
                                String Kelurahan = tvKelurahan.getText().toString();
                                String Kecamatan = tvKecamatan.getText().toString();

                                //throw data
                                holder.getFull(getApplicationContext(), model.getPelapor(), model.getPendamping1(), model.getPenyelenggara()
                                        , model.getJmlpeserta(), model.getKetpeserta(), model.getAcara(), model.getLokasi(), model.getKecamatan()
                                        , model.getKelurahan(), model.getTanggal(), model.getJam1(), model.getJam2(), model.getNotulen()
                                        , model.getKendala(), model.getJmldownloader(), model.getPhotoUrl());
                                {
                                    Intent intent = new Intent(view.getContext(), DetailActivity.class);

                                    intent.putExtra("pelapor", Pelapor);
                                    intent.putExtra("pendamping", model.getPendamping1());
                                    intent.putExtra("penyelenggara", model.getPenyelenggara());
                                    intent.putExtra("jmlpeserta", model.getJmlpeserta());
                                    intent.putExtra("ketpeserta", model.getKetpeserta());
                                    intent.putExtra("acara", model.getAcara());
                                    intent.putExtra("lokasi", Lokasi);
                                    intent.putExtra("kelurahan", Kelurahan);
                                    intent.putExtra("kecamatan", Kecamatan);
                                    intent.putExtra("tanggal", Tanggal);
                                    intent.putExtra("pendamping", model.getPendamping1());
                                    intent.putExtra("jam1", model.getJam1());
                                    intent.putExtra("jam2", model.getJam2());
                                    intent.putExtra("notulensi", model.getNotulen());
                                    intent.putExtra("kendala", model.getKendala());
                                    intent.putExtra("jmldownloader", model.getJmldownloader());
                                    if (imageView.equals(null)) {
                                        imageView.setVisibility(View.GONE);
                                    } else {
                                        try {
                                            Picasso.get().load(model.getPhotoUrl()).into(imageView);
                                        } catch (Exception e) {

                                        }
                                    }

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }

                            }

                            @Override
                            public void onItemlongClick(View view, int position) {

                                id = getItem(position).getId();

                                showDeleteDataDialog(id);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.recycler_view, parent, false);
                        return new ViewHolder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void showDeleteDataDialog(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Hapus");
        builder.setMessage("Anda Yakin Menghapus Data Ini");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Query query = databaseReference.orderByChild("id").equalTo(id);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(MainActivity.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
