package com.example.mac.appproject_moneymanager.model;

import java.io.Serializable;
import java.util.Date;

public class DuLieuKhachHang implements Serializable{

    private Integer ms_chinhanh ;

    private Integer ms_tql ;

    private String ten_tql ;

    private Integer ms_tuyen ;

    private Integer ms_cky ;

    private Integer ms_dh_mn ;

    private Integer ms_tk ;

    private Integer stt_lo_trinh ;

    private String so_seri ;

    private Integer ms_mnoi ;

    private String nguoi_thue ;

    private String diachi ;

    private Integer ms_md_chinh ;

    private String gia_chinh ;

    private Integer ms_gia_vuot ;

    private String gia_vuot ;

    private Date ngay_thuc_te ;

    private Date ngay_doc_cu ;

    private Date ngay_doc_moi ;

    private Integer chi_so_cu ;

    private Integer chi_so_moi;

    private Integer STT1 ;

    private Integer STT2 ;

    private Integer STT3 ;

    private Integer STT4 ;

    private Integer ms_userthanhtra ;

    private Integer ttkiemsoat ;

    private Integer ms_dk_ks ;
    private String ten_dk_ks;
    private Integer ms_ttrang ;
    private String mo_ta_ttrang ;
    private String mo_ta_tuyen ;
    private String dien_thoai ;
    private String ghi_chu;
    private String mucdichsudung;
    private Date ngay_loc;

    public DuLieuKhachHang() {
    }

    public DuLieuKhachHang(Integer ms_chinhanh, Integer ms_tql, String ten_tql, Integer ms_tuyen, Integer ms_cky, Integer ms_dh_mn, Integer ms_tk, Integer stt_lo_trinh, String so_seri, Integer ms_mnoi, String nguoi_thue, String diachi, Integer ms_md_chinh, String gia_chinh, Integer ms_gia_vuot, String gia_vuot, Date ngay_thuc_te, Date ngay_doc_cu, Date ngay_doc_moi, Integer chi_so_cu, Integer chi_so_moi, Integer STT1, Integer STT2, Integer STT3, Integer STT4, Integer ms_userthanhtra, Integer ttkiemsoat, Integer ms_dk_ks, String ten_dk_ks, Integer ms_ttrang, String mo_ta_ttrang, String mo_ta_tuyen, String dien_thoai, String ghi_chu, String mucdichsudung, Date ngay_loc) {
        this.ms_chinhanh = ms_chinhanh;
        this.ms_tql = ms_tql;
        this.ten_tql = ten_tql;
        this.ms_tuyen = ms_tuyen;
        this.ms_cky = ms_cky;
        this.ms_dh_mn = ms_dh_mn;
        this.ms_tk = ms_tk;
        this.stt_lo_trinh = stt_lo_trinh;
        this.so_seri = so_seri;
        this.ms_mnoi = ms_mnoi;
        this.nguoi_thue = nguoi_thue;
        this.diachi = diachi;
        this.ms_md_chinh = ms_md_chinh;
        this.gia_chinh = gia_chinh;
        this.ms_gia_vuot = ms_gia_vuot;
        this.gia_vuot = gia_vuot;
        this.ngay_thuc_te = ngay_thuc_te;
        this.ngay_doc_cu = ngay_doc_cu;
        this.ngay_doc_moi = ngay_doc_moi;
        this.chi_so_cu = chi_so_cu;
        this.chi_so_moi = chi_so_moi;
        this.STT1 = STT1;
        this.STT2 = STT2;
        this.STT3 = STT3;
        this.STT4 = STT4;
        this.ms_userthanhtra = ms_userthanhtra;
        this.ttkiemsoat = ttkiemsoat;
        this.ms_dk_ks = ms_dk_ks;
        this.ten_dk_ks = ten_dk_ks;
        this.ms_ttrang = ms_ttrang;
        this.mo_ta_ttrang = mo_ta_ttrang;
        this.mo_ta_tuyen = mo_ta_tuyen;
        this.dien_thoai = dien_thoai;
        this.ghi_chu = ghi_chu;
        this.mucdichsudung = mucdichsudung;
        this.ngay_loc = ngay_loc;
    }

    public String getMucdichsudung() {
        return mucdichsudung;
    }

    public void setMucdichsudung(String mucdichsudung) {
        this.mucdichsudung = mucdichsudung;
    }

    public Date getNgay_loc() {
        return ngay_loc;
    }

    public void setNgay_loc(Date ngay_loc) {
        this.ngay_loc = ngay_loc;
    }

    public Integer getMs_chinhanh() {
        return ms_chinhanh;
    }

    public void setMs_chinhanh(Integer ms_chinhanh) {
        this.ms_chinhanh = ms_chinhanh;
    }

    public Integer getMs_tql() {
        return ms_tql;
    }

    public void setMs_tql(Integer ms_tql) {
        this.ms_tql = ms_tql;
    }

    public String getTen_tql() {
        return ten_tql;
    }

    public void setTen_tql(String ten_tql) {
        this.ten_tql = ten_tql;
    }

    public Integer getMs_tuyen() {
        return ms_tuyen;
    }

    public void setMs_tuyen(Integer ms_tuyen) {
        this.ms_tuyen = ms_tuyen;
    }

    public Integer getMs_cky() {
        return ms_cky;
    }

    public void setMs_cky(Integer ms_cky) {
        this.ms_cky = ms_cky;
    }

    public Integer getMs_dh_mn() {
        return ms_dh_mn;
    }

    public void setMs_dh_mn(Integer ms_dh_mn) {
        this.ms_dh_mn = ms_dh_mn;
    }

    public Integer getMs_tk() {
        return ms_tk;
    }

    public void setMs_tk(Integer ms_tk) {
        this.ms_tk = ms_tk;
    }

    public Integer getStt_lo_trinh() {
        return stt_lo_trinh;
    }

    public void setStt_lo_trinh(Integer stt_lo_trinh) {
        this.stt_lo_trinh = stt_lo_trinh;
    }

    public String getSo_seri() {
        return so_seri;
    }

    public void setSo_seri(String so_seri) {
        this.so_seri = so_seri;
    }

    public Integer getMs_mnoi() {
        return ms_mnoi;
    }

    public void setMs_mnoi(Integer ms_mnoi) {
        this.ms_mnoi = ms_mnoi;
    }

    public String getNguoi_thue() {
        return nguoi_thue;
    }

    public void setNguoi_thue(String nguoi_thue) {
        this.nguoi_thue = nguoi_thue;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Integer getMs_md_chinh() {
        return ms_md_chinh;
    }

    public void setMs_md_chinh(Integer ms_md_chinh) {
        this.ms_md_chinh = ms_md_chinh;
    }

    public String getGia_chinh() {
        return gia_chinh;
    }

    public void setGia_chinh(String gia_chinh) {
        this.gia_chinh = gia_chinh;
    }

    public Integer getMs_gia_vuot() {
        return ms_gia_vuot;
    }

    public void setMs_gia_vuot(Integer ms_gia_vuot) {
        this.ms_gia_vuot = ms_gia_vuot;
    }

    public String getGia_vuot() {
        return gia_vuot;
    }

    public void setGia_vuot(String gia_vuot) {
        this.gia_vuot = gia_vuot;
    }

    public Date getNgay_thuc_te() {
        return ngay_thuc_te;
    }

    public void setNgay_thuc_te(Date ngay_thuc_te) {
        this.ngay_thuc_te = ngay_thuc_te;
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

    public Integer getChi_so_cu() {
        return chi_so_cu;
    }
    public Integer getMs_userthanhtra() {
        return ms_userthanhtra;
    }

    public void setMs_userthanhtra(Integer ms_userthanhtra) {
        this.ms_userthanhtra = ms_userthanhtra;
    }

    public Integer getTtkiemsoat() {
        return ttkiemsoat;
    }

    public void setTtkiemsoat(Integer ttkiemsoat) {
        this.ttkiemsoat = ttkiemsoat;
    }

    public Integer getMs_dk_ks() {
        return ms_dk_ks;
    }

    public void setMs_dk_ks(Integer ms_dk_ks) {
        this.ms_dk_ks = ms_dk_ks;
    }

    public String getTen_dk_ks() {
        return ten_dk_ks;
    }

    public void setTen_dk_ks(String ten_dk_ks) {
        this.ten_dk_ks = ten_dk_ks;
    }

    public Integer getMs_ttrang() {
        return ms_ttrang;
    }

    public void setMs_ttrang(Integer ms_ttrang) {
        this.ms_ttrang = ms_ttrang;
    }

    public String getMo_ta_ttrang() {
        return mo_ta_ttrang;
    }

    public void setMo_ta_ttrang(String mo_ta_ttrang) {
        this.mo_ta_ttrang = mo_ta_ttrang;
    }

    public String getMo_ta_tuyen() {
        return mo_ta_tuyen;
    }

    public void setMo_ta_tuyen(String mo_ta_tuyen) {
        this.mo_ta_tuyen = mo_ta_tuyen;
    }

    public String getDien_thoai() {
        return dien_thoai;
    }

    public void setDien_thoai(String dien_thoai) {
        this.dien_thoai = dien_thoai;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public void setChi_so_cu(Integer chi_so_cu) {
        this.chi_so_cu = chi_so_cu;
    }

    public Integer getSTT1() {
        return STT1;
    }

    public void setSTT1(Integer STT1) {
        this.STT1 = STT1;
    }

    public Integer getSTT2() {
        return STT2;
    }

    public void setSTT2(Integer STT2) {
        this.STT2 = STT2;
    }

    public Integer getSTT3() {
        return STT3;
    }

    public void setSTT3(Integer STT3) {
        this.STT3 = STT3;
    }

    public Integer getSTT4() {
        return STT4;
    }

    public void setSTT4(Integer STT4) {
        this.STT4 = STT4;
    }

    public Integer getChi_so_moi() {
        return chi_so_moi;
    }

    public void setChi_so_moi(Integer chi_so_moi) {
        this.chi_so_moi = chi_so_moi;
    }
}
