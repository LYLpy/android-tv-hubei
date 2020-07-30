package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import example.getlearn.tv.util.OnItemSelectedListener;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.NumberOneClassBean;

/**
 * Created by zlw on 2018/11/8.
 */

public class NumberOneClassAdapter extends BaseQuickAdapter<NumberOneClassBean.DataBean,BaseViewHolder> {
    private int currentPosition;
    private OnItemSelectedListener mOnItemSelectedListener;
    public NumberOneClassAdapter(int layoutResId, @Nullable List<NumberOneClassBean.DataBean> data) {
        super(R.layout.adapter_number_one_class_item_a, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, NumberOneClassBean.DataBean item) {
        helper.itemView.setFocusable(true);
        helper.setText(R.id.adapter_number_one_class_item_text,item.getName());

        helper.itemView.setTag(R.id.text_key,helper.getPosition());
        helper.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                Log.d(TAG, "焦点监听器被调用了");
//                Log.d(TAG, "hasFocus=" + hasFocus);
                if (hasFocus) {
                    currentPosition = (int) helper.itemView.getTag(R.id.text_key);
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
