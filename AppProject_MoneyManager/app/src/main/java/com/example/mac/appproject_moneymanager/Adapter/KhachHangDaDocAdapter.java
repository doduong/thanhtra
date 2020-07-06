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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class KhachHangDaDocAdapter extends BaseAdapter {
    private Activity mActivity;
    private ArrayList<DuLieuKhachHang> listKH;
    private DuLieuKhachHang tempValue = null;
    int stt = 0;



    public KhachHangDaDocAdapter(Activity a, ArrayList<DuLieuKhachHang> d) {
        this.mActivity = a;
        this.listKH = d;
        stt = 0;
        // mInflater = (LayoutInflater)mActivity.getSystemService(
        //Activity.LAYOUT_INFLATER_SERVICE);
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
        return null;
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

        //int stt = 0;

        LayoutInflater mInflater = (LayoutInflater) mActivity.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);

        View vi = view;
        KhachHangDaDocAdapter.ViewHolder holder;
        if (view == null) {
            holder = new KhachHangDaDocAdapter.ViewHolder();

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
        holder = (KhachHangDaDocAdapter.ViewHolder) vi.getTag();
        //holder = new ViewHolder();
        try {
            if (listKH.size() <= 0) {
                holder.tvMaKH.setText("");
                holder.tvTenKH.setText("");

            } else {
                tempValue = null;
                tempValue = (DuLieuKhachHang) listKH.get(i);



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




            }


        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return vi;
    }
}
