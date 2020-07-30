package example.getlearn.tv.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.maywide.gdpay.GDApi;
import com.maywide.gdpay.PayContent;
import com.maywide.gdpay.PayReq;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.R;
import example.getlearn.tv.adapter.VipAdapter;
import example.getlearn.tv.adapter.VipTitleAdapter;
import example.getlearn.tv.application.MyApplication;
import example.getlearn.tv.bean.KeyNoBean;
import example.getlearn.tv.bean.OrderInfoBean;
import example.getlearn.tv.bean.PageBean;
import example.getlearn.tv.bean.VipBean;
import example.getlearn.tv.controller.SMSBroadcastReceiver;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.MD5Utils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import example.getlearn.tv.util.OnItemSelectedListener;
import example.getlearn.tv.util.SysUtil;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

public class VipActivity extends Activity {
    @BindView(R.id.activity_vip_rec_list)
    RecyclerView activityVipRecList;
    @BindView(R.id.activity_vip_title)
    RecyclerView activity_vip_title;
    @BindView(R.id.activity_vip_text_color)
    TextView activityVipTextColor;
    @BindView(R.id.activity_vip_ll_layout_bg)
    LinearLayout activity_vip_ll_layout_bg;
    @BindView(R.id.activiry_watch_the_record_esc)
    ImageView activiry_watch_the_record_esc;
    @BindView(R.id.activiry_watch_the_record_home)
    ImageView activiry_watch_the_record_home;
    @BindView(R.id.activiry_watch_the_record_src)
    ImageView activiry_watch_the_record_src;

    private String Template_id,bag_url;
    private int dataTitleCurrentIndex;
    private LinearLayoutManager linearLayoutManager;
    private int item_int_A = 1;
    private int page_int_A=0;
    private List<PageBean> data_page_02 = new ArrayList<>();
    private MyApplication myApplication;
    //智能卡号
    private String keyNo = "";
    private boolean hasKeyNo;

    public static void starActivity(Activity activity,String Template_id,String bag_url, String type) {
        Intent intent = new Intent(activity, VipActivity.class);
        intent.putExtra("Template_id",Template_id);
        intent.putExtra("bag_url",bag_url);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    private List<VipBean.PriceResBean> data_vip = new ArrayList<>();
    private VipBean vipBean;
    private VipAdapter vipAdapter;


    private List<VipBean.PriceCateBean> data_title = new ArrayList<>();
    private VipTitleAdapter vipTitleAdapter;

    private String type = "1";
    private int initTitlePostion = -1;
    private boolean isInitTitle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntenData();
        //选择模板
        switch (Template_id){
            case "1":
                setContentView(R.layout.activity_vip);
                break;
            case "2":
                setContentView(R.layout.activity_vip_02);
                break;
        }
        ButterKnife.bind(this);
        //网络检测
        if (NetworkUtils.isNetWorkAvailable(this)){
            initData(type);
            initAdapter();
            initView();
            //注册EventBus
            EventBus.getDefault().register(this);
        }else {
            Toast.makeText(this, "网络连接失败！", Toast.LENGTH_SHORT).show();
        }
    }

    private void initIntenData() {
        Intent intent = getIntent();
        Template_id = intent.getStringExtra("Template_id");
        bag_url = intent.getStringExtra("bag_url");
        type = intent.getStringExtra("type");
    }

    private void initData(final String moduleId) {
//        String keyNo = SysUtil.getSystemProperties(VipActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);

        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(VipActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
//            if(keyNo == null || keyNo.isEmpty()){
//                Toast.makeText(VipActivity.this, "请插入智能卡后重试", Toast.LENGTH_SHORT).show();
//                Log.i(TAG, "initData: ".concat("未获取到智能卡号keyNo"));
//                return;
//            }
        }

        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_Vip)
                .addParams("os","android")
                .addParams("id", moduleId)
                .addParams("keyNo", keyNo)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i("tag-initData", "onResponse: "+response);
                        Gson gson = new Gson();
                        vipBean = gson.fromJson(response,VipBean.class);
                        GroceryStoreUtils.GlideBG(VipActivity.this,NetworkInterfaceUtils.InterFace_Imag + vipBean.getPublic_skin_img(),activity_vip_ll_layout_bg);
//                        GroceryStoreUtils.GlideBG(VipActivity.this,NetworkInterfaceUtils.InterFace_Imag + "Public/Uploads/images/5b8f8ecc38ee0.jpg",activity_vip_ll_layout_bg);
                        data_vip = vipBean.getPriceRes();
                        for (int i =0;i<data_vip.size();i++){
                            if (data_vip.get(i).getProductid() != null && data_vip.get(i).getProductid().equals("")){
                                data_vip.remove(i);
                            }
                        }

                        //VIP订购页面产品说明
                        String str = "";
                        if(vipBean.getContent() != null){
                            str = vipBean.getContent();
                        }

                        activityVipTextColor.setText(Html.fromHtml(str));

                        vipAdapter.setNewData(data_vip);
                        data_title = vipBean.getPriceCate();
                        vipTitleAdapter.setNewData(data_title);

//                        //遍历获取id所在list中的位置
//                        for(int i=0; i<data_title.size(); i++){
//                            if(data_title.get(i).getId().equals(moduleId)){
//                                initTitlePostion = i;
//                                break;
//                            }
//                        }

                        if(isInitTitle){
                            isInitTitle = false;
                            //遍历获取id所在list中的位置
                            for(int i=0; i<data_title.size(); i++){
                                if(data_title.get(i).getId().equals(moduleId)){
                                    initTitlePostion = i;
                                    break;
                                }
                            }
                        }


//                        if (id<=1){
//                            data_title = vipBean.getPriceCate();
//                            List<VipBean.PriceCateBean> listPriceCate = new ArrayList<>();
//                            for (VipBean.PriceCateBean pcb: data_title) {
//                                if(pcb.getId().equals("1")){
//                                    listPriceCate.add(pcb);
//                                }
//                            }
//                            data_title.clear();
//                            data_title.addAll(listPriceCate);
//                            vipTitleAdapter.setNewData(data_title);
//                        }
                    }
                });

    }

    private void initAdapter() {
        //商品
        activityVipRecList.setLayoutManager(new GridLayoutManager(this, 3));
        vipAdapter = new VipAdapter(R.layout.adapter_vip_item, data_vip);
        activityVipRecList.setAdapter(vipAdapter);

        //标题
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        activity_vip_title.setLayoutManager(linearLayoutManager);
        activity_vip_title.setItemAnimator(null);
        vipTitleAdapter = new VipTitleAdapter(R.layout.adapter_vip_title_item, data_title);
        vipTitleAdapter.setHasStableIds(true);
        activity_vip_title.setAdapter(vipTitleAdapter);
        vipTitleAdapter.setOnItemSelectedListener(onItemSelectedListener);
    }

    private void initView() {
        //bg
//        GroceryStoreUtils.GlideBG(VipActivity.this,NetworkInterfaceUtils.InterFace_Imag + vipBean.getPublic_skin_img(),activity_vip_ll_layout_bg);
//        GroceryStoreUtils.GlideBG(VipActivity.this,bag_url,activity_vip_ll_layout_bg);
        //返回
        activiry_watch_the_record_esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //首页
        activiry_watch_the_record_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        收藏
        activiry_watch_the_record_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.starActivity(VipActivity.this);
            }
        });

//        String str="<font color='#97FFFA'>共</font><font color='#FFEA3b'>505</font><font color='#97FFFA'>个课程</font>";
//        activityVipTextColor.setText(Html.fromHtml(str));

        vipAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String isOrdered = data_vip.get(position).getProduct_isOrder();
                //该产品中有卡被订购
                if(isOrdered.equals("true")){
                    Toast.makeText(VipActivity.this, data_vip.get(0).getFirst_cate()+"已被订购", Toast.LENGTH_SHORT).show();
                    return;
                }

                //年卡季卡月卡id
                String productId = data_vip.get(position).getProductid();

                //模块id
                String module_id = data_vip.get(position).getModule_id();

                //价格
                String price = data_vip.get(position).getPrice();

//                String price = "0.01";
                //产品名称
                String productName = data_title.get(dataTitleCurrentIndex).getFirst_cate();

                //卡类型  0无类型，1月卡，2季卡，3年卡，4连续包月,5半年卡
                String cardType = data_vip.get(position).getType();

                if(price == null || price.isEmpty()){
                    price = "0";
                }
                if(productId == null || productId.isEmpty()){
                    Log.i("tag-productId", "onItemClick: 获取不到产品包id");
                    return;
                }
                if (module_id == null || module_id.isEmpty()){
                    Log.i("tag-module_id", "onItemClick: 获取不到模块id");
                    return;
                }
                if(productName == null || productName.isEmpty()){
                    Log.i("tag-productName", "onItemClick: 获取不到产品名称");
                    return;
                }
                //获取订单信息并调用支付
                getOrderInfoAndPay(productId, module_id, price, productName, cardType);
            }
        });
//        vipTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                initData(Integer.parseInt(data_title.get(position).getId()));
//                dataTitleCurrentIndex = position;
//            }
//        });
    }

    //监听头部标题焦点改变
    OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {
            /*initData(data_title.get(i).getId());
            dataTitleCurrentIndex = i;

            //改变焦点处头标题字体颜色
            view = linearLayoutManager.findViewByPosition(i);
            TextView statusb = (TextView) view.findViewById(R.id.adapter_vip_title_item_text);
            statusb.setTextColor(getResources().getColor(R.color.color_02));

            if (item_int_A==1){
                data_page_02.add(page_int_A,new PageBean(i));
                item_int_A=2;
            }else {
                page_int_A++;
                data_page_02.add(page_int_A, new PageBean(i));
                if (i!=data_page_02.get(page_int_A-1).getPage_int()) {
                    view = linearLayoutManager.findViewByPosition(data_page_02.get(page_int_A - 1).getPage_int());
                    TextView textView = (TextView) view.findViewById(R.id.adapter_vip_title_item_text);
                    textView.setTextColor(getResources().getColor(R.color.lb_browse_header_color));
                }
            }*/

            if(initTitlePostion != -1){
                isInitTitle = false;
                dataTitleCurrentIndex = initTitlePostion;
                view = linearLayoutManager.findViewByPosition(i);
                TextView textView = (TextView) view.findViewById(R.id.adapter_vip_title_item_text);
                textView.setTextColor(getResources().getColor(R.color.lb_browse_header_color));

                //改变焦点处头标题字体颜色
                view = linearLayoutManager.findViewByPosition(initTitlePostion);
                TextView statusb = (TextView) view.findViewById(R.id.adapter_vip_title_item_text);
                statusb.setTextColor(getResources().getColor(R.color.color_02));

                data_page_02.add(page_int_A,new PageBean(initTitlePostion));
                item_int_A=2;

                //进入VIP订购页面后默认选中月包
                activityVipRecList.setFocusable(true);
                activityVipRecList.requestFocus();

                initTitlePostion = -1;
            }else{
                initData(data_title.get(i).getId());
                dataTitleCurrentIndex = i;

                //改变焦点处头标题字体颜色
                view = linearLayoutManager.findViewByPosition(i);
                TextView statusb = (TextView) view.findViewById(R.id.adapter_vip_title_item_text);
                statusb.setTextColor(getResources().getColor(R.color.color_02));

                if (item_int_A==1){
                    data_page_02.add(page_int_A,new PageBean(i));
                    item_int_A=2;
                }else {
                    page_int_A++;
                    data_page_02.add(page_int_A, new PageBean(i));
                    if (i!=data_page_02.get(page_int_A-1).getPage_int()) {
                        view = linearLayoutManager.findViewByPosition(data_page_02.get(page_int_A - 1).getPage_int());
                        TextView textView = (TextView) view.findViewById(R.id.adapter_vip_title_item_text);
                        textView.setTextColor(getResources().getColor(R.color.lb_browse_header_color));
                    }
                }
            }
        }
    };

    //反注册EventBus
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void Event(KeyNoBean keyNoBean) {
        //获取智能卡id
        keyNo = keyNoBean.getKeyNo();
    }

    //获取订单信息，调用支付
    public void getOrderInfoAndPay(String productId, String module_id, final String price, final String productName, final String cardType){
//        keyNo = SysUtil.getSystemProperties(VipActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(VipActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);
            if(keyNo == null || keyNo.isEmpty()){
                Toast.makeText(VipActivity.this, "请插入智能卡后重试", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "getOrderInfoAndPay: ".concat("未获取到智能卡号keyNo"));
                return;
            }
        }

        if(keyNo != null && !keyNo.isEmpty()){
            //获取订单信息
            OkHttpUtils
                    .post()
                    .url(NetworkInterfaceUtils.InterFace_OrderInfo)
                    .addParams("os","android")
                    .addParams("keyNo", keyNo)
                    .addParams("price", price)
                    .addParams("module_id", module_id)
                    .addParams("productId", productId)
                    .addParams("clientcode", NetworkInterfaceUtils.InterFace_Param_Clientcode)
                    .addParams("type", cardType)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.i(TAG, "onError: "+e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
//                            Log.i("tag-OrderInfo", response);
                            Gson gson = new Gson();
                            //商品订购单位	0-天，1-月，2-年，-1次
                            String unit = "1";
                            //商品订购数量
                            String count = "1";
                            if(cardType != null && !cardType.isEmpty()){
                                switch (cardType){
                                    case "0":
                                        break;
                                    case "1":
                                        unit = "1";
                                        count = "1";
                                        break;
                                    case "2":
                                        unit = "1";
                                        count = "3";
                                        break;
                                    case "3":
                                        unit = "2";
                                        count = "1";
                                        break;
                                    case "4":
                                        break;
                                    case "5":
                                        unit = "1";
                                        count = "6";
                                        break;
                                    default:
                                        break;
                                }
                            }

                            OrderInfoBean orderInfoBean = gson.fromJson(response, OrderInfoBean.class);
                            //获取订单号
                            String orderNo = orderInfoBean.getOrderId();

                            //获取交易流水号
                            String requestId = orderInfoBean.getRequestid();

                            //组装支付参数
                            PayContent payContent = getPayParam(keyNo, price, productName, orderNo, requestId, unit, count);
                            //调用支付
                            pay(payContent, requestId);
                        }
                    });

        }else{
            Toast.makeText(VipActivity.this, "订购失败，无法获取用户智能卡号", Toast.LENGTH_SHORT).show();
        }
    }

    public PayContent getPayParam(String keyNo, String price, String productName, String orderNo, String requestId, String unit, String count){
        //组装custInfo
        PayContent.CustInfo custInfo = new PayContent.CustInfo();
        custInfo.setCardNo(keyNo);
        myApplication = (MyApplication)getApplication();
        custInfo.setCustid(myApplication.getCustid());

        custInfo.setCity(NetworkInterfaceUtils.InterFace_Param_Citycode);

        //组装Product
        PayContent.OrderInfo.Product product = new PayContent.OrderInfo.Product();
        //需支付的金额，单位元
        product.setFee(new Double(price));
        product.setKeyno(keyNo);
        product.setProductName(productName);
        //商品订购单位	0-天，1-月，2-年，-1次
        product.setUnit(unit);
        //商品单位价格，单位元
        product.setUnit_price(new Double(price));
        product.setCount(count);

        //组装orderInfo
        PayContent.OrderInfo orderInfo = new PayContent.OrderInfo();
        orderInfo.setOrderNo(orderNo);
        List<PayContent.OrderInfo.Product> list = new ArrayList<>();
        list.add(product);
        orderInfo.setProductList(list);

        //组装payContent
        PayContent payContent = new PayContent();
        payContent.setIsOrder(NetworkInterfaceUtils.IsOrder);
        payContent.setDataSign(MD5Utils.encode(requestId + NetworkInterfaceUtils.Private_Key).toUpperCase());
        payContent.setNoticeAction(NetworkInterfaceUtils.Pay_BackCall_Address);
        //需支付的总金额
        payContent.setTotalFee(new Double(price));
        payContent.setCustInfo(custInfo);
        payContent.setOrderInfo(orderInfo);

        return payContent;
    }

    //调用支付
    public void pay(PayContent payContent, String requestId){
        PayReq payReq = new PayReq();
        payReq.setCitycode(NetworkInterfaceUtils.InterFace_Param_Citycode);
        payReq.setClientcode(NetworkInterfaceUtils.InterFace_Param_Clientcode);
        payReq.setClientpwd(NetworkInterfaceUtils.InterFace_Param_Clientpwd);
        payReq.setRequestid(requestId);
        payReq.setPayContent(payContent);
        GDApi.sendReq(VipActivity.this,payReq);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    //支付结果回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.i("tag-payBack","SUCCESS");
        if (requestCode == GDApi.REQUESTCODE &&
                resultCode == GDApi.RESULTCODE) {
            Bundle bundle = data.getExtras();
            //拿到订单号
            String orderNo = bundle.getString("orderNo");
            //拿到支付结果  0代表支付成功,1代表支付失败，2代表取消订单
            int payResultCode = bundle.getInt("resultCode",-1);
            //拿到页面通知地址
            String redirectUrl = bundle.getString("redirectUrl");
            //拿到信息描述
            String payInfo = bundle.getString("payInfo");

//            Log.i("orderNo:",orderNo);
//            Log.i("payResultCode:", String.valueOf(payResultCode));
//            Log.i("redirectUrl:",redirectUrl);

            if(payResultCode == 0){
//                Log.i("tag-payBackCallResult", "支付成功！");
//                Log.i("payInfo:",payInfo);
                Toast.makeText(VipActivity.this, "支付成功！", Toast.LENGTH_SHORT).show();
            }else{
//                Log.i("tag-payBackCallResult", "支付失败！支付返回结果码：" + payResultCode);
//                Toast.makeText(VipActivity.this, "支付失败！", Toast.LENGTH_SHORT).show();
            }

            vipTitleAdapter.getViewByPosition(activity_vip_title, data_page_02.get(data_page_02.size()-1).getPage_int(), R.id.adapter_vip_title_item_text).requestFocus();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_UP:   //向上键
//                Log.d(TAG, "up--->");
//                ImageView ivHome = findViewById(R.id.activiry_watch_the_record_home);
//                //获取主页键是否被选中
//                boolean homeFocused = ivHome.isFocused();
//                ImageView ivSearch = findViewById(R.id.activiry_watch_the_record_src);
//                //获取搜索键是否被选中
//                boolean searchFocused = ivHome.isFocused();
//                ImageView ivBack = findViewById(R.id.activiry_watch_the_record_esc);
//                //获取返回键是否被选中
//                boolean backFocused = ivHome.isFocused();
//                if (!homeFocused && !searchFocused && !backFocused){
//                    vipTitleAdapter.getViewByPosition(activity_vip_title, data_page_02.get(data_page_02.size()-1).getPage_int(), R.id.adapter_vip_title_item_text).requestFocus();
//                }
                vipTitleAdapter.getViewByPosition(activity_vip_title, data_page_02.get(data_page_02.size()-1).getPage_int(), R.id.adapter_vip_title_item_text).requestFocus();
                break;

//            case KeyEvent.KEYCODE_DPAD_LEFT:
//
//                break;
//
//            case KeyEvent.KEYCODE_DPAD_RIGHT:
//
//                break;
        }
        return super.onKeyDown(keyCode, event);
//        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode()==KeyEvent.KEYCODE_DPAD_UP){
            if (activiry_watch_the_record_esc.hasFocus()){
                activityVipRecList.requestFocus();
                //return true 让焦点跑到上次的地方，并且终止在该容器中手势的继续传递
                return true;
            }
            if (activiry_watch_the_record_src.hasFocus()){
                activityVipRecList.requestFocus();
                //return true 让焦点跑到上次的地方，并且终止在该容器中手势的继续传递
                return true;
            }
            if (activiry_watch_the_record_home.hasFocus()){
                activityVipRecList.requestFocus();
                //return true 让焦点跑到上次的地方，并且终止在该容器中手势的继续传递
                return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
