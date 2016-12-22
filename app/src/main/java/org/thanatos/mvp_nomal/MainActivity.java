package org.thanatos.mvp_nomal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {
    @InjectView(R.id.activity_main_lv)
     ListView lv;
    private List<String> stringList;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0x100){
                lv.setAdapter(new MyAdapter());
                hindProgress();
            }else if (msg.what==0x110){
                hindSwipRFL();
            }
        }
    };


    @Override
    public void onCreateView(Bundle savedInstanceState) {
        setView(R.layout.activity_main);
        ButterKnife.inject(this);
        showProgress();
        stringList = Arrays.asList("1","2","3","4","5","6");
        handler.sendEmptyMessageDelayed(0x100,2000);


    }

    @Override
    public void onRefreshData() {
        super.onRefreshData();
        handler.sendEmptyMessageDelayed(0x110,2000);
    }

    @Override
    public void back() {
        finish();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return stringList.size();
        }

        @Override
        public Object getItem(int position) {
            return stringList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHolder holder;
            if (convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.item,null);
                holder=new MyHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder= (MyHolder) convertView.getTag();
            }

            holder.tv.setText(stringList.get(position));
            return convertView;
        }

        class  MyHolder {
            private TextView tv;
             MyHolder(View view) {
                tv= (TextView) view.findViewById(R.id.tv);
            }
        }
    }
}
