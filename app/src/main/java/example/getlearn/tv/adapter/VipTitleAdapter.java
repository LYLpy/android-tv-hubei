package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.VipBean;
import example.getlearn.tv.util.MovieViewHolder;
import example.getlearn.tv.util.OnItemSelectedListener;

/**
 * Created by zlw on 2018/11/14.
 */

public class VipTitleAdapter extends BaseQuickAdapter<VipBean.PriceCateBean,MovieViewHolder> {

    private int currentPosition;
    private OnItemSelectedListener mOnItemSelectedListener;

    public VipTitleAdapter(int layoutResId, @Nullable List<VipBean.PriceCateBean> data) {
        super(R.layout.adapter_vip_title_item, data);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(final MovieViewHolder helper, VipBean.PriceCateBean item) {
        helper.itemView.setFocusable(true);
        helper.setText(R.id.adapter_vip_title_item_text,item.getFirst_cate());

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
}
