package example.getlearn.tv.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import example.getlearn.tv.view.activity.WatchTheRecordActivity;

import static android.content.ContentValues.TAG;

/**
 * 大杂烩
 * Created by zlw on 2018/11/10.
 */

public class GroceryStoreUtils {

    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }
    //获取时间  日期等
    public   static String  getSimpleDateFprmat(String yyyymmdd){
        String Date = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyymmdd);//yyyy:MM:dd HH:mm:ss
        java.util.Date date = new Date(System.currentTimeMillis());
        return Date =simpleDateFormat.format(date);
    }

        //获取星期  yyyy-MM-dd
    public static String getWeek(String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int wek=c.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "星期日";
        }
        if (wek == 2) {
            Week += "星期一";
        }
        if (wek == 3) {
            Week += "星期二";
        }
        if (wek == 4) {
            Week += "星期三";
        }
        if (wek == 5) {
            Week += "星期四";
        }
        if (wek == 6) {
            Week += "星期五";
        }
        if (wek == 7) {
            Week += "星期六";
        }
        return Week;
    }

    //Glide为layout添加背景色

    public static void GlideBG(Activity activity,String iamg,View view){
        Glide.with(activity).load(iamg)
                .into(new ViewTarget<View, GlideDrawable>(view) {
                    //括号里为需要加载的控件
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }
    //UTF-8
    public static String toUtf8(String str) {
        String result = null;
        try {
            result = new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    //截取之后的字符
    public   static String  getIntercept(String s,String name){
        String Date = "";
        String userIdJiequ = "";
        if (s.indexOf("id=")!= -1){
            String userId = s;
            userIdJiequ = userId.substring(userId.indexOf(name));
        }
        return Date =userIdJiequ;
    }
    //删除字符
    public   static String  getDeleText(String s,String name){
        String Date = "";
        String newStr = "";
        if (s.indexOf("id=")!= -1){
            newStr = s.replace(name,""); //得到新的字符串
        }
        return Date =newStr;
    }

    //获取参数类型
    public static String getParamType(String paramName, String source){
        String result = "";
        if(source == null || source.isEmpty()){
            return "";
        }
        if(source.indexOf("&") == -1){
            return "";
        }
        String[] arrayStr = source.split("&");
        String param = paramName.concat("=");
        for (String array: arrayStr) {
            if(array.contains(param) && (array.length() > param.length())){
                result = array.substring(array.indexOf("=")+1);
            }
        }
        return result;
    }

//    //首页字符串截取
//    public static String TextIntercept (String text){
//        String name = "";
//        String userId =text;
//        String userIdJiequ = userId.substring(userId.indexOf("a="));
//        String str =userIdJiequ;
//        String a = str.substring(0, str.indexOf("&"));
//        return name = a;
//    }

    //首页字符串截取
    public static String TextIntercept (String text){
        String name = "";
        String a = "";
        String userId =text;
        String userIdJiequ = userId.substring(userId.indexOf("a="));
        String str =userIdJiequ;
        if (str.indexOf("&") != -1){
            a = str.substring(0, str.indexOf("&"));
            //有
        }else {
            //没有
            a=str;
        }
        return name = a;
    }

//    用来判断字符里有没有当前内容
    public static boolean setTextIndexOf(String str,String text){
        if (str.indexOf(text) != -1){
            return  true;
        }else {
            return  false;
        }
    }


    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager   设置RecyclerView对应的manager
     * @param mRecyclerView  当前的RecyclerView
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }



}
