package com.example.mac.appproject_moneymanager.Actions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mac.appproject_moneymanager.R;
import com.example.mac.appproject_moneymanager.api.BarcodeReaderApiManager;
import com.example.mac.appproject_moneymanager.model.request.UpdateUserPwd;
import com.example.mac.appproject_moneymanager.utils.BaseActivity;
import com.example.mac.appproject_moneymanager.utils.CommonText;
import com.example.mac.appproject_moneymanager.utils.ReadJson;
import com.example.mac.appproject_moneymanager.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener{

    EditText username;
    EditText password;
    EditText edttk;
    Button btnLogin;
    CheckBox rem_userpass;
    Button btnchangepass;
    SharedPref config;
    EditText edtuserdialog;
    EditText edtpassword;
    EditText edtnewpassword;
    EditText edtrepeatpassword;

    private static final String KEY_REMEMBER = "app_remember";
    private static final String RADIO_TEXT_CLTD = "Chênh lệch tuyệt đối";
    private static final String TEN_USERTHANHTRA = "ten_userthanhtra";
    private static final String MS_USERTHANHTRA = "ms_userthanhtra";
    private static final String TEN_TKY = "ten_tk";
    private static final String MS_TK = "ms_tk";
    private static final String KEY_PASS = "app_password";


    String ms_tk = "";
    String ten_tk="";
    CommonText common = new CommonText();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edttk = (EditText) findViewById(R.id.kydoc);
        rem_userpass = (CheckBox) findViewById(R.id.checkBox);
        btnchangepass = (Button) findViewById(R.id.btnchangepass);

        config = new SharedPref(this);
        if (config.getBoolean(KEY_REMEMBER, false)) {
            rem_userpass.setChecked(true);
        } else {
            rem_userpass.setChecked(false);
        }

        username.setText(config.getString(TEN_USERTHANHTRA, ""));
        username.addTextChangedListener(this);
        password.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);


        if(isConnected()){
            String url = common.URL_API + "/khachhang/getthoiky";
            new HttpAsyncTaskGetMaxPeriod().execute(url);

        }else {
            Toast.makeText(Login.this, "Không có kết nối Internet!", Toast.LENGTH_LONG).show();

        }

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    username.setHint("");
                else
                    username.setHint("Tên đăng nhập");
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    password.setHint("");
                else
                    password.setHint("Mật khẩu");
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = "";
                String pass = "";



                if (isConnected()) {

                    if(!"TK: ".equals(edttk.getText().toString()) ) {
                            user = username.getText().toString();
                            pass = password.getText().toString();
                            if (!"".equals(user)) {
                                if (!"".equals(pass)) {
                                    CheckLogin(user, pass);
                                } else {
                                    Toast.makeText(Login.this, "Không để trống mật khẩu", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(Login.this, "Không để trống tên đăng nhập", Toast.LENGTH_LONG).show();
                            }

                    }else {
                        Toast.makeText(Login.this, "Không có kết nối Internet!", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(Login.this, "Không có kết nối internet", Toast.LENGTH_LONG).show();

                }

            }


        });

        btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogChangePassword();
            }


        });




    }

    public void showDialogChangePassword() {
        ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_AppCompat_Light_Dialog);
        final AlertDialog.Builder dialogchangepass = new AlertDialog.Builder(ctw);

        dialogchangepass.setTitle("THAY ĐỔI MẬT KHẨU");
        LayoutInflater inflater = this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.change_password, null);



        edtuserdialog = (EditText) layout.findViewById(R.id.edtuserdialog);
        edtpassword = (EditText) layout.findViewById(R.id.edtpassword);
        edtnewpassword = (EditText) layout.findViewById(R.id.edtnewpassword);
        edtrepeatpassword = (EditText) layout.findViewById(R.id.edtrepeatpassword);

        dialogchangepass.setView(layout);

        dialogchangepass.setPositiveButton("THAY ÐỔI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (isConnected()) {

                    if (!"".equals(edtuserdialog.getText().toString()) && !"".equals(edtpassword.getText().toString()) && !"".equals(edtnewpassword.getText().toString()) && !"".equals(edtrepeatpassword.getText().toString())) {
                        Log.d("edtpassword", edtpassword.getText().toString());

                        if (edtnewpassword.getText().toString().equals(edtrepeatpassword.getText().toString())) {
                            if (!edtpassword.getText().toString().equals(edtnewpassword.getText().toString())) {
                                String userId = "";
                                try {
                                    userId = edtuserdialog.getText().toString();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                updateUserPassword(userId, edtpassword.getText().toString(), edtnewpassword.getText().toString());
                                //UpdatePassword( "108",  "1234", "1") ;

                                //Toast.makeText(Login.this, "C?p nh?t m?t kh?u thành công!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Login.this, "Mật khẩu mới không được trùng mật khẩu cũ!", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(Login.this, "Mật khẩu mới và Gõ lại mật khẩu phải trùng nhau!", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(Login.this, "Mật khẩu không được để trống!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Không có kết nối internet!", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialogchangepass.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        dialogchangepass.show().getWindow().setLayout(displayWidth, (int) (displayHeight * 0.6));
    }


    private void updateUserPassword(String userId, String oldPwd, String newPwd){
        UpdateUserPwd request = new UpdateUserPwd(userId,oldPwd,newPwd);
        BarcodeReaderApiManager.getInstance().accountApi().updateUserPassword(""+userId,request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        if(null !=response.body()){
                            //Log.d("update", response.fbody().string().toString());
                            String result = response.body().string().toString();
                            if(result.equals("true")){
                                Toast.makeText(Login.this, "Cập nhật mật khẩu thành công!", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Login.this, "Sai tên đăng nhập hoặc mật khẩu cũ!", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(Login.this, "Sai tên đăng nhập hoặc mật khẩu cũ!", Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }else{
                    Toast.makeText(Login.this, "Lỗi trong quá trình cập nhật mật khẩu!", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private class HttpAsyncTaskGetMaxPeriod extends AsyncTask<String, JSONObject, Void> {

        ProgressDialog progressDialog = new ProgressDialog(Login.this);

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Vui lòng chờ trong giây lát...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {

            String url = params[0];
            JSONObject objMaxPeriod;
            JSONArray jsonArray;

            try {
                jsonArray = ReadJson.readJSonArrayFromURL(url);
                objMaxPeriod = jsonArray.getJSONObject(0);
                ms_tk = objMaxPeriod.getString("ms_tk");
                ten_tk = objMaxPeriod.getString("thoi_ky");

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
            edttk.setText("TK: " + ten_tk);

            progressDialog.hide();
            progressDialog.dismiss();
        }
    }

    private void managePrefs() {
        if (rem_userpass.isChecked()) {
            config.putString(TEN_USERTHANHTRA, username.getText().toString().trim());
            config.putString(KEY_PASS, password.getText().toString().trim());
            config.putBoolean(KEY_REMEMBER, true);
            config.commit();
        } else {
            config.putBoolean(KEY_REMEMBER, false);
            config.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            config.remove(TEN_USERTHANHTRA);//editor.putString(KEY_USERNAME, "");
            config.commit();

        }
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void CheckLogin(String msbd, String mat_khau) {
        if (isConnected()) {
            String ten_userthanhtra = "";
            String ms_userthanhtra = "";
            JSONObject objUser = null;
            JSONArray jsonArray = null;
            //CheckLogin?msbd=108&mat_khau=123456
            String url = common.URL_API + "/khachhang/checklogin?ten_userthanhtra=" + msbd + "&mk_userthanhtra=" + mat_khau;
            //Log.d("CheckLogin", url);
            //new HttpAsyncTaskCheckLogin().execute(url);

            try {
                jsonArray = ReadJson.readJSonArrayFromURL(url);
                if(jsonArray.length()>0) {
                    objUser = jsonArray.getJSONObject(0);
                    ten_userthanhtra = objUser.getString("ten_userthanhtra");
                    ms_userthanhtra = objUser.getString("ms_userthanhtra");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(null!= objUser)
            {
                config.clear();
                config.remove(MS_USERTHANHTRA);
                config.remove(TEN_USERTHANHTRA);
                config.putString(TEN_USERTHANHTRA, ten_userthanhtra);
                config.putString(MS_USERTHANHTRA, common.cat2kitucuoi(ms_userthanhtra));
                config.putString(TEN_TKY,ten_tk);
                if(null!=ms_tk && ms_tk.length() >2) {
                    config.putString(MS_TK, common.cat2kitucuoi(ms_tk));
                }else {
                    config.putString(MS_TK, "");
                }
                config.commit();
                Intent intent = new Intent(Login.this, ToQuanLy.class);
                startActivity(intent);
            }else {

                Toast.makeText(Login.this, "Sai tên đăng nhập hoặc mật khẩu.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Không có kết nối internet", Toast.LENGTH_LONG).show();
        }
    }

    private class HttpAsyncTaskCheckLogin extends AsyncTask<String, JSONObject, Void> {


        String ten_userthanhtra = "";
        String ms_userthanhtra = "";
        JSONObject objUser = null;


        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {

            String url = params[0];
            JSONArray jsonArray = null;
            try {
                jsonArray = ReadJson.readJSonArrayFromURL(url);
                if(jsonArray.length()>0) {
                    objUser = jsonArray.getJSONObject(0);
                    ten_userthanhtra = objUser.getString("ten_userthanhtra");
                    ms_userthanhtra = objUser.getString("ms_userthanhtra");
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
            if(null!= objUser)
                {
                    config.clear();
                    config.putString(TEN_USERTHANHTRA, ten_userthanhtra);
                    config.putString(MS_USERTHANHTRA, common.cat2kitucuoi(ms_userthanhtra));
                    config.putString(TEN_TKY,ten_tk);
                    if(null!=ms_tk && ms_tk.length() >2) {
                        config.putString(MS_TK, common.cat2kitucuoi(ms_tk));
                    }else {
                        config.putString(MS_TK, "");
                    }
                    config.commit();
                    Intent intent = new Intent(Login.this, ToQuanLy.class);
                    startActivity(intent);
                }else {

                Toast.makeText(Login.this, "Sai tên đăng nhập hoặc mật khẩu.", Toast.LENGTH_LONG).show();
            }
        }
    }


}
