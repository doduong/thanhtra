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

import com.example.mac.appproject_moneymanager.Actions.KiemSoat;
import com.example.mac.appproject_moneymanager.Actions.NhapThongTinKiemSoat;
import com.example.mac.appproject_moneymanager.Actions.ScannerActivity;
import com.example.mac.appproject_moneymanager.Actions.TabActivity;
import com.example.mac.appproject_moneymanager.R;
import com.example.mac.appproject_moneymanager.model.DuLieuKhachHang;
import com.example.mac.appproject_moneymanager.model.ToQuanLyModel;
import com.example.mac.appproject_moneymanager.utils.CommonText;
import com.example.mac.appproject_moneymanager.utils.ReadJson;
import com.example.mac.appproject_moneymanager.utils.SharedPref;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tab1 extends Fragment implements DecoratedBarcodeView.TorchListener {

    private View mRootView;
    Spinner spitieuchi;
    Spinner spichinhanh;
    Spinner spitoquanly;
    Spinner spituyen;
    EditText edtdanhba;
    EditText edtSearchName;
    EditText edtinputdb;
    Button btntimkiemkoloc;
    TextView tvslcks;
    Spinner spidstimkiem;

    Button btntimkiem;
    Button btnquetmavach;
    ListView lstpro;
    CommonText common;
    SharedPref config;
    String ms_userthanhtra="";
    String ms_tk = "";
    int positionloaitk = 0;
    String ms_tql = "";
    ArrayList<DuLieuKhachHang> listkh = new ArrayList<>();
    private KhachHangAdapter adapter;
    String keysearch = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_kiem_soat, container, false);
        config = new SharedPref(getActivity());
        ms_userthanhtra = config.getString("ms_userthanhtra", "");
        ms_tk = config.getString("ms_tk","");
        ms_tql = config.getString("ms_tql", "");

        initView();
        return mRootView;
    }

    public void initView(){
        common = new CommonText();
        lstpro = (ListView) mRootView.findViewById(R.id.lstpro);
        edtdanhba = (EditText) mRootView.findViewById(R.id.edtdanhba);
        //spitoquanly = (Spinner) mRootView.findViewById(R.id.spitql);
        edtSearchName = (EditText) mRootView.findViewById(R.id.edtsearchname);
        spidstimkiem = (Spinner) mRootView.findViewById(R.id.spidstimkiem);

        btntimkiem= (Button) mRootView.findViewById(R.id.btnsearch);
        btnquetmavach = (Button) mRootView.findViewById(R.id.btnquetmavach);
        final EditText edtinputdb = (EditText) mRootView.findViewById(R.id.edtinputdb);
        Button btntimkiemkoloc = (Button) mRootView.findViewById(R.id.btntimkiemkoloc);
        tvslcks = (TextView) mRootView.findViewById(R.id.tvslcks);
        //loadSpiner();
        //ToQuanLy tql = (ToQuanLy) spitoquanly.getSelectedItem();
        //getThongTinDiemDung(tql.getMs_tql());
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


        getThongTinDiemDung(Integer.parseInt(ms_tql));


        btntimkiem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                keysearch = edtSearchName.getText().toString();
                searchUsePointNotControl(keysearch, positionloaitk);
            }


        });

        btnquetmavach.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setBeepEnabled(false);
                integrator.forSupportFragment(Tab1.this).setCaptureActivity(ScannerActivity.class).initiateScan();

            }


        });

        btntimkiemkoloc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ms_db = edtinputdb.getText().toString();
                int lengtext = ms_db.length();
                if(lengtext == 7) {
                    if (isConnected()) {
                        //ToQuanLyModel tql = (ToQuanLyModel) spitoquanly.getSelectedItem();
                        String url = common.URL_API + "/khachhang/getquetmavach?ms_mnoi=" + ms_db + "&ms_tk=" + ms_tk + "&ms_tql=" + ms_tql + "&ms_userthanhtra=" + ms_userthanhtra;
                        Log.d("GetCustomerInfo", url);
                        new HttpAsyncTaskGetDiemDungQuetMaVach().execute(url);

                    } else {

                        Toast.makeText(getActivity(), "Chưa kết nối internet", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Không đúng mã danh bạ", Toast.LENGTH_LONG).show();
                }

            }


        });

        lstpro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isConnected()) {
                    if(listkh.size()>0) {
                        DuLieuKhachHang ttkh = listkh.get(i);
                        Intent intent = new Intent(getActivity(), NhapThongTinKiemSoat.class);
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

    public void searchUsePointNotControl(String keysearch, int conditionfield) {
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
                String url = common.URL_API + "/khachhang/timkiemdskhtheotql?ms_tql=" + ms_tql + keywrord + field;
                Log.d("SearchUsePointNotRead", url);
                new HttpAsyncTaskGetDiemDung().execute(url);
            }else  {
                Toast.makeText(getActivity(), "Chưa kết nối internet", Toast.LENGTH_LONG).show();
            }
        } else {
            getThongTinDiemDung(Integer.parseInt(ms_tql));
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getActivity(), "Scan được hủy bỏ", Toast.LENGTH_LONG).show();
            } else {
                Log.d("showResultDialogue:", result.getContents());
                showResultDialogue(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    String ms_db = "";


    public void showResultDialogue(final String resultscan) {


        ms_db = common.strimBarcode(resultscan);
        if (isConnected()) {
            //ToQuanLyModel tql = (ToQuanLyModel) spitoquanly.getSelectedItem();
            String url = common.URL_API + "/khachhang/getquetmavach?ms_mnoi=" + ms_db + "&ms_tk=" +ms_tk+"&ms_tql="+ms_tql+"&ms_userthanhtra="+ms_userthanhtra;
            Log.d("GetCustomerInfo", url);
            new HttpAsyncTaskGetDiemDungQuetMaVach().execute(url);

        } else {

            Toast.makeText(getActivity(), "Chưa kết nối internet", Toast.LENGTH_LONG).show();
        }



    }

    private class HttpAsyncTaskGetDiemDungQuetMaVach extends AsyncTask<String, JSONObject, Void> {

        //ProgressDialog progressDialog = new ProgressDialog(getActivity());
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog.setMessage("Nạp dữ liệu...");
            //progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {

            String url = params[0];
            JSONArray jsonArrayKH;
            listkh = new ArrayList<>();

            try {
                jsonArrayKH = ReadJson.readJSonArrayFromURL(url);
                if(jsonArrayKH.length()>0) {
                    for (int i = 0; i < jsonArrayKH.length(); i++) {


                        DuLieuKhachHang ttkh;
                        JSONObject objTTTT = jsonArrayKH.getJSONObject(i);
                        Integer ms_chinhanh = objTTTT.getInt("ms_chinhanh");
                        Integer ms_tql = objTTTT.getInt("ms_tql");
                        String ten_tql = objTTTT.getString("ten_tql");
                        Integer ms_tuyen = objTTTT.getInt("ms_tuyen");
                        Integer ms_cky = objTTTT.getInt("ms_cky");
                        Integer ms_dh_mn = objTTTT.getInt("ms_dh_mn");
                        Integer ms_tk = objTTTT.getInt("ms_tk");
                        Integer stt_lo_trinh = objTTTT.getInt("stt_lo_trinh");
                        String so_seri = objTTTT.getString("so_seri");
                        Integer ms_mnoi = objTTTT.getInt("ms_mnoi");
                        String nguoi_thue = objTTTT.getString("nguoi_thue");
                        //Integer ms_dh = objTTTT.getInt("ms_dh");
                        String dia_chi = objTTTT.getString("diachi");
                        Integer ms_md_chinh = objTTTT.getInt("ms_md_chinh");
                        String gia_chinh = objTTTT.getString("gia_chinh");
                        String str_ms_gia_vuot = common.GetDataToValue(objTTTT.getString("ms_gia_vuot"), "");
                        Integer ms_gia_vuot = null;
                        if (!str_ms_gia_vuot.equals("")) {
                            ms_gia_vuot = objTTTT.getInt("ms_gia_vuot");
                        }
                        String gia_vuot = objTTTT.getString("gia_vuot");
                        String ngay_thuc_te_str = common.GetDataToValue(objTTTT.getString("ngay_thuc_te"),"");
                        String ngay_doc_cu_str = common.GetDataToValue(objTTTT.getString("ngay_doc_cu"),"");
                        String str = common.GetDataToValue(objTTTT.getString("ngay_doc_moi"),"");
                        String ngay_doc_moi_str = "";
                        if(!str.equals("")) {
                            ngay_doc_moi_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_moi"));
                        }
                        if(!ngay_doc_cu_str.equals("")) {
                            ngay_doc_cu_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_cu"));
                        }
                        if(!ngay_thuc_te_str.equals("")) {
                            ngay_thuc_te_str = common.cat10kitucuoi(objTTTT.getString("ngay_thuc_te"));
                        }
                        Integer chi_so_cu = null;
                        String str_ssc = common.GetDataToValue(objTTTT.getString("chi_so_cu"), "");
                        if (!"".equals(str_ssc)) {
                            chi_so_cu = objTTTT.getInt("chi_so_cu");
                        }

                        Integer chi_so_moi = null;
                        String str_csm = common.GetDataToValue(objTTTT.getString("chi_so_moi"), "");
                        if (!"".equals(str_csm)) {
                            chi_so_moi = objTTTT.getInt("chi_so_moi");
                        }
                        //Integer chi_so_moi = objTTTT.getInt("chi_so_moi");
                        String str_STT1 = common.GetDataToValue(objTTTT.getString("STT1"), "");
                        String str_STT2 = common.GetDataToValue(objTTTT.getString("STT2"), "");
                        String str_STT3 = common.GetDataToValue(objTTTT.getString("STT3"), "");
                        String str_STT4 = common.GetDataToValue(objTTTT.getString("STT4"), "");
                        Integer STT1 = null;
                        if (!"".equals(str_STT1)) {
                            STT1 = objTTTT.getInt("STT1");
                        }
                        Integer STT2 = null;
                        if (!"".equals(str_STT2)) {
                            STT2 = objTTTT.getInt("STT2");
                        }
                        Integer STT3 = null;
                        if (!"".equals(str_STT3)) {
                            STT3 = objTTTT.getInt("STT3");
                        }
                        Integer STT4 = null;
                        if (!"".equals(str_STT4)) {
                            STT4 = objTTTT.getInt("STT4");
                        }
                        //String str_STT4 = common.GetDataToValue(objTTTT.getString("STT4"),"");
                        //if()
                        Integer ms_userthanhtra1 = Integer.parseInt(ms_userthanhtra);
                        Integer ttkiemsoat = 0;


                        Integer ms_dk_ks = 10;
                        String ten_dk_ks = "ĐT bất thường";
                        String ms_ttrang_str = common.GetDataToValue(objTTTT.getString("ms_ttrang"), "");
                        Integer ms_ttrang =null;
                        if(!"".equals(ms_ttrang_str)){
                             ms_ttrang = objTTTT.getInt("ms_ttrang");
                        }
                        String mo_ta_ttrang = objTTTT.getString("mo_ta_ttrang");
                        String mo_ta_tuyen = objTTTT.getString("mo_ta_tuyen");
                        String dien_thoai = objTTTT.getString("dien_thoai");
                        String ghi_chu = objTTTT.getString("ghi_chu");


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


                        ttkh = new DuLieuKhachHang(ms_chinhanh, ms_tql, ten_tql, ms_tuyen, ms_cky, ms_dh_mn, ms_tk, stt_lo_trinh, so_seri, ms_mnoi,
                                nguoi_thue, dia_chi, ms_md_chinh, gia_chinh, ms_gia_vuot, gia_vuot, ngay_thuc_te, ngay_doc_cu, ngay_doc_moi, chi_so_cu, chi_so_moi,
                                STT1, STT2, STT3, STT4, ms_userthanhtra1, ttkiemsoat, ms_dk_ks, ten_dk_ks, ms_ttrang, mo_ta_ttrang, mo_ta_tuyen, dien_thoai, ghi_chu,null,null);
                        listkh.add(ttkh);
                    }
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
            if(listkh.size()>0) {
                adapter = new KhachHangAdapter(getActivity(), listkh);
                adapter.notifyDataSetChanged();
                lstpro.setAdapter(adapter);
                lstpro.invalidateViews();
                lstpro.refreshDrawableState();
            }else {
                Toast.makeText(getActivity(), "Không tồn tại điểm dùng trên", Toast.LENGTH_LONG).show();
            }
            //progressDialog.hide();
            //progressDialog.dismiss();
        }
    }

    public void getThongTinDiemDung(int ms_tql) {

        if (isConnected()) {

            String url = common.URL_API + "/khachhang/getdskhtheotql?ms_tql=" + ms_tql+"&ms_userthanhtra="+ms_userthanhtra;
            new HttpAsyncTaskGetDiemDung().execute(url);

        } else {

            Toast.makeText(getActivity(), "Chưa kết nối internet", Toast.LENGTH_LONG).show();
        }

    }

    /*private void loadSpiner(){
        setSpinnerToQuanLy();
    }
    private void setSpinnerToQuanLy(){

        String url = common.URL_API + "/khachhang/gettoquanly"+"?ms_userthanhtra="+ms_userthanhtra;

        List<ToQuanLyModel> lsttql =  new ArrayList<>();
        JSONArray lstjson;
        try {
            lstjson = ReadJson.readJSonArrayFromURL(url);
            for (int i = 0; i < lstjson.length(); i++) {
                ToQuanLyModel tql;
                JSONObject objCN = lstjson.getJSONObject(i);
                int ms_tql = objCN.getInt("ms_tql");
                String ten_tql = objCN.getString("ten_tql");
                tql = new ToQuanLyModel(ms_tql, ten_tql);
                lsttql.add(tql);
            }

            ArrayAdapter<ToQuanLyModel> adapter = new ArrayAdapter<ToQuanLyModel>(getActivity(), android.R.layout.simple_spinner_item, lsttql);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spitoquanly.setAdapter(adapter);
            //ToQuanLy tql = (ToQuanLy) spitoquanly.getSelectedItem();
            //getThongTinDiemDung(tql.getMs_tql());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    private class HttpAsyncTaskGetDiemDung extends AsyncTask<String, JSONObject, Void> {

        //ProgressDialog progressDialog = new ProgressDialog(getActivity());
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog.setMessage("Nạp dữ liệu...");
            //progressDialog.show();
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
                    String str_ms_dh_mn = common.GetDataToValue(objTTTT.getString("ms_dh_mn"),"");
                    Integer ms_dh_mn = null;
                    if(!str_ms_dh_mn.equals("")){
                        ms_dh_mn = objTTTT.getInt("ms_dh_mn");
                    }
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
                    String ngay_doc_cu_str = common.GetDataToValue(objTTTT.getString("ngay_doc_cu"),"");
                    String str = common.GetDataToValue(objTTTT.getString("ngay_doc_moi"),"");
                    String ngay_doc_moi_str = "";
                    if(!str.equals("")) {
                         ngay_doc_moi_str = common.cat10kitucuoi(objTTTT.getString("ngay_doc_moi"));
                    }
                    if(!ngay_doc_cu_str.equals("")){
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
                        chi_so_moi =  objTTTT.getInt("chi_so_moi");
                    }
                    //Integer chi_so_moi = objTTTT.getInt("chi_so_moi");
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
                    //String str_STT4 = common.GetDataToValue(objTTTT.getString("STT4"),"");
                    //if()
                    Integer ms_userthanhtra = objTTTT.getInt("ms_userthanhtra");
                    String str_ttks = objTTTT.getString("ttkiemsoat");
                    Integer ttkiemsoat = 0;
                    if("true".equals(str_ttks)){
                        ttkiemsoat = 1;
                    }

                    Integer ms_dk_ks = objTTTT.getInt("ms_dk_ks");
                    String ten_dk_ks = objTTTT.getString("ten_dk_ks");
                    String ms_ttrang_str = common.GetDataToValue(objTTTT.getString("ms_ttrang"),"");
                    Integer ms_ttrang = null;
                    if(!"".equals(ms_ttrang_str)){
                        ms_ttrang = objTTTT.getInt("ms_ttrang");
                    }
                    String mo_ta_ttrang = objTTTT.getString("mo_ta_ttrang");
                    String mo_ta_tuyen = objTTTT.getString("mo_ta_tuyen");
                    String dien_thoai = objTTTT.getString("dien_thoai");
                    String ghi_chu = objTTTT.getString("ghi_chu");
                    String mucdichsudung = common.GetDataToValue(objTTTT.getString("mucdichsudung"),"");
                    String ngay_loc_str = common.GetDataToValue(objTTTT.getString("ngay_loc"),"");
                    if(!ngay_loc_str.equals("")){
                        ngay_loc_str = common.cat10kitucuoi(objTTTT.getString("ngay_loc"));
                    }

                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                    Date ngay_thuc_te = null;
                    Date ngay_doc_cu = null;
                    Date ngay_doc_moi = null;
                    Date ngay_loc = null;
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
                        if(!"".equals(ngay_loc_str)) {
                            ngay_loc = format1.parse(ngay_loc_str);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    ttkh = new DuLieuKhachHang( ms_chinhanh,  ms_tql,  ten_tql,  ms_tuyen,  ms_cky,  ms_dh_mn,  ms_tk,  stt_lo_trinh,  so_seri,  ms_mnoi,
                            nguoi_thue,  dia_chi,  ms_md_chinh,  gia_chinh,  ms_gia_vuot,  gia_vuot,  ngay_thuc_te,  ngay_doc_cu,  ngay_doc_moi,  chi_so_cu, chi_so_moi,
                            STT1,  STT2,  STT3,  STT4,  ms_userthanhtra,  ttkiemsoat,  ms_dk_ks, ten_dk_ks, ms_ttrang, mo_ta_ttrang, mo_ta_tuyen, dien_thoai, ghi_chu, mucdichsudung, ngay_loc ) ;
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
            adapter = new KhachHangAdapter(getActivity(), listkh);
            adapter.notifyDataSetChanged();
            lstpro.setAdapter(adapter);
            lstpro.invalidateViews();
            lstpro.refreshDrawableState();
            tvslcks.setText("Số lượng: " + listkh.size());
            //progressDialog.hide();
            //progressDialog.dismiss();
        }
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }




    @Override
    public void onTorchOn() {

    }

    @Override
    public void onTorchOff() {

    }
}
