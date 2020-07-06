package com.example.mac.appproject_moneymanager.model;

public class DieuKienThanhTra {
    private int ms_dk_ks ;
    private String ten_dk_ks;

    public DieuKienThanhTra() {

    }

    public DieuKienThanhTra(int ms_dk_ks, String ten_dk_ks) {
        this.ms_dk_ks = ms_dk_ks;
        this.ten_dk_ks = ten_dk_ks;
    }

    public int getMs_dk_ks() {
        return ms_dk_ks;
    }

    public void setMs_dk_ks(int ms_dk_ks) {
        this.ms_dk_ks = ms_dk_ks;
    }

    public String getTen_dk_ks() {
        return ten_dk_ks;
    }

    public void setTen_dk_ks(String ten_dk_ks) {
        this.ten_dk_ks = ten_dk_ks;
    }

    @Override
    public String toString() {
        return ten_dk_ks;
    }
}
