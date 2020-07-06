package com.example.mac.appproject_moneymanager.Actions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mac.appproject_moneymanager.Adapter.KhachHangAdapter;
import com.example.mac.appproject_moneymanager.R;
import com.example.mac.appproject_moneymanager.model.ChiNhanh;
import com.example.mac.appproject_moneymanager.model.DuLieuKhachHang;
import com.example.mac.appproject_moneymanager.model.TieuChi;
import com.example.mac.appproject_moneymanager.model.Tuyen;
import com.example.mac.appproject_moneymanager.utils.CommonText;
import com.example.mac.appproject_moneymanager.utils.ReadJson;
import com.example.mac.appproject_moneymanager.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KiemSoat extends    Activity {

    Spinner spitieuchi;
    Spinner spichinhanh;
    Spinner spitoquanly;
    Spinner spituyen;
    EditText edtdanhba;
    Button btntimkiem;
    ListView lstpro;
    CommonText common;
    SharedPref config;
    String ms_userthanhtra="";
    ArrayList<DuLieuKhachHang> listkh = new ArrayList<>();
    private KhachHangAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_soat);
        config = new SharedPref(this);
        ms_userthanhtra = config.getString("ms_userthanhtra", "");

        initView();
    }

    private void initView(){
        common = new CommonText();
        lstpro = (ListView) findViewById(R.id.lstpro);
        /*spitieuchi = (Spinner) findViewById(R.id.spitieuchi);
        spichinhanh = (Spinner) findViewById(R.id.spichinhanh);

        spituyen = (Spinner) findViewById(R.id.spituyen);

        edtsokhoi= (EditText) findViewById(R.id.edtsokhoi);*/
        edtdanhba = (EditText) findViewById(R.id.edtdanhba);

        btntimkiem= (Button) findViewById(R.id.btnsearch);

        /*if(isConnected()) {
            setSpinnerTieuChi();
            setSpinnerChiNhanh();
        }else {
            Toast.makeText(KiemSoat.this, "Không có kết nối Internet!", Toast.LENGTH_LONG).show();
        }*/
        loadSpiner();

        btntimkiem.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {


                //ChiNhanh cn  = (ChiNhanh) spichinhanh.getSelectedItem();
                ToQuanLy tql = (ToQuanLy) spitoquanly.getSelectedItem();
                //Tuyen tuyen = (Tuyen) spituyen.getSelectedItem();
                //getThongTinDiemDung(tql.getMs_tql());

            }


        });

        lstpro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isConnected()) {
                        DuLieuKhachHang ttkh = listkh.get(i);
                        Intent intent = new Intent(KiemSoat.this, NhapThongTinKiemSoat.class);
                        //intent.putExtra("arrlist", listkh);
                        intent.putExtra("ttkh", ttkh);
                        startActivity(intent);
                }else {
                    Toast.makeText(KiemSoat.this, "Không có kết nối internet", Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    public void getThongTinDiemDung(int ms_tql) {

        if (isConnected()) {

            String url = common.URL_API + "/khachhang/getdskhtheotql?ms_tql=" + ms_tql;
           // new HttpAsyncTaskGetDiemDung().execute(url);

        } else {

            Toast.makeText(KiemSoat.this, "Chưa kết nối internet", Toast.LENGTH_LONG).show();
        }

    }

    private void loadSpiner(){
        /*spichinhanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ChiNhanh cn = (ChiNhanh) spichinhanh.getSelectedItem();
                setSpinnerToQuanLy(String.valueOf(cn.ms_chinhanh));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spitoquanly.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ToQuanLy tql = (ToQuanLy) spitoquanly.getSelectedItem();
                setSpinnerTuyen(String.valueOf(tql.ms_tql));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
        //setSpinnerTuyen();
        setSpinnerToQuanLy();
    }

    private  void setSpinnerTieuChi(){
        List<TieuChi> lsttieuchi =  new ArrayList<>();
        TieuChi tc0 = new TieuChi(1, "DS Tuyến");
        TieuChi tc1 = new TieuChi(2, "Khách Hàng Chưa Đọc");
        TieuChi tc2 = new TieuChi(3, "Khách Hàng Tiêu Thụ = 0");
        TieuChi tc3 = new TieuChi(4, "Khách Hàng Treo ĐH");
        TieuChi tc4 = new TieuChi(5, "DS Nợ cước");
        TieuChi tc5 = new TieuChi(6, "SHĐT tiêu thụ cao");
        TieuChi tc6 = new TieuChi(7, "SHKD tiêu thụ thấp");
        lsttieuchi.add(tc0);
        lsttieuchi.add(tc1);
        lsttieuchi.add(tc2);
        lsttieuchi.add(tc3);
        lsttieuchi.add(tc4);
        lsttieuchi.add(tc5);
        lsttieuchi.add(tc6);
        ArrayAdapter<TieuChi> adapter = new ArrayAdapter<TieuChi>(this, android.R.layout.simple_spinner_item, lsttieuchi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spitieuchi.setAdapter(adapter);

    }



    private void setSpinnerChiNhanh(){

        String url = common.URL_API + "/khachhang/getchinhanh";
        List<ChiNhanh> lstcn =  new ArrayList<>();
        JSONArray lstjson;
        try {
            lstjson = ReadJson.readJSonArrayFromURL(url);
            for (int i = 0; i < lstjson.length(); i++) {
                ChiNhanh cn;
                JSONObject objCN = lstjson.getJSONObject(i);
                int ms_chinhanh = objCN.getInt("ms_chinhanh");
                String ten_chinhanh = objCN.getString("ten_chinhanh");
                cn = new ChiNhanh(ms_chinhanh, ten_chinhanh);
                lstcn.add(cn);
            }

            ArrayAdapter<ChiNhanh> adapter = new ArrayAdapter<ChiNhanh>(this, android.R.layout.simple_spinner_item, lstcn);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spichinhanh.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setSpinnerToQuanLy(){

        String url = common.URL_API + "/khachhang/gettoquanly"+"?ms_userthanhtra="+ms_userthanhtra;

        List<ToQuanLy> lsttql =  new ArrayList<>();
        JSONArray lstjson;
        try {
            lstjson = ReadJson.readJSonArrayFromURL(url);
            for (int i = 0; i < lstjson.length(); i++) {
                ToQuanLy tql;
                JSONObject objCN = lstjson.getJSONObject(i);
                int ms_tql = objCN.getInt("ms_tql");
                String ten_tql = objCN.getString("ten_tql");
                //tql = new ToQuanLy(ms_tql, ten_tql);
                //lsttql.add(tql);
            }

            ArrayAdapter<ToQuanLy> adapter = new ArrayAdapter<ToQuanLy>(this, android.R.layout.simple_spinner_item, lsttql);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spitoquanly.setAdapter(adapter);
             //ChiNhanh cn  = (ChiNhanh) spichinhanh.getSelectedItem();
                ToQuanLy tql = (ToQuanLy) spitoquanly.getSelectedItem();
                //Tuyen tuyen = (Tuyen) spituyen.getSelectedItem();
               // getThongTinDiemDung(tql.getMs_tql());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setSpinnerTuyen(){

        String url = common.URL_API + "/khachhang/gettuyendoc"+"?ms_userthanhtra="+ms_userthanhtra;

        List<Tuyen> lsttuyen =  new ArrayList<>();
        JSONArray lstjson;
        try {
            lstjson = ReadJson.readJSonArrayFromURL(url);
            for (int i = 0; i < lstjson.length(); i++) {
                Tuyen tuyen;
                JSONObject objCN = lstjson.getJSONObject(i);
                int ms_tuyen = objCN.getInt("ms_tuyen");
                String mo_ta_tuyen = objCN.getString("mo_ta_tuyen");
                tuyen = new Tuyen(ms_tuyen, mo_ta_tuyen);
                lsttuyen.add(tuyen);
            }

            ArrayAdapter<Tuyen> adapter = new ArrayAdapter<Tuyen>(this, android.R.layout.simple_spinner_item, lsttuyen);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spituyen.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

   /* private class HttpAsyncTaskGetDiemDung extends AsyncTask<String, JSONObject, Void> {

        ProgressDialog progressDialog = new ProgressDialog(KiemSoat.this);
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Nạp dữ liệu...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {

            String url = params[0];
            JSONArray jsonArrayKH;
            listkh = new ArrayList<>();

            try {
                jsonArrayKH = ReadJson.readJSonArrayFromURL(url);
                for (int i = 0; i < jsonArrayKH.length(); i++) {


                    DuLieuKhachHang ttkh;
                    JSONObject objTTTT = jsonArrayKH.getJSONObject(i);
                    Integer ms_chinhanh =objTTTT.getInt("ms_chinhanh");
                    Integer ms_tql =objTTTT.getInt("ms_tql");
                    String ten_tql = objTTTT.getString("ten_tql");
                    Integer ms_tuyen = objTTTT.getInt("ms_tuyen");
                    Integer ms_cky =objTTTT.getInt("ms_cky");
                    Integer ms_dh_mn =objTTTT.getInt("ms_dh_mn");
                    Integer ms_tk = objTTTT.getInt("ms_tk");
                    Integer stt_lo_trinh = objTTTT.getInt("stt_lo_trinh");
                    String so_seri = objTTTT.getString("so_seri");
                    Integer ms_mnoi =objTTTT.getInt("ms_mnoi");
                    String nguoi_thue = objTTTT.getString("nguoi_thue");
                    //Integer ms_dh = objTTTT.getInt("ms_dh");
                    String dia_chi = objTTTT.getString("diachi");
                    Integer ms_md_chinh = objTTTT.getInt("ms_md_chinh");
                    String gia_chinh = objTTTT.getString("gia_chinh");
                    Integer ms_gia_vuot = objTTTT.getInt("ms_gia_vuot");
                    String gia_vuot = objTTTT.getString("gia_vuot");
                    String ngay_thuc_te_str = common.cat10kitucuoi(objTTTT.getString("ngay_thuc_te"));
                    String ngay_doc_cu_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_cu"));
                    String ngay_doc_moi_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_moi"));
                    Integer chi_so_cu = objTTTT.getInt("chi_so_cu");
                    Integer chi_so_moi = objTTTT.getInt("chi_so_moi");
                    Integer STT1 = objTTTT.getInt("STT1");
                    Integer STT2 = objTTTT.getInt("STT2");
                    Integer STT3 = objTTTT.getInt("STT3");
                    Integer STT4 = objTTTT.getInt("STT4");
                    Integer ms_userthanhtra = objTTTT.getInt("ms_userthanhtra");
                    Integer ttkiemsoat = objTTTT.getInt("ttkiemsoat");
                    Integer ms_dk_ks = objTTTT.getInt("ms_dk_ks");
                    String ten_dk_ks = objTTTT.getString("ten_dk_ks");
                    Integer ms_ttrang = objTTTT.getInt("ms_ttrang");
                    String mo_ta_ttrang = objTTTT.getString("mo_ta_ttrang");
                    String mo_ta_tuyen = objTTTT.getString("mo_ta_tuyen");
                    String dien_thoai = objTTTT.getString("dien_thoai");
                    String ghi_chu = objTTTT.getString("ghi_chu");
                    //if (null != objTTTT.getString("dien_thoai") || "".equals(objTTTT.getString("dien_thoai"))) {
                    //   dien_thoai = objTTTT.getString("dien_thoai");
                    //}

                    //Integer ms_ttrang_doc = objTTTT.getInt("ms_ttrang_doc");
                    //Integer ms_tt_dd = objTTTT.getInt("ms_tt_dd");
                    //String ghi_chu = objTTTT.getString("ghi_chu");
                    //String url_image = objTTTT.getString("url_image");


                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                    Date ngay_thuc_te = null;
                    Date ngay_doc_cu = null;
                    Date ngay_doc_moi = null;
                    try {
                        ngay_thuc_te = format1.parse(ngay_thuc_te_str);
                        ngay_doc_cu = format1.parse(ngay_doc_cu_str);
                        ngay_doc_moi = format1.parse(ngay_doc_moi_str);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    ttkh = new DuLieuKhachHang( ms_chinhanh,  ms_tql,  ten_tql,  ms_tuyen,  ms_cky,  ms_dh_mn,  ms_tk,  stt_lo_trinh,  so_seri,  ms_mnoi,
                            nguoi_thue,  dia_chi,  ms_md_chinh,  gia_chinh,  ms_gia_vuot,  gia_vuot,  ngay_thuc_te,  ngay_doc_cu,  ngay_doc_moi,  chi_so_cu, chi_so_moi,
                            STT1,  STT2,  STT3,  STT4,  ms_userthanhtra,  ttkiemsoat,  ms_dk_ks, ten_dk_ks, ms_ttrang, mo_ta_ttrang, mo_ta_tuyen, dien_thoai, ghi_chu ) ;
                    listkh.add(ttkh);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            adapter = new KhachHangAdapter(KiemSoat.this, listkh);
            adapter.notifyDataSetChanged();
            lstpro.setAdapter(adapter);
            lstpro.invalidateViews();
            lstpro.refreshDrawableState();
            progressDialog.hide();
            progressDialog.dismiss();
        }
    }*/

    /*private class HttpAsyncTaskGetDiemDung extends AsyncTask<String, JSONObject, Void> {

        ProgressDialog progressDialog = new ProgressDialog(KiemSoat.this);
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Nạp dữ liệu...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {

            String url = params[0];
            JSONArray jsonArrayKH;
            String dien_thoai = "";
            listkh = new ArrayList<>();

            try {
                jsonArrayKH = ReadJson.readJSonArrayFromURL(url);
                for (int i = 0; i < jsonArrayKH.length(); i++) {


                    ThongTinKhachHang ttkh;
                    JSONObject objTTTT = jsonArrayKH.getJSONObject(i);
                    Integer ms_mnoi =objTTTT.getInt("ms_mnoi");
                    Integer stt_lo_trinh = objTTTT.getInt("stt_lo_trinh");
                    String nguoi_thue = objTTTT.getString("nguoi_thue");
                    Integer ms_dh = objTTTT.getInt("ms_dh");
                    String dia_chi = objTTTT.getString("dia_chi");
                    String so_seri = objTTTT.getString("so_seri");
                    Integer chi_so_cu = objTTTT.getInt("chi_so_cu");
                    Integer chi_so_moi = objTTTT.getInt("chi_so_moi");
                    Integer so_tthu = objTTTT.getInt("so_tthu");
                    String ngay_doc_cu_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_cu"));
                    String ngay_doc_moi_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_moi"));
                    Integer so_ho = objTTTT.getInt("so_ho");
                    if (null != objTTTT.getString("dien_thoai") || "".equals(objTTTT.getString("dien_thoai"))) {
                        dien_thoai = objTTTT.getString("dien_thoai");
                    }
                    Integer ms_tuyen = objTTTT.getInt("ms_tuyen");
                    Integer ms_tk = objTTTT.getInt("ms_tk");
                    Integer ms_ttrang_doc = objTTTT.getInt("ms_ttrang_doc");
                    Integer ms_tt_dd = objTTTT.getInt("ms_tt_dd");
                    String ghi_chu = objTTTT.getString("ghi_chu");
                    String url_image = objTTTT.getString("url_image");


                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                    Date ngay_doc_cu = null;
                    Date ngay_doc_moi = null;
                    try {
                        ngay_doc_cu = format1.parse(ngay_doc_cu_str);
                        ngay_doc_moi = format1.parse(ngay_doc_moi_str);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    ttkh = new ThongTinKhachHang( ms_mnoi,  stt_lo_trinh,  nguoi_thue,  dia_chi,  ms_dh,  so_seri,  chi_so_cu,  chi_so_moi,  so_tthu,
                             ngay_doc_cu,  ngay_doc_moi,  so_ho,  dien_thoai,  ms_tuyen,  ms_tk,  ms_ttrang_doc,  ms_tt_dd,  ghi_chu,  url_image) ;
                    listkh.add(ttkh);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            adapter = new KhachHangAdapter(KiemSoat.this, listkh);
            adapter.notifyDataSetChanged();
            lstpro.setAdapter(adapter);
            lstpro.invalidateViews();
            lstpro.refreshDrawableState();
            progressDialog.hide();
            progressDialog.dismiss();
        }
    }*/


    public void getSelectedChiNhanh() {
        ChiNhanh cn = (ChiNhanh) spichinhanh.getSelectedItem();
        Toast.makeText(KiemSoat.this, cn.ten_chinhanh, Toast.LENGTH_LONG).show();

    }




    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }



}
