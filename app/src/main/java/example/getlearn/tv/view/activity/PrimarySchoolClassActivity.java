package example.getlearn.tv.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import example.getlearn.tv.util.OnItemSelectedListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.R;
import example.getlearn.tv.adapter.PrimarySchoolClassAAdapter;
import example.getlearn.tv.adapter.PrimarySchoolClassAdapter;
import example.getlearn.tv.adapter.PrimarySchoolClassQTAdapter;
import example.getlearn.tv.bean.PageBean;
import example.getlearn.tv.bean.PrimarySchoolClasCBean;
import example.getlearn.tv.bean.PrimarySchoolClassABean;
import example.getlearn.tv.bean.PrimarySchoolClassBean;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/****
 *
 * 小学课堂
 *
 */


public class PrimarySchoolClassActivity extends Activity {

    @BindView(R.id.activity_primary_school_class_rec_list_01)
    example.getlearn.tv.util.TvRecyclerView activityPrimarySchoolClassRecList01;
    @BindView(R.id.activity_primary_school_class_rec_list_02)
    example.getlearn.tv.util.TvRecyclerView activityPrimarySchoolClassRecList02;
    @BindView(R.id.activity_primary_school_class_rec_list)
    android.support.v7.widget.RecyclerView activity_primary_school_class_rec_list;
    @BindView(R.id.activity_primary_school_class_layout)
    LinearLayout activity_primary_school_class_layout;
    private List<PageBean> data_page_01 = new ArrayList<>();
    private List<PageBean> data_page_02 = new ArrayList<>();
    //年级
    private PrimarySchoolClassAdapter primarySchoolClassAdapter;
    private PrimarySchoolClassBean primarySchoolClassBean;
    private List<PrimarySchoolClassBean.NianjiListBean> data = new ArrayList<>();

    //科门
    private PrimarySchoolClassAAdapter primarySchoolClassAdapterA;
    private PrimarySchoolClassABean primarySchoolClassBeanA;
    private List<PrimarySchoolClassABean.XuekeListBean> data_A = new ArrayList<>();

    //嵌套
    private List<PrimarySchoolClasCBean.CourseListBean> data_qt = new ArrayList<>();
    private PrimarySchoolClasCBean primarySchoolClassBeanC;
    private PrimarySchoolClassQTAdapter primarySchoolClassQTAdapter;

    private LinearLayoutManager linearLayoutManager,linearLayoutManager_ab;
    private int item_int = 1;
    private int page_int=0;
    private int item_int_A = 1;
    private int page_int_A=0;
    private int page=1;
    private int nianji_id;
    private String xueke_id;
    private int type=1;
    private int ItemSelected;
    private int ItemSelected_02;
    private String Template_id,id;
    private boolean xueke_change;
    private int xuekeIdRecord = -1;
    private String bgUrl;

    public static void StarPrimarySchoolClassActivity(Activity activity,String Template_id,String id, String bgUrl) {
        Intent intent = new Intent(activity, PrimarySchoolClassActivity.class);
                intent.putExtra("Template_id",Template_id);
                intent.putExtra("id",id);
                intent.putExtra("bgUrl", bgUrl);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntenData();
        //选择模板
        switch (Template_id){
            case "1":
                setContentView(R.layout.activity_primary_school_class);
                break;
        }
        ButterKnife.bind(this);
        //网络检测
        if (NetworkUtils.isNetWorkAvailable(this)){
            initAdapter();
            initDataA();
            initView();
        }else {
            Toast.makeText(this, "网络连接失败！", Toast.LENGTH_SHORT).show();
        }
    }
    private void initIntenData() {
        Intent intent = getIntent();
        Template_id = intent.getStringExtra("Template_id");
        id = intent.getStringExtra("id");
        bgUrl = intent.getStringExtra("bgUrl");
    }

    private void initView() {
        //设置背景
        GroceryStoreUtils.GlideBG(PrimarySchoolClassActivity.this,bgUrl,activity_primary_school_class_layout);
    }

    private void initAdapter() {
        //年级列表
       linearLayoutManager = new LinearLayoutManager(this);
        activityPrimarySchoolClassRecList01.setLayoutManager(linearLayoutManager);
        activityPrimarySchoolClassRecList01.setItemAnimator(null);
        primarySchoolClassAdapter = new PrimarySchoolClassAdapter(R.layout.adapter_primary_school_class_item, data);
        primarySchoolClassAdapter.setHasStableIds(true);
        activityPrimarySchoolClassRecList01.setAdapter(primarySchoolClassAdapter);
        primarySchoolClassAdapter.setOnItemSelectedListener(onItemSelectedListener);
        //科门
        linearLayoutManager_ab = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        activityPrimarySchoolClassRecList02.setLayoutManager(linearLayoutManager_ab);
        activityPrimarySchoolClassRecList02.setItemAnimator(null);
        primarySchoolClassAdapterA = new PrimarySchoolClassAAdapter(R.layout.adapter_primary_school_classs_item_a, data_A);
        primarySchoolClassAdapterA.setHasStableIds(true);
        activityPrimarySchoolClassRecList02.setAdapter(primarySchoolClassAdapterA);
        primarySchoolClassAdapterA.setOnItemSelectedAListener(onItemSelectedListenerA);
        //嵌套
        activity_primary_school_class_rec_list.setLayoutManager(new LinearLayoutManager(this));
        primarySchoolClassQTAdapter = new PrimarySchoolClassQTAdapter(R.layout.adapter_primary_school_class_item_qt, data_qt);
        activity_primary_school_class_rec_list.setAdapter(primarySchoolClassQTAdapter);
        primarySchoolClassQTAdapter.setOnItemSelectedListener(onItemSelectedListenerB);
    }


    private void initDataA() {
        String url = NetworkInterfaceUtils.InterFace_Home_09_A+id+NetworkInterfaceUtils.InterFace_Home_09_A_x;
        //年级列表
        OkHttpUtils
                .post()
                .url(url)
                .addParams("os","android")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i("tag-primaryResponse", "onResponse: "+response);
                        Gson gson = new Gson();
                        primarySchoolClassBean = gson.fromJson(response,PrimarySchoolClassBean.class);
                        data = primarySchoolClassBean.getNianjiList();
                        primarySchoolClassAdapter.setNewData(data);

                        nianji_id = primarySchoolClassBean.getNianjiId();
                        xueke_id = primarySchoolClassBean.getXuekeId();
                        initDataB(nianji_id,xueke_id);
                        //背景
//                        GroceryStoreUtils.GlideBG(PrimarySchoolClassActivity.this,NetworkInterfaceUtils.InterFace_Imag + primarySchoolClassBean.getPublic_skin_img(),activity_primary_school_class_layout);
                    }
                });

    }

    private void initDataB(final int nianji_id, final String xuekeId) {
//        Log.i(TAG, "initDataB: ".concat("nianji_id: "+nianji_id).concat("; xueke_id: "+xuekeId));
        //科门
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_Home_09_B)
                .addParams("os","android")
                .addParams("nianji_id",String.valueOf(nianji_id))
                .addParams("xueke_id",String.valueOf(xuekeId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
//                        Log.i("tag-PrimarySchoolClass", "PrimarySchoolClass: "+response);
                        primarySchoolClassBeanA = gson.fromJson(response,PrimarySchoolClassABean.class);
                        primarySchoolClassBeanC = gson.fromJson(response,PrimarySchoolClasCBean.class);
                        //data_A为空是第一次,type=1为移动科目
                        if(data_A == null || data_A.isEmpty() || type == 1){
                            //获取学科数据
                            data_A = primarySchoolClassBeanA.getXuekeList();
                            //加载学科
                            primarySchoolClassAdapterA.setNewData(data_A);
                            //获取课程
                            data_qt = primarySchoolClassBeanC.getCourseList();
                            //加载课程
                            primarySchoolClassQTAdapter.setNewData(data_qt);
                        }else{
                            //上一次的学科id列表
                            List<PrimarySchoolClassABean.XuekeListBean> preXuekeList = data_A;
                            //这一次的学科id列表
                            data_A = primarySchoolClassBeanA.getXuekeList();
                            //学科id在这一次学科id列表中的位置，-1则表示学科id不在这一次的学科id列表中
                            int xuekeId_currentPos = -1;
                            for(int i=0; i<data_A.size(); i++){
                                if(data_A.get(i).getXueke_id().equals(xuekeId)){
                                    xuekeId_currentPos = i;
                                    break;
                                }
                            }
                            //学科id在上一次学科列表中的位置
                            int xuekeId_prePos = -1;
                            for(int i=0; i<preXuekeList.size(); i++){
                                if(preXuekeList.get(i).getXueke_id().equals(xuekeId)){
                                    xuekeId_prePos = i;
                                    break;
                                }
                            }

                            if(xuekeId_currentPos != -1){//学科id存在这一次的学科id列表中
                                if(xuekeId_currentPos != xuekeId_prePos){//学科id位置发生变化
                                    xueke_change = true;
                                    xuekeIdRecord = xuekeId_prePos;
                                    initDataB(nianji_id, data_A.get(0).getXueke_id());
                                }else{
                                    //获取学科数据
                                    data_A = primarySchoolClassBeanA.getXuekeList();
                                    //加载学科
                                    primarySchoolClassAdapterA.setNewData(data_A);
                                    //获取课程
                                    data_qt = primarySchoolClassBeanC.getCourseList();
                                    //加载课程
                                    primarySchoolClassQTAdapter.setNewData(data_qt);
                                    if(xueke_change){
                                        xueke_change = false;
                                        View  view = primarySchoolClassAdapterA.getViewByPosition(activityPrimarySchoolClassRecList02,0, R.id.adapter_primary_school_class_text_a);
                                        view.setBackgroundResource(R.drawable.ico_btn_1);
                                        xueke_id = data_A.get(0).getXueke_id();
                                        page_int_A++;
                                        data_page_02.add(page_int_A,new PageBean(0));
                                        if(xuekeIdRecord != -1){
                                            View  view2 = primarySchoolClassAdapterA.getViewByPosition(activityPrimarySchoolClassRecList02, xuekeIdRecord, R.id.adapter_primary_school_class_text_a);
                                            view2.setBackground(null);
                                            xuekeIdRecord = -1;
                                        }
                                    }
                                }
                            }else{
                                xueke_change = true;
                                xuekeIdRecord = xuekeId_prePos;
                                initDataB(nianji_id, data_A.get(0).getXueke_id());
                            }
                        }
                    }
                });
    }

    private void initNewData(int p,String tags,String nianji_id,String xueke_id) {
        //科门
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_Home_09_B)
                .addParams("os","android")
                .addParams("nianji_id",String.valueOf(nianji_id))
                .addParams("xueke_id",String.valueOf(xueke_id))
                .addParams("p",String.valueOf(p))
                .addParams("tags",String.valueOf(tags))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        primarySchoolClassBeanA = gson.fromJson(response,PrimarySchoolClassABean.class);
                        data_A = primarySchoolClassBeanA.getXuekeList();
                        primarySchoolClassAdapterA.setNewData(data_A);
                    }
                });
    }


        OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {
            type=2;
            nianji_id = data.get(i).getNianji_id();
            initDataB(nianji_id,xueke_id);
//年级
            ItemSelected=i;

            view = linearLayoutManager.findViewByPosition(i);
            TextView statusb = (TextView) view.findViewById(R.id.adapter_primary_school_class_text) ;
            statusb.setBackgroundResource(R.drawable.ico_btn_1);
            if (item_int==1){
                data_page_01.add(page_int,new PageBean(i));
                item_int=2;
            }else {
                page_int++;
                data_page_01.add(page_int,new PageBean(i));
                if (i!=data_page_01.get(page_int-1).getPage_int()){
                    view = linearLayoutManager.findViewByPosition(data_page_01.get(page_int-1).getPage_int());
                    TextView statusbs = (TextView) view.findViewById(R.id.adapter_primary_school_class_text) ;
                    statusbs.setBackground(null);
                }
            }


        }
    };

        OnItemSelectedListener onItemSelectedListenerA = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {
            xueke_id = data_A.get(i).getXueke_id();
            type =1;
            initDataB(nianji_id,xueke_id);

            //学科

            view = linearLayoutManager_ab.findViewByPosition(i);
            TextView statusb = (TextView) view.findViewById(R.id.adapter_primary_school_class_text_a) ;
            statusb.setBackgroundResource(R.drawable.ico_radius);

            if (item_int_A==1){
                data_page_02.add(page_int_A,new PageBean(i));
                item_int_A=2;
            }else {
                page_int_A++;
                data_page_02.add(page_int_A, new PageBean(i));
                if (i!=data_page_02.get(page_int_A-1).getPage_int()) {
                    view = linearLayoutManager_ab.findViewByPosition(data_page_02.get(page_int_A - 1).getPage_int());
                    ItemSelected=data_page_02.get(page_int_A - 1).getPage_int();
                    TextView statusbs = (TextView) view.findViewById(R.id.adapter_primary_school_class_text_a);
                    statusbs.setBackground(null);
                }
            }

        }
    };

    private class OnFocusHandler implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
//                case R.id.edit_name:
//
//                    if (hasFocus) {
//                        edit_name.setText("ning shuai");
//                    } else {
//                        edit_name.setText("");
//                    }
//                    break;
//                case R.id.edit_mobile:
//                    if (hasFocus) {
//                        edit_mobile.setText("963258741");
//                    } else {
//                        edit_mobile.setText("");
//                    }
//                    break;
            }
        }
    }


    OnItemSelectedListener onItemSelectedListenerB = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {

        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
                    finish();
                break;
            case KeyEvent.KEYCODE_DPAD_UP:   //向上键
//                Log.d(TAG, "up--->");
            //科门
                if (primarySchoolClassAdapter.getViewByPosition(activityPrimarySchoolClassRecList01,0,R.id.adapter_primary_school_class_text).hasFocus())
                {
                    if (ItemSelected==0){
                        primarySchoolClassAdapterA.getViewByPosition(activityPrimarySchoolClassRecList02,data_page_02.get(data_page_02.size()-1).getPage_int(),R.id.adapter_primary_school_class_text_a).requestFocus();
                    }
                }else {
                    if (primarySchoolClassQTAdapter.primarySchoolClassCAdapter.currentPositionInt==true){
                        List<PrimarySchoolClasCBean.CourseListBean.ListBean> listBeanList = data_qt.get(0).getList();
                        String courserId = primarySchoolClassQTAdapter.getCourseId();
                        for (PrimarySchoolClasCBean.CourseListBean.ListBean bean : listBeanList) {
                            if(bean.getId().equals(courserId)){
                                activity_primary_school_class_rec_list.scrollToPosition(0);
                                primarySchoolClassAdapterA.getViewByPosition(activityPrimarySchoolClassRecList02,data_page_02.get(data_page_02.size()-1).getPage_int(),R.id.adapter_primary_school_class_text_a).requestFocus();
                                break;
                            }
                        }
                    }

                }
//                    primarySchoolClassAdapterA.getViewByPosition(activityPrimarySchoolClassRecList02,data_page_02.get(data_page_02.size()-1).getPage_int(),R.id.adapter_primary_school_class_text_a).requestFocus();

                break;
            case KeyEvent.KEYCODE_DPAD_LEFT: //向左键
                //年级
                if (primarySchoolClassQTAdapter.getCurrentPosition()==0){
                    if (data_page_01.size()==0){
                    activityPrimarySchoolClassRecList01.requestFocus();
                }else {
                        primarySchoolClassAdapter.getViewByPosition(activityPrimarySchoolClassRecList01,data_page_01.get(data_page_01.size()-1).getPage_int(),R.id.adapter_primary_school_class_text).requestFocus();
                }
                }

//                activityPrimarySchoolClassRecList01.setItemSelected(1);
//                Log.d(TAG, "left--->");
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 拦截按键事件
     * @param event
     * @return
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
        //按下键若为最后一行则阻止焦点移到导航栏
        if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN && event.getAction() == KeyEvent.ACTION_DOWN) {
            int itemCount = primarySchoolClassQTAdapter.getItemCount();
            if(itemCount > 0 && activity_primary_school_class_rec_list.hasFocus()){
                List<PrimarySchoolClasCBean.CourseListBean.ListBean> listBeanList = data_qt.get(itemCount-1).getList();
                String courserId = primarySchoolClassQTAdapter.getCourseId();
                for (PrimarySchoolClasCBean.CourseListBean.ListBean bean : listBeanList) {
                    if(bean.getId().equals(courserId)){
                        return true;
                    }
                }
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
