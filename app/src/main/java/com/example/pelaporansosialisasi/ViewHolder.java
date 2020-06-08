package com.example.pelaporansosialisasi;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pelaporansosialisasi.model.LaporanModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ViewHolder extends RecyclerView.ViewHolder {
    LaporanModel laporanModel;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClicklistener.onItemClick(view, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onItemlongClick(view, getAdapterPosition());
                return false;
            }
        });
    }

    public void setData(Context context, String pelapor, String tanggal, String photoUrl, String lokasi, String kelurahan, String kecamatan) {
        TextView tvPelapor = itemView.findViewById(R.id.tvPelapor);
        TextView tvTanggal = itemView.findViewById(R.id.tanggal);
        TextView tvLokasi = itemView.findViewById(R.id.lokasi1);
        TextView tvKelurahan = itemView.findViewById(R.id.lokasi2);
        TextView tvKecamatan = itemView.findViewById(R.id.lokasi3);
        ImageView imLaporan = itemView.findViewById(R.id.fotolaporan);
        tvPelapor.setText(pelapor);
        tvTanggal.setText(tanggal);
        Picasso.get().load(photoUrl).into(imLaporan);
        tvLokasi.setText(lokasi);
        tvKelurahan.setText(kelurahan);
        tvKecamatan.setText(kecamatan);
    }

    public void getFull(Context context, String pelapor,
                        String pendamping1,
                        String penyelenggara,
                        String jmlpeserta,
                        String ketpeserta,
                        String acara,
                        String lokasi,
                        String kecamatan,
                        String kelurahan,
                        String tanggal,
                        String jam1,
                        String jam2,
                        String notulen,
                        String kendala,
                        String jmldownloader,
                        String photoUrl) {

    }


    private ViewHolder.Clicklistener mClicklistener;

    public interface Clicklistener {
        void onItemClick(View view, int position);

        void onItemlongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.Clicklistener clickListener) {
        mClicklistener = clickListener;
    }


}
