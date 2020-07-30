package example.getlearn.tv.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.R;
import example.getlearn.tv.adapter.WatchTheRecordAdapter;
import example.getlearn.tv.bean.VideoAuthenticationBean;
import example.getlearn.tv.bean.VideoUrlBean;
import example.getlearn.tv.bean.WatchTheRecordBean;
import example.getlearn.tv.controller.SMSBroadcastReceiver;
import example.getlearn.tv.util.AESUtils;
import example.getlearn.tv.util.ConfirmDialog;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import example.getlearn.tv.util.SysUtil;
import example.getlearn.tv.view.activity.video.vlc.VideoHActivity;
import example.getlearn.tv.view.activity.video.vlc.VlcPlayerActivity;
import okhttp3.Call;
import okhttp3.MediaType;

import static android.content.ContentValues.TAG;

/*****
 *
 *观看记录
 *
 * */

public class WatchTheRecordActivity extends Activity {
    @BindView(R.id.activity_wathc_the_record_rec_list)
    RecyclerView activityWathcTheRecordRecList;
    @BindView(R.id.activity_watch_the_record_text_but_up)
    TextView activityWatchTheRecordTextButUp;
    @BindView(R.id.activity_watch_the_record_text_but_low)
    TextView activityWatchTheRecordTextButLow;
    @BindView(R.id.activiry_watch_the_record_home)
    ImageView activiryWatchTheRecordHome;
    @BindView(R.id.activiry_watch_the_record_src)
    ImageView activiryWatchTheRecordSrc;
    @BindView(R.id.activiry_watch_the_record_esc)
    ImageView activiryWatchTheRecordEsc;
    @BindView(R.id.activity_watch_the_record_text_02)
    TextView activity_watch_the_record_text_02;
    @BindView(R.id.activity_watch_the_record_text_03)
    TextView activity_watch_the_record_text_03;
    @BindView(R.id.activity_watch_the_record_text_04)
    TextView activity_watch_the_record_text_04;
    @BindView(R.id.activity_watch_the_record_text_06)
    TextView activity_watch_the_record_text_06;
    @BindView(R.id.activity_watch_the_record_rec_laytou_bg)
    RelativeLayout activity_watch_the_record_rec_laytou_bg;
    @BindView(R.id.watch_record_page)
    LinearLayout watch_record_page;

    private String Template_id,bag_url;
    public static void starActivity(Activity activity,String Template_id,String bag_url) {
        Intent intent = new Intent(activity, WatchTheRecordActivity.class);
        intent.putExtra("Template_id",Template_id);
        intent.putExtra("bag_url",bag_url);
        activity.startActivity(intent);
    }
    private int page;
    private int MAX_page;

    private WatchTheRecordAdapter watchTheRecordAdapter;
    private WatchTheRecordBean watchTheRecordBean;
    private List<WatchTheRecordBean.DataBean> data = new ArrayList<>();
    private VideoAuthenticationBean videoAuthenticationBean;
    private VideoUrlBean videoUrlBean;
    //刚进入页面
    private boolean firstEnterPage;
    //智能卡号
    private String keyNo = "";
    private boolean hasKeyNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntenData();
        //选择模板
        switch (Template_id){
            case "1":
                setContentView(R.layout.activity_watch_the_record);
                break;
        }
        ButterKnife.bind(this);
        page =1;
        //网络检测
        if (NetworkUtils.isNetWorkAvailable(this)){
            watch_record_page.setVisibility(View.INVISIBLE);
            initData(page);
            initAdapter();
            initView();
        }else {
            Toast.makeText(this, "网络连接失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        firstEnterPage = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        firstEnterPage = false;
        super.onPause();
    }

    private void initIntenData() {
        Intent intent = getIntent();
        Template_id = intent.getStringExtra("Template_id");
        bag_url = intent.getStringExtra("bag_url");
    }

    private void initView() {
        //bg
        GroceryStoreUtils.GlideBG(WatchTheRecordActivity.this,bag_url,activity_watch_the_record_rec_laytou_bg);

        //时间
        activity_watch_the_record_text_06.setText(GroceryStoreUtils.getSimpleDateFprmat("yyyy年MM月dd日")+"  "+GroceryStoreUtils.getWeek(GroceryStoreUtils.getSimpleDateFprmat("yyyy-MM-dd")));

        activityWatchTheRecordTextButLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page<MAX_page){
                data.clear();
                initData(++page);
                }else {
//                    Toast.makeText(WatchTheRecordActivity.this, "已经到底了！", Toast.LENGTH_SHORT).show();
                }

            }
        });
        activityWatchTheRecordTextButUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page>1){
                    data.clear();
                    initData(--page);
                }

            }
        });
        activiryWatchTheRecordHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activiryWatchTheRecordSrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.starActivity(WatchTheRecordActivity.this);
            }
        });
        activiryWatchTheRecordEsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        watchTheRecordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String videoId = data.get(position).getVideoid();
                authentication(videoId, new ArrayList<String>());
//                Toast.makeText(WatchTheRecordActivity.this, data.get(position).getCourse_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAdapter() {
        activityWathcTheRecordRecList.setLayoutManager(new GridLayoutManager(this, 3));
        watchTheRecordAdapter = new WatchTheRecordAdapter(R.layout.adapte_watch_the_record_item, data);
        activityWathcTheRecordRecList.setAdapter(watchTheRecordAdapter);

    }
    private void initData(final int p) {
//        String keyNo = SysUtil.getSystemProperties(WatchTheRecordActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(WatchTheRecordActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
        }
        if(keyNo == null || keyNo.isEmpty()){
            Toast.makeText(WatchTheRecordActivity.this, "观看记录获取失败，请插入机顶盒智能卡后重试！", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "initData: keyNo(智能卡号获取不到！)");
            return;
        }
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_VWatchTheRecord)
                .addParams("os","android")
                .addParams("p",String.valueOf(p))
                .addParams("keyNo", keyNo)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse-watchRecord: "+response);

                        Gson gson = new Gson();
                        watchTheRecordBean = gson.fromJson(response,WatchTheRecordBean.class);
                        data = watchTheRecordBean.getData();
                        watchTheRecordAdapter.setNewData(data);
                        activity_watch_the_record_text_03.setText(String.valueOf(page));

                        if (page==1){
                            MAX_page = watchTheRecordBean.getPage();
                            activity_watch_the_record_text_02.setText("共"+watchTheRecordBean.getCount()+"个视频");
                            activity_watch_the_record_text_04.setText(String.valueOf("/"+watchTheRecordBean.getPage()));
//                            GroceryStoreUtils.GlideBG(WatchTheRecordActivity.this,NetworkInterfaceUtils.InterFace_Imag + watchTheRecordBean.getPublic_skin_img(),activity_watch_the_record_rec_laytou_bg);
                        }

                        if(p == 1 && watchTheRecordBean.getPage() <= 1){// 当前页为第一页，并且只有一页
                            activityWatchTheRecordTextButUp.setVisibility(View.INVISIBLE);
                            activityWatchTheRecordTextButLow.setVisibility(View.INVISIBLE);
                        }else if(p == 1 && watchTheRecordBean.getPage() > 1){// 当前页为第一页，并且总页数大于一页
                            activityWatchTheRecordTextButUp.setVisibility(View.INVISIBLE);
                            activityWatchTheRecordTextButLow.setVisibility(View.VISIBLE);
                            if(!firstEnterPage){
                                activityWatchTheRecordTextButLow.requestFocus();
                            }
                            firstEnterPage = false;

                        }else if(p == watchTheRecordBean.getPage()){// 当前页是最后一页
                            activityWatchTheRecordTextButUp.setVisibility(View.VISIBLE);
                            activityWatchTheRecordTextButLow.setVisibility(View.INVISIBLE);
                            activityWatchTheRecordTextButUp.requestFocus();
                        }else{// 当前页不是第一页，也不是最后一页
                            activityWatchTheRecordTextButUp.setVisibility(View.VISIBLE);
                            activityWatchTheRecordTextButLow.setVisibility(View.VISIBLE);
                        }

                        watch_record_page.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    //鉴权
    private void authentication(final String videoId, final List<String> videoIdList){
//        String keyNo = SysUtil.getSystemProperties(WatchTheRecordActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);

        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(WatchTheRecordActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
        }

        if(keyNo == null || keyNo.isEmpty()){
            Toast.makeText(this, "播放失败，请插入智能卡后重试", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "authentication: ".concat("为获取到智能卡号keyNo，无法进行鉴权操作"));
            return;
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
//                        Log.i("tag-Authentication", response);
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
                            getVideoUrl(url, jsonParam, videoId, videoIdList);
                        }else{
                            final String moduleid = videoAuthenticationBean.getModuleid();
                            final ConfirmDialog confirmDialog = new ConfirmDialog(WatchTheRecordActivity.this, "您还未订购此视频，请按确定进行订购,若无操作5秒后自动进入VIP订购页面", "确定", "取消");
                            confirmDialog.show();

                            final Handler handle = new Handler();
                            final Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    confirmDialog.dismiss();
                                    VipActivity.starActivity(WatchTheRecordActivity.this, NetworkInterfaceUtils.Param_Template_Id, "", moduleid);
                                }
                            };
                            handle.postDelayed(runnable, 5000);

                            confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                                @Override
                                public void doConfirm() {
                                    handle.removeCallbacks(runnable);
                                    confirmDialog.dismiss();
                                    VipActivity.starActivity(WatchTheRecordActivity.this, NetworkInterfaceUtils.Param_Template_Id, "", moduleid);
                                }

                                @Override
                                public void doCancel() {
                                    handle.removeCallbacks(runnable);
                                    confirmDialog.dismiss();
                                }
                            });

                        }

                    }
                });
    }

    //获取播放地址，播放视频
    private void getVideoUrl(String url, String param, final String videoId, final List<String> videoIdList){
//        Log.i(TAG, "getVideoUrl: "+param);
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
                        Toast.makeText(WatchTheRecordActivity.this, "-_-||您的视频走丢了,请稍后再试试", Toast.LENGTH_SHORT).show();
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
//                                Log.i("tag-screteData", "screteData: "+screteData);
                                try {
                                    String playAddress = AESUtils.decrypt(screteData, NetworkInterfaceUtils.Param_ScreteKey);
                                    Gson gAddr = new Gson();
                                    VideoUrlBean.UrlBean urlBean = gAddr.fromJson(playAddress, VideoUrlBean.UrlBean.class);
                                    String url = urlBean.getUrl();
//                                    Log.i("tag-playAddress", "playAddress: "+url);
                                    VideoHActivity.starActivity(WatchTheRecordActivity.this, url, videoId, videoIdList);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 100:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 参数不合法");
                                break;
                            case 110:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: ak不对");
                                break;
                            case 120:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 签名不对");
                                break;
                            case 130:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 接口名不对");
                                break;
                            case 140:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 时间戳不对");
                                break;
                            case 150:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 没有权限");
                                break;
                            case 160:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 视频id=".concat(videoId).concat("的视频未上传或为转码"));
                                break;
                            case 500:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 系统故障");
                                break;
                            case 510:
                                Toast.makeText(WatchTheRecordActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 系统繁忙");
                                break;
                            default:
                                break;
                        }

                    }
                });
    }
}
