package com.example.mac.appproject_moneymanager.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mac.appproject_moneymanager.R;
import com.example.mac.appproject_moneymanager.model.ToQuanLyModel;

import java.util.ArrayList;

public class AdapterToQuanLy extends BaseAdapter {

    private Activity mActivity;
    private ArrayList<ToQuanLyModel> lsttql;
    private LayoutInflater mInflater = null;
    private ToQuanLyModel tempValue = null;

    public AdapterToQuanLy(Activity a, ArrayList<ToQuanLyModel> lsttql) {
        super();
        this.mActivity = a;
        this.lsttql = lsttql;
    }


    @Override
    public int getCount() {
        Log.d("lsttuyendoc", String.valueOf(lsttql.size()));
        return lsttql.size();
    }

    @Override
    public Object getItem(int i) {
        return lsttql.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder{
        public TextView tvtql;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        mInflater = (LayoutInflater)mActivity.getLayoutInflater();
        ViewHolder holder;
        if(view == null){
            view = mInflater.inflate(R.layout.activity_adapter_to_quan_ly,null);
            holder = new ViewHolder();
            holder.tvtql = view.findViewById(R.id.tvtql);
            view.setTag(holder);
        }else {
            holder = (AdapterToQuanLy.ViewHolder) view.getTag();
        }
        try {
            if(lsttql.size()<=0){
                holder.tvtql.setText("NoData");

            }else  {
                tempValue = null;
                tempValue = (ToQuanLyModel) lsttql.get(i);
                //Log.d("ms_tuyen: ", String.valueOf(tempValue.getMs_tuyen())+ " --- mo_ta_tuyen: " + tempValue.getMo_ta_tuyen() );

                holder.tvtql.setBackgroundColor(Color.WHITE);
                holder.tvtql.setText(String.valueOf(tempValue.getMs_tql())+ ": "+ tempValue.getTen_tql());

            }
        }catch (Exception ex) {

            ex.printStackTrace();
        }

        return view;
    }
}
