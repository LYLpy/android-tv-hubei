package example.getlearn.tv.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
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
import example.getlearn.tv.adapter.MyCollectionAdapter;
import example.getlearn.tv.application.MyApplication;
import example.getlearn.tv.bean.MainMessage;
import example.getlearn.tv.bean.MyCollectionBean;
import example.getlearn.tv.controller.SMSBroadcastReceiver;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import example.getlearn.tv.util.SysUtil;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/****
 * 我的收藏
 *
 */
public class MyCollectionActivity extends Activity {
    @BindView(R.id.activity_my_collection_rec_list)
    app.com.tvrecyclerview.TvRecyclerView activityMyCollectionRecList;
    @BindView(R.id.ll_layout_01)
    LinearLayout ll_layout_01;
    @BindView(R.id.activiry_my_collection_clear)
    ImageView activiry_my_collection_clear;
    @BindView(R.id.activiry_my_collection_home)
    ImageView activiry_my_collection_home;
    @BindView(R.id.activiry_my_collection_src)
    ImageView activiry_my_collection_src;
    @BindView(R.id.activiry_my_collection_esc)
    ImageView activiry_my_collection_esc;
    @BindView(R.id.activity_my_collection_text_coun)
    TextView activity_my_collection_text_coun;

    private  MyApplication myApplication;
    private String Template_id;
    public static void starActivity(Activity activity,String Template_id ) {
        Intent intent = new Intent(activity, MyCollectionActivity.class);
        intent.putExtra("Template_id",Template_id);
        activity.startActivity(intent);
    }
    private MyCollectionAdapter myCollectionAdapter;
    private MyCollectionBean myCollectionBean;
    private List<MyCollectionBean.CollectionBean> data = new ArrayList<>();
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
                setContentView(R.layout.activity_my_collection);
                break;
        }
        EventBus.getDefault().register(this);//注册
        ButterKnife.bind(this);
        //网络检测
        if (NetworkUtils.isNetWorkAvailable(this)){
            initAdapter();
            initData();
            initView();
        }else {
            Toast.makeText(this, "网络连接失败！", Toast.LENGTH_SHORT).show();
        }

    }
    private void initIntenData() {
        Intent intent = getIntent();
        Template_id = intent.getStringExtra("Template_id");
    }
    private void initView() {
//        清除
        activiry_my_collection_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogActivity.starDialog02Activity(MyCollectionActivity.this,"1","");
            }
        });
        //主页
        activiry_my_collection_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //搜索
        activiry_my_collection_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.starActivity(MyCollectionActivity.this);
            }
        });
        //返回
        activiry_my_collection_esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        list
        myCollectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CompositionGuidanceActivity.starActivity(MyCollectionActivity.this,"1",
                       data.get(position).getId()
                        ,"course");
            }
        });
    }

    private void initAdapter() {
        activityMyCollectionRecList.setLayoutManager(new GridLayoutManager(this,3));
        activityMyCollectionRecList.setItemAnimator(null);
        myCollectionAdapter = new MyCollectionAdapter(R.layout.adapter_my_collectoon_item, data);
        myCollectionAdapter.setHasStableIds(true);
        activityMyCollectionRecList.setAdapter(myCollectionAdapter);
        myCollectionAdapter.notifyDataSetChanged();
    }

    private void initData() {
//        String keyNo = SysUtil.getSystemProperties(MyCollectionActivity.this, SysUtil.SYSKEY_HB_SMART_CARD);
        if(NetworkInterfaceUtils.isTest){
            hasKeyNo = true;
            keyNo = NetworkInterfaceUtils.Test_KeyNo;
        }else{
//            hasKeyNo = SMSBroadcastReceiver.hasKeyNo;
//            if(hasKeyNo){
//                keyNo = SMSBroadcastReceiver.keyNo;
//            }

            keyNo = SysUtil.getSystemProperties(MyCollectionActivity.this,SysUtil.SYSKEY_HB_SMART_CARD);

        }
        if(keyNo == null || keyNo.isEmpty()){
            Toast.makeText(MyCollectionActivity.this, "我的收藏获取失败，请插入机顶盒智能卡后重试！", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "initData: keyNo(智能卡号获取不到！)");
            return;
        }
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_MyCollection)
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
//                        Log.i("tag-mycollection", "mycollection: "+response);
                        Gson gson = new Gson();
                        myCollectionBean = gson.fromJson(response,MyCollectionBean.class);
                        data = myCollectionBean.getCollection();
                        GroceryStoreUtils.GlideBG(MyCollectionActivity.this,NetworkInterfaceUtils.InterFace_Imag + myCollectionBean.getPublic_skin_img(),ll_layout_01);
                        myCollectionAdapter.setNewData(data);
                        activity_my_collection_text_coun.setText("共"+String.valueOf(myCollectionBean.getCount())+"个课程");
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MainMessage messageEvent){
        if (messageEvent.getMessage().equals("MyCollectionActivity")){
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

}
