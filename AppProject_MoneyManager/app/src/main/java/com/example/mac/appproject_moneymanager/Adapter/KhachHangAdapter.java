package com.example.mac.appproject_moneymanager.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mac.appproject_moneymanager.R;
import com.example.mac.appproject_moneymanager.model.DuLieuKhachHang;
import com.example.mac.appproject_moneymanager.model.ThongTinKhachHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class KhachHangAdapter extends BaseAdapter {

    private Activity mActivity;
    private ArrayList<DuLieuKhachHang> listKH;
    private DuLieuKhachHang tempValue = null;
    int stt = 0;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");



    public KhachHangAdapter(Activity a, ArrayList<DuLieuKhachHang> d) {
        this.mActivity = a;
        this.listKH = d;
        stt = 0;
    }

    @Override
    public int getCount() {
        if (listKH.size() <= 0) {
            return 1;
        }
        return listKH.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder {
        private TextView tvMaKH;
        private TextView tvTenKH;
        private TextView tvdiachi;
        private TextView tvstt;
        private TextView tvtuyen;
        private TextView tvlydo;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) mActivity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View vi = view;
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            vi = mInflater.inflate(R.layout.danhsachkhachhang, viewGroup, false);
            vi.requestFocus();
            vi.setMinimumHeight(50);
            holder.tvstt = vi.findViewById(R.id.tvstt);
            holder.tvMaKH = vi.findViewById(R.id.tvmakh);
            holder.tvTenKH = vi.findViewById(R.id.tvtenkh);
            holder.tvdiachi = vi.findViewById(R.id.tvdiachi);
            holder.tvtuyen = vi.findViewById(R.id.tvtuyen);
            holder.tvlydo = vi.findViewById(R.id.tvlydo);
            vi.setTag(holder);
        }
        //else {
        holder = (ViewHolder) vi.getTag();
        //holder = new ViewHolder();
        try {
            if (listKH.size() <= 0) {
                holder.tvMaKH.setText("");
                holder.tvTenKH.setText("");

            } else {
                tempValue = null;
                tempValue = (DuLieuKhachHang) listKH.get(i);
                Date ngay_loc = null;
                Date ngay_hien_tai = null;
                int difference = 0;
                if(null!=tempValue.getNgay_loc()) {
                     ngay_loc = format1.parse(format1.format(tempValue.getNgay_loc()));
                     ngay_hien_tai = format1.parse(format1.format(new Date()));
                     difference=  ((int)((ngay_hien_tai.getTime()/(24*60*60*1000))-(int)(ngay_loc.getTime()/(24*60*60*1000))));
                }

                if(difference==1) {
                    holder.tvtuyen.setBackgroundColor(Color.WHITE);
                    holder.tvstt.setBackgroundColor(Color.WHITE);
                    holder.tvMaKH.setBackgroundColor(Color.WHITE);
                    holder.tvTenKH.setBackgroundColor(Color.WHITE);
                    holder.tvdiachi.setBackgroundColor(Color.WHITE);
                    holder.tvlydo.setBackgroundColor(Color.WHITE);


                    holder.tvtuyen.setText(String.valueOf(tempValue.getMs_tuyen()));
                    //holder.tvstt.setText(String.valueOf(tempValue.getStt_lo_trinh()));
                    holder.tvstt.setText(String.valueOf(i + 1));
                    holder.tvMaKH.setText(String.valueOf(tempValue.getMs_mnoi()));
                    holder.tvTenKH.setText(tempValue.getNguoi_thue());
                    holder.tvdiachi.setText(String.valueOf(tempValue.getDiachi()));
                    holder.tvlydo.setText(String.valueOf(tempValue.getTen_dk_ks()));
                }else if(difference!=1){
                    holder.tvtuyen.setBackgroundResource(R.color.blue_kd);
                    holder.tvstt.setBackgroundResource(R.color.blue_kd);
                    holder.tvMaKH.setBackgroundResource(R.color.blue_kd);
                    holder.tvTenKH.setBackgroundResource(R.color.blue_kd);
                    holder.tvdiachi.setBackgroundResource(R.color.blue_kd);
                    holder.tvlydo.setBackgroundResource(R.color.blue_kd);


                    holder.tvtuyen.setText(String.valueOf(tempValue.getMs_tuyen()));
                    //holder.tvstt.setText(String.valueOf(tempValue.getStt_lo_trinh()));
                    holder.tvstt.setText(String.valueOf(i + 1));
                    holder.tvMaKH.setText(String.valueOf(tempValue.getMs_mnoi()));
                    holder.tvTenKH.setText(tempValue.getNguoi_thue());
                    holder.tvdiachi.setText(String.valueOf(tempValue.getDiachi()));
                    holder.tvlydo.setText(String.valueOf(tempValue.getTen_dk_ks()));
                }



            }


        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return vi;
    }
}

