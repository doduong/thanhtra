package com.example.mac.appproject_moneymanager.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.appproject_moneymanager.Actions.HienThiThongTinKiemSoat;
import com.example.mac.appproject_moneymanager.Actions.NhapThongTinKiemSoat;
import com.example.mac.appproject_moneymanager.R;
import com.example.mac.appproject_moneymanager.model.DuLieuKhachHang;
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

public class Tab2 extends Fragment {

    private View mRootView;
    EditText edtSearchName;
    Spinner spidstimkiem;
    EditText edtdanhba;
    TextView tvtsldks;
    Button btntimkiem;
    ListView lstpro;
    CommonText common;
    SharedPref config;
    String ms_userthanhtra="";
    ArrayList<DuLieuKhachHang> listkh = new ArrayList<>();
    private KhachHangDaDocAdapter adapter;
    int positionloaitk = 0;
    String  ms_tql = "";
    String keysearch = "";
    String ms_tk = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_tab2, container, false);
        config = new SharedPref(getActivity());
        ms_userthanhtra = config.getString("ms_userthanhtra", "");
        ms_tql = config.getString("ms_tql", "");
        ms_tk = config.getString("ms_tk","");
        initView();
        return mRootView;
    }

    public void initView(){

        common = new CommonText();
        lstpro = (ListView) mRootView.findViewById(R.id.lstpro);
        edtdanhba = (EditText) mRootView.findViewById(R.id.edtdanhba);
        edtSearchName = (EditText) mRootView.findViewById(R.id.edtsearchname);
        spidstimkiem = (Spinner) mRootView.findViewById(R.id.spidstimkiem);
        tvtsldks = (TextView) mRootView.findViewById(R.id.tvtsldks);

        btntimkiem= (Button) mRootView.findViewById(R.id.btnsearch);
        ArrayList<String> listTT1 = new ArrayList<String>();
        listTT1.add("Danh bạ");
        listTT1.add("Tên");
        listTT1.add("Địa chỉ");
        listTT1.add("Seri ĐH");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listTT1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spidstimkiem.setAdapter(dataAdapter1);
        spidstimkiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionloaitk = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getdanhsachkkiemsoatbathuong(Integer.parseInt(ms_tk));


        btntimkiem.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {


                keysearch = edtSearchName.getText().toString();
                searchUsePointControled(keysearch, positionloaitk);

            }


        });

        lstpro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isConnected()) {
                    if(listkh.size()>0) {
                        DuLieuKhachHang ttkh = listkh.get(i);
                        Intent intent = new Intent(getActivity(), HienThiThongTinKiemSoat.class);
                        //intent.putExtra("arrlist", listkh);
                        intent.putExtra("ttkh", ttkh);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getActivity(), "Không có dữ liệu kiểm soát!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Không có kết nối internet", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void searchUsePointControled(String keysearch, int conditionfield) {
        String field = "";
        String keywrord = "&keyWord=" + keysearch;
        if (conditionfield == 0) {
            field = "&conditionField=ms_mnoi";
        } else if (conditionfield == 1) {
            field = "&conditionField=nguoi_thue";
        } else if (conditionfield == 2) {
            field = "&conditionField=diachi";
        } else if (conditionfield == 3) {
            field = "&conditionField=so_seri";
        }
        if (!"".equals(keysearch)) {
            if(isConnected()) {
                //SearchUsePointWereRead?ms_tuyen=5115&ms_tk=247&keyWord=%22%22&conditionField=customer'
                String url = common.URL_API + "/khachhang/timkiemdskhdakstheotql?ms_tk=" +ms_tk+"&ms_tql="+ms_tql+"&ms_userthanhtra="+ms_userthanhtra + keywrord + field;
                Log.d("timkiemdskhdakstheotql", url);
                GetDiemDungBatThuongDaKS(url);
            }else  {
                Toast.makeText(getActivity(), "Chưa kết nối internet", Toast.LENGTH_LONG).show();
            }
        } else {
            getdanhsachkkiemsoatbathuong(Integer.parseInt(ms_tk));
        }

    }

    public void getdanhsachkkiemsoatbathuong(Integer ms_tk){
        if (isConnected()) {
            //ToQuanLyModel tql = (ToQuanLyModel) spitoquanly.getSelectedItem();
            String url = common.URL_API + "/khachhang/getksbatthuong?ms_tk=" +ms_tk+"&ms_tql="+ms_tql+"&ms_userthanhtra="+ms_userthanhtra;
            Log.d("GetCustomerInfo", url);
            GetDiemDungBatThuongDaKS(url);

        } else {

            Toast.makeText(getActivity(), "Chưa kết nối internet", Toast.LENGTH_LONG).show();
        }
    }

    private void GetDiemDungBatThuongDaKS(String url) {

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
                    String ms_dh_mn_str = common.GetDataToValue(objTTTT.getString("ms_dh_mn"),"");
                    Integer ms_dh_mn = null;
                    if(!ms_dh_mn_str.equals("")){
                        ms_dh_mn = objTTTT.getInt("ms_dh_mn");
                    }
                    String ms_tk_str = common.GetDataToValue(objTTTT.getString("ms_tk"),"");
                    Integer ms_tk = null;
                    if(!ms_tk_str.equals("")){
                        ms_tk = objTTTT.getInt("ms_tk");
                    }

                    String stt_lo_trinh_str = common.GetDataToValue(objTTTT.getString("stt_lo_trinh"),"");
                    Integer stt_lo_trinh = null;
                    if(!stt_lo_trinh_str.equals("")){
                        stt_lo_trinh = objTTTT.getInt("stt_lo_trinh");
                    }

                    String so_seri = common.GetDataToValue(objTTTT.getString("so_seri"),"");
                    Integer ms_mnoi =objTTTT.getInt("ms_mnoi");
                    String nguoi_thue = objTTTT.getString("nguoi_thue");
                    //Integer ms_dh = objTTTT.getInt("ms_dh");
                    String dia_chi = objTTTT.getString("diachi");
                    Integer ms_md_chinh = objTTTT.getInt("ms_md_chinh");
                    String gia_chinh = objTTTT.getString("gia_chinh");
                    String str_ms_gia_vuot = common.GetDataToValue(objTTTT.getString("ms_gia_vuot"),"");
                    Integer ms_gia_vuot = null;
                    if(!str_ms_gia_vuot.equals("")){
                        ms_gia_vuot = objTTTT.getInt("ms_gia_vuot");
                    }
                    String gia_vuot = objTTTT.getString("gia_vuot");
                    String ngay_thuc_te_str = common.GetDataToValue(objTTTT.getString("ngay_thuc_te"),"");
                    if(!"".equals(ngay_thuc_te_str)){
                        ngay_thuc_te_str = common.cat10kitucuoi(objTTTT.getString("ngay_thuc_te"));
                    }
                    String str = common.GetDataToValue(objTTTT.getString("ngay_doc_moi"),"");

                    String ngay_doc_moi_str = "";

                    if(!str.equals("")) {
                        ngay_doc_moi_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_moi"));
                    }
                    String ngay_doc_cu_str = common.GetDataToValue(objTTTT.getString("ngay_doc_cu"),"");
                    if(!"".equals(ngay_doc_cu_str)){
                        ngay_doc_cu_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_cu"));
                    }

                    Integer chi_so_cu = null;
                    String str_ssc = common.GetDataToValue(objTTTT.getString("chi_so_cu"),"");
                    if(!"".equals(str_ssc)){
                        chi_so_cu = objTTTT.getInt("chi_so_cu");
                    }

                    Integer chi_so_moi = null;
                    String str_csm = common.GetDataToValue(objTTTT.getString("chi_so_moi"),"");
                    if(!"".equals(str_csm)){
                        chi_so_moi = objTTTT.getInt("chi_so_moi");
                    }
                    String str_STT1 = common.GetDataToValue(objTTTT.getString("STT1"),"");
                    String str_STT2 = common.GetDataToValue(objTTTT.getString("STT2"),"");
                    String str_STT3 = common.GetDataToValue(objTTTT.getString("STT3"),"");
                    String str_STT4 = common.GetDataToValue(objTTTT.getString("STT4"),"");
                    Integer STT1 = null;
                    if(!"".equals(str_STT1)){
                        STT1 = objTTTT.getInt("STT1");
                    }
                    Integer STT2 = null;
                    if(!"".equals(str_STT2)){
                        STT2 = objTTTT.getInt("STT2");
                    }
                    Integer STT3 = null;
                    if(!"".equals(str_STT3)){
                        STT3 = objTTTT.getInt("STT3");
                    }
                    Integer STT4 = null;
                    if(!"".equals(str_STT4)){
                        STT4 = objTTTT.getInt("STT4");
                    }
                    Integer ms_userthanhtra1 = Integer.parseInt(ms_userthanhtra);
                    //String str_ttks = objTTTT.getString("ttkiemsoat");

                     Integer   ttkiemsoat = 1;

                    Integer ms_dk_ks = objTTTT.getInt("ms_dk_ks");
                    String ten_dk_ks = objTTTT.getString("ten_dk_ks");
                    String ms_ttrang_str= common.GetDataToValue(objTTTT.getString("ms_ttrang"),"");
                    Integer ms_ttrang = null;
                    if(!"".equals(ms_ttrang_str)) {
                         ms_ttrang = objTTTT.getInt("ms_ttrang");
                    }
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
                        if(!"".equals(ngay_thuc_te_str)) {
                            ngay_thuc_te = format1.parse(ngay_thuc_te_str);
                        }
                        if(!"".equals(ngay_doc_cu_str)) {
                            ngay_doc_cu = format1.parse(ngay_doc_cu_str);
                        }
                        if(!"".equals(ngay_doc_moi_str)) {
                            ngay_doc_moi = format1.parse(ngay_doc_moi_str);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    ttkh = new DuLieuKhachHang( ms_chinhanh,  ms_tql,  ten_tql,  ms_tuyen,  ms_cky,  ms_dh_mn,  ms_tk,  stt_lo_trinh,  so_seri,  ms_mnoi,
                            nguoi_thue,  dia_chi,  ms_md_chinh,  gia_chinh,  ms_gia_vuot,  gia_vuot,  ngay_thuc_te,  ngay_doc_cu,  ngay_doc_moi,  chi_so_cu, chi_so_moi,
                            STT1,  STT2,  STT3,  STT4,  ms_userthanhtra1,  ttkiemsoat,  ms_dk_ks, ten_dk_ks, ms_ttrang, mo_ta_ttrang, mo_ta_tuyen, dien_thoai, ghi_chu, null, null ) ;
                    listkh.add(ttkh);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        tvtsldks.setText("Số lượng: " + listkh.size());
        adapter = new KhachHangDaDocAdapter(getActivity(), listkh);
        adapter.notifyDataSetChanged();
        lstpro.setAdapter(adapter);
        lstpro.invalidateViews();
        lstpro.refreshDrawableState();


    }

    /*public void getThongTinDiemDung(int ms_tql) {

        if (isConnected()) {

            String url = common.URL_API + "/khachhang/getdskhdakstheotql?ms_tql=" + ms_tql;
            new HttpAsyncTaskGetDiemDungDaKiemSoat().execute(url);

        } else {

            Toast.makeText(getActivity(), "Chưa kết nối internet", Toast.LENGTH_LONG).show();
        }

    }*/



    /*private class HttpAsyncTaskGetDiemDungDaKiemSoat extends AsyncTask<String, JSONObject, Void> {

      //  ProgressDialog progressDialog = new ProgressDialog(getActivity());
        protected void onPreExecute() {
            super.onPreExecute();
    //        progressDialog.setMessage("Nạp dữ liệu...");
//            progressDialog.show();
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
                    String str_ms_gia_vuot = common.GetDataToValue(objTTTT.getString("ms_gia_vuot"),"");
                    Integer ms_gia_vuot = null;
                    if(!str_ms_gia_vuot.equals("")){
                        ms_gia_vuot = objTTTT.getInt("ms_gia_vuot");
                    }
                    String gia_vuot = objTTTT.getString("gia_vuot");
                    String ngay_thuc_te_str = common.cat10kitucuoi(objTTTT.getString("ngay_thuc_te"));
                    String ngay_doc_cu_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_cu"));
                    String str = common.GetDataToValue(objTTTT.getString("ngay_doc_moi"),"");
                    String ngay_doc_moi_str = "";
                    if(!str.equals("")) {
                        ngay_doc_moi_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_moi"));
                    }
                    Integer chi_so_cu = null;
                    String str_ssc = common.GetDataToValue(objTTTT.getString("chi_so_cu"),"");
                    if(!"".equals(str_ssc)){
                        chi_so_cu = objTTTT.getInt("chi_so_cu");
                    }

                    Integer chi_so_moi = null;
                    String str_csm = common.GetDataToValue(objTTTT.getString("chi_so_moi"),"");
                    if(!"".equals(str_ssc)){
                        chi_so_moi = objTTTT.getInt("chi_so_moi");
                    }
                    String str_STT1 = common.GetDataToValue(objTTTT.getString("STT1"),"");
                    String str_STT2 = common.GetDataToValue(objTTTT.getString("STT2"),"");
                    String str_STT3 = common.GetDataToValue(objTTTT.getString("STT3"),"");
                    String str_STT4 = common.GetDataToValue(objTTTT.getString("STT4"),"");
                    Integer STT1 = null;
                    if(!"".equals(str_STT1)){
                        STT1 = objTTTT.getInt("STT1");
                    }
                    Integer STT2 = null;
                    if(!"".equals(str_STT2)){
                        STT2 = objTTTT.getInt("STT2");
                    }
                    Integer STT3 = null;
                    if(!"".equals(str_STT3)){
                        STT3 = objTTTT.getInt("STT2");
                    }
                    Integer STT4 = null;
                    if(!"".equals(str_STT4)){
                        STT4 = objTTTT.getInt("STT4");
                    }
                    Integer ms_userthanhtra = objTTTT.getInt("ms_userthanhtra");
                    String str_ttks = objTTTT.getString("ttkiemsoat");
                    Integer ttkiemsoat = 0;
                    if("true".equals(str_ttks)){
                        ttkiemsoat = 1;
                    }
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

                getdanhsachkkiemsoatbathuong(252);
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
            adapter = new KhachHangAdapter(getActivity(), listkh);
            adapter.notifyDataSetChanged();
            lstpro.setAdapter(adapter);
            lstpro.invalidateViews();
            lstpro.refreshDrawableState();
      //      progressDialog.hide();
      //      progressDialog.dismiss();
        }
    }*/

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


}
