package example.getlearn.tv.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import example.getlearn.tv.NFCActivity;
import example.getlearn.tv.bean.KeyNoBean;
import example.getlearn.tv.util.SysUtil;

/**
 * Created by zlw on 2018/11/29.
 */

public class SMSBroadcastReceiver extends BroadcastReceiver {
    private String acNfc;
    public static String keyNo;
    public static boolean hasKeyNo;
    @Override
    public void onReceive(Context context, Intent intent) {
        acNfc = SysUtil.getSystemProperties(context,SysUtil.SYSKEY_HB_SMART_CARD);
        if(acNfc == null || acNfc.isEmpty()){
            hasKeyNo = false;
        }else {
            hasKeyNo = true;
        }
        keyNo = acNfc;
        EventBus.getDefault().post(new KeyNoBean(acNfc));

//        Toast.makeText(context, intent.getStringExtra("smcartid"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, intent.getStringExtra(acNfc), Toast.LENGTH_SHORT).show();
    }
}
