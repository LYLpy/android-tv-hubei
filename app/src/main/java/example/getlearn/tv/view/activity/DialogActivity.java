package example.getlearn.tv.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.videolan.libvlc.Dialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.R;
import example.getlearn.tv.application.MyApplication;
import example.getlearn.tv.bean.MainMessage;
import example.getlearn.tv.controller.SMSBroadcastReceiver;
import example.getlearn.tv.util.CloseActivitys;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import example.getlearn.tv.util.SysUtil;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

public class DialogActivity extends Activity  {
    @BindView(R.id.activity_text_button_qx)
    TextView activityTextButtonQx;
    @BindView(R.id.activity_text_button_ent)
    TextView activityTextButtonEnt;
    @BindView(R.id.activity_dialog_title)
    TextView activity_dialog_title;
    @BindView(R.id.activity_dialog_title_02)
    TextView activity_dialog_title_02;
    @BindView(R.id.activity_text_button_ent_02)
    TextView activity_text_button_ent_02;
    @BindView(R.id.dialog_02)
    RelativeLayout dialog_02;
    @BindView(R.id.dialog_01)
    RelativeLayout dialog_01;
    private String name,type;
    //智能卡号
    private String keyNo = "";
    private boolean hasKeyNo;

    public static void starDialog02Activity(Activity activity,String type,String name) {
        Intent intent = new Intent(activity, DialogActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("name",name);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  1 收藏页面删除            2三级页面简介弹出框
        setContentView(R.layout.activity_dialog);
        initIntentData();
        ButterKnife.bind(this);

        switch (type){
            case "1":
            case "3":
                if(type.equals("3")){
                    activityTextButtonEnt.requestFocus();
                    activity_dialog_title.setText(name);
//                    CloseActivitys.addActivity(this);
                }
                dialog_01.setVisibility(View.VISIBLE);
                dialog_02.setVisibility(View.GONE);
                break;
            case "2":
                dialog_01.setVisibility(View.GONE);
                dialog_02.setVisibility(View.VISIBLE);
                break;
        }
        initOnClik();


    }

    private void initIntentData() {
        Intent intent = getIntent();
        type= intent.getStringExtra("type");
        name= intent.getStringExtra("name");
    }



    private void initOnClik() {
        //取消
        activityTextButtonQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //确定
        activityTextButtonEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type){
                    case "1":
                        initDataDelete();
                        EventBus.getDefault().post(new MainMessage("MyCollectionActivity"));
                        finish();
                        break;
                    case "3":
                        CloseActivitys.clearActivity();
                        break;
                }

            }
        });
        //title
        activity_text_button_ent_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activity_dialog_title_02.setText(name);

    }

    private void initDataDelete() {
//        String keyNo = SysUtil.getSystemProperties(DialogActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(DialogActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);

        }
        if(keyNo == null || keyNo.isEmpty()){
            Toast.makeText(DialogActivity.this, "观看记录获取失败，请插入机顶盒智能卡后重试！", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "initData: keyNo(智能卡号获取不到！)");
            return;
        }
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_MyCollection_DELETE)
                .addParams("os","android")
                .addParams("keyNo", keyNo)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        if (GroceryStoreUtils.setTextIndexOf(response, "status\":1")) {
//                            Toast.makeText(DialogActivity.this, "成功！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
