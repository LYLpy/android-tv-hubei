package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import example.getlearn.tv.util.OnItemSelectedListener;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.PrimarySchoolClassBean;

/**
 * Created by zlw on 2018/11/7.
 */

public class PrimarySchoolClassAdapter extends BaseQuickAdapter<PrimarySchoolClassBean.NianjiListBean,BaseViewHolder> {

    private int currentPosition;
    private OnItemSelectedListener mOnItemSelectedListener;
    public PrimarySchoolClassAdapter(int layoutResId, @Nullable List<PrimarySchoolClassBean.NianjiListBean> data) {
        super(R.layout.adapter_primary_school_class_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, PrimarySchoolClassBean.NianjiListBean item) {
        helper.itemView.setFocusable(true);
        helper.setText(R.id.adapter_primary_school_class_text,item.getName());


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
