package com.bw.yanyibo20200323.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * 作者：闫艺博
 * 时间：2020/3/23
 * 功能：工具类
 *
 * */

public class NetUtil {


    //单例模式
    public NetUtil(){

    }

    private static final NetUtil NET_UTIL = new NetUtil();

    public static NetUtil getInstance() {
        return NET_UTIL;
    }

    //判断是否有网
    public boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isAvailable()){
            return true;
        }else{
            return false;
        }
    }

    //判断是否是WIFI
    public boolean isWIFI(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            return true;
        }else{
            return false;
        }
    }

    //判断是否是移动
    public boolean isMOBILE(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            return true;
        }else{
            return false;
        }
    }

    //流转字符串
    public String ioToString(InputStream inputStream) throws IOException {

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;

        while((len != inputStream.read(bytes))){
            byteArrayOutputStream.write(bytes,0,len);
        }

        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        String json = new String(bytes1);

        return json;
    }

    //流转图片
    public Bitmap ioToBitmap(InputStream inputStream){
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    //获取字符串数据
    public String  doget(String httpurl){
        InputStream inputStream=null;
        try {
            URL url = new URL(httpurl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                String string = ioToString(inputStream);
                return string;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取图片数据
    public void  dogetphoto(String photourl, ImageView imageView){
        InputStream inputStream=null;
        try {
            URL url = new URL(photourl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                Bitmap bitmap = ioToBitmap(inputStream);
                imageView.setImageBitmap(bitmap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
