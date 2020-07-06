package com.example.mac.appproject_moneymanager.model;

import java.util.Date;

public class NoiDungKiemSoat {
    private Integer ms_ks ;

    private Integer ms_mnoi ;

    private Integer  ms_userthanhtra ;

    private Integer ms_tk ;

    private Date ngay_kiem_soat ;

    private Integer chi_so_cu ;
    private Integer chi_so_moi ;

    private Integer chi_so_ks ;

    private Integer so_tthu_ks ;

    private Integer ms_dk_ks;

    private Integer ms_tt_ks ;

    private String ghi_chu_ks ;

    private String url_image_ks ;

    private String thu_muc ;

    private Integer co_chi_so;

    private Integer ms_tql;

    private Integer co_chi_so_moi;

    private Date ngay_doc_cu ;

    private Date ngay_doc_moi ;

    public NoiDungKiemSoat(Integer ms_mnoi, Integer ms_userthanhtra, Integer ms_tk, Integer chi_so_cu, Integer chi_so_moi, Integer chi_so_ks, Integer so_tthu_ks, Integer ms_dk_ks, Integer ms_tt_ks, String ghi_chu_ks, String thu_muc, Integer co_chi_so, Integer ms_tql, Integer co_chi_so_moi, Date ngay_doc_cu, Date ngay_doc_moi) {
        this.ms_ks = ms_ks;
        this.ms_mnoi = ms_mnoi;
        this.ms_userthanhtra = ms_userthanhtra;
        this.ms_tk = ms_tk;
        this.chi_so_cu = chi_so_cu;
        this.chi_so_moi = chi_so_moi;
        this.chi_so_ks = chi_so_ks;
        this.so_tthu_ks = so_tthu_ks;
        this.ms_dk_ks = ms_dk_ks;
        this.ms_tt_ks = ms_tt_ks;
        this.ghi_chu_ks = ghi_chu_ks;
        this.thu_muc = thu_muc;
        this.co_chi_so = co_chi_so;
        this.ms_tql = ms_tql;
        this.co_chi_so_moi =  co_chi_so_moi;
        this.ngay_doc_cu = ngay_doc_cu;
        this.ngay_doc_moi = ngay_doc_moi;
    }

    public Integer getCo_chi_so_moi() {
        return co_chi_so_moi;
    }

    public void setCo_chi_so_moi(Integer co_chi_so_moi) {
        this.co_chi_so_moi = co_chi_so_moi;
    }

    public Integer getChi_so_moi() {
        return chi_so_moi;
    }

    public void setChi_so_moi(Integer chi_so_moi) {
        this.chi_so_moi = chi_so_moi;
    }

    public Integer getCo_chi_so() {
        return co_chi_so;
    }

    public void setCo_chi_so(Integer co_chi_so) {
        this.co_chi_so = co_chi_so;
    }

    public int getMs_dk_ks() {
        return ms_dk_ks;
    }

    public void setMs_dk_ks(int ms_dk_ks) {
        this.ms_dk_ks = ms_dk_ks;
    }

    public NoiDungKiemSoat() {
    }

    public Integer getMs_tql() {
        return ms_tql;
    }

    public void setMs_tql(Integer ms_tql) {
        this.ms_tql = ms_tql;
    }

    public int getMs_ks() {
        return ms_ks;
    }

    public void setMs_ks(int ms_ks) {
        this.ms_ks = ms_ks;
    }

    public int getMs_mnoi() {
        return ms_mnoi;
    }

    public void setMs_mnoi(int ms_mnoi) {
        this.ms_mnoi = ms_mnoi;
    }

    public int getMs_userthanhtra() {
        return ms_userthanhtra;
    }

    public void setMs_userthanhtra(int ms_userthanhtra) {
        this.ms_userthanhtra = ms_userthanhtra;
    }

    public int getMs_tk() {
        return ms_tk;
    }

    public void setMs_tk(int ms_tk) {
        this.ms_tk = ms_tk;
    }

    public Date getNgay_kiem_soat() {
        return ngay_kiem_soat;
    }

    public void setNgay_kiem_soat(Date ngay_kiem_soat) {
        this.ngay_kiem_soat = ngay_kiem_soat;
    }

    public float getChi_so_cu() {
        return chi_so_cu;
    }

    public void setMs_ks(Integer ms_ks) {
        this.ms_ks = ms_ks;
    }

    public void setMs_mnoi(Integer ms_mnoi) {
        this.ms_mnoi = ms_mnoi;
    }

    public void setMs_userthanhtra(Integer ms_userthanhtra) {
        this.ms_userthanhtra = ms_userthanhtra;
    }

    public void setMs_tk(Integer ms_tk) {
        this.ms_tk = ms_tk;
    }

    public void setChi_so_cu(Integer chi_so_cu) {
        this.chi_so_cu = chi_so_cu;
    }

    public Integer getChi_so_ks() {
        return chi_so_ks;
    }

    public void setChi_so_ks(Integer chi_so_ks) {
        this.chi_so_ks = chi_so_ks;
    }

    public Integer getSo_tthu_ks() {
        return so_tthu_ks;
    }

    public void setSo_tthu_ks(Integer so_tthu_ks) {
        this.so_tthu_ks = so_tthu_ks;
    }

    public void setMs_dk_ks(Integer ms_dk_ks) {
        this.ms_dk_ks = ms_dk_ks;
    }

    public void setMs_tt_ks(Integer ms_tt_ks) {
        this.ms_tt_ks = ms_tt_ks;
    }

    public int getMs_tt_ks() {
        return ms_tt_ks;
    }

    public void setMs_tt_ks(int ms_tt_ks) {
        this.ms_tt_ks = ms_tt_ks;
    }

    public String getGhi_chu_ks() {
        return ghi_chu_ks;
    }

    public void setGhi_chu_ks(String ghi_chu_ks) {
        this.ghi_chu_ks = ghi_chu_ks;
    }

    public String getUrl_image_ks() {
        return url_image_ks;
    }

    public void setUrl_image_ks(String url_image_ks) {
        this.url_image_ks = url_image_ks;
    }

    public String getThu_muc() {
        return thu_muc;
    }

    public void setThu_muc(String thu_muc) {
        this.thu_muc = thu_muc;
    }

    public Date getNgay_doc_cu() {
        return ngay_doc_cu;
    }

    public void setNgay_doc_cu(Date ngay_doc_cu) {
        this.ngay_doc_cu = ngay_doc_cu;
    }

    public Date getNgay_doc_moi() {
        return ngay_doc_moi;
    }

    public void setNgay_doc_moi(Date ngay_doc_moi) {
        this.ngay_doc_moi = ngay_doc_moi;
    }
}
