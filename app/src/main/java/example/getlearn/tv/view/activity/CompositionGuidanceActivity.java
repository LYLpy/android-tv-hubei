package example.getlearn.tv.view.activity;


/****
 *
 * 作文指导
 * */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import example.getlearn.tv.util.OnItemSelectedListener;
import com.maywide.gdpay.PayContent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.MainActivity;
import example.getlearn.tv.R;
import example.getlearn.tv.adapter.CompositionGuidanceAdapter;
import example.getlearn.tv.adapter.CompositionGuidanceXAdapter;
import example.getlearn.tv.bean.CompositionGuidanceBean;
import example.getlearn.tv.bean.DataFabulousBean;
import example.getlearn.tv.bean.KeyNoBean;
import example.getlearn.tv.bean.OrderInfoBean;
import example.getlearn.tv.bean.VideoAuthenticationBean;
import example.getlearn.tv.bean.VideoUrlBean;
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


public class CompositionGuidanceActivity extends Activity {

    @BindView(R.id.activity_composition_rec_list_01)
    TvRecyclerView activityCompositionRecList01;
    @BindView(R.id.activity_composition_rec_list_02)
    TvRecyclerView activityCompositionRecList02;
    @BindView(R.id.activity_composition_text_rec_up)
    TextView activityCompositionTextRecUp;
    @BindView(R.id.activity_composition_text_rec_low)
    TextView activityCompositionTextRecLow;
    @BindView(R.id.activity_composition_text_page)
    TextView activityCompositionTextPage;
    @BindView(R.id.activity_composition_text_max)
    TextView activityCompositionTextMax;
    @BindView(R.id.activity_composition_guidance)
    ImageView activity_composition_guidance;
    @BindView(R.id.activity_composition_text_title_01)
    TextView activity_composition_text_title_01;
    @BindView(R.id.activity_composition_text_title_02)
    TextView activity_composition_text_title_02;
    @BindView(R.id.activity_composition_guidance_bg)
    LinearLayout activity_composition_guidance_bg;
    @BindView(R.id.activity_composition_text_button_01)
    TextView activity_composition_text_button_01;
    @BindView(R.id.activity_composition_text_button_02)
    TextView activity_composition_text_button_02;
    @BindView(R.id.activity_composition_text_button_03)
    TextView activity_composition_text_button_03;
    @BindView(R.id.activity_composition_text_button_04)
    TextView activity_composition_text_button_04;

    private List<CompositionGuidanceBean.VideoListBean> data_text = new ArrayList<>();
    private List<CompositionGuidanceBean.RecommendListBean> data_Recommend = new ArrayList<>();
    private CompositionGuidanceBean compositionGuidanceBean;
    private DataFabulousBean dataFabulousBean;
    private CompositionGuidanceAdapter compositionGuidanceAdapter;
    private CompositionGuidanceXAdapter compositionGuidanceXAdapter;
    private CompositionGuidanceBean.VideoListBean videoListBean;

    private String Template_id,id,types,FabulousID;
    private int page;
    private int type=1;
    private int Max_page;
    private  LinearLayoutManager linearLayoutManager;
    private VideoAuthenticationBean videoAuthenticationBean;
    private VideoUrlBean videoUrlBean;
    private boolean KEYCODE_DPAD_UP_XXX=false;
    private int SelectedInt = 1;
    private static List<String> videoIdList = new ArrayList<>();
    //刚进入页面
    private boolean firstEnterPage;
    //智能卡号
    private String keyNo = "";
    private boolean hasKeyNo;
    // 最后一次点击视频列表记录的时间
    private long lastclicktime = 0;
    // 点击视频列表记录的最大时间间隔
    private static final long TIME_INTERVAL = 14000;

    public static void starActivity(Activity activity, String Template_id,String id,String types) {
        Intent intent = new Intent(activity, CompositionGuidanceActivity.class);
        intent.putExtra("Template_id", Template_id);
        intent.putExtra("id", id);
        intent.putExtra("types", types);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntenData();
        //选择模板
        switch (Template_id) {
            case "1":
                setContentView(R.layout.activity_composition_guidance);
                break;
        }
        page=1;
        ButterKnife.bind(this);
        //网络检测
        if (NetworkUtils.isNetWorkAvailable(this)){
            initAdapter();
            //获取当前课程所有视频id
            getCourseVideoId(id);
            initData(page,id,types);
            initView();
            //注册EventBus
            EventBus.getDefault().register(this);
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
        id = intent.getStringExtra("id");
        types = intent.getStringExtra("types");
    }


    private void initView() {

        activity_composition_text_button_01.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            KEYCODE_DPAD_UP_XXX = false;
                        }
                    }, 200);
                    SelectedInt=2;
                }else {
                }
            }
        });

        activityCompositionTextRecUp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //选中
                    SelectedInt=2;
                }else {
                }
            }
        });

        //点赞
        activity_composition_text_button_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCompositionRecList02.setItemSelected(0);
                initDataFabulous(FabulousID,NetworkInterfaceUtils.InterFace_Three_level_Fabulous,1);
            }
        });
        //收藏
        activity_composition_text_button_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDataFabulous(FabulousID,NetworkInterfaceUtils.InterFace_Three_level_Hide,2);
            }
        });
        //上册
        activity_composition_text_button_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=1;
                type=1;

                initDataBook(page,id,types,"1");
            }
        });
        //下册
        activity_composition_text_button_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=1;
                type=1;
                initDataBook(page,id,types,"2");
            }
        });

//        上一页
        activityCompositionTextRecUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page>1){
                    data_text.clear();
                    initData(--page,id,types);
                }
            }
        });
        //下一页
        activityCompositionTextRecLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page<Max_page){
                    data_text.clear();
                    initData(++page,id,types);
                }else {
                    Toast.makeText(CompositionGuidanceActivity.this, "已经到底了！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //展示标题
        activity_composition_text_title_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogActivity.starDialog02Activity(CompositionGuidanceActivity.this,"2",activity_composition_text_title_02.getText().toString());
            }
        });
                activityCompositionRecList02.requestFocus();
                activityCompositionRecList02.setItemSelected(0);

    }



    private void initDataFabulous(String id, String inter, final int youName) {
//        String keyNo = SysUtil.getSystemProperties(CompositionGuidanceActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(CompositionGuidanceActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);

        }
        if(keyNo == null || keyNo.isEmpty()){
            String operation = "";
            switch (youName){
                case 1:
                    operation = "点赞失败！请插入机顶盒智能卡后重试！";
                    break;
                case 2:
                    operation = "收藏失败！请插入机顶盒智能卡后重试！";
                    break;
                default:
                    break;
            }
            Log.i(TAG, "initDataFabulous: keyNo(智能卡号获取不到！)");
        }

        Log.e("ssssssss",id+keyNo);
//        点赞
        OkHttpUtils
                .post()
                .url(inter)
                .addParams("os", "android")
                .addParams("id", String.valueOf(id))
                .addParams("keyNo", keyNo)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        dataFabulousBean = gson.fromJson(response,DataFabulousBean.class);
                        if (youName == 1){
                            activity_composition_text_button_01.setText(dataFabulousBean.getText());
                        }else {
                            activity_composition_text_button_02.setText(dataFabulousBean.getText());
                        }
                    }
                });
    }


    private void initDataBook(int p,String ids,String typeX,String xueqi_id) {
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_Three_level_book)
                .addParams("xueqi_id",xueqi_id)
                .addParams("os", "android")
                .addParams("p", String.valueOf(p))
                .addParams("id", ids)
                .addParams("type", typeX)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        compositionGuidanceBean = gson.fromJson(response, CompositionGuidanceBean.class);
                        data_text = compositionGuidanceBean.getVideoList();
                        compositionGuidanceAdapter.setNewData(data_text);
                        activityCompositionTextPage.setText(String.valueOf(page));
                        compositionGuidanceAdapter.notifyDataSetChanged();
                        if (page==1){
                            Max_page = compositionGuidanceBean.getPages();
                            activityCompositionTextMax.setText(String.valueOf("/"+Max_page));
                        }
                        if (type==1){
                            data_Recommend = compositionGuidanceBean.getRecommendList();
                            compositionGuidanceXAdapter.setNewData(data_Recommend);
                            //标题内容大集合
                            FabulousID=compositionGuidanceBean.getCourseInfo().getId();
                            Glide
                                    .with(CompositionGuidanceActivity.this)
                                    .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+compositionGuidanceBean.getCourseInfo().getIcon())
                                    .error(R.drawable.nimg168_1)
                                    .into(activity_composition_guidance);
                            activity_composition_text_title_01.setText(compositionGuidanceBean.getCourseInfo().getName());
                            activity_composition_text_title_02.setText(compositionGuidanceBean.getCourseInfo().getContent());
                            if (compositionGuidanceBean.getPraiseFlag()==1){
//                                1 点赞            0未点赞
                                activity_composition_text_button_01.setText("已点赞");
                            }else {
                                activity_composition_text_button_01.setText("点赞");
                            }
                            if (compositionGuidanceBean.getCollenctionFlag()==1){
//                                1 收藏           0未收藏
                                activity_composition_text_button_02.setText("已收藏");
                            }else {
                                activity_composition_text_button_02.setText("收藏");
                            }
                            if (compositionGuidanceBean.getXueqiFlag()==1){
//                                1 存在            0不存在
                                activity_composition_text_button_03.setVisibility(View.VISIBLE);
                                activity_composition_text_button_04.setVisibility(View.VISIBLE);
                            }else {
                                activity_composition_text_button_03.setVisibility(View.INVISIBLE);
                                activity_composition_text_button_04.setVisibility(View.INVISIBLE);
                            }
                            type=2;
                        }
                    }
                });
    }

    private void initData(final int p, String ids, String typeX) {
//        String keyNo = SysUtil.getSystemProperties(CompositionGuidanceActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(CompositionGuidanceActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);

        }
        if(keyNo == null || keyNo.isEmpty()){
            Toast.makeText(CompositionGuidanceActivity.this, "无法获取收藏、点赞信息，请插入机顶盒智能卡后重试！", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "initData: keyNo(智能卡号获取不到！)");
        }
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_Three_level)
                .addParams("os", "android")
                .addParams("p", String.valueOf(p))
                .addParams("id", ids)
                .addParams("type", typeX)
                .addParams("keyNo", keyNo)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("sss",response);
                        Gson gson = new Gson();
                        compositionGuidanceBean = gson.fromJson(response, CompositionGuidanceBean.class);
                        data_text = compositionGuidanceBean.getVideoList();
                        compositionGuidanceAdapter.setNewData(data_text);
                        activityCompositionTextPage.setText(String.valueOf(page));
                        compositionGuidanceAdapter.notifyDataSetChanged();
                        if (page==1){
                            Max_page = compositionGuidanceBean.getPages();
                            activityCompositionTextMax.setText(String.valueOf("/"+Max_page));
                            activityCompositionTextPage.setVisibility(View.VISIBLE);
                            activityCompositionTextMax.setVisibility(View.VISIBLE);
                        }
                        if (type==1){
                            data_Recommend = compositionGuidanceBean.getRecommendList();
                            compositionGuidanceXAdapter.setNewData(data_Recommend);
                            //标题内容大集合
                            if (compositionGuidanceBean.getCourseInfo()!=null && !compositionGuidanceBean.getCourseInfo().toString().isEmpty()){
                                FabulousID=compositionGuidanceBean.getCourseInfo().getId();
                                Glide
                                        .with(CompositionGuidanceActivity.this)
                                        .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+compositionGuidanceBean.getCourseInfo().getIcon())
                                        .error(R.drawable.nimg168_1)
                                        .into(activity_composition_guidance);
                                activity_composition_text_title_01.setText(compositionGuidanceBean.getCourseInfo().getName());
                                activity_composition_text_title_02.setText(compositionGuidanceBean.getCourseInfo().getContent());
                            }

                            if (compositionGuidanceBean.getPraiseFlag()==1){
//                                1 点赞            0未点赞
                                activity_composition_text_button_01.setText("已点赞");
                            }else {
                                activity_composition_text_button_01.setText("点赞");
                            }
                            if (compositionGuidanceBean.getCollenctionFlag()==1){
//                                1 收藏           0未收藏
                                activity_composition_text_button_02.setText("已收藏");
                            }else {
                                activity_composition_text_button_02.setText("收藏");
                            }
                            if (compositionGuidanceBean.getXueqiFlag()==1){
//                                1 存在            0不存在
                                activity_composition_text_button_03.setVisibility(View.VISIBLE);
                                activity_composition_text_button_04.setVisibility(View.VISIBLE);
                            }else {
                                activity_composition_text_button_03.setVisibility(View.INVISIBLE);
                                activity_composition_text_button_04.setVisibility(View.INVISIBLE);
                            }

                            type=2;
                        }

                        if(p == 1 && compositionGuidanceBean.getPages() <= 1){// 当前页为第一页，并且只有一页
                            activityCompositionTextRecUp.setVisibility(View.INVISIBLE);
                            activityCompositionTextRecLow.setVisibility(View.INVISIBLE);
                        }else if(p == 1 && compositionGuidanceBean.getPages() > 1){// 当前页为第一页，并且总页数大于一页
                            activityCompositionTextRecUp.setVisibility(View.INVISIBLE);
                            activityCompositionTextRecLow.setVisibility(View.VISIBLE);
                            if(!firstEnterPage){// 设置焦点，如果不是第一次进入页面则焦点到下一页
                                activityCompositionTextRecLow.requestFocus();
                            }
                            firstEnterPage = false;
                        }else if(p == compositionGuidanceBean.getPages()){// 当前页是最后一页
                            activityCompositionTextRecUp.setVisibility(View.VISIBLE);
                            activityCompositionTextRecLow.setVisibility(View.INVISIBLE);
                            activityCompositionTextRecUp.requestFocus();
                        }else{// 当前页不是第一页，也不是最后一页
                            activityCompositionTextRecUp.setVisibility(View.VISIBLE);
                            activityCompositionTextRecLow.setVisibility(View.VISIBLE);
                        }

                    }
                });
    }

    //找到数组中的最大值
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private void initAdapter() {

//        目录
        // 设置布局管理器：瀑布流式
        linearLayoutManager = new LinearLayoutManager(this);
        activityCompositionRecList02.setLayoutManager(linearLayoutManager);
        // 根据需要设置间距等
        activityCompositionRecList02.setItemAnimator(null);
        compositionGuidanceAdapter = new CompositionGuidanceAdapter(R.layout.adapter_composition_item, data_text);
        compositionGuidanceAdapter.setHasStableIds(true);
        compositionGuidanceAdapter.setOnItemSelectedListener(onItemSelectedListenerw);
        activityCompositionRecList02.setAdapter(compositionGuidanceAdapter);

        activityCompositionRecList02.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //当前RecyclerView显示出来的最后一个的item的position
                int lastPosition = -1;

                //当前状态为停止滑动状态SCROLL_STATE_IDLE时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        //通过LayoutManager找到当前显示的最后的item的position
                        lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                        //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                        int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                        lastPosition = findMax(lastPositions);
                    }

                    //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                    //如果相等则说明已经滑动到最后了
                    if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
//                        Toast.makeText(CompositionGuidanceActivity.this, "滑动到底了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        compositionGuidanceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (System.currentTimeMillis() - lastclicktime >= TIME_INTERVAL){
                    // 关闭上一个视频播放的activity，保证只存在一个视频播放窗口
                    if(VideoHActivity.instance != null){
                        VideoHActivity.instance.finish();
                    }
                    lastclicktime = System.currentTimeMillis();
                    String videoId = data_text.get(position).getId();
                    //鉴权
                    authentication(videoId, videoIdList);
                }else{
                    Log.i(TAG, "重复点击同一视频");
                }
            }
        });

        //课程推荐
        // 设置布局管理器：瀑布流式
        activityCompositionRecList01.setLayoutManager(new GridLayoutManager(this, 4));
        // 根据需要设置间距等

        compositionGuidanceXAdapter = new CompositionGuidanceXAdapter(R.layout.adapter_composition_x_item, data_Recommend);
        activityCompositionRecList01.setAdapter(compositionGuidanceXAdapter);
        compositionGuidanceXAdapter.setOnItemSelectedListener(onItemSelectedListener);
        compositionGuidanceXAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                finish();
                CompositionGuidanceActivity.starActivity(CompositionGuidanceActivity.this,"1",
                        data_Recommend.get(position).getId()
                        ,"course");
            }
        });

        activityCompositionTextRecUp.setVisibility(View.INVISIBLE);
        activityCompositionTextRecLow.setVisibility(View.INVISIBLE);
        activityCompositionTextPage.setVisibility(View.INVISIBLE);
        activityCompositionTextMax.setVisibility(View.INVISIBLE);
    }

    OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {
            //课程列表
            SelectedInt = 1;
        }
    };
    OnItemSelectedListener onItemSelectedListenerw = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {
            //视频列表
            SelectedInt = 2;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {

            case KeyEvent.KEYCODE_ENTER:     //确定键enter
            case KeyEvent.KEYCODE_DPAD_CENTER:
//                Log.d(TAG, "enter--->");

                break;

            case KeyEvent.KEYCODE_BACK:    //返回键
//                Log.d(TAG, "back--->");
                finish();
                break;   //这里由于break会退出，所以我们自己要处理掉 不返回上一层

            case KeyEvent.KEYCODE_SETTINGS: //设置键
//                Log.d(TAG, "setting--->");

                break;

            case KeyEvent.KEYCODE_DPAD_DOWN:   //向下键

                /*    实际开发中有时候会触发两次，所以要判断一下按下时触发 ，松开按键时不触发
                 *    exp:KeyEvent.ACTION_UP
                 */
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    Log.d(TAG, "down--->");
                }

                break;

            case KeyEvent.KEYCODE_DPAD_UP:   //向上键
//                Log.d(TAG, "up--->");
//                if(activityCompositionRecList01.hasFocus()){
//                    switch (compositionGuidanceXAdapter.getCurrentPosition()){
//                        case 0:
//                            activity_composition_text_button_01.setFocusable(true);
//                            activity_composition_text_button_01.requestFocus();
//                            break;
//                        case 1:
//                            activity_composition_text_button_01.setFocusable(true);
//                            activity_composition_text_button_01.requestFocus();
//                            break;
//                        case 2:
//                            activity_composition_text_button_01.setFocusable(true);
//                            activity_composition_text_button_01.requestFocus();
//                            break;
//                        case 3:
//                            activity_composition_text_button_01.setFocusable(true);
//                            activity_composition_text_button_01.requestFocus();
//                            break;
//                    }
//                }

                switch (compositionGuidanceXAdapter.getCurrentPosition()){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        if (SelectedInt==1){
                            KEYCODE_DPAD_UP_XXX=true;
                            activity_composition_text_button_01.requestFocus();
                            break;
                        }
                }
                return KEYCODE_DPAD_UP_XXX;
//                break;

            case KeyEvent.KEYCODE_0:   //数字键0
//                Log.d(TAG, "0--->");

                break;

            case KeyEvent.KEYCODE_DPAD_LEFT: //向左键
//                Log.d(TAG, "left--->");
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:  //向右键
//                Log.d(TAG, "right--->");
                break;

            case KeyEvent.KEYCODE_INFO:    //info键
//                Log.d(TAG, "info--->");

                break;

            case KeyEvent.KEYCODE_PAGE_DOWN:     //向上翻页键
            case KeyEvent.KEYCODE_MEDIA_NEXT:
//                Log.d(TAG, "page down--->");

                break;


            case KeyEvent.KEYCODE_PAGE_UP:     //向下翻页键
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
//                Log.d(TAG, "page up--->");

                break;

            case KeyEvent.KEYCODE_VOLUME_UP:   //调大声音键
//                Log.d(TAG, "voice up--->");

                break;

            case KeyEvent.KEYCODE_VOLUME_DOWN: //降低声音键
//                Log.d(TAG, "voice down--->");

                break;
            case KeyEvent.KEYCODE_VOLUME_MUTE: //禁用声音
//                Log.d(TAG, "voice mute--->");
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册EventBus
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(KeyNoBean keyNoBean) {
        //获取智能卡id
        keyNo = keyNoBean.getKeyNo();
    }

    //鉴权
    private void authentication(final String videoId, final List<String> videoIdList){
//        String keyNo = SysUtil.getSystemProperties(CompositionGuidanceActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(CompositionGuidanceActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);

        }

        if(keyNo == null || keyNo.isEmpty()){
            Log.i(TAG, "authentication: ".concat("为获取智能卡号keyNo"));
            Toast.makeText(this, "未检测到智能卡，请插入智能卡后重试", Toast.LENGTH_SHORT).show();
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
                            final ConfirmDialog confirmDialog = new ConfirmDialog(CompositionGuidanceActivity.this, "您还未订购此视频，请按确定进行订购,若无操作5秒后自动进入VIP订购页面", "确定", "取消");
                            confirmDialog.show();

                            final Handler handle = new Handler();
                            final Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    confirmDialog.dismiss();
                                    VipActivity.starActivity(CompositionGuidanceActivity.this, NetworkInterfaceUtils.Param_Template_Id, "", moduleid);
                                }
                            };
                            handle.postDelayed(runnable, 5000);

                            confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                                @Override
                                public void doConfirm() {
                                    handle.removeCallbacks(runnable);
                                    confirmDialog.dismiss();
                                    VipActivity.starActivity(CompositionGuidanceActivity.this, NetworkInterfaceUtils.Param_Template_Id, "", moduleid);
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
                        Toast.makeText(CompositionGuidanceActivity.this, "-_-||您的视频走丢了,请稍后再试试", Toast.LENGTH_SHORT).show();
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
                                    //播放记录
                                    setPlayRecord(videoId);
                                    VideoHActivity.starActivity(CompositionGuidanceActivity.this, url, videoId, videoIdList);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 100:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 参数不合法");
                                break;
                            case 110:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: ak不对");
                                break;
                            case 120:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 签名不对");
                                break;
                            case 130:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 接口名不对");
                                break;
                            case 140:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 时间戳不对");
                                break;
                            case 150:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 没有权限");
                                break;
                            case 160:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 视频id=".concat(videoId).concat("的视频未上传或为转码"));
                                break;
                            case 500:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: 系统故障");
                                break;
                            case 510:
                                Toast.makeText(CompositionGuidanceActivity.this, "视频正在更新，敬请期待", Toast.LENGTH_SHORT).show();
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
//        String keyNo = SysUtil.getSystemProperties(CompositionGuidanceActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(CompositionGuidanceActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);

        }

        if(keyNo == null || keyNo.isEmpty()){
            Log.i(TAG, "setPlayRecord: keyNo(智能卡号获取不到！,无法记录观看记录)");
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

    //获取当前课程所有视频id
    public static void getCourseVideoId(String courseId){
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_GetVideoId)
                .addParams("os","android")
                .addParams("cid", courseId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i("tag-videoIdListResponse", "onResponse: "+response);
                        Gson gson = new Gson();
                        videoIdList = gson.fromJson(response, new TypeToken<List<String>>(){}.getType());
                    }
                });
    }
}
