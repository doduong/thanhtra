package com.example.mac.appproject_moneymanager.model;

import java.util.Date;

public class LichSuDongHo {

    private Integer ms_ls_dh ;

    private Integer ms_sk_dh ;

    private Integer ms_dh ;

    private Integer ms_mnoi ;

    private Date ngay_thuc_hien ;

    private Integer chi_so_dh ;

    public LichSuDongHo(Integer ms_ls_dh, Integer ms_sk_dh, Integer ms_dh, Integer ms_mnoi, Date ngay_thuc_hien, Integer chi_so_dh) {
        this.ms_ls_dh = ms_ls_dh;
        this.ms_sk_dh = ms_sk_dh;
        this.ms_dh = ms_dh;
        this.ms_mnoi = ms_mnoi;
        this.ngay_thuc_hien = ngay_thuc_hien;
        this.chi_so_dh = chi_so_dh;
    }

    public LichSuDongHo() {
    }

    public Integer getMs_ls_dh() {
        return ms_ls_dh;
    }

    public void setMs_ls_dh(Integer ms_ls_dh) {
        this.ms_ls_dh = ms_ls_dh;
    }

    public Integer getMs_sk_dh() {
        return ms_sk_dh;
    }

    public void setMs_sk_dh(Integer ms_sk_dh) {
        this.ms_sk_dh = ms_sk_dh;
    }

    public Integer getMs_dh() {
        return ms_dh;
    }

    public void setMs_dh(Integer ms_dh) {
        this.ms_dh = ms_dh;
    }

    public Integer getMs_mnoi() {
        return ms_mnoi;
    }

    public void setMs_mnoi(Integer ms_mnoi) {
        this.ms_mnoi = ms_mnoi;
    }

    public Date getNgay_thuc_hien() {
        return ngay_thuc_hien;
    }

    public void setNgay_thuc_hien(Date ngay_thuc_hien) {
        this.ngay_thuc_hien = ngay_thuc_hien;
    }

    public Integer getChi_so_dh() {
        return chi_so_dh;
    }

    public void setChi_so_dh(Integer chi_so_dh) {
        this.chi_so_dh = chi_so_dh;
    }
}
