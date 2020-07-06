package com.example.mac.appproject_moneymanager.api;

import com.example.mac.appproject_moneymanager.model.BarcodeResponse;
import com.example.mac.appproject_moneymanager.model.request.KhachHangUpdateRequest;
import com.example.mac.appproject_moneymanager.model.request.UpdateUserPwd;
import com.example.mac.appproject_moneymanager.model.response.UserLoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountApi {

    /*@PUT("UserUpdatePassword/{id}")
    Call<BarcodeResponse> updateUserPassword(@Path("id") String userId, @Body UpdateUserPwd pwdRequest);*/

    @GET("khachhang/checklogin")
    Call<UserLoginResponse> checkLogin(@Query("ten_userthanhtra") String msdb, @Query("mk_userthanhtra") String pwd);

    @GET("GetCurrentAppVersion")
    Call<UserLoginResponse> getVersion();

    @PUT("khachhang/changepwd/{id}")
    Call<ResponseBody> updateUserPassword(@Path("id") String userId, @Body UpdateUserPwd pwdRequest);


}
