package com.example.mac.appproject_moneymanager.Actions;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import static android.Manifest.permission.CALL_PHONE;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.appproject_moneymanager.Adapter.KhachHangAdapter;
import com.example.mac.appproject_moneymanager.Adapter.Tab1;
import com.example.mac.appproject_moneymanager.R;
import com.example.mac.appproject_moneymanager.model.DuLieuKhachHang;
import com.example.mac.appproject_moneymanager.model.LichSuDongHo;
import com.example.mac.appproject_moneymanager.model.ThongTinKiemSoat;
import com.example.mac.appproject_moneymanager.utils.BaseActivity;
import com.example.mac.appproject_moneymanager.utils.CommonText;
import com.example.mac.appproject_moneymanager.utils.ReadJson;
import com.example.mac.appproject_moneymanager.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HienThiThongTinKiemSoat extends BaseActivity {

    DuLieuKhachHang ttkh;
    TextView tvtentuyen;
    TextView tvSoLuongAnh;
    TextView tvdkks;

    EditText edtdanhba;
    EditText edtseridh;
    EditText edttennguoithue;
    EditText edtDiaChi;
    EditText edtSocu;
    EditText edtSomoi;
    EditText edtsoks;
    //EditText edtsottks;
    EditText edtTT3T;
    EditText edtngaydoccu;
    EditText edtngaydocmoi;
    EditText edtmdsdkh;
    EditText edtSDT;
    EditText edtngayks;
    EditText edtkieuthanhtoan;
    EditText edtghichu;

    Button btnLuuSDT;

    ImageView imgPhoto;
    String ba1;
    int soluonganh;
    CommonText common;
    SharedPref config;
    String ms_userthanhtra="";
    String ms_tk = "";
    String ms_tql = "";


    List<Bitmap> bitmaps = null;
    ThongTinKiemSoat ttks;
    String[] listStrImages = null;
    Bitmap selectedBitmap;
    private Uri photoUri;
    private final static int TAKE_PHOTO = 100;
    private final static String PHOTO_URI = "photoUri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_thong_tin_kiem_soat);
        config = new SharedPref(HienThiThongTinKiemSoat.this);
        ms_userthanhtra = config.getString("ms_userthanhtra", "");
        ms_tk = config.getString("ms_tk","");
        ms_tql = config.getString("ms_tql", "");
        initView();

        btnLuuSDT.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {

                String number=edtSDT.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                if (ContextCompat.checkSelfPermission(HienThiThongTinKiemSoat.this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
                //startActivity(callIntent);
            }
        });
    }

    private void initView() {
        common = new CommonText();
        Intent intent = getIntent();
        //final ArrayList<DuLieuKhachHang> arrTTCT = (ArrayList<DuLieuKhachHang>) intent.getSerializableExtra("arrlist");
        ttkh = (DuLieuKhachHang) intent.getSerializableExtra("ttkh");
        khaibaobien();
        displayInfoCustomer();
        getThongTinDiemDungDaKiemSoat(ttkh.getMs_mnoi());

    }

    private void displayInfoCustomer(){
        edtdanhba.setText(String.valueOf(ttkh.getMs_mnoi()));
        edtseridh.setText(ttkh.getSo_seri());
        edttennguoithue.setText(ttkh.getNguoi_thue());
        edtDiaChi.setText(ttkh.getDiachi());

        if(null != ttkh.getChi_so_cu()){
            Integer chi_so_cu = (int)ttkh.getChi_so_cu();
            edtSocu.setText(String.valueOf(chi_so_cu));
        }else {
            edtSocu.setText("");
        }
        if(null != ttkh.getChi_so_moi()) {
            edtSomoi.setText(String.valueOf(ttkh.getChi_so_moi()));
        }else {
            edtSomoi.setText("");
        }

        /*if(null != ttkh.getSTT4())        {
            edtsotieuthu.setText(String.valueOf(ttkh.getSTT4()));
        }else {
            edtsotieuthu.setText("");
        }*/
        String str_stt1;
        String str_stt2;
        String str_stt3;
        String str_stt4;
        if( null == ttkh.getSTT1()){
            str_stt1 = "*";
        }else {
            str_stt1 = String.valueOf(ttkh.getSTT1());
        }
        if( null == ttkh.getSTT2()){
            str_stt2 = "*";
        }else {
            str_stt2 = String.valueOf(ttkh.getSTT2());
        }
        if( null == ttkh.getSTT3()){
            str_stt3 = "*";
        }else {
            str_stt3 = String.valueOf(ttkh.getSTT3());
        }
        if( null == ttkh.getSTT4()){
            str_stt4 = "*";
        }else {
            str_stt4 = String.valueOf(ttkh.getSTT4());
        }
        edtTT3T.setText(String.valueOf(str_stt1+"-"+ str_stt2+"-"+str_stt3+"-"+str_stt4));
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        if(null!=ttkh.getNgay_doc_cu()) {
            edtngaydoccu.setText(format2.format(ttkh.getNgay_doc_cu()).toString());
        }else {
            edtngaydoccu.setText("");
        }

        if(null!=ttkh.getNgay_doc_moi()) {
            edtngaydocmoi.setText(format2.format(ttkh.getNgay_doc_moi()).toString());
        }else {
            edtngaydocmoi.setText("");
        }
        if("null".equals(ttkh.getGia_vuot())) {
            edtmdsdkh.setText(String.valueOf(ttkh.getGia_chinh()));
        }else {
            edtmdsdkh.setText(String.valueOf(ttkh.getGia_chinh() + ttkh.getGia_vuot()));
        }
        tvtentuyen.setText(ttkh.getMo_ta_tuyen());
        edtSDT.setText(ttkh.getDien_thoai());
        tvdkks.setText(ttkh.getTen_dk_ks());
        //trường hợp đồng hồ treo
        if(ttkh.getMs_dk_ks() == 8){
            LichSuDongHo lsdh = getThongTinKhachHangTreo(ttkh.getMs_mnoi());
            if(null!=lsdh.getNgay_thuc_hien()) {
                edtngaydoccu.setText(format2.format(lsdh.getNgay_thuc_hien()).toString());
            }else {
                edtngaydoccu.setText("");
            }
            edtSocu.setText(lsdh.getChi_so_dh().toString());
        }

    }

    public LichSuDongHo getThongTinKhachHangTreo(Integer ms_mnoi){
        String url = common.URL_API + "/khachhang/getlichsudongho?ms_mnoi=" + ms_mnoi;
        Log.d("getlichsudongho", url);
        JSONObject objUser = null;
        LichSuDongHo lsdh = new LichSuDongHo();
        JSONArray jsonArray = null;
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            jsonArray = ReadJson.readJSonArrayFromURL(url);
            if(jsonArray.length()>0) {
                objUser = jsonArray.getJSONObject(0);
                Integer ms_ls_dh  = objUser.getInt("ms_ls_dh");
                Integer ms_sk_dh  = objUser.getInt("ms_sk_dh");
                Integer ms_dh  = objUser.getInt("ms_dh");
                String ngay_thuc_hien_str = common.GetDataToValue(objUser.getString("ngay_thuc_hien"),"");
                if(!ngay_thuc_hien_str.equals("")) {
                    ngay_thuc_hien_str = common.cat10kitucuoi(objUser.getString("ngay_thuc_hien"));
                }
                Date ngay_thuc_hien = null;
                if(!"".equals(ngay_thuc_hien_str)) {
                    try {
                        ngay_thuc_hien = format1.parse(ngay_thuc_hien_str);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                Integer chi_so_dh  = objUser.getInt("chi_so_dh");
                lsdh = new LichSuDongHo(ms_ls_dh, ms_sk_dh, ms_dh, ms_mnoi, ngay_thuc_hien, chi_so_dh);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lsdh;
    }


    private void khaibaobien() {

        tvtentuyen = (TextView) findViewById(R.id.tentuyen);
        tvSoLuongAnh = (TextView) findViewById(R.id.tvSoLuongAnh) ;

        edtdanhba = (EditText) findViewById(R.id.edtdanhba);
        edtseridh = (EditText) findViewById(R.id.edtseridh);
        edttennguoithue = (EditText) findViewById(R.id.edttennguoithue);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtSocu = (EditText) findViewById(R.id.edtSocu);
        edtSomoi = (EditText) findViewById(R.id.edtSomoi);
        edtsoks = (EditText) findViewById(R.id.edtsoks);
        //edtsottks = (EditText) findViewById(R.id.edtsottks) ;
        edtTT3T = (EditText) findViewById(R.id.edtTT3T);
        edtngaydoccu = (EditText) findViewById(R.id.edtngaydoccu);
        edtngaydocmoi = (EditText) findViewById(R.id.edtngaydocmoi);
        edtmdsdkh = (EditText) findViewById(R.id.edtmdsdkh);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
        edtngayks = (EditText) findViewById(R.id.edtngayks);
        edtghichu = (EditText) findViewById(R.id.edtghichu) ;
        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        btnLuuSDT = (Button) findViewById(R.id.btnLuuSDT);
        tvdkks = (TextView) findViewById(R.id.tvdkks);

    }

    public void getThongTinDiemDungDaKiemSoat(Integer ms_mnoi) {

        if (isConnected()) {

            String url = common.URL_API + "/khachhang/getkhachhangkiemsoat?ms_mnoi=" + ms_mnoi+"&ms_tk="+ ms_tk+"&ms_userthanhtra="+ms_userthanhtra;
            new HttpAsyncTaskGetDiemDungDaKiemSoat().execute(url);

        } else {

            Toast.makeText(HienThiThongTinKiemSoat.this, "Chưa kết nối internet", Toast.LENGTH_LONG).show();
        }

    }

    private class HttpAsyncTaskGetDiemDungDaKiemSoat extends AsyncTask<String, JSONObject, Void> {

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
            ttks = new ThongTinKiemSoat();
            try {
                jsonArrayKH = ReadJson.readJSonArrayFromURL(url);
                for (int i = 0; i < jsonArrayKH.length(); i++) {
                    JSONObject objTTTT = jsonArrayKH.getJSONObject(i);
                    Integer ms_ks =objTTTT.getInt("ms_ks");
                    Integer ms_mnoi =objTTTT.getInt("ms_mnoi");
                    Integer ms_userthanhtra = objTTTT.getInt("ms_userthanhtra");
                    Integer ms_tk =objTTTT.getInt("ms_tk");
                    String ngay_kiem_soat_str = common.cat10kitucuoi(objTTTT.getString("ngay_kiem_soat"));
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

                    Date ngay_kiem_soat = null;
                    try {
                        ngay_kiem_soat = format1.parse(ngay_kiem_soat_str);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Integer chi_so_cu = null;
                    String str_ssc = common.GetDataToValue(objTTTT.getString("chi_so_cu"),"");
                    if(!"".equals(str_ssc)){
                        chi_so_cu = objTTTT.getInt("chi_so_cu");
                    }
                    Integer chi_so_ks = null;
                    String str_csks = common.GetDataToValue(objTTTT.getString("chi_so_ks"),"");
                    if(!"".equals(str_csks)){
                        chi_so_ks =  objTTTT.getInt("chi_so_ks");
                    }
                    Integer so_tthu_ks = null;
                    String str_ttks = common.GetDataToValue(objTTTT.getString("so_tthu_ks"),"");
                    if(!"".equals(str_ttks)){
                        so_tthu_ks =  objTTTT.getInt("so_tthu_ks");
                    }
                    Integer ms_dk_ks = objTTTT.getInt("ms_dk_ks");
                    Integer ms_tt_ks = objTTTT.getInt("ms_tt_ks");
                    String ghi_chu = common.GetDataToValue(objTTTT.getString("ghi_chu_ks"),"");
                    String url_image_ks = common.GetDataToValue(objTTTT.getString("url_image_ks"),"");
                    String thu_muc = common.GetDataToValue(objTTTT.getString("thu_muc"),"");

                    ttks = new ThongTinKiemSoat( ms_ks,  ms_mnoi,  ms_userthanhtra,  ms_tk,  ngay_kiem_soat,  chi_so_cu,  chi_so_ks,  so_tthu_ks,  ms_dk_ks,  ms_tt_ks,  ghi_chu,  url_image_ks,  thu_muc);



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
            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
            edtngayks.setText(format2.format(ttks.getNgay_kiem_soat()).toString());
            edtghichu.setText(ttks.getGhi_chu_ks());
            if("".equals(ttks.getUrl_image_ks())){
                imgPhoto.setImageBitmap(null);
            }else {
                imgPhoto.setImageBitmap(common.getBitmapFromURL(ttks.getUrl_image_ks()));
            }
            if(null!=ttks.getChi_so_ks()) {
                edtsoks.setText(ttks.getChi_so_ks().toString());
            }else {
                edtsoks.setText("");
            }
            //edtsottks.setText(ttks.getSo_tthu_ks().toString());
            tvSoLuongAnh.setText(ttks.getThu_muc());
        }
    }



    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed(){
        if (isConnected()) {
            Intent intent = new Intent(HienThiThongTinKiemSoat.this, TabActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(HienThiThongTinKiemSoat.this, "Chưa kết nối internet", Toast.LENGTH_LONG).show();
        }

    }
}
