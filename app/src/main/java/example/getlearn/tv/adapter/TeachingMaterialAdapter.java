package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import example.getlearn.tv.util.OnItemSelectedListener;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.CateDataBean;
import example.getlearn.tv.bean.TeachingMaterialBean;

/**
 * Created by zlw on 2018/11/6.
 */

public class TeachingMaterialAdapter extends BaseQuickAdapter<CateDataBean,BaseViewHolder> {
    private int currentPosition;
    private OnItemSelectedListener mOnItemSelectedListener;
    public TeachingMaterialAdapter(int layoutResId, @Nullable List<CateDataBean> data) {
        super(R.layout.adapter_teaching_material_item, data);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(final BaseViewHolder helper, CateDataBean item) {
        helper.itemView.setFocusable(true);
        switch (item.getCname())
        {
            case "推荐":
                helper.setTextColor(R.id.adapter_text_01,helper.getView(R.id.adapter_text_01).getResources().getColor(R.drawable.color_text_bg_002));
                helper.setText(R.id.adapter_text_01,item.getCname());
                break;
            case "vip":
                helper.setBackgroundRes(R.id.adapter_text_01,R.drawable.rbtn2);
                break;
            default:
                helper.setTextColor(R.id.adapter_text_01,helper.getView(R.id.adapter_text_01).getResources().getColor(R.drawable.color_text_01));
                helper.setText(R.id.adapter_text_01,item.getCname());
                break;
        }

//        if (item.getCname().equals("推荐")){
//            helper.setTextColor(R.id.adapter_text_01,helper.getView(R.id.adapter_text_01).getResources().getColor(R.drawable.color_text_bg_002));
//            helper.setText(R.id.adapter_text_01,item.getCname());
//        }else {
//            helper.setTextColor(R.id.adapter_text_01,helper.getView(R.id.adapter_text_01).getResources().getColor(R.drawable.color_text_01));
//            helper.setText(R.id.adapter_text_01,item.getCname());
//        }
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

    public void setOnItemSelectedAListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }
//   extends BaseMultiItemQuickAdapter
//    public TeachingMaterialAdapter(List data) {
//        super(data);
//        addItemType(TeachingMaterialBean.TEXT, R.layout.adapter_teaching_material_item);
//        addItemType(TeachingMaterialBean.IMG, R.layout.adapter_teaching_material_item_x);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, TeachingMaterialBean item) {
//      helper.itemView.setFocusable(true);
//
//        switch (helper.getItemViewType()) {
//            case TeachingMaterialBean.TEXT:
//                helper.setText(R.id.adapter_text_01,item.getText());
//                break;
//            case TeachingMaterialBean.IMG:
//                helper.setText(R.id.adapter_text_03,item.getText());
//                break;
//        }
//
//    }


//    public TeachingMaterialAdapter(int layoutResId, @Nullable List<TeachingMaterialBean> data) {
//        super(R.layout.adapter_teaching_material_item, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, TeachingMaterialBean item) {
//        helper.itemView.setFocusable(true);
//
//        helper.setText(R.id.adapter_text_01,item.getText());
//
//        if (item.getText().equals("推荐")){
//            helper.getView(R.id.adapter_text_01).getResources().getColor(R.color.color_01);
//
//        }
//
//
//
//
//    }
}
