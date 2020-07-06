package com.example.mac.appproject_moneymanager.model;
import java.io.Serializable;
import java.util.Date;

public class ThongTinKhachHang implements Serializable{

    private Integer ms_mnoi ;

    private Integer stt_lo_trinh ;

    private String nguoi_thue ;

    private String dia_chi ;

    private Integer ms_dh ;

    private String so_seri ;

    private Integer chi_so_cu ;

    private Integer chi_so_moi ;

    private Integer so_tthu ;

    private Date ngay_doc_cu ;

    private Date ngay_doc_moi ;

    private Integer so_ho ;

    private String dien_thoai ;

    private Integer ms_tuyen ;

    private Integer ms_tk ;

    private Integer ms_ttrang_doc ;

    private Integer ms_tt_dd ;
    private String ghi_chu ;
    private String url_image ;

    public ThongTinKhachHang() {
    }

    public ThongTinKhachHang(Integer ms_mnoi, int stt_lo_trinh, String nguoi_thue, String dia_chi, Integer ms_dh, String so_seri, Integer chi_so_cu, Integer chi_so_moi, Integer so_tthu, Date ngay_doc_cu, Date ngay_doc_moi, Integer so_ho, String dien_thoai, Integer ms_tuyen, Integer ms_tk, Integer ms_ttrang_doc, Integer ms_tt_dd, String ghi_chu, String url_image) {
        this.ms_mnoi = ms_mnoi;
        this.stt_lo_trinh = stt_lo_trinh;
        this.nguoi_thue = nguoi_thue;
        this.dia_chi = dia_chi;
        this.ms_dh = ms_dh;
        this.so_seri = so_seri;
        this.chi_so_cu = chi_so_cu;
        this.chi_so_moi = chi_so_moi;
        this.so_tthu = so_tthu;
        this.ngay_doc_cu = ngay_doc_cu;
        this.ngay_doc_moi = ngay_doc_moi;
        this.so_ho = so_ho;
        this.dien_thoai = dien_thoai;
        this.ms_tuyen = ms_tuyen;
        this.ms_tk = ms_tk;
        this.ms_ttrang_doc = ms_ttrang_doc;
        this.ms_tt_dd = ms_tt_dd;
        this.ghi_chu = ghi_chu;
        this.url_image = url_image;
    }

    public Integer getMs_mnoi() {
        return ms_mnoi;
    }

    public void setMs_mnoi(Integer ms_mnoi) {
        this.ms_mnoi = ms_mnoi;
    }

    public int getStt_lo_trinh() {
        return stt_lo_trinh;
    }

    public void setStt_lo_trinh(int stt_lo_trinh) {
        this.stt_lo_trinh = stt_lo_trinh;
    }

    public String getNguoi_thue() {
        return nguoi_thue;
    }

    public void setNguoi_thue(String nguoi_thue) {
        this.nguoi_thue = nguoi_thue;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public Integer getMs_dh() {
        return ms_dh;
    }

    public void setMs_dh(Integer ms_dh) {
        this.ms_dh = ms_dh;
    }

    public String getSo_seri() {
        return so_seri;
    }

    public void setSo_seri(String so_seri) {
        this.so_seri = so_seri;
    }

    public Integer getChi_so_cu() {
        return chi_so_cu;
    }

    public void setChi_so_cu(Integer chi_so_cu) {
        this.chi_so_cu = chi_so_cu;
    }

    public Integer getChi_so_moi() {
        return chi_so_moi;
    }

    public void setChi_so_moi(Integer chi_so_moi) {
        this.chi_so_moi = chi_so_moi;
    }

    public Integer getSo_tthu() {
        return so_tthu;
    }

    public void setSo_tthu(Integer so_tthu) {
        this.so_tthu = so_tthu;
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

    public Integer getSo_ho() {
        return so_ho;
    }

    public void setSo_ho(Integer so_ho) {
        this.so_ho = so_ho;
    }

    public String getDien_thoai() {
        return dien_thoai;
    }

    public void setDien_thoai(String dien_thoai) {
        this.dien_thoai = dien_thoai;
    }

    public Integer getMs_tuyen() {
        return ms_tuyen;
    }

    public void setMs_tuyen(Integer ms_tuyen) {
        this.ms_tuyen = ms_tuyen;
    }

    public Integer getMs_tk() {
        return ms_tk;
    }

    public void setMs_tk(Integer ms_tk) {
        this.ms_tk = ms_tk;
    }

    public Integer getMs_ttrang_doc() {
        return ms_ttrang_doc;
    }

    public void setMs_ttrang_doc(Integer ms_ttrang_doc) {
        this.ms_ttrang_doc = ms_ttrang_doc;
    }

    public Integer getMs_tt_dd() {
        return ms_tt_dd;
    }

    public void setMs_tt_dd(Integer ms_tt_dd) {
        this.ms_tt_dd = ms_tt_dd;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
