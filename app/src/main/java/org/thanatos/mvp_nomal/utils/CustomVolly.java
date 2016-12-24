package org.thanatos.mvp_nomal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.thanatos.mvp_nomal.base.BaseEntity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装Volly
 */

public class CustomVolly {
    private static final String TAG = "CustomVolly";

    private static RequestQueue queue;

    private static WeakReference<Context> reference=null;

    private CustomVolly() {
    }

    public static CustomVolly registerVolly(Context application){
        reference=new WeakReference<>(application);
        queue= Volley.newRequestQueue(reference.get());
        return CustomVollyHolder.volly;

    }

    private static class CustomVollyHolder{
        private static CustomVolly volly=new CustomVolly();
    }

    public static void getEntity(int method, final String url, JSONObject body, final HashMap<String ,String> header,
                                 final OnLoadingListener<BaseEntity> loadingListener){
        JsonObjectRequest request=new JsonObjectRequest(method, url, body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson=new Gson();
                loadingListener.success(gson.fromJson(jsonObject.toString(), BaseEntity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(queue.getCache().get(url)!=null){
                    String cachedResponse = new String(queue.getCache().get(url).data);
                    try {
                        JSONObject jsonObject=new JSONObject(cachedResponse);
                        Gson gson=new Gson();
                        Log.w(TAG, "onErrorResponse: i am cache:=="+cachedResponse );
                        loadingListener.success(gson.fromJson(jsonObject.toString(), BaseEntity.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    loadingListener.error(volleyError);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        request.setTag(Integer.MAX_VALUE);
        request.setShouldCache(false);
        request.setCacheEntry(new Cache.Entry());
        if (queue==null)throw new NullPointerException("please register CustomVolly");
        queue.add(request);

    }

    public static void getText(int method, final String url, final HashMap<String ,String> header,
                               final OnLoadingListener<String> loadingListener){
        StringRequest request=new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loadingListener.success(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                if(queue.getCache().get(url)!=null){
                    String cachedResponse = new String(queue.getCache().get(url).data);
                    Log.w(TAG, "onErrorResponse: i am cache:==" +cachedResponse);
                    loadingListener.success(cachedResponse);
                }else{
                    loadingListener.error(volleyError);
                }

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        request.setShouldCache(false);
        request.setCacheEntry(new Cache.Entry());
        request.setTag(Integer.MAX_VALUE);
        if (queue==null)throw new NullPointerException("please register CustomVolly");
        queue.add(request);
    }

    public static void cancelRequest(){
        queue.cancelAll(Integer.MAX_VALUE);
    }

    public static void  unRegister(){
        if (queue==null)throw new NullPointerException("please register CustomVolly");
        queue.stop();
        reference.clear();
        reference=null;
    }


    /**
     * 检查当前网络是否可用
     * @return true:有网络 false:反之
     */
    @SuppressWarnings("deprecation")
    public static boolean isNetworkAvailable() {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) reference.get().getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

     public interface OnLoadingListener<E >{
        void success(E s);
        void error(VolleyError e);
    }

    private static String oldMsg;
    private static Toast toast = null;
    private static long oneTime=0;
    private static long twoTime=0;

    public static void showToast( String s){
        if(toast==null){
            toast =Toast.makeText(reference.get(), s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime=twoTime;
    }

}
