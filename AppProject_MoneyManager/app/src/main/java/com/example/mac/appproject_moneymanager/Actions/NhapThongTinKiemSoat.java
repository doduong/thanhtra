package com.example.mac.appproject_moneymanager.Actions;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.appproject_moneymanager.R;
import com.example.mac.appproject_moneymanager.api.BarcodeReaderApiManager;
import com.example.mac.appproject_moneymanager.model.DieuKienThanhTra;
import com.example.mac.appproject_moneymanager.model.DuLieuKhachHang;
import com.example.mac.appproject_moneymanager.model.LichSuDongHo;
import com.example.mac.appproject_moneymanager.model.NoiDungKiemSoat;
import com.example.mac.appproject_moneymanager.model.ThongTinDongHo;
import com.example.mac.appproject_moneymanager.model.ThongTinKhachHang;
import com.example.mac.appproject_moneymanager.model.ToQuanLyModel;
import com.example.mac.appproject_moneymanager.model.request.KhachHangUpdateRequest;
import com.example.mac.appproject_moneymanager.utils.BaseActivity;
import com.example.mac.appproject_moneymanager.utils.CommonText;
import com.example.mac.appproject_moneymanager.utils.ReadJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.Manifest.permission.CALL_PHONE;
import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;


public class NhapThongTinKiemSoat extends BaseActivity {
    DuLieuKhachHang ttkh;
    TextView tvtentuyen;
    TextView tvSoLuongAnh;

    EditText edtdanhba;
    EditText edtseridh;
    EditText edttennguoithue;
    EditText edtDiaChi;
    EditText edtSocu;
    EditText edtSomoi;
    EditText edtsoks;
    //EditText edtsottks;
    EditText edtTT3T;
    EditText edtTBTT;
    EditText edtngaydoccu;
    EditText edtngaydocmoi;
    EditText edtmdsdkh;
    EditText edtSDT;
    EditText edtkieuthanhtoan;
    EditText edtghichu;
    EditText edtghichumdsd;

    ImageButton btncamera;
    Button btnLuu;
    Button btnTruyenAnh;
    Spinner spindktt;
    Button btnLuuSDT;
    CheckBox chkdocvanban;

    ImageView imgPhoto;
    String ba1;
    int soluonganh;
    boolean daluukiemsoat = false;
    CommonText common;
    ArrayList<DieuKienThanhTra> listdk;


    List<Bitmap> bitmaps = null;
    //ArrayList<String> listStrImages = null;
    String[] listStrImages = null;
    Bitmap selectedBitmap;
    private Uri photoUri;
    private final static int TAKE_PHOTO = 100;
    private final static String PHOTO_URI = "photoUri";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]
                    { android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

        if (ActivityCompat.checkSelfPermission(NhapThongTinKiemSoat.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NhapThongTinKiemSoat.this, new String[]
                    {android.Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }
        if (ActivityCompat.checkSelfPermission(NhapThongTinKiemSoat.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NhapThongTinKiemSoat.this, new String[]
                    {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            return;
        }
        setContentView(R.layout.activity_nhap_thong_tin_kiem_soat);
        initView();

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*bitmaps = new ArrayList<>();
                if (ActivityCompat.checkSelfPermission(NhapThongTinKiemSoat.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NhapThongTinKiemSoat.this, new String[]
                            {Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    return;
                }
                if (ActivityCompat.checkSelfPermission(NhapThongTinKiemSoat.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NhapThongTinKiemSoat.this, new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);*/
                // Kiểm tra Camera trong thiết bị

                if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                    // Mở camera mặc định
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //phien ban vsmart
                    photoUri = getContentResolver().insert(EXTERNAL_CONTENT_URI, new ContentValues());
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    //End Vsmart

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, TAKE_PHOTO);
                    }

                } else {
                    Toast.makeText(getApplication(), "Camera không được hỗ trợ", Toast.LENGTH_LONG).show();
                }

            }
        });

        //phien ban vsmart
        if (savedInstanceState != null) {
            photoUri = (Uri) savedInstanceState.get(PHOTO_URI);
        }
        //End Vsmart
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(NhapThongTinKiemSoat.this);
                b.setTitle("Cảnh báo");
                b.setMessage("Bạn có chắc chắn muốn lưu thông tin kiểm soát?");
                b.setPositiveButton("ĐỒNG Ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        luuThongTinKiemSoat();

                    }
                });
                b.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();

            }
        });

        btnTruyenAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xacNhanAnLuu();
            }
        });

        btnLuuSDT.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                String number=edtSDT.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                if (ContextCompat.checkSelfPermission(NhapThongTinKiemSoat.this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    public void getDieuKienThanhTra() {
        if (isConnected()) {
            String url = common.URL_API + "/khachhang/gettdktt";
            new HttpAsyncTaskDieuKienThanhTra().execute(url);
        } else {
            Toast.makeText(this, "Không có kết nối internet", Toast.LENGTH_LONG).show();
        }

    }

    private class HttpAsyncTaskDieuKienThanhTra extends AsyncTask<String, JSONObject, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {

            String url = params[0];
            JSONArray jsonArrayTinhTrang;

            try {
                listdk = new ArrayList<>();
                jsonArrayTinhTrang = ReadJson.readJSonArrayFromURL(url);
                // Log.d("doInBackground", String.valueOf(jsonArrayTinhTrang.length()));
                for (int i = 0; i < jsonArrayTinhTrang.length(); i++) {
                    DieuKienThanhTra dktt;
                    JSONObject objTinhTrang = jsonArrayTinhTrang.getJSONObject(i);
                    int ms_dk_tt = objTinhTrang.getInt("ms_dk_ks");
                    String ten_dk_tt = objTinhTrang.getString("ten_dk_ks");

                    dktt = new DieuKienThanhTra(ms_dk_tt, ten_dk_tt);
                    listdk.add(dktt);
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

            ArrayAdapter<DieuKienThanhTra> dataAdapter = new ArrayAdapter<DieuKienThanhTra>(NhapThongTinKiemSoat.this, android.R.layout.simple_spinner_item, listdk);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spindktt.setAdapter(dataAdapter);
            spindktt.setSelection(ttkh.getMs_dk_ks()-1);

            if(ttkh.getMs_dk_ks()==10) {
                spindktt.setEnabled(true);
            }else {
                spindktt.setEnabled(false);
            }
        }
    }

    public void luuThongTinKiemSoat(){
        Integer co_chi_so = 0;
        Integer co_chi_so_moi=0;
        Integer ms_mnoi = ttkh.getMs_mnoi();
        Integer  ms_userthanhtra = ttkh.getMs_userthanhtra();
        Integer ms_tk = ttkh.getMs_tk();
        Integer chi_so_cu = null ;
        if(null!=ttkh.getChi_so_cu()){
            chi_so_cu = (int) ttkh.getChi_so_cu();
        }
        Integer chi_so_moi =  null;
        if(null!=ttkh.getChi_so_moi()){
            chi_so_moi = (int) ttkh.getChi_so_moi();
            co_chi_so_moi = 1;
        }
        Integer chi_so_ks = null;
        String somoi = edtsoks.getText().toString();
        if(!somoi.equals("")) {
            chi_so_ks = Integer.parseInt(edtsoks.getText().toString());
            co_chi_so = 1;
        }
        Integer so_tthu_ks = null;
        if(chi_so_cu != null && chi_so_ks !=null) {
            so_tthu_ks = chi_so_ks - chi_so_cu;
        }
        DieuKienThanhTra dktt = (DieuKienThanhTra) spindktt.getSelectedItem();
        Integer ms_dk_ks = dktt.getMs_dk_ks();
        Integer ms_tt_ks = 1;
        String ghi_chu_ks = edtghichu.getText().toString();
        String thu_muc = ms_tk+"/"+ms_userthanhtra+"/"+ms_mnoi;

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        Date ngay_doc_cu = null;
        Date ngay_doc_moi = null;
        if(null!= ttkh.getNgay_doc_cu()){
            ngay_doc_cu = ttkh.getNgay_doc_cu();
        }
        if(null!= ttkh.getNgay_doc_moi()){
            ngay_doc_moi = ttkh.getNgay_doc_moi();
        }

        luuKiemSoat(ms_mnoi ,  ms_userthanhtra , ms_tk , chi_so_cu , chi_so_moi, chi_so_ks , so_tthu_ks , ms_dk_ks , ms_tt_ks , ghi_chu_ks  , thu_muc, co_chi_so, ttkh.getMs_tql(),co_chi_so_moi, ngay_doc_cu , ngay_doc_moi);

    }

    public void xacNhanAnLuu(){
        if(daluukiemsoat==true) {
            xacNhanTruyenAnh();
        }else{
            Toast.makeText(NhapThongTinKiemSoat.this, "Bạn phải lưu thông tin kiểm soát trước!", Toast.LENGTH_LONG).show();
        }

    }

    public void xacNhanTruyenAnh(){
        android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(NhapThongTinKiemSoat.this);
        b.setTitle("Cảnh báo");
        b.setMessage("Bạn có chắc chắn truyền ảnh về?");
        b.setPositiveButton("ĐỒNG Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                truyenAnh();

            }
        });
        b.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.create().show();
    }

    public void truyenAnh(){
        if(isConnected()){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    /*ByteArrayOutputStream bao = null;
                    //byte[] ba = bao.toByteArray();
                    //ba1 = Base64.encodeToString(ba, Base64.DEFAULT);
                    listStrImages = new String[bitmaps.size()];
                    for(int i = 0; i<bitmaps.size(); i++){
                        bao = new ByteArrayOutputStream();
                        bitmaps.get(i).compress(Bitmap.CompressFormat.JPEG, 100, bao);
                        byte[] ba = bao.toByteArray();
                        String strba= Base64.encodeToString(ba,Base64.DEFAULT);
                        listStrImages[i] = strba;

                    }*/
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                    byte[] ba = bao.toByteArray();
                    ba1 = Base64.encodeToString(ba, Base64.DEFAULT);

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {

                    KhachHangUpdateRequest request = new KhachHangUpdateRequest(ttkh.getMs_tk(), ttkh.getMs_userthanhtra(), ttkh.getMs_mnoi() , ttkh.getMs_userthanhtra(),ba1);
                    //if (checkGetCustomerInfoWereRead(ms_db, ms_tk1, Integer.parseInt(ms_tuyendoc))) {
                    if (isConnected()) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        final ProgressDialog progressDialog = new ProgressDialog(NhapThongTinKiemSoat.this);
                        progressDialog.setCancelable(false);
                        progressDialog.setTitle("Cập nhật Kiểm Soát....");
                        progressDialog.show();
                        try {
                            boolean rp = BarcodeReaderApiManager.getInstance().waterApi().updateInfoControl(ttkh.getMs_mnoi(), request).execute().isSuccessful();
                            if(rp){
                                progressDialog.dismiss();
                                Toast.makeText(NhapThongTinKiemSoat.this, "Cập nhật thành công!", Toast.LENGTH_LONG).show();
                                soluonganh = soluonganh+ 1;
                                tvSoLuongAnh.setText("SL: "+ soluonganh);

                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(NhapThongTinKiemSoat.this, "Cập nhật KHÔNG thành công!", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                        e.printStackTrace();
                    }
                    } else {
                        Toast.makeText(NhapThongTinKiemSoat.this, "Không có kết nối Intenet!", Toast.LENGTH_LONG).show();
                    }

                }
            }.execute();

        }else {
            Toast.makeText(NhapThongTinKiemSoat.this, "Không có kết nối internet!", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);
        if ((requestCode == TAKE_PHOTO || requestCode == 200) && resultCode == Activity.RESULT_OK) {
            //Phiên bản vsmart
            try {
                InputStream stream = getContentResolver().openInputStream(photoUri);

                if(chkdocvanban.isChecked()){
                    Log.d("ảnh net", "...");
                    selectedBitmap = BitmapFactory.decodeStream(stream);
                }else {
                    selectedBitmap = getResizedBitmap(BitmapFactory.decodeStream(stream), 450);
                }
                imgPhoto.setImageBitmap(selectedBitmap);
                getContentResolver().delete(photoUri, null, null);
                stream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //End phien ban Vsmart

        }


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
        edtsoks.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
        edtTT3T = (EditText) findViewById(R.id.edtTT3T);
        edtngaydoccu = (EditText) findViewById(R.id.edtngaydoccu);
        edtngaydocmoi = (EditText) findViewById(R.id.edtngaydocmoi);
        edtmdsdkh = (EditText) findViewById(R.id.edtmdsdkh);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
        //edtkieuthanhtoan = (EditText) findViewById(R.id.edtkieuthanhtoan);
        edtghichu = (EditText) findViewById(R.id.edtghichu) ;

        btncamera = (ImageButton) findViewById(R.id.btncamera);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        btnTruyenAnh = (Button) findViewById(R.id.btntruyenanh);

        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        btnTruyenAnh.setBackgroundColor(Color.GRAY);
        btnTruyenAnh.setEnabled(false);
        btnLuuSDT = (Button) findViewById(R.id.btnLuuSDT);
        spindktt = (Spinner) findViewById(R.id.spindktt);
        edtghichumdsd = (EditText) findViewById(R.id.edtghichumdsd);
        chkdocvanban = (CheckBox) findViewById(R.id.chkchupvanban);
        /*img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);*/


    }

    private void initView() {
        common = new CommonText();
        Intent intent = getIntent();
        //final ArrayList<DuLieuKhachHang> arrTTCT = (ArrayList<DuLieuKhachHang>) intent.getSerializableExtra("arrlist");
        ttkh = (DuLieuKhachHang) intent.getSerializableExtra("ttkh");
        khaibaobien();
        getDieuKienThanhTra();
        displayInfoCustomer();

        //getKhaNangDongHo(ttkh.get);

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
        if(null!=ttkh.getMucdichsudung()) {
            edtghichumdsd.setText(ttkh.getMucdichsudung());
        }else {
            edtghichumdsd.setText("");
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
        spindktt.setSelection(ttkh.getMs_dk_ks());
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
    private void luuKiemSoat(Integer ms_mnoi ,Integer  ms_userthanhtra ,Integer ms_tk ,Integer chi_so_cu ,Integer chi_so_moi, Integer chi_so_ks ,Integer so_tthu_ks ,Integer ms_dk_ks,Integer ms_tt_ks ,String ghi_chu_ks  ,String thu_muc, Integer co_chi_so, Integer ms_tql, Integer co_chi_so_moi, Date ngay_doc_cu, Date ngay_doc_moi ) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        NoiDungKiemSoat request = new NoiDungKiemSoat(ms_mnoi ,  ms_userthanhtra , ms_tk  , chi_so_cu , chi_so_moi, chi_so_ks , so_tthu_ks , ms_dk_ks , ms_tt_ks , ghi_chu_ks , thu_muc, co_chi_so, ms_tql, co_chi_so_moi, ngay_doc_cu, ngay_doc_moi);
        try {
            boolean rp = BarcodeReaderApiManager.getInstance().waterApi().luuKiemSoat(request).execute().isSuccessful();
            if(rp){
                daluukiemsoat = true;
                btnTruyenAnh.setBackgroundResource(R.drawable.button_style);
                btnTruyenAnh.setEnabled(true);

                Toast.makeText(getApplication(), "Cập nhật kiểm soát thành công!", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplication(), "Cập nhật KHÔNG thành công!", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /*private void getKhaNangDongHo(Integer ms_dh){
        BarcodeReaderApiManager.getInstance().waterApi().getWaterMeterInfo(ms_dh).enqueue(new Callback<ThongTinDongHo>() {
            @Override
            public void onResponse(Call<ThongTinDongHo> call, retrofit2.Response<ThongTinDongHo> response) {

                Integer he_so = response.body().getHe_so();
                He_So = he_so;
                Integer kha_nang_dh = response.body().getKha_nang_dh();
                Kha_Nang_DH = kha_nang_dh;
            }

            @Override
            public void onFailure(Call<ThongTinDongHo> call, Throwable t) {

            }
        });
    }*/

    /*private void setDisplayPictures(List<Bitmap> bitmaps) {
        img1.setImageDrawable(null);
        img2.setImageDrawable(null);
        img3.setImageDrawable(null);
        img4.setImageDrawable(null);
        img5.setImageDrawable(null);
        img6.setImageDrawable(null);
        int number = bitmaps.size();
        if (number > 0 && number < 7) {
            if (number == 1) {
                img1.setImageBitmap(bitmaps.get(0));
            } else if (number == 2) {
                img1.setImageBitmap(bitmaps.get(0));
                img2.setImageBitmap(bitmaps.get(1));
            } else if (number == 3) {
                img1.setImageBitmap(bitmaps.get(0));
                img2.setImageBitmap(bitmaps.get(1));
                img3.setImageBitmap(bitmaps.get(2));
            } else if (number == 4) {
                img1.setImageBitmap(bitmaps.get(0));
                img2.setImageBitmap(bitmaps.get(1));
                img3.setImageBitmap(bitmaps.get(2));
                img4.setImageBitmap(bitmaps.get(3));
            } else if (number == 5) {
                img1.setImageBitmap(bitmaps.get(0));
                img2.setImageBitmap(bitmaps.get(1));
                img3.setImageBitmap(bitmaps.get(2));
                img4.setImageBitmap(bitmaps.get(3));
                img5.setImageBitmap(bitmaps.get(4));
            } else if (number == 6) {
                img1.setImageBitmap(bitmaps.get(0));
                img2.setImageBitmap(bitmaps.get(1));
                img6.setImageBitmap(bitmaps.get(2));
                img3.setImageBitmap(bitmaps.get(3));
                img4.setImageBitmap(bitmaps.get(4));
                img5.setImageBitmap(bitmaps.get(5));

            }
        }
    }*/

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

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
        //return Bitmap.createBitmap(image, 0,0, width, height);
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed(){
        if (isConnected()) {

            Intent intent = new Intent(NhapThongTinKiemSoat.this, TabActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(NhapThongTinKiemSoat.this, "Chưa kết nối internet", Toast.LENGTH_LONG).show();
        }

    }
}
