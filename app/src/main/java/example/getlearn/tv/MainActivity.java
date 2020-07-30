package example.getlearn.tv;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.application.MyApplication;
import example.getlearn.tv.bean.BagBean;
import example.getlearn.tv.bean.MainBean;
import example.getlearn.tv.bean.UserInfoBean;
import example.getlearn.tv.controller.SMSBroadcastReceiver;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.ImageViewMaxUtils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import example.getlearn.tv.util.SysUtil;
import example.getlearn.tv.view.activity.CompositionGuidanceActivity;
import example.getlearn.tv.view.activity.MyCollectionActivity;
import example.getlearn.tv.view.activity.NumberOneClassActivity;
import example.getlearn.tv.view.activity.PrimarySchoolClassActivity;
import example.getlearn.tv.view.activity.SearchActivity;
import example.getlearn.tv.view.activity.TeachingMaterialActivity;
import example.getlearn.tv.view.activity.VipActivity;
import example.getlearn.tv.view.activity.WatchTheRecordActivity;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.activity_main_img_01)
    ImageViewMaxUtils activity_main_img_01;
    @BindView(R.id.activity_main_img_02)
    ImageViewMaxUtils activity_main_img_02;
    @BindView(R.id.activity_main_img_03)
    ImageViewMaxUtils activity_main_img_03;
//    @BindView(R.id.activity_main_img_04)
//    ImageViewMaxUtils activity_main_img_04;
//    @BindView(R.id.activity_main_img_05)
//    ImageViewMaxUtils activity_main_img_05;
    @BindView(R.id.activity_main_img_06)
    ImageViewMaxUtils activity_main_img_06;
    @BindView(R.id.activity_main_img_07)
    ImageViewMaxUtils activity_main_img_07;
    @BindView(R.id.activity_main_img_08)
    ImageViewMaxUtils activity_main_img_08;
    @BindView(R.id.activity_main_img_09)
    XBanner activity_main_img_09;
    @BindView(R.id.activity_main_img_10)
    ImageViewMaxUtils activity_main_img_10;
    @BindView(R.id.activity_main_img_11)
    ImageViewMaxUtils activity_main_img_11;
    @BindView(R.id.activity_main_img_12)
    ImageViewMaxUtils activity_main_img_12;
    @BindView(R.id.activity_main_img_13)

    ImageViewMaxUtils activity_main_img_13;
    @BindView(R.id.activity_main_logo)
    ImageView activity_main_logo;
    @BindView(R.id.activity_main_img_09_ll)
    LinearLayout activity_main_img_09_ll;
    @BindView(R.id.activity_main_bag)
    LinearLayout activity_main_bag;
    @BindView(R.id.activity_main_text_03)
    TextView activity_main_text_03;
    @BindView(R.id.activity_main_text_02)
    TextView activity_main_text_02;
    @BindView(R.id.activity_main_text_01)
    TextView activity_main_text_01;

    private MainBean mainBean;
    private String imag_title=NetworkInterfaceUtils.InterFace_Imag;
    private static final int msgKey = 1;
    private String Template_id,id,text1,text2;
    private int positionX;
    private String bag_url;
    private String paramType;
    private MyApplication myApplication;
    private SharedPreferences sharedPreferences;
    //智能卡号
    private String keyNo = "";
    private boolean hasKeyNo;
    //IP地址
    private String ipAddr = "";
    //mac地址
    private String mac = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new TimeThread().start();
        //网络检测
        if (NetworkUtils.isNetWorkAvailable(this)){
            initData();
            initView();
            initClick();
            initCalender();
            getCustomeInfo();
            //记录用户进入app的信息
            recordLoginInfo();
        }else {
            Toast.makeText(this, "网络连接失败！", Toast.LENGTH_SHORT).show();
        }

    }
    private void initEvntBus() {
        EventBus.getDefault().post(new BagBean(NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome1().getBgimg()));
    }
    private void initView() {
        activity_main_img_09.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
               if(hasFocus){
                   activity_main_img_09.stopAutoPlay();
               }else {
                   activity_main_img_09.startAutoPlay();
               }
            }

        });
    }

    private void initCalender() {
        activity_main_text_01.setText(GroceryStoreUtils.getSimpleDateFprmat("MM-dd"));
        activity_main_text_02.setText(GroceryStoreUtils.getWeek(GroceryStoreUtils.getSimpleDateFprmat("yyyy-MM-dd")));
    }

    private void initGlide() {
        //背景
        GroceryStoreUtils.GlideBG(MainActivity.this,imag_title + mainBean.getPublic_skin_img(),activity_main_bag);
    //logo
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getLogo().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_logo);
        //VIP
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome1().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_01);
        //观看
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome2().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_02);
//        妙解教材
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome8().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_03);
//        小学课堂
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome9().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_10);
//        状元课堂
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome10().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_06);
//        国学经典
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome11().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_11);
//        我的收藏
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome7().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_12);
//        搜索
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome6().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_13);
//        安全知识
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome4().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_08);
//        作文指导
        Glide.with(this)
                .load(imag_title+ mainBean.getData().getHome5().getIcon())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(activity_main_img_07);
    }

    private void initClick() {
        activity_main_img_01.setOnClickListener(this);
        activity_main_img_02.setOnClickListener(this);
        activity_main_img_03.setOnClickListener(this);
//        activity_main_img_04.setOnClickListener(this);
//        activity_main_img_05.setOnClickListener(this);
        activity_main_img_06.setOnClickListener(this);
        activity_main_img_07.setOnClickListener(this);
        activity_main_img_08.setOnClickListener(this);
        activity_main_img_09.setOnClickListener(this);
        activity_main_img_10.setOnClickListener(this);
        activity_main_img_11.setOnClickListener(this);
        activity_main_img_12.setOnClickListener(this);
        activity_main_img_13.setOnClickListener(this);
    }

    private void initData() {
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(MainActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
            if(keyNo == null || keyNo.isEmpty()){
                Toast.makeText(MainActivity.this, "请插入机顶盒智能卡", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "initData: keyNo(智能卡号获取不到！)");
                hasKeyNo = false;
            }else{
                hasKeyNo = true;
            }
        }

        //ip地址
        ipAddr = SysUtil.getLocalIp();
        //mac
        mac = SysUtil.getMacAddress();
//        Log.i("tag-recordLoginLog", "recordLoginLog: keyNO: "+keyNo+";ipAddr: "+ipAddr+";mac："+mac);

        OkHttpUtils.post()
                .url(NetworkInterfaceUtils.InterFace_Home)
                .addParams("os","android")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("tag-mainResponse", "onResponse: "+response);
                        Gson gson = new Gson();
                        mainBean = gson.fromJson(response, MainBean.class);
                        initGlide();
                        initVivoPage();
                    }
                });

    }
    private void initVivoPage() {
        // 初始化XBanner中展示的数据
//        final List<String> images = new ArrayList<>();
//        List<String> titles = new ArrayList<>();
//        images.add("http://img0.imgtn.bdimg.com/it/u=3405210909,3110029909&fm=26&gp=0.jpg");
//        images.add("http://img4.imgtn.bdimg.com/it/u=1293919120,3114443152&fm=26&gp=0.jpg");
//        images.add("http://img1.imgtn.bdimg.com/it/u=2213670986,2923778817&fm=26&gp=0.jpg");
//        images.add("http://img5.imgtn.bdimg.com/it/u=251161048,2956789207&fm=26&gp=0.jpg");

        // 为XBanner绑定数据
        activity_main_img_09.setData(mainBean.getData().getHome3().getIcon(), null);
        // XBanner适配数据
        activity_main_img_09.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide
                        .with(MainActivity.this)
                        .load(imag_title+mainBean.getData().getHome3().getIcon().get(position))
                        .crossFade()
                        .override(300,250)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into((ImageView) view);

            }
        });

        activity_main_img_09.setAllowUserScrollable(false);
        activity_main_img_09.setmAutoPlayAble(true);
        // 设置XBanner的页面切换特效
        activity_main_img_09.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        activity_main_img_09.setPageChangeDuration(1000);
//        activity_main_img_09.focus
        activity_main_img_09.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 把当前显示的position传递出去
                positionX=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_img_01:
                //vip 会员            home1
                Template_id = mainBean.getData().getHome1().getTemplate_id();
                id =   GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome1().getTourl(),"id"),"id=");
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome1().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome1().getTourl()));
                break;
            case R.id.activity_main_img_02:
//                    观看记录    home2
                Template_id = mainBean.getData().getHome2().getTemplate_id();
                id =   GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome2().getTourl(),"id"),"id=");
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome2().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome2().getTourl()));
                break;
            case R.id.activity_main_img_03:
                //妙解教材  home8
                Template_id = mainBean.getData().getHome8().getTemplate_id();
                id =   GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome8().getTourl(),"id"),"id=");
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome8().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome8().getTourl()));
                break;
            case R.id.activity_main_img_06:
                //状元课堂   home10
                Template_id = mainBean.getData().getHome10().getTemplate_id();
                text1 =   GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome10().getTourl(),"id="),"id=");
                if (GroceryStoreUtils.setTextIndexOf(text1,"&")){
                    text2 =text1.substring(0, text1.indexOf("&"));
                }
                id =  text2;
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome10().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome10().getTourl()));
                break;
            case R.id.activity_main_img_07:
                //作文指导   home5
                Log.e("____",mainBean.getData()+"");
                Template_id = mainBean.getData().getHome5().getTemplate_id();
                id =GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome5().getTourl(),"id"),"id=");
                paramType = GroceryStoreUtils.getParamType(NetworkInterfaceUtils.URL_SubstringParam, mainBean.getData().getHome4().getTourl());
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome5().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome5().getTourl()));
                break;
            case R.id.activity_main_img_08:
                //安全知识    home4
                Template_id = mainBean.getData().getHome4().getTemplate_id();
                id =   GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome4().getTourl(),"id"),"id=");
                paramType = GroceryStoreUtils.getParamType(NetworkInterfaceUtils.URL_SubstringParam, mainBean.getData().getHome4().getTourl());
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome4().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome4().getTourl()));
                break;
            case R.id.activity_main_img_09:
                //广告轮播图    home3      getCurrentItem
                Template_id = mainBean.getData().getHome3().getTemplate_id();
                paramType = GroceryStoreUtils.getParamType(NetworkInterfaceUtils.URL_SubstringParam, mainBean.getData().getHome3().getTourl().get(positionX));
                String home3Url = mainBean.getData().getHome3().getTourl().get(positionX);
                String idEndStr = GroceryStoreUtils.getIntercept(home3Url,"id");
                text1 = GroceryStoreUtils.getDeleText(idEndStr,"id=");
                if (GroceryStoreUtils.setTextIndexOf(text1,"&")){
                    text2 =text1.substring(0, text1.indexOf("&"));
                }else{
                    text2 = text1;
                }
                id =  text2;

                if(id.equals("3")){
                    bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome11().getBgimg();
                }else if(id.equals("4")){
                    bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome9().getBgimg();
                }else if(id.equals("5")){
                    bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome10().getBgimg();
                }else{
                    bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome3().getBgimg();
                }

                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome3().getTourl().get(positionX)));
                break;
            case R.id.activity_main_img_10:
                //小学课堂  home9
                Template_id = mainBean.getData().getHome9().getTemplate_id();
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome9().getBgimg();
                text1 =  GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome9().getTourl(),"id="),"id=");
                if (GroceryStoreUtils.setTextIndexOf(text1,"&")){
                    text2 =text1.substring(0, text1.indexOf("&"));
                }
                id =  text2;
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome9().getTourl()));
                break;
            case R.id.activity_main_img_11:
                //国学经典     home11
                Template_id = mainBean.getData().getHome11().getTemplate_id();
                text1 =   GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome11().getTourl(),"id"),"id=");
                if (GroceryStoreUtils.setTextIndexOf(text1,"&")){
                    text2 =text1.substring(0, text1.indexOf("&"));
                }
                id =  text2;
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome11().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome11().getTourl()));
                break;
            case R.id.activity_main_img_12:
                //我的收藏     home7
                Template_id = mainBean.getData().getHome7().getTemplate_id();
                id =   GroceryStoreUtils.getDeleText(GroceryStoreUtils.getIntercept(mainBean.getData().getHome7().getTourl(),"id"),"id=");
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome7().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome7().getTourl()));
                break;
            case R.id.activity_main_img_13:
                //搜索    home6
                bag_url = NetworkInterfaceUtils.InterFace_Imag+mainBean.getData().getHome6().getBgimg();
                Selector(GroceryStoreUtils.TextIntercept(mainBean.getData().getHome6().getTourl()));
                break;
//            case R.id.activity_main_img_04:
//                //主页
//               // VlcPlayerActivity.starActivity(MainActivity.this);
////                SimplePlayerActivity.starActivity(MainActivity.this,"http://221.228.226.23/11/t/j/v/b/tjvbwspwhqdmgouolposcsfafpedmb/sh.yinyuetai.com/691201536EE4912BF7E4F1E2C67B8119.mp4", "","");
//                VlcPlayerActivity.starActivity(MainActivity.this, "http://221.228.226.23/11/t/j/v/b/tjvbwspwhqdmgouolposcsfafpedmb/sh.yinyuetai.com/691201536EE4912BF7E4F1E2C67B8119.mp4",);
//                break;
//            case R.id.activity_main_img_05:
//                //菜单
//                NFCActivity.starActivity(MainActivity.this);
//                break;
        }
    }

    private void Selector(String Text){
        switch (Text){
            case "a=index":
                //           a=index
                VipActivity.starActivity(MainActivity.this,Template_id,bag_url, "2");
                break;
            case "a=record":
                //      a=record
                WatchTheRecordActivity.starActivity(MainActivity.this,Template_id,bag_url);

                break;
            case "a=courseInfo":
                //         a=courseInfo
                if(paramType != null && !paramType.isEmpty()){
                    CompositionGuidanceActivity.starActivity(MainActivity.this,Template_id,id,paramType);
                }else{
                    CompositionGuidanceActivity.starActivity(MainActivity.this,Template_id,id,"course");
                }

                break;
            case "a=sreach":
                //          a=sreach
                SearchActivity.starActivity(MainActivity.this);
                break;
            case "a=collection":
                //             a=collection
                MyCollectionActivity.starActivity(MainActivity.this,Template_id);

                break;
            case "a=pschool":
                //             a=pschool
                TeachingMaterialActivity.starActivity(MainActivity.this,Template_id);
                initEvntBus();

                break;
            case "a=courseList":
                //         a=courseList
                PrimarySchoolClassActivity.StarPrimarySchoolClassActivity(MainActivity.this,Template_id,id,bag_url);

                break;
            case "a=getTopicDetail":
                //          a=getTopicDetail
                NumberOneClassActivity.starActivity(MainActivity.this,Template_id);
                break;
        }

    }

    public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey:
                    activity_main_text_03.setText(GroceryStoreUtils.getSimpleDateFprmat("HH:mm"));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getCustomeInfo(){
//        String icno = SysUtil.getSystemProperties(MainActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(!hasKeyNo || keyNo.isEmpty()){
            Log.i(TAG, "getCustomeInfo: ".concat("无法获取智能卡号keyNo"));
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("icno", keyNo);
        map.put("permark", "1");
        final String requestContent = new Gson().toJson(map);
        String currentDate = SysUtil.getCurrentDate();
        String randomNum = SysUtil.generateShortUuid();
        String requestid = NetworkInterfaceUtils.InterFace_Param_Clientcode.concat(currentDate).concat(randomNum);

        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.BOSS_HTTP_FORMAT)
                .addParams("version",NetworkInterfaceUtils.InterFace_Param_Version)
                .addParams("citycode", NetworkInterfaceUtils.InterFace_Param_Citycode)
                .addParams("clientcode", NetworkInterfaceUtils.InterFace_Param_Clientcode)
                .addParams("clientpwd",NetworkInterfaceUtils.InterFace_Param_Clientpwd)
                .addParams("servicecode",NetworkInterfaceUtils.InterFace_Param_GetCustomInfo)
                .addParams("requestid", requestid)
                .addParams("requestContent", requestContent)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i("tag-getCustom", "onResponse: "+response);
                        Gson gson = new Gson();
                        UserInfoBean userInfoBean = gson.fromJson(response, UserInfoBean.class);
                        if(userInfoBean.getStatus().equals("0000")){
                            String custid = userInfoBean.getOutput().getCustid();
                            myApplication = (MyApplication)getApplication();
                            myApplication.setCustid(custid);
                        }
                    }
                });
    }

    //记录进入app的数据到后台
    private void recordLoginInfo(){
//        //智能卡号
//        String keyNo = SysUtil.getSystemProperties(MainActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);

        if(!hasKeyNo){
            Log.i(TAG, "recordLoginLog: 未插入智能卡");
            return;
        }

        //请求后台，发送进入app的日志信息
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_RecordUserInfo)
                .addParams("os","android")
                .addParams("keyNo", keyNo)
                .addParams("STBID", keyNo)
                .addParams("type", "")
                .addParams("ip", ipAddr)
                .addParams("mac", mac)
                .addParams("UserGroupNMB", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i("tag-primaryResponse", "onResponse: "+response);
                    }
                });
    }

}
