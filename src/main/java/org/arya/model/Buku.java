package org.arya.model;

public class Buku {
    private String judulbuku;
    private String pengarangbuku;
    private double hargaBuku;
    private double diskonBuku;
    private Integer nomerbuku;
    private String namaauthor;

    public String getJudulbuku() {
        return judulbuku;
    }

    public void setJudulbuku(String judulbuku) {
        this.judulbuku = judulbuku;
    }

    public String getPengarangbuku() {
        return pengarangbuku;
    }

    public void setPengarangbuku(String pengarangbuku) {
        this.pengarangbuku = pengarangbuku;
    }

    public double getHargaBuku() {
        return hargaBuku;
    }

    public void setHargaBuku(double hargaBuku) {
        this.hargaBuku = hargaBuku;
    }

    public double getDiskonBuku() {
        return diskonBuku;
    }

    public void setDiskonBuku(double diskonBuku) {
        this.diskonBuku = diskonBuku;
    }

    public void setPublisherId(int publisher_id) {
    }

    public void setPublisherName(String publisher_name) {
    }
    public void setNomerbuku(int nomerbuku_id){

    }
    public String getNamaauthor(){
        return namaauthor;
    }


    /*

    public void setJudulDanPengarang(String namaBuku, String pengarang){
        this.judulbuku = namaBuku;
        this.pengarangbuku = pengarang;
    }
    public String getJudulDanPengarang(){
        return this.judulbuku+ " | "+this.pengarangbuku;
    }*/
}
