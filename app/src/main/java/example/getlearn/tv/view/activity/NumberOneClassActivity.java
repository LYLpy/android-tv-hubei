package example.getlearn.tv.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.MainActivity;
import example.getlearn.tv.R;
import example.getlearn.tv.adapter.NumberOneClassAdapter;
import example.getlearn.tv.adapter.NumberOneClassBaseAdapter;
import example.getlearn.tv.bean.NumberOneClassBaseBean;
import example.getlearn.tv.bean.NumberOneClassBean;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/****
 *
 * 状元课堂
 *
 * */
public class NumberOneClassActivity extends Activity {


    @BindView(R.id.activity_number_one_class_rua_imag)
    ImageView activity_number_one_class_rua_imag;
    @BindView(R.id.activity_number_one_class_rec_list)
    RecyclerView activity_number_one_class_rec_list;
    @BindView(R.id.activity_number_one_class_rec_base)
    RecyclerView activity_number_one_class_rec_base;
    @BindView(R.id.activity_number_one_class_layout_bg)
    LinearLayout activity_number_one_class_layout_bg;
    @BindView(R.id.activity_number_one_class_title)
    TextView activity_number_one_class_title;
    @BindView(R.id.activity_number_one_class_title_02)
    TextView activity_number_one_class_title_02;
    @BindView(R.id.activity_number_one_class_text_01)
    TextView activity_number_one_class_text_01;
    @BindView(R.id.activity_number_one_class_text_02)
    TextView activity_number_one_class_text_02;
    @BindView(R.id.activity_watch_the_record_text_but_up)
    TextView activity_watch_the_record_text_but_up;
    @BindView(R.id.activity_watch_the_record_text_but_low)
    TextView activity_watch_the_record_text_but_low;
    @BindView(R.id.activity_number_one_class_title_03)
    TextView activity_number_one_class_title_03;
    @BindView(R.id.activiry_watch_the_record_home)
    ImageView activiryWatchTheRecordHome;
    @BindView(R.id.activiry_watch_the_record_src)
    ImageView activiryWatchTheRecordSrc;
    @BindView(R.id.activiry_watch_the_record_esc)
    ImageView activiryWatchTheRecordEsc;

    private NumberOneClassAdapter numberOneClassAdapter;
    private NumberOneClassBean numberOneClassBean;
    private List<NumberOneClassBean.DataBean> data_a = new ArrayList<>();

    private NumberOneClassBaseAdapter numberOneClassAdapter_base;
    private NumberOneClassBaseBean numberOneClassBean_base;
    private List<NumberOneClassBaseBean.ListBean> data_base = new ArrayList<>();
    private int page = 1;
    private int MAX_page = 1;
    private String type, lid, pageSize;
    private String URLPlay;

    private String Template_id;

    public static void starActivity(Activity activity, String Template_id) {
        Intent intent = new Intent(activity, NumberOneClassActivity.class);
        intent.putExtra("Template_id", Template_id);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntenData();
        //选择模板
        switch (Template_id) {
            case "1":
                setContentView(R.layout.activity_number_one_class);
                break;
        }
        ButterKnife.bind(this);
        //网络检测
        if (NetworkUtils.isNetWorkAvailable(this)){
            initDataList();
            initAdapter();
            initView();
        }else {
            Toast.makeText(this, "网络连接失败！", Toast.LENGTH_SHORT).show();
        }
    }

    private void initIntenData() {
        Intent intent = getIntent();
        Template_id = intent.getStringExtra("Template_id");
    }

    private void initAdapter() {
        //动物专题列表
        activity_number_one_class_rec_list.setLayoutManager(new LinearLayoutManager(this));
        numberOneClassAdapter = new NumberOneClassAdapter(R.layout.adapter_number_one_class_item_a, data_a);
        activity_number_one_class_rec_list.setAdapter(numberOneClassAdapter);
        numberOneClassAdapter.setOnItemSelectedListener(onItemSelectedListener);
        //课程列表
        activity_number_one_class_rec_base.setLayoutManager(new GridLayoutManager(this, 4));
        numberOneClassAdapter_base = new NumberOneClassBaseAdapter(R.layout.adapter_number_one_class_item_a, data_base);
        activity_number_one_class_rec_base.setAdapter(numberOneClassAdapter_base);
        numberOneClassAdapter_base.setOnItemSelectedListenerwww(onItemSelectedListener02);


    }

    private void initData(final int p, String type, String lid, String pageSize) {
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.HTTP_NAME + NetworkInterfaceUtils.InterFace_Data_01_list_imag)
                .addParams("os", "android")              //string类型  固定值 android
                .addParams("page", String.valueOf(p))         //int类型  页码
                .addParams("type", type)         //int类型  数据类型id
                .addParams("lid", lid)         //int类型  数据来源id
                .addParams("pageSize", pageSize)    //int类型  每页数据量
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        numberOneClassBean_base = gson.fromJson(response, NumberOneClassBaseBean.class);
                        data_base = numberOneClassBean_base.getList();
                        numberOneClassAdapter_base.setNewData(data_base);
                        activity_number_one_class_text_01.setText(String.valueOf(page));

                        if (page == 1) {
                            MAX_page = numberOneClassBean_base.getPages();
                            activity_number_one_class_title_03.setText("共"+numberOneClassBean_base.getCount()+"个视频");
                            activity_number_one_class_text_02.setText(String.valueOf("/" + numberOneClassBean_base.getPages()));
                        }

                    }
                });
    }

    private void initDataList() {
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.HTTP_NAME + NetworkInterfaceUtils.InterFace_Data_01_list_title)
                .addParams("os", "android")
                .addParams("topic_id", String.valueOf(1))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        numberOneClassBean = gson.fromJson(response, NumberOneClassBean.class);
                        data_a = numberOneClassBean.getData();
                        numberOneClassAdapter.setNewData(data_a);
                        activity_number_one_class_title.setText(numberOneClassBean.getTopic().getName());
                        activity_number_one_class_title_02.setText(numberOneClassBean.getTopic().getTopicdesc());
                        initGlide(NetworkInterfaceUtils.InterFace_Imag + numberOneClassBean.getTopic().getImgpath());
//                        GroceryStoreUtils.GlideBG(NumberOneClassActivity.this,NetworkInterfaceUtils.InterFace_Imag + numberOneClassBean.getTopic().getImgpath(),activity_number_one_class_layout_bg);
                    }
                });
    }

    private void initView() {


        activity_watch_the_record_text_but_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page < MAX_page) {
                    data_base.clear();
                    initData(++page, type, lid, pageSize);
                } else {
//                    Toast.makeText(NumberOneClassActivity.this, "已经到底了！", Toast.LENGTH_SHORT).show();
                }

            }
        });
        activity_watch_the_record_text_but_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    data_base.clear();
                    initData(--page, type, lid, pageSize);
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
                SearchActivity.starActivity(NumberOneClassActivity.this);
            }
        });
        activiryWatchTheRecordEsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initGlide(String urlImag) {
//        Glide.with(this)
//                .load(urlImag)
//                .bitmapTransform(new CropCircleTransformation(this),
//                        new CropTransformation(this, 140, 120, CropTransformation.CropType.TOP))//顶部裁剪
//                .into(activity_number_one_class_rua_imag);

        Glide.with(this)
                .load(urlImag)
                .bitmapTransform(new CropCircleTransformation(this))//顶部裁剪
                .into(activity_number_one_class_rua_imag);
    }

    OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {
//            initData(final int p,String type,String lid,String pageSize)
            page = 1;
            type = data_a.get(i).getType();
            lid = data_a.get(i).getLid();
            pageSize = "8";
            initData(page, type, lid, pageSize);
        }
    };

    OnItemSelectedListener onItemSelectedListener02= new OnItemSelectedListener() {
        @Override
        public void OnItemSelected(View view, int i) {
            URLPlay = data_base.get(i).getPlay_url();
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
}
