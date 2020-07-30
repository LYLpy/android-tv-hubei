package example.getlearn.tv.view.activity.video.vlc;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.videolan.libvlc.player.IMediaController;
import org.videolan.libvlc.player.IMediaPlayer;
import org.videolan.libvlc.player.IRenderView;
import org.videolan.libvlc.player.VlcVideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.VideoAuthenticationBean;
import example.getlearn.tv.bean.VideoUrlBean;
import example.getlearn.tv.controller.MySystemBarController;
import example.getlearn.tv.controller.SMSBroadcastReceiver;
import example.getlearn.tv.util.AESUtils;
import example.getlearn.tv.util.CloseActivitys;
import example.getlearn.tv.util.ConfirmDialog;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.SysUtil;
import example.getlearn.tv.util.SystemBarUtil;
import example.getlearn.tv.view.activity.DialogActivity;
import example.getlearn.tv.view.activity.VipActivity;
import example.getlearn.tv.view.activity.WatchTheRecordActivity;
import okhttp3.Call;
import okhttp3.MediaType;

import static android.content.ContentValues.TAG;

/**
 * Created by zhe on 2017/8/23.
 */

public class VlcPlayerActivity extends Activity {

    private  String URL;
    private IMediaController mMediaController;
    private VlcVideoView videoView;
    private VlcPlayerController mediaController;
    private boolean isPortrait = true;
    private MySystemBarController systemBarController;
    //暂停，开始标识
    private int enterInt =1;
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    //快进，快退标志
    private boolean isChange;
    //快进，快退位置
    private int changePos;
    private int currentPosition;
    private int percent;
    private List<String> videoIdList;
    private String videoId;
    private VideoAuthenticationBean videoAuthenticationBean;
    private VideoUrlBean videoUrlBean;
    private TextView videoTitle;
    private boolean isLastVideo;
    //智能卡号
    private String keyNo = "";
    private boolean hasKeyNo;

    public static void starActivity(Activity activity,String URL, String videoId, List<String> videoIdList) {
        Intent intent = new Intent(activity, VlcPlayerActivity.class);
        intent.putExtra("URL",URL);
        intent.putStringArrayListExtra("videoIdList", (ArrayList<String>) videoIdList);
        intent.putExtra("videoId", videoId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        CloseActivitys.addActivity(this);
        setContentView(R.layout.activity_vlc_player);
        Intent intent = getIntent();
        //获取播放地址
        URL=intent.getStringExtra("URL");
        videoId = intent.getStringExtra("videoId");
        videoIdList = intent.getStringArrayListExtra("videoIdList");

        init();
    }

    //初始化
    public void init(){
        //获取播放对象
        videoView = (VlcVideoView) findViewById(R.id.videoview);
        //获取播放器控制器对象
        mediaController = new VlcPlayerController(this);
        //设置播放完成监听
        videoView.setmOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer var1) {
                //续播
                for (int i=0; i<videoIdList.size(); i++){
                    if(videoIdList.get(i).equals(videoId) && i<videoIdList.size()-1){
                        videoId = videoIdList.get(i+1);
                        authentication(videoId);
                        break;
                    }
                    if(i == videoIdList.size()-1){
                        Toast.makeText(VlcPlayerActivity.this, "最后一个视频也播放完了", Toast.LENGTH_SHORT).show();
                    }
                }
                if(videoIdList.isEmpty()){
                    Toast.makeText(VlcPlayerActivity.this, "视频播放完了", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置播放控制器
        videoView.setMediaController(mediaController);
        //设置播放地址
        videoView.setVideoPath(URL);

//        videoTitle = findViewById(R.id.player_ui_title);
//        videoTitle.setText();
        //开始播放
        videoView.start();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK: //返回
            case KeyEvent.KEYCODE_HOME:
                finish();
                break;
            case KeyEvent.KEYCODE_ENTER:     //暂停，开始
            case KeyEvent.KEYCODE_DPAD_CENTER:
                if (enterInt==1){
                    enterInt=2;
                    onPause();
                    Toast.makeText(this, "暂停", Toast.LENGTH_SHORT).show();
                }else {
                    enterInt=1;
                    onStart();
                    Toast.makeText(this, "播放", Toast.LENGTH_SHORT).show();
                }
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:  //快进
                isChange = true;
                mediaController.setmDragging(true);
                mediaController.show(360000);
                //视频总时长
                int duration = videoView.getDuration();
                if(changePos != 0){
                    currentPosition = changePos;
                }else{
                    //视频当前位置
                    currentPosition = videoView.getCurrentPosition();
                }
                //每次快进5秒
                changePos = currentPosition + 5000;
                if(changePos >= duration){
                    changePos = duration;
                }
                if (currentPosition > 0) {
                    long pos = 1000L * changePos / duration;
                    //设置进度条快进到的位置
                    mediaController.getmSeekBar().setProgress((int) pos);
                }
                percent = mediaController.getmPlayer().getBufferPercentage();
                //设置缓冲进度
                mediaController.getmSeekBar().setSecondaryProgress(percent * 10);
                if (mediaController.getmCurrentTimeView() != null) {
                    //设置当前时间
                    mediaController.getmCurrentTimeView().setText(mediaController.stringForTime(changePos));
                }
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:  //快退
                isChange = true;
                mediaController.setmDragging(true);
                mediaController.show(360000);
                int duration_left = videoView.getDuration();
                if(changePos != 0){
                    currentPosition = changePos;
                }else{
                    //视频当前位置
                    currentPosition = videoView.getCurrentPosition();
                }
                changePos = currentPosition - 10000;
                if(changePos <= 0){
                    changePos = 0;
                }
                if (currentPosition > 0) {
                    long pos = 1000L * changePos / duration_left;
                    mediaController.getmSeekBar().setProgress((int) pos);
                }
                percent = mediaController.getmPlayer().getBufferPercentage();
                mediaController.getmSeekBar().setSecondaryProgress(percent * 10);

                if (mediaController.getmCurrentTimeView() != null) {
                    mediaController.getmCurrentTimeView().setText(mediaController.stringForTime(changePos));
                }
                break;

            default:
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_RIGHT:  //快进
                if(isChange){
                    isChange = false;
                    mediaController.getmPlayer().seekTo(changePos);
                    changePos = 0;
                    mediaController.show(60000);
                    mediaController.getmHandler().removeMessages(2);
                    mediaController.setmDragging(false);
                    mediaController.getmHandler().sendEmptyMessageDelayed(2, 1000);
                }
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:  //快退
                if(isChange){
                    isChange = false;
                    mediaController.getmPlayer().seekTo(changePos);
                    changePos = 0;
                    mediaController.show(60000);
                    mediaController.getmHandler().removeMessages(2);
                    mediaController.setmDragging(false);
                    mediaController.getmHandler().sendEmptyMessageDelayed(2, 1000);
                }
                break;

            default:
                break;

        }
        return super.onKeyUp(keyCode, event);
    }



    @Override
    protected void onResume() {
        //模拟点击播放图标
        videoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaController.startPlay();
//                videoView.start();
//                videoView.startPlayProgressBar();
            }
        }, 300);
        super.onResume();
    }

    @Override
    protected void onPause() {
        videoView.pause();
        super.onPause();
    }

    @Override
    protected void onStart() {
        videoView.start();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        videoView.release(true);
        super.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus && !isPortrait) {
            if (hasFocus) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
            }
        }
                super.onWindowFocusChanged(hasFocus);
    }

    //鉴权
    private void authentication(final String videoId){
//        String keyNo = SysUtil.getSystemProperties(VlcPlayerActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }else{
//                Log.i(TAG, "authentication: ".concat("未获取到智能卡号keyNo"));
//                return;
//            }
            keyNo = SysUtil.getSystemProperties(VlcPlayerActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
            if(keyNo == null || keyNo.isEmpty()){
                Toast.makeText(this, "请重新插入智能卡", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "authentication: ".concat("未获取到智能卡号keyNo"));
                return;
            }
        }

        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_Authentication)
                .addParams("os","android")
                .addParams("keyNo", keyNo)
                .addParams("videoid", videoId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i("tag-Authentication2", response);
                        Gson gson = new Gson();
                        videoAuthenticationBean = gson.fromJson(response,VideoAuthenticationBean.class);

                        if(videoAuthenticationBean.isStatus()){
                            String params = videoAuthenticationBean.getParams();
                            String result = new String(Base64.decode(params.getBytes(), Base64.DEFAULT));
                            Gson gsonParam = new Gson();
                            VideoAuthenticationBean.ParamsBean paramBean = gsonParam.fromJson(result, VideoAuthenticationBean.ParamsBean.class);
                            //获取播放地址接口的url
                            String url = videoAuthenticationBean.getUrl();
                            String urlId = paramBean.getId();
                            String apiName = paramBean.getApiName();
                            String appAk = paramBean.getAppAk();
                            String sign = paramBean.getSign();
                            String timeStamp = paramBean.getTimeStamp();
                            Gson videoGson = new Gson();
                            Map<String, String> map = new HashMap<>();
                            map.put("id",urlId);
                            map.put("appAk",appAk);
                            map.put("apiName",apiName);
                            map.put("timeStamp",timeStamp);
                            map.put("sign",sign);
                            String jsonParam = videoGson.toJson(map);
                            //获取播放地址，并播放
                            getVideoUrl(url, jsonParam, videoId);
                        }else{
                            final String moduleid = videoAuthenticationBean.getModuleid();
                            final ConfirmDialog confirmDialog = new ConfirmDialog(VlcPlayerActivity.this, "您还未订购此视频，请按确定进行订购", "确定", "取消");
                            confirmDialog.show();
                            confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                                @Override
                                public void doConfirm() {
                                    confirmDialog.dismiss();
                                    finish();
                                    VipActivity.starActivity(VlcPlayerActivity.this, NetworkInterfaceUtils.Param_Template_Id, "", moduleid);
                                }

                                @Override
                                public void doCancel() {
                                    confirmDialog.dismiss();
                                }
                            });
                        }

                    }
                });
    }

    //获取播放地址，播放视频
    private void getVideoUrl(String url, String param, final String videoId){
        OkHttpUtils
                .postString()
                .url(url)
                .content(param)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .connTimeOut(10000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        Log.i(TAG, "onError: "+e.getMessage());
                        Toast.makeText(VlcPlayerActivity.this, "-_-||您的视频走丢了,请稍后再试试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i("tag-videoUrl", "videoUrl："+response);
                        Gson gson = new Gson();
                        videoUrlBean = gson.fromJson(response, VideoUrlBean.class);
                        Long code = videoUrlBean.getCode();
                        switch (code.intValue()){
                            case 0:
                                String screteData = videoUrlBean.getData();
//                                Log.i("tag-screteData2", "screteData: "+screteData);
                                try {
                                    String playAddress = AESUtils.decrypt(screteData, NetworkInterfaceUtils.Param_ScreteKey);
                                    Gson gAddr = new Gson();
                                    VideoUrlBean.UrlBean urlBean = gAddr.fromJson(playAddress, VideoUrlBean.UrlBean.class);
                                    String url = urlBean.getUrl();
//                                    Log.i("tag-playAddress", "playAddress: "+url);
                                    //播放记录
                                    setPlayRecord(videoId);
                                    Toast.makeText(VlcPlayerActivity.this, "即将播放下一个视频", Toast.LENGTH_SHORT).show();

                                    VideoHActivity.starActivity(VlcPlayerActivity.this, url, videoId, videoIdList);
                                    finish();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 100:
                                Toast.makeText(VlcPlayerActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 参数不合法");
                                break;
                            case 110:
                                Toast.makeText(VlcPlayerActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: ak不对");
                                break;
                            case 120:
                                Toast.makeText(VlcPlayerActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 签名不对");
                                break;
                            case 130:
                                Toast.makeText(VlcPlayerActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 接口名不对");
                                break;
                            case 140:
                                Toast.makeText(VlcPlayerActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 时间戳不对");
                                break;
                            case 150:
                                Toast.makeText(VlcPlayerActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 没有权限");
                                break;
                            case 160:
                                Toast.makeText(VlcPlayerActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 视频id=".concat(videoId).concat("的视频未上传或为转码"));
                                break;
                            case 500:
                                Toast.makeText(VlcPlayerActivity.this, "系统故障，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 系统故障");
                                break;
                            case 510:
                                Toast.makeText(VlcPlayerActivity.this, "系统繁忙", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 系统繁忙");
                                break;
                            default:
                                break;
                        }

                    }
                });
    }

    //记录观看记录
    public void setPlayRecord(String videoId){
//        String keyNo = SysUtil.getSystemProperties(VlcPlayerActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }else{
//                Log.i(TAG, "authentication: ".concat("未获取到智能卡号keyNo"));
//                return;
//            }

            keyNo = SysUtil.getSystemProperties(VlcPlayerActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
            
        }

        if(keyNo == null || keyNo.isEmpty()){
            Log.i(TAG, "setPlayRecord2: keyNo(智能卡号获取不到！,无法记录观看记录)");
            return;
        }
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InteFace_SetWatchRecord)
                .addParams("os","android")
                .addParams("keyNo", keyNo)
                .addParams("video_id", videoId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }
}
