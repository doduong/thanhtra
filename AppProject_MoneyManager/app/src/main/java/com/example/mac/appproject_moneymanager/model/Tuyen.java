package com.example.mac.appproject_moneymanager.model;

public class Tuyen {

    public int ms_tuyen ;

    public String mo_ta_tuyen;

    public Tuyen() {
    }

    public Tuyen(int ms_tuyen, String mo_ta_tuyen) {
        this.ms_tuyen = ms_tuyen;
        this.mo_ta_tuyen = mo_ta_tuyen;
    }

    public int getMs_tuyen() {
        return ms_tuyen;
    }

    public void setMs_tuyen(int ms_tuyen) {
        this.ms_tuyen = ms_tuyen;
    }

    public String getMo_ta_tuyen() {
        return mo_ta_tuyen;
    }

    public void setMo_ta_tuyen(String mo_ta_tuyen) {
        this.mo_ta_tuyen = mo_ta_tuyen;
    }

    @Override
    public String toString() {
        return ms_tuyen +"-"+mo_ta_tuyen;
    }
}
