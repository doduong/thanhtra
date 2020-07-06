package com.example.mac.appproject_moneymanager.model;

public class ChiNhanh {
    public int ms_chinhanh;

    public String ten_chinhanh ;

    public ChiNhanh() {
    }

    public ChiNhanh(int ms_chinhanh, String ten_chinhanh) {
        this.ms_chinhanh = ms_chinhanh;
        this.ten_chinhanh = ten_chinhanh;
    }

    public int getMs_chinhanh() {
        return ms_chinhanh;
    }

    public void setMs_chinhanh(int ms_chinhanh) {
        this.ms_chinhanh = ms_chinhanh;
    }

    public String getTen_chinhanh() {
        return ten_chinhanh;
    }

    public void setTen_chinhanh(String ten_chinhanh) {
        this.ten_chinhanh = ten_chinhanh;
    }

    @Override
    public String toString() {
        return ten_chinhanh;
    }
}
