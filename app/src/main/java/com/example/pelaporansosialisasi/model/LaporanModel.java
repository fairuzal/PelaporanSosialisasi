package com.example.pelaporansosialisasi.model;

import java.io.Serializable;

public class LaporanModel implements Serializable {
    String id;
    String pelapor;
    String pendamping1;
    String penyelenggara;
    String jmlpeserta;
    String ketpeserta;
    String acara;
    String lokasi;
    String kecamatan;
    String kelurahan;
    String tanggal;
    String jam1;
    String jam2;
    String notulen;
    String kendala;
    String jmldownloader;
    String photoUrl;

    public LaporanModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPelapor() {
        return pelapor;
    }

    public void setPelapor(String pelapor) {
        this.pelapor = pelapor;
    }

    public String getPendamping1() {
        return pendamping1;
    }

    public void setPendamping1(String pendamping1) {
        this.pendamping1 = pendamping1;
    }


    public String getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(String penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

    public String getJmlpeserta() {
        return jmlpeserta;
    }

    public void setJmlpeserta(String jmlpeserta) {
        this.jmlpeserta = jmlpeserta;
    }

    public String getKetpeserta() {
        return ketpeserta;
    }

    public void setKetpeserta(String ketpeserta) {
        this.ketpeserta = ketpeserta;
    }

    public String getAcara() {
        return acara;
    }

    public void setAcara(String acara) {
        this.acara = acara;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam1() {
        return jam1;
    }

    public void setJam1(String jam1) {
        this.jam1 = jam1;
    }

    public String getJam2() {
        return jam2;
    }

    public void setJam2(String jam2) {
        this.jam2 = jam2;
    }

    public String getNotulen() {
        return notulen;
    }

    public void setNotulen(String notulen) {
        this.notulen = notulen;
    }

    public String getKendala() {
        return kendala;
    }

    public void setKendala(String kendala) {
        this.kendala = kendala;
    }

    public String getJmldownloader() {
        return jmldownloader;
    }

    public void setJmldownloader(String jmldownloader) {
        this.jmldownloader = jmldownloader;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LaporanModel(String id,
                        String pelapor,
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
        this.id = id;
        this.pelapor = pelapor;
        this.pendamping1 = pendamping1;
        this.penyelenggara = penyelenggara;
        this.jmlpeserta = jmlpeserta;
        this.ketpeserta = ketpeserta;
        this.acara = acara;
        this.lokasi = lokasi;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.tanggal = tanggal;
        this.jam1 = jam1;
        this.jam2 = jam2;
        this.notulen = notulen;
        this.kendala = kendala;
        this.jmldownloader = jmldownloader;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return  " "+id+"\n" +
                " "+pelapor+"\n" +
                " "+pendamping1+"\n" +
                " "+penyelenggara+"\n" +
                " "+jmlpeserta+"\n" +
                " "+ketpeserta+"\n" +
                " "+acara+"\n" +
                " "+lokasi+"\n" +
                " "+kecamatan+"\n" +
                " "+kelurahan+"\n" +
                " "+tanggal+"\n" +
                " "+jam1+"\n" +
                " "+jam2+"\n" +
                " "+notulen+"\n" +
                " "+kendala+"\n" +
                " "+jmldownloader+"\n" +
                " "+photoUrl;
    }


}


