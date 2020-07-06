package com.example.mac.appproject_moneymanager.api;


import com.example.mac.appproject_moneymanager.model.BarcodeResponse;
import com.example.mac.appproject_moneymanager.model.NoiDungKiemSoat;
import com.example.mac.appproject_moneymanager.model.ThongTinDongHo;
import com.example.mac.appproject_moneymanager.model.request.KhachHangUpdateRequest;
import com.example.mac.appproject_moneymanager.model.response.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WaterApi {

    @PUT("khachhang/savecontrol/{id}")
    Call<BarcodeResponse> updateInfoControl(@Path("id") int id, @Body KhachHangUpdateRequest kiemsoatRequest);

    @POST("khachhang/luuKiemSoat")
    Call<Void> luuKiemSoat(@Body NoiDungKiemSoat noiDungKiemSoat);
    @GET("khachhang/gettdktt")
    Call<BarcodeResponse> getDieuKienThanhTra();

}
