package example.getlearn.tv.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.R;
import example.getlearn.tv.adapter.SearchOneAdapter;
import example.getlearn.tv.adapter.SearchOneDataAdapter;
import example.getlearn.tv.adapter.SearchOneLookupAdapter;
import example.getlearn.tv.bean.MyCollectionBean;
import example.getlearn.tv.bean.SearchOneBean;
import example.getlearn.tv.bean.SearchOneDataBean;
import example.getlearn.tv.bean.SearchOneLookupBean;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.LinearLayoutUtils;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.NetworkUtils;
import example.getlearn.tv.util.SystemUtil;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/***
 *
 * 搜索
 *
 */


public class SearchActivity extends Activity {
    @BindView(R.id.activity_search_rec_list)
    RecyclerView activitySearchRecList;
    @BindView(R.id.activity_search_rec_list_data)
    TvRecyclerView activitySearchRecListData;
    @BindView(R.id.activity_search_rec_list_data_sc)
    TvRecyclerView activity_search_rec_list_data_sc;
    @BindView(R.id.activity_search_et)
    EditText activitySearchEt;
    @BindView(R.id.activity_watch_the_record_text_but_low)
    TextView activity_watch_the_record_text_but_low;
    @BindView(R.id.activity_watch_the_record_text_but_up)
    TextView activity_watch_the_record_text_but_up;
    @BindView(R.id.activity_search_ll_layout_bg)
    LinearLayout activity_search_ll_layout_bg;
    @BindView(R.id.activity_search_text_01)
    TextView activity_search_text_01;
    @BindView(R.id.activity_search_text_02)
    TextView activity_search_text_02;
    @BindView(R.id.activity_search_layout_sx_01)
    LinearLayout activity_search_layout_sx_01;
    @BindView(R.id.activity_search_layout_sx_02)
    LinearLayout activity_search_layout_sx_02;
    @BindView(R.id.activity_text_button_climinate)
    TextView activity_text_button_climinate;
    @BindView(R.id.activity_text_button_delete)
    TextView activity_text_button_delete;
    @BindView(R.id.activity_search_ll_01)
    LinearLayout activity_search_page;

    private StringBuffer s = new StringBuffer();


    public static void starActivity(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
     activity.startActivity(intent);
    }

    private SearchOneAdapter searchOneAdapter;
    private SearchOneBean searchOneBean;
    private List<SearchOneBean> data_one = new ArrayList<>();

    private SearchOneDataAdapter searchOneDataAdapter;
    private SearchOneDataBean searchOneDataBean;
    private List<SearchOneDataBean.CourseBean> data_Data = new ArrayList<>();


    private SearchOneLookupBean searchOneLookupBean;
    private SearchOneLookupAdapter searchOneLookupAdapter;
    private List<SearchOneLookupBean.CourseBean> data_lookup = new ArrayList<>();
    private  int page;
    private  int page_sc;
    private  int Max_page;
    private  int tytle;
    private String a;
    //刚进入页面
    private boolean firstEnterPage;
    //点击搜索的字母
    private boolean hasClickWord;
    //点击删除
    private boolean hasClickDel;
    //点击清除
    private boolean hasclickClean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        page=1;
        page_sc=1;
        tytle = 4;
        //网络检测
        if (NetworkUtils.isNetWorkAvailable(this)){
            initKeyboardData();
            initAdapter();
            activity_search_page.setVisibility(View.INVISIBLE);
            initData(page);
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

    private void initAdapter() {
        //搜索 英文字母
        activitySearchRecList.setLayoutManager(new GridLayoutManager(this, 6));
        searchOneAdapter = new SearchOneAdapter(R.layout.adapter_search_one_item, data_one);
        activitySearchRecList.setAdapter(searchOneAdapter);
        searchOneAdapter.notifyDataSetChanged();
        //搜索历史
//        activitySearchRecList.setLayoutManager(new GridLayoutManager(this, 3));
//        searchOneAdapter = new SearchOneAdapter(R.layout.adapter_search_one_item, data_one);
//        activitySearchRecList.setAdapter(searchOneAdapter);
//        searchOneAdapter.notifyDataSetChanged();

        //热门搜索
        activitySearchRecListData.setLayoutManager(new GridLayoutManager(this, 2));
        searchOneDataAdapter = new SearchOneDataAdapter(R.layout.adapter_search_one_data_item_two, data_Data);
        activitySearchRecListData.setAdapter(searchOneDataAdapter);

        //查找
        activity_search_rec_list_data_sc.setLayoutManager(new GridLayoutManager(this, 2));
        searchOneLookupAdapter = new SearchOneLookupAdapter(R.layout.adapter_search_one_data_item_two, data_lookup);
        activity_search_rec_list_data_sc.setAdapter(searchOneLookupAdapter);
    }

    private void initView() {

        //下一页
        activity_watch_the_record_text_but_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tytle==9){
                    if (page_sc<Max_page){
                        data_lookup.clear();
                        initDataLookup(++page_sc,a);
                    }else {
                        Toast.makeText(SearchActivity.this, "已经到底了！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (page<Max_page){
                        data_Data.clear();
                        initData(++page);
                    }else {
                        Toast.makeText(SearchActivity.this, "已经到底了！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //上一页
        activity_watch_the_record_text_but_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tytle==9){
                    if (page_sc>1){
                        data_lookup.clear();
                        initDataLookup(--page_sc,a);
                    }
                }else {
                    if (page>1){
                        data_Data.clear();
                        initData(--page);
                    }
                }
            }
        });
        searchOneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                hasClickWord = true;
                tytle=9;
                activity_search_layout_sx_02.setVisibility(View.GONE);
                s.append(data_one.get(position).getNumber());
                page_sc=1;
                a = s.toString();
                activitySearchEt.setText(a);
                activity_search_layout_sx_01.setVisibility(View.VISIBLE);
                initDataLookup(page_sc,a);
            }
        });
        //清除
        activity_text_button_climinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tytle=666;
                hasclickClean = true;
                activity_search_layout_sx_01.setVisibility(View.GONE);
                int  sb_length = s.length();// 取得字符串的长度
                s.delete(0,sb_length);
                a = s.toString();
                activitySearchEt.setText(a);
                page=1;
                page_sc=1;
                initData(page);
                activity_search_layout_sx_02.setVisibility(View.VISIBLE);
            }
        });
        //删除
        activity_text_button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.length() > 1){
                    tytle=9;
                    hasClickDel = true;
                    a = s.deleteCharAt(s.length() - 1).toString();
                    activitySearchEt.setText(a);
                    page=1;
                    page_sc=1;
                    initDataLookup(page_sc, a);
                    activity_search_layout_sx_01.setVisibility(View.VISIBLE);
                    activity_search_layout_sx_02.setVisibility(View.GONE);
                }else if(s.length() == 1){
                    tytle=666;
                    hasclickClean = true;
                    activity_search_layout_sx_01.setVisibility(View.GONE);
                    int  sb_length = s.length();// 取得字符串的长度
                    s.delete(0,sb_length);
                    a = s.toString();
                    activitySearchEt.setText(a);
                    page=1;
                    page_sc=1;
                    initData(page);
                    activity_search_layout_sx_02.setVisibility(View.VISIBLE);
                }

            }
        });


        searchOneLookupAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CompositionGuidanceActivity.starActivity(SearchActivity.this,"1",
                        data_lookup.get(position).getId()
                        ,"course");
            }
        });
        searchOneDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CompositionGuidanceActivity.starActivity(SearchActivity.this,"1",
                        data_Data.get(position).getId()
                        ,"course");
            }
        });
    }

    private void initDataLookup(final int p, String Title) {
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.InterFace_SearchListData)
                .addParams("os","android")
                .addParams("p",String.valueOf(p))
                .addParams("title",Title)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i(TAG, "initDataLookup: "+response);
                        Gson gson = new Gson();
                        searchOneLookupBean = gson.fromJson(response,SearchOneLookupBean.class);
                        data_lookup = searchOneLookupBean.getCourse();
                        searchOneLookupAdapter.setNewData(data_lookup);
                        activity_search_text_01.setText(String.valueOf(page_sc));
                        if (page_sc==1){
                            Max_page = searchOneLookupBean.getPages();
                            activity_search_text_02.setText(String.valueOf("/"+Max_page));
                        }
                        if(tytle == 9){
                            if(p == 1 && searchOneLookupBean.getPages() <= 1){// 当前页为第一页，并且只有一页
                                activity_watch_the_record_text_but_up.setVisibility(View.INVISIBLE);
                                activity_watch_the_record_text_but_low.setVisibility(View.INVISIBLE);
                            }else if(p == 1 && searchOneLookupBean.getPages() > 1){// 当前页为第一页，并且总页数大于一页
                                activity_watch_the_record_text_but_up.setVisibility(View.INVISIBLE);
                                activity_watch_the_record_text_but_low.setVisibility(View.VISIBLE);
                                if(!hasClickWord && !hasClickDel){
                                    activity_watch_the_record_text_but_low.requestFocus();
                                }
                                hasClickDel = false;
                                hasClickWord = false;
                            }else if(p == searchOneLookupBean.getPages()){// 当前页是最后一页
                                activity_watch_the_record_text_but_up.setVisibility(View.VISIBLE);
                                activity_watch_the_record_text_but_low.setVisibility(View.INVISIBLE);
                                activity_watch_the_record_text_but_up.requestFocus();
                            }else{// 当前页不是第一页，也不是最后一页
                                activity_watch_the_record_text_but_up.setVisibility(View.VISIBLE);
                                activity_watch_the_record_text_but_low.setVisibility(View.VISIBLE);
                            }

                        }

                    }
                });
    }

    private void initData(final int p) {
        OkHttpUtils
                .post()
                .url(NetworkInterfaceUtils.HTTP_NAME+NetworkInterfaceUtils.InterFace_SearchAc)
                .addParams("os","android")
                .addParams("p",String.valueOf(p))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i(TAG, "initData: "+response);
                        Gson gson = new Gson();
                        searchOneDataBean = gson.fromJson(response,SearchOneDataBean.class);
                        data_Data = searchOneDataBean.getCourse();
                        searchOneDataAdapter.setNewData(data_Data);
                        activity_search_text_01.setText(String.valueOf(page));
//                        Toast.makeText(SearchActivity.this, String.valueOf(page), Toast.LENGTH_SHORT).show();
                        if (page==1){
                            Max_page = searchOneDataBean.getPage();
                            activity_search_text_02.setText(String.valueOf("/"+Max_page));
                            GroceryStoreUtils.GlideBG(SearchActivity.this,NetworkInterfaceUtils.InterFace_Imag + searchOneDataBean.getPublic_skin_img(),activity_search_ll_layout_bg);

                        }
                        if(tytle != 9){
                            if(p == 1 && searchOneDataBean.getPage() <= 1){// 当前页为第一页，并且只有一页
                                activity_watch_the_record_text_but_up.setVisibility(View.INVISIBLE);
                                activity_watch_the_record_text_but_low.setVisibility(View.INVISIBLE);
                            }else if(p == 1 && searchOneDataBean.getPage() > 1){// 当前页为第一页，并且总页数大于一页
                                activity_watch_the_record_text_but_up.setVisibility(View.INVISIBLE);
                                activity_watch_the_record_text_but_low.setVisibility(View.VISIBLE);
                                if(!firstEnterPage && !hasclickClean){
                                    activity_watch_the_record_text_but_low.requestFocus();
                                }
                                hasclickClean = false;
                                firstEnterPage = false;

                            }else if(p == searchOneDataBean.getPage()){// 当前页是最后一页
                                activity_watch_the_record_text_but_up.setVisibility(View.VISIBLE);
                                activity_watch_the_record_text_but_low.setVisibility(View.INVISIBLE);
                                activity_watch_the_record_text_but_up.requestFocus();
                            }else{// 当前页不是第一页，也不是最后一页
                                activity_watch_the_record_text_but_up.setVisibility(View.VISIBLE);
                                activity_watch_the_record_text_but_low.setVisibility(View.VISIBLE);
                            }

                        }
                        activity_search_page.setVisibility(View.VISIBLE);
                    }
                });
    }
    private void initKeyboardData () {
        data_one.add(new SearchOneBean("A"));
        data_one.add(new SearchOneBean("B"));
        data_one.add(new SearchOneBean("C"));
        data_one.add(new SearchOneBean("D"));
        data_one.add(new SearchOneBean("E"));
        data_one.add(new SearchOneBean("F"));
        data_one.add(new SearchOneBean("G"));
        data_one.add(new SearchOneBean("H"));
        data_one.add(new SearchOneBean("I"));
        data_one.add(new SearchOneBean("J"));
        data_one.add(new SearchOneBean("K"));
        data_one.add(new SearchOneBean("L"));
        data_one.add(new SearchOneBean("M"));
        data_one.add(new SearchOneBean("N"));
        data_one.add(new SearchOneBean("O"));
        data_one.add(new SearchOneBean("P"));
        data_one.add(new SearchOneBean("Q"));
        data_one.add(new SearchOneBean("R"));
        data_one.add(new SearchOneBean("S"));
        data_one.add(new SearchOneBean("T"));
        data_one.add(new SearchOneBean("V"));
        data_one.add(new SearchOneBean("N"));
        data_one.add(new SearchOneBean("W"));
        data_one.add(new SearchOneBean("X"));
        data_one.add(new SearchOneBean("Y"));
        data_one.add(new SearchOneBean("Z"));
        data_one.add(new SearchOneBean("1"));
        data_one.add(new SearchOneBean("2"));
        data_one.add(new SearchOneBean("3"));
        data_one.add(new SearchOneBean("4"));
        data_one.add(new SearchOneBean("5"));
        data_one.add(new SearchOneBean("6"));
        data_one.add(new SearchOneBean("7"));
        data_one.add(new SearchOneBean("8"));
        data_one.add(new SearchOneBean("9"));
        data_one.add(new SearchOneBean("0"));
    }

}
