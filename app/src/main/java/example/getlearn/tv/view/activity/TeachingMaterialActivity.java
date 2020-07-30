package example.getlearn.tv.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import example.getlearn.tv.util.OnItemSelectedListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.MainActivity;
import example.getlearn.tv.R;
import example.getlearn.tv.adapter.TeachingMaterialAdapter;
import example.getlearn.tv.adapter.TeachingMaterialBateAdapter;
import example.getlearn.tv.bean.BagBean;
import example.getlearn.tv.bean.CateDataBean;
import example.getlearn.tv.bean.PageBean;
import example.getlearn.tv.bean.TeachingMaterialBateBean;
import example.getlearn.tv.bean.TeachingMaterialBean;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.LayoutManagerUtils;
import example.getlearn.tv.util.LinearLayoutManagerUtils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import example.getlearn.tv.util.TvRecyclerView;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/*****
 *
 *
 * 妙解教材
 *
 */
public class TeachingMaterialActivity extends Activity implements View.OnClickListener{

    @BindView(R.id.ac_teaching_material_rec_list)
    app.com.tvrecyclerview.TvRecyclerView acTeachingMaterialRecList;
    @BindView(R.id.ac_teaching_material_rec_bate)
    app.com.tvrecyclerview.TvRecyclerView acTeachingMaterialRecBate;
    @BindView(R.id.ac_teaching_material_rec_layout)
    LinearLayout acTeachingMaterialRecLayout;
    @BindView(R.id.ac_teaching_material_imag_01)
    ImageView acTeachingMaterialImag01;
    @BindView(R.id.ac_teaching_material_imag_02)
    ImageView acTeachingMaterialImag02;
    @BindView(R.id.ac_teaching_material_imag_03)
    ImageView acTeachingMaterialImag03;
    @BindView(R.id.ac_teaching_material_imag_04)
    ImageView acTeachingMaterialImag04;
    @BindView(R.id.ac_teaching_material_imag_05)
    ImageView acTeachingMaterialImag05;
    @BindView(R.id.ac_teaching_material_imag_06)
    ImageView acTeachingMaterialImag06;
    @BindView(R.id.ac_teaching_material_imag_07)
    ImageView acTeachingMaterialImag07;
    @BindView(R.id.activity_teaching_material_ll_layout)
    LinearLayout activity_teaching_material_ll_layout;
//    @BindView(R.id.ac_teaching_material_text_01)
//    TextView ac_teaching_material_text_01;
//    @BindView(R.id.ac_teaching_material_iamg_vip)
//    ImageView ac_teaching_material_iamg_vip;
private boolean mFocusConsumed = false; //长按标志位
    private TeachingMaterialAdapter teachingMaterialAdapter;
    private TeachingMaterialBean teachingMaterialBean;
    private List<TeachingMaterialBean.CateDataBean> data = new ArrayList<>();
    private List<CateDataBean> data_02 = new ArrayList<>();

    private TeachingMaterialBateAdapter teachingMaterialBateAdapter;
    private TeachingMaterialBateBean teachingMaterialBateBean;
    private List<TeachingMaterialBateBean.CourseBean> dataBate;
    private String home1_bg_imag_url;
    private  long mTimeDelay,mTimeLast,mTimeSpace;


    private List<PageBean> data_page = new ArrayList<>();

    //    private TeachingMaterialBateAdapter teachingMaterialBateAdapter;
//    private TeachingMaterialBateBean teachingMaterialBateBean;
//    private List<TeachingMaterialBateBean> dataBate = new ArrayList<>();
    private int page;
    private int item_int = 1;
    private int page_int=0;
    private int page_max,bottom;
    private String cid_max;
    private  LayoutManagerUtils linearLayoutManager;
    private LinearLayoutManagerUtils linearLayoutManagerUtils;
    private int mLastKeyDownTime=10000;
    private String Template_id;
    public static void starActivity(Activity activity,String Template_id) {
        Intent intent = new Intent(activity, TeachingMaterialActivity.class);
        intent.putExtra("Template_id",Template_id);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //网络检测
            initIntenData();
            switch (Template_id){
                case "1":
                    setContentView(R.layout.activity_teaching_material);
                    break;
            }
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
            dataBate = new ArrayList<>();
            page=1;
        if (NetworkUtils.isNetWorkAvailable(this)){
            initAdapter();
            ititData();
            initView();

        }else {
            Toast.makeText(this, "网络连接失败！", Toast.LENGTH_SHORT).show();
        }
    }
    private void initIntenData() {
        Intent intent = getIntent();
        Template_id = intent.getStringExtra("Template_id");
    }
    private void initGlide() {
        Glide.with(this)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+teachingMaterialBean.getRec_data().get(0).getImg())
                .into(acTeachingMaterialImag01);
        Glide.with(this)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+teachingMaterialBean.getRec_data().get(1).getImg())
                .into(acTeachingMaterialImag02);
        Glide.with(this)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+teachingMaterialBean.getRec_data().get(2).getImg())
                .into(acTeachingMaterialImag03);
        Glide.with(this)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+teachingMaterialBean.getRec_data().get(3).getImg())
                .into(acTeachingMaterialImag04);
        Glide.with(this)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+teachingMaterialBean.getRec_data().get(4).getImg())
                .into(acTeachingMaterialImag05);
        Glide.with(this)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+teachingMaterialBean.getRec_data().get(5).getImg())
                .into(acTeachingMaterialImag06);
        Glide.with(this)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+teachingMaterialBean.getRec_data().get(6).getImg())
                .into(acTeachingMaterialImag07);
        acTeachingMaterialImag01.setOnClickListener(this);
        acTeachingMaterialImag02.setOnClickListener(this);
        acTeachingMaterialImag03.setOnClickListener(this);
        acTeachingMaterialImag04.setOnClickListener(this);
        acTeachingMaterialImag05.setOnClickListener(this);
        acTeachingMaterialImag06.setOnClickListener(this);
        acTeachingMaterialImag07.setOnClickListener(this);

    }

    private void initView() {
//        ac_teaching_material_iamg_vip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                VipActivity.starActivity(TeachingMaterialActivity.this,"1",home1_bg_imag_url);
//            }
//        });

        teachingMaterialBateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CompositionGuidanceActivity.starActivity(TeachingMaterialActivity.this,"1",
                        dataBate.get(position).getId()
                        ,"course");
            }
        });

//        teachingMaterialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (data_02.get(position).getCname().equals("vip")){
//                    VipActivity.starActivity(TeachingMaterialActivity.this,"1",home1_bg_imag_url);
//                }
//            }
//        });
//
//        ac_teaching_material_text_01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                acTeachingMaterialRecLayout.setVisibility(View.VISIBLE);
//                acTeachingMaterialRecBate.setVisibility(View.GONE);
//            }
//        });

        acTeachingMaterialImag01.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    bottom=99;
                }else {
                    bottom=3;
                }
            }
        });
        acTeachingMaterialImag05.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    bottom=99;
                }else {
                    bottom=3;
                }
            }
        });
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == 0) {
            long nowTime = SystemClock.elapsedRealtime();
            this.mTimeDelay = nowTime - this.mTimeLast;
            this.mTimeLast = nowTime;
            if(this.mTimeSpace <= 300L && this.mTimeDelay <= 300L) {
                this.mTimeSpace += this.mTimeDelay;
                return true;
            }

            this.mTimeSpace = 0L;
        }

        if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT){
            if(acTeachingMaterialRecList.hasFocus()){
                if (bottom==2){
                    bottom=99;
                    acTeachingMaterialRecBate.requestFocus();
                    acTeachingMaterialRecBate.setItemSelected(0);
                    return true;
                }

            }
        }

        if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
//            Log.d(TAG, "down--->");
            if(acTeachingMaterialRecBate.hasFocus() && page == 1){
                int courseSize = teachingMaterialBateBean.getCourse().size();
                int selectedPos = acTeachingMaterialRecBate.getSelectedPosition();
//                Log.i(TAG, "onKeyDown: selectedPos = " + selectedPos);
                if(courseSize <= 4){
                    return true;
                }else if(courseSize > 4 && courseSize <= 8){
                    if(selectedPos > 3){
                        return true;
                    }
                }
            }
        }

        return super.dispatchKeyEvent(event);
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
         linearLayoutManager =new LayoutManagerUtils(TeachingMaterialActivity.this, 4);
        acTeachingMaterialRecBate.setLayoutManager(linearLayoutManager);
//        acTeachingMaterialRecBate.setItemAnimator(null);
//        teachingMaterialAdapter = new TeachingMaterialAdapter(R.layout.adapter_teaching_material_item, data);
        teachingMaterialBateAdapter = new TeachingMaterialBateAdapter(R.layout.adapter_teaching_material_bate_item, dataBate);
        teachingMaterialBateAdapter.setHasStableIds(true);
        acTeachingMaterialRecBate.setAdapter(teachingMaterialBateAdapter);
        teachingMaterialBateAdapter.setOnItemSelectedAListener(onItemSelectedListenerfff);
        acTeachingMaterialRecBate.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        if (page<page_max){
                        ititDataList(cid_max,++page);
//                            Toast.makeText(TeachingMaterialActivity.this, String.valueOf(page), Toast.LENGTH_SHORT).show();
                        }
                        recyclerView.scrollToPosition(teachingMaterialBateAdapter.getItemCount()-1);
//                        Toast.makeText(TeachingMaterialActivity.this, "滑动到底了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        linearLayoutManagerUtils =new LinearLayoutManagerUtils(this);
        acTeachingMaterialRecList.setLayoutManager(linearLayoutManagerUtils);
        acTeachingMaterialRecList.setItemAnimator(null);
        teachingMaterialAdapter = new TeachingMaterialAdapter(R.layout.adapter_teaching_material_item, data_02);
        teachingMaterialAdapter.setHasStableIds(true);
        acTeachingMaterialRecList.setAdapter(teachingMaterialAdapter);
        teachingMaterialAdapter.setOnItemSelectedAListener(onItemSelectedListener);
    }

    private void ititData() {

        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_SecondSolution)
                .addParams("os","android")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        teachingMaterialBean = gson.fromJson(response,TeachingMaterialBean.class);
                        data = teachingMaterialBean.getCate_data();
                        data_02.add(0,new CateDataBean("推荐",""));
                        for (int i=1;i<data.size()+1;i++){
//                            if (data.size()+1==i){
//                                data_02.add(i,new CateDataBean("vip",""));
//                            }else {
                                data_02.add(i,new CateDataBean(data.get(i-1).getCname(),data.get(i-1).getCid()));
//                            }
                        }
                        teachingMaterialAdapter.setNewData(data_02);
                        GroceryStoreUtils.GlideBG(TeachingMaterialActivity.this,
                                NetworkInterfaceUtils.InterFace_Imag + teachingMaterialBean.getPublic_skin_img(),activity_teaching_material_ll_layout);
                        initGlide();
                    }
                });
    }
    private void ititDataList(String cid,int p) {

        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_SecondSolution_listData)
                .addParams("os","android")
                .addParams("cid",cid)
                .addParams("p",String.valueOf(p))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        teachingMaterialBateBean = gson.fromJson(response,TeachingMaterialBateBean.class);
                        List<TeachingMaterialBateBean.CourseBean> temp = teachingMaterialBateBean.getCourse();
                        if (page==1){
                            dataBate.clear();
                            dataBate.addAll(temp);
                            teachingMaterialBateAdapter.notifyDataSetChanged();
                            page_max = teachingMaterialBateBean.getPages();
                        }else {
                            teachingMaterialBateAdapter.addData(temp);
                            teachingMaterialBateAdapter.getData().size();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_teaching_material_imag_01:
                CompositionGuidanceActivity.starActivity(TeachingMaterialActivity.this,"1",
                        teachingMaterialBean.getRec_data().get(0).getCourse_id()
                        ,"course");
                break;
            case R.id.ac_teaching_material_imag_02:
                CompositionGuidanceActivity.starActivity(TeachingMaterialActivity.this,"1",
                        teachingMaterialBean.getRec_data().get(1).getCourse_id()
                        ,"course");
                break;
            case R.id.ac_teaching_material_imag_03:
                CompositionGuidanceActivity.starActivity(TeachingMaterialActivity.this,"1",
                        teachingMaterialBean.getRec_data().get(2).getCourse_id()
                        ,"course");
                break;
            case R.id.ac_teaching_material_imag_04:
                CompositionGuidanceActivity.starActivity(TeachingMaterialActivity.this,"1",
                        teachingMaterialBean.getRec_data().get(3).getCourse_id()
                        ,"course");
                break;
            case R.id.ac_teaching_material_imag_05:
                CompositionGuidanceActivity.starActivity(TeachingMaterialActivity.this,"1",
                        teachingMaterialBean.getRec_data().get(4).getCourse_id()
                        ,"course");
                break;
            case R.id.ac_teaching_material_imag_06:
                CompositionGuidanceActivity.starActivity(TeachingMaterialActivity.this,"1",
                        teachingMaterialBean.getRec_data().get(5).getCourse_id()
                        ,"course");
                break;
            case R.id.ac_teaching_material_imag_07:
                CompositionGuidanceActivity.starActivity(TeachingMaterialActivity.this,"1",
                        teachingMaterialBean.getRec_data().get(6).getCourse_id()
                        ,"course");
                break;
        }
    }
@Subscribe(threadMode = ThreadMode.MAIN)
public void BagImagURLEventBus(BagBean bagBean){
        home1_bg_imag_url=bagBean.getBag_imag_url_home1();
}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {


            if (data_02.get(i).getCname().equals("推荐")){
                bottom=77;
                acTeachingMaterialRecLayout.setVisibility(View.VISIBLE);
                acTeachingMaterialRecBate.setVisibility(View.GONE);
            }else {
//                if (!data_02.get(i).getCname().equals("vip")){
                bottom=2;
                acTeachingMaterialRecBate.setVisibility(View.VISIBLE);
                acTeachingMaterialRecLayout.setVisibility(View.GONE);
                page = 1;
                cid_max = data_02.get(i).getCid();
                ititDataList(cid_max,page);
//                }
            }
//            if (data_02.get(i).getCname().equals("vip")){
//                view = linearLayoutManager.findViewByPosition(i);
//                TextView statusbs = (TextView) view.findViewById(R.id.adapter_text_01) ;
//                statusbs.setBackgroundResource(R.drawable.rbtn2on);
//            }else {
                view = linearLayoutManagerUtils.findViewByPosition(i);
                TextView statusbs = (TextView) view.findViewById(R.id.adapter_text_01) ;
                statusbs.setBackgroundResource(R.drawable.rimg200_2);
//            }

            if (item_int==1){
                data_page.add(page_int,new PageBean(i));
                item_int=2;
            }else {
                page_int++;
                data_page.add(page_int,new PageBean(i));
                if (i!=data_page.get(page_int-1).getPage_int()) {


//                    if (!data_02.get(data_page.get(page_int - 1).getPage_int()).getCname().equals("vip")) {


                        view = linearLayoutManagerUtils.findViewByPosition(data_page.get(page_int - 1).getPage_int());
                        TextView statusb = (TextView) view.findViewById(R.id.adapter_text_01);
                        statusb.setBackground(null);
                        statusb.setTextColor(getResources().getColor(R.color.color_white));


//                    } else {
//                        view = linearLayoutManager.findViewByPosition(data_page.get(page_int - 1).getPage_int());
//                        TextView statsss = (TextView) view.findViewById(R.id.adapter_text_01);
//                        statsss.setBackgroundResource(R.drawable.rbtn2);
//                    }
                }
            }
        }
    };
    OnItemSelectedListener onItemSelectedListenerfff = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {

        }

    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long current = System.currentTimeMillis();
        boolean dispatch;
        if (current - mLastKeyDownTime < 150) {
            dispatch= true;
        } else {
            dispatch= super.onKeyDown(keyCode, event);
            mLastKeyDownTime = (int) current;
        }

        switch (keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT: //向左键
                if (bottom==99){
                int gayint = teachingMaterialBateAdapter.getCurrentPosition()%4;
                if (gayint==0 ){
                    teachingMaterialAdapter.getViewByPosition(acTeachingMaterialRecList,data_page.get(data_page.size()-1).getPage_int(),R.id.adapter_text_01).requestFocus();
                }
                }

                break;

            case KeyEvent.KEYCODE_DPAD_DOWN:   //向下键
                /*    实际开发中有时候会触发两次，所以要判断一下按下时触发 ，松开按键时不触发
                 *    exp:KeyEvent.ACTION_UP
                 */
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    Log.d(TAG, "down--->");
                }

                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:  //向右键
//                Log.d(TAG, "right--->");
                if (bottom==2){
                    bottom=99;
                    acTeachingMaterialRecBate.requestFocus();
//                    if(acTeachingMaterialRecList.hasFocus()){
//                        acTeachingMaterialRecBate.setItemSelected(0);
//                    }
                }

                break;
            case KeyEvent.KEYCODE_DPAD_UP:   //向上键
//                Log.d(TAG, "up--->");

        }
        return dispatch;

    }

}
