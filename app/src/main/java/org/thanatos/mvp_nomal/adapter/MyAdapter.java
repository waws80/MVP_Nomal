package org.thanatos.mvp_nomal.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import org.thanatos.mvp_nomal.entity.UserInfo;

import java.util.List;

/**
 * Created by lxf52 on 2016/12/25.
 */

public class MyAdapter extends BaseAdapter{

    private Context context;
    private List list;

    public MyAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserInfo userInfo=new Gson().fromJson(list.get(position).toString(),UserInfo.class);
        TextView tv=new TextView(context);
        tv.setPadding(10,10,10,10);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);
        tv.setText(userInfo.getName());
        return tv;
    }
}
