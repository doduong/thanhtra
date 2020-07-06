package com.example.mac.appproject_moneymanager.model.request;

import java.util.List;

public class KhachHangUpdateRequest {


    private int ms_tk ;
    private int ms_ks;
    private int ms_mnoi ;
    private int ms_userthanhtra;
    private String base_64_image ;

    public KhachHangUpdateRequest(int ms_tk, int ms_ks, int ms_mnoi, int ms_userthanhtra, String base_64_image) {
        this.ms_tk = ms_tk;
        this.ms_ks = ms_ks;
        this.ms_mnoi = ms_mnoi;
        this.ms_userthanhtra = ms_userthanhtra;
        this.base_64_image = base_64_image;
    }

    public KhachHangUpdateRequest() {
    }

    public int getMs_userthanhtra() {
        return ms_userthanhtra;
    }

    public void setMs_userthanhtra(int ms_userthanhtra) {
        this.ms_userthanhtra = ms_userthanhtra;
    }

    public int getMs_ks() {
        return ms_ks;
    }

    public void setMs_ks(int ms_ks) {
        this.ms_ks = ms_ks;
    }

    public int getMs_tk() {
        return ms_tk;
    }

    public void setMs_tk(int ms_tk) {
        this.ms_tk = ms_tk;
    }

    public int getMs_mnoi() {
        return ms_mnoi;
    }

    public void setMs_mnoi(int ms_mnoi) {
        this.ms_mnoi = ms_mnoi;
    }

    public String getBase_64_image() {
        return base_64_image;
    }

    public void setBase_64_image(String base_64_image) {
        this.base_64_image = base_64_image;
    }
}
