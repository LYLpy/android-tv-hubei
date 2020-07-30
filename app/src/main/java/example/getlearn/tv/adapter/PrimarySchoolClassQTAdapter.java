package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import example.getlearn.tv.util.OnItemSelectedListener;

import java.util.List;

import example.getlearn.tv.MainActivity;
import example.getlearn.tv.R;
import example.getlearn.tv.bean.PrimarySchoolClasCBean;
import example.getlearn.tv.util.GroceryStoreUtils;
import example.getlearn.tv.util.OnItemSelectedListenerUtls;
import example.getlearn.tv.view.activity.CompositionGuidanceActivity;

/**
 * Created by zlw on 2018/11/20.
 */

public class PrimarySchoolClassQTAdapter extends BaseQuickAdapter<PrimarySchoolClasCBean.CourseListBean,BaseViewHolder> {
    private  int postionX;
    private int currentPosition;
    private RecyclerView mGridRecyclerView;
    public static PrimarySchoolClassCAdapter primarySchoolClassCAdapter;
    private OnItemSelectedListener mOnItemSelectedListener;
    private String courseId;
    private static String currentPositionStringQT;

    public String getCourseId() {
        return courseId;
    }

    //课程数
    private int courseNum;
    public PrimarySchoolClassQTAdapter(int layoutResId, @Nullable List<PrimarySchoolClasCBean.CourseListBean> data) {
        super(R.layout.adapter_primary_school_class_item_qt, data);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public PrimarySchoolClassCAdapter getPrimarySchoolClassCAdapter() {
        return primarySchoolClassCAdapter;
    }

    public RecyclerView getmGridRecyclerView() {
        return mGridRecyclerView;
    }

    @Override
    protected void convert(final BaseViewHolder helper, PrimarySchoolClasCBean.CourseListBean item) {
        helper.setText(R.id.adapter_rec_text,item.getName());
        mGridRecyclerView = helper.getView(R.id.adapter_primary_school_list);

        courseNum = item.getList().size();

        helper.itemView.setTag(helper.getPosition());
        helper.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                Log.d(TAG, "焦点监听器被调用了");
//                Log.d(TAG, "hasFocus=" + hasFocus);
                if (hasFocus) {
                    currentPosition = (int) helper.itemView.getTag();
//                    Log.d(TAG, "getTag=" + currentPosition);
//                    Log.i(TAG, "The view hasFocus=" + v + ", holder.itemView=" + helper.itemView);
                    mOnItemSelectedListener.OnItemSelected(v, currentPosition);
                }
            }
        });
    }
    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }
    OnItemSelectedListenerUtls onItemSelectedListener = new OnItemSelectedListenerUtls() {


        @Override
        public void OnItemSelected(View var1, int var2, String item_ID) {
            currentPosition=var2;
            courseId = item_ID;

            if (currentPosition==(courseNum-1)){
//                Toast.makeText(mContext, "滑动到底了", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final List<PrimarySchoolClasCBean.CourseListBean> usersList = mData;
        mGridRecyclerView.setHasFixedSize(true);
        mGridRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        primarySchoolClassCAdapter = new PrimarySchoolClassCAdapter(R.layout.adapter_primary_school_class_item_c,usersList.get(position).getList());
        mGridRecyclerView.setAdapter(primarySchoolClassCAdapter);
        primarySchoolClassCAdapter.setOnItemSelectedListener(onItemSelectedListener);

        mGridRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
//                        Toast.makeText(mContext, "滑动到底了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        primarySchoolClassCAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                selectedPersonName = usersList.get(position).getRealName();
//                selectedUserId = usersList.get(position).getUserId();
//                //强转成radiobutton
//                RadioButton radioButton = (RadioButton) view;
//                if(oldView!=null){
//                    //将上一条被点击的设置为未选中
//                    oldView.setChecked(false);
//                }
//                //记录当前被点击的item
//                oldView = radioButton;

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
}
