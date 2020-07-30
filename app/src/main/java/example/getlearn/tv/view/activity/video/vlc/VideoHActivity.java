package example.getlearn.tv.view.activity.video.vlc;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.R;
import example.getlearn.tv.bean.VideoAuthenticationBean;
import example.getlearn.tv.bean.VideoUrlBean;
import example.getlearn.tv.util.AESUtils;
import example.getlearn.tv.util.ConfirmDialog;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.SeekBarUtils;
import example.getlearn.tv.util.SysUtil;
import example.getlearn.tv.view.activity.VipActivity;
import okhttp3.Call;
import okhttp3.MediaType;

import static android.content.ContentValues.TAG;

public class VideoHActivity extends Activity implements SurfaceHolder.Callback{
    private static final int msgKey = 1;
    @BindView(R.id.coship_video_view_play)
    SurfaceView coshipVideoViewPlay;
    @BindView(R.id.activity_video_progress_bar)
    SeekBarUtils activity_video_progress_bar;
    @BindView(R.id.activity_video_text_time)
    TextView activity_video_text_time;
    @BindView(R.id.activity_video_text_time_max)
    TextView activity_video_text_time_max;
    @BindView(R.id.activity_video_h_rel)
    RelativeLayout activity_video_h_rel;
    @BindView(R.id.activity_video_h_gif_lod)
    ImageView activity_video_h_gif_lod;
    @BindView(R.id.player_ui_play_toggle)
    ImageView player_ui_play_toggle;

    private MediaPlayer iMediaPlayer;
    private Timer timer;
    private TimerTask mTimerTask;
    private SurfaceHolder surfaceHolder;
    //false 进度条快进快退中         true 进度条正常
    private boolean booleanProgressSB=true;
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    private int CurrentPosition,intDuratioMax;
    private int CurrentInt;
    private  boolean PlayerBoolean = false;
    private  boolean TimerSeekBool = false;
    private  boolean LoadBoolean = true;
    private  boolean PreparedBoolean = true;
    private Timer timerDD;
    private TimerTask timerTaskDD;

    private  boolean intDDBoolean = false;

    private  String URL;
    private List<String> videoIdList;
    private String videoId;
    //智能卡号
    private String keyNo = "";
    private boolean hasKeyNo;
    private VideoAuthenticationBean videoAuthenticationBean;
    private VideoUrlBean videoUrlBean;

    public static VideoHActivity instance;

    public static void starActivity(Activity activity,String URL, String videoId, List<String> videoIdList) {
        Intent intent = new Intent(activity, VideoHActivity.class);
        intent.putExtra("URL",URL);
        intent.putStringArrayListExtra("videoIdList", (ArrayList<String>) videoIdList);
        intent.putExtra("videoId", videoId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_h);
        ButterKnife.bind(this);
        instance = this;

        initIntenData();

        // 初始化SurfaceHolder类，SurfaceView的控制器
        surfaceHolder = coshipVideoViewPlay.getHolder();
        surfaceHolder.addCallback(VideoHActivity.this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // 显示的分辨率,不设置为视频默认
//        surfaceHolder.setFixedSize(320, 220);
//        activity_video_h_gif_lod.setVisibility(View.GONE);
//        GlideGifLod();
    }

    private void initIntenData() {
        Intent intent = getIntent();
        //获取播放地址
        URL=intent.getStringExtra("URL");
        videoId = intent.getStringExtra("videoId");
        videoIdList = intent.getStringArrayListExtra("videoIdList");
    }

    private void initView() {

        activity_video_text_time_max.setText(GroceryStoreUtils.generateTime(iMediaPlayer.getDuration()));
        intDuratioMax = (int) iMediaPlayer.getDuration();
        activity_video_progress_bar.setMax(intDuratioMax);

        iMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                TimerSeekBool= true;
            }
        });

        iMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        iMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        iMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {

            }
        });
//        TimerSeekBool();

        //监听视频播放完毕
        iMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //续播
                for (int i=0; i<videoIdList.size(); i++){
                    if(videoIdList.get(i).equals(videoId) && i<videoIdList.size()-1){
                        videoId = videoIdList.get(i+1);
                        authentication(videoId);
                        break;
                    }
                    if(i == videoIdList.size()-1){
                        Toast.makeText(VideoHActivity.this, "最后一个视频也播放完了", Toast.LENGTH_SHORT).show();
                        mHandler.removeCallbacksAndMessages(null);
                        timer.cancel();
                        timer = null;
                        mTimerTask.cancel();
                        mTimerTask = null;
                        finish();
                    }
                }
                if(videoIdList.isEmpty()){
                    Toast.makeText(VideoHActivity.this, "视频播放完了", Toast.LENGTH_SHORT).show();
                    mHandler.removeCallbacksAndMessages(null);
                    timer.cancel();
                    timer = null;
                    mTimerTask.cancel();
                    mTimerTask = null;
                    finish();
                }
            }
        });
    }

    //鉴权
    private void authentication(final String videoId){
//        String keyNo = SysUtil.getSystemProperties(VlcPlayerActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
            keyNo = SysUtil.getSystemProperties(VideoHActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
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
                            final ConfirmDialog confirmDialog = new ConfirmDialog(VideoHActivity.this, "您还未订购此视频，请按确定进行订购,若无操作5秒后自动进入VIP订购页面", "确定", "取消");
                            confirmDialog.show();

                            final Handler handle = new Handler();
                            final Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    confirmDialog.dismiss();
                                    finish();
                                    timer.cancel();
                                    timer = null;
                                    mTimerTask.cancel();
                                    mTimerTask = null;
                                    mHandler.removeCallbacksAndMessages(null);
                                    VipActivity.starActivity(VideoHActivity.this, NetworkInterfaceUtils.Param_Template_Id, "", moduleid);
                                }
                            };
                            handle.postDelayed(runnable, 5000);

                            confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                                @Override
                                public void doConfirm() {
                                    handle.removeCallbacks(runnable);
                                    confirmDialog.dismiss();
                                    finish();
                                    timer.cancel();
                                    timer = null;
                                    mTimerTask.cancel();
                                    mTimerTask = null;
                                    mHandler.removeCallbacksAndMessages(null);
                                    VipActivity.starActivity(VideoHActivity.this, NetworkInterfaceUtils.Param_Template_Id, "", moduleid);
                                }

                                @Override
                                public void doCancel() {
                                    handle.removeCallbacks(runnable);
                                    confirmDialog.dismiss();
                                    timer.cancel();
                                    timer = null;
                                    mTimerTask.cancel();
                                    mTimerTask = null;
                                    mHandler.removeCallbacksAndMessages(null);
                                    finish();
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
                        Toast.makeText(VideoHActivity.this, "-_-||您的视频走丢了,请稍后再试试", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(VideoHActivity.this, "即将播放下一个视频", Toast.LENGTH_SHORT).show();

                                    timer.cancel();
                                    timer = null;
                                    mTimerTask.cancel();
                                    mTimerTask = null;
                                    mHandler.removeCallbacksAndMessages(null);

                                    VideoHActivity.starActivity(VideoHActivity.this, url, videoId, videoIdList);
                                    finish();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 100:
                                Toast.makeText(VideoHActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 参数不合法");
                                break;
                            case 110:
                                Toast.makeText(VideoHActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: ak不对");
                                break;
                            case 120:
                                Toast.makeText(VideoHActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 签名不对");
                                break;
                            case 130:
                                Toast.makeText(VideoHActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 接口名不对");
                                break;
                            case 140:
                                Toast.makeText(VideoHActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 时间戳不对");
                                break;
                            case 150:
                                Toast.makeText(VideoHActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 没有权限");
                                break;
                            case 160:
                                Toast.makeText(VideoHActivity.this, "视频播放失败，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 视频id=".concat(videoId).concat("的视频未上传或为转码"));
                                break;
                            case 500:
                                Toast.makeText(VideoHActivity.this, "系统故障，请联系客服人员", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 系统故障");
                                break;
                            case 510:
                                Toast.makeText(VideoHActivity.this, "系统繁忙", Toast.LENGTH_SHORT).show();
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
            keyNo = SysUtil.getSystemProperties(VideoHActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        playerStar();
    }

    private void playerStar( )   {
        iMediaPlayer = new MediaPlayer();
        iMediaPlayer.reset();
        iMediaPlayer.setDisplay(surfaceHolder);
        try {
            iMediaPlayer.setDataSource(this, Uri.parse(URL));
            iMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        iMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                iMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                iMediaPlayer.start();
                initView();
                initProgressbar();
                PreparedBoolean = false;
            }
        });

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey:
                    activity_video_progress_bar.setProgress((int) iMediaPlayer.getCurrentPosition());
                    activity_video_text_time.setText(GroceryStoreUtils.generateTime(iMediaPlayer.getCurrentPosition()));
                    break;
                case 2:
                    LoadBoolean=false;
                    seekToX(CurrentInt);
//                    activity_video_h_gif_lod.setVisibility(View.VISIBLE);
                    break;
                case 0:
                    break;
                case 3:
                    activity_video_h_rel.setVisibility(View.GONE);
//                    activity_video_h_gif_lod.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };

    private void goneSeekBar(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity_video_h_rel.setVisibility(View.GONE);
            }
        }, 3000);
    }

    private void initProgressbar() {
        activity_video_h_rel.setVisibility(View.GONE);

        timer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (booleanProgressSB==false){
                    if(PlayerBoolean ==false){
                        if (iMediaPlayer.getCurrentPosition()>CurrentInt){
                            booleanProgressSB =true;
                            LoadBoolean =true;
                            Message message = new Message();
                            message.what = 3;
                            mHandler.sendMessage(message);

                        }

                    }else {
                        if(iMediaPlayer.getCurrentPosition() <= CurrentInt){
                            booleanProgressSB =true;
                            LoadBoolean =true;
                            Message message = new Message();
                            message.what = 3;
                            mHandler.sendMessage(message);
                        }
                    }
                }else {
                    Message message = new Message();
                    message.what = 1;
                    mHandler.sendMessage(message);
                }

            }
        };
        timer.schedule(mTimerTask, 0, 1000);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }


    public void videoUP(){
        //首先获取seekbar拖动后的位置
        activity_video_h_rel.setVisibility(View.VISIBLE);
        if (CurrentPosition+10000<=intDuratioMax){
            CurrentPosition= activity_video_progress_bar.getProgress();
            booleanProgressSB = false;
            PlayerBoolean = false;
            int Position =CurrentPosition+10000;
            activity_video_progress_bar.setProgress((int) Position);
            CurrentInt= Position;
        }else {
            booleanProgressSB = false;
            PlayerBoolean = false;
            int Position =activity_video_progress_bar.getMax()-1000;
            activity_video_progress_bar.setProgress((int) Position);
            CurrentInt= Position;
        }

        activity_video_text_time.setText(GroceryStoreUtils.generateTime(CurrentInt));
    }
    private  void  seekToX(int page){
        iMediaPlayer.seekTo(page);
    }
    public void videoDow(){
        //首先获取seekbar拖动后的位置
        activity_video_h_rel.setVisibility(View.VISIBLE);
        if (CurrentPosition-10000>=0){
            CurrentPosition= activity_video_progress_bar.getProgress();
            booleanProgressSB = false;
            PlayerBoolean = true;
            int Position =CurrentPosition-10000;
            activity_video_progress_bar.setProgress((int) Position);
            CurrentInt= Position;
        }else {
            booleanProgressSB = false;
            PlayerBoolean = true;
            int Position =1000;
            activity_video_progress_bar.setProgress((int) Position);
            CurrentInt= Position;
        }

        activity_video_text_time.setText(GroceryStoreUtils.generateTime(CurrentInt));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iMediaPlayer.isPlaying()) {
            iMediaPlayer.stop();
        }
        iMediaPlayer.release();

        mHandler.removeCallbacksAndMessages(null);
        timer = null;
        mTimerTask =null;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT: //向左键
                Log.d(TAG, "left--->");
                if (PreparedBoolean==false) {
                    if (LoadBoolean == true) {
                        TimerSeekBool();
                    }
                }
                break ;

            case KeyEvent.KEYCODE_DPAD_RIGHT:  //向右键
                Log.d(TAG, "right--->");
                if (PreparedBoolean==false) {
                    if (LoadBoolean == true) {
                        TimerSeekBool();
                    }
                }
                break;

            default:
                break;
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onUserLeaveHint() {
        mHandler.removeCallbacksAndMessages(null);
        timer.cancel();
        timer = null;
        mTimerTask.cancel();
        mTimerTask = null;
        finish();
        super.onUserLeaveHint();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {

            case KeyEvent.KEYCODE_ENTER:     //确定键enter
            case KeyEvent.KEYCODE_DPAD_CENTER:
                Log.d(TAG, "enter--->");
                //start 开始         pause 暂停
                if (iMediaPlayer.isPlaying()){
                    player_ui_play_toggle.setImageResource(R.drawable.exo_controls_play);
                    activity_video_h_rel.setVisibility(View.VISIBLE);
                    activity_video_h_gif_lod.setVisibility(View.VISIBLE);
                    goneSeekBar();
                    iMediaPlayer.pause();
                }else {
                    player_ui_play_toggle.setImageResource(R.drawable.exo_controls_pause);
                    activity_video_h_rel.setVisibility(View.VISIBLE);
                    activity_video_h_gif_lod.setVisibility(View.GONE);
                    goneSeekBar();
                    iMediaPlayer.start();
                }
                break;

            case KeyEvent.KEYCODE_BACK:    //返回键
                Log.d(TAG, "back--->");
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((System.currentTimeMillis() - exitTime) > 2000) {
                        //弹出提示，可以有多种方式
                        Toast.makeText(getApplicationContext(), "再按一次退出播放！", Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        mHandler.removeCallbacksAndMessages(null);
                        timer.cancel();
                        timer = null;
                        mTimerTask.cancel();
                        mTimerTask = null;
                        finish();
                    }
                    return true;
                }
                break;   //这里由于break会退出，所以我们自己要处理掉 不返回上一层

            case KeyEvent.KEYCODE_DPAD_LEFT: //向左键
                Log.d(TAG, "left--->");
                if (PreparedBoolean==false) {
                    if (LoadBoolean == true) {
                        videoDow();
                        timerDDNull();
                    }
                }
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:  //向右键
                Log.d(TAG, "right--->");
                if (PreparedBoolean==false){
                    if (LoadBoolean ==true){
                        videoUP();
                        timerDDNull();
                    }
                }

                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }



    private void timerDDNull(){
        if (timerDD !=null){
            timerDD.cancel();
            timerDD =null;
            timerTaskDD.cancel();
            timerTaskDD=null;
        }
    }
    private void TimerSeekBool(){
        activity_video_h_rel.setVisibility(View.VISIBLE);
        timerDD = new Timer();
        timerTaskDD = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessage(message);
            }
        };
        timerDD.schedule(timerTaskDD,2000);
    }

//    private void GlideGifLod(){
//        Glide.with(this).load(R.drawable.gificon)
//                .asGif()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(activity_video_h_gif_lod);
//    }

}




