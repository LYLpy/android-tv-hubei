package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.CompositionGuidanceBean;
import example.getlearn.tv.util.OnItemSelectedListener;
/**
 * Created by zlw on 2018/11/5.
 */

public class CompositionGuidanceAdapter extends BaseQuickAdapter<CompositionGuidanceBean.VideoListBean,BaseViewHolder>  {

    private OnItemSelectedListener mOnItemSelectedListener;
    private int currentPosition;

    public CompositionGuidanceAdapter(int layoutResId, @Nullable List<CompositionGuidanceBean.VideoListBean> data) {
        super(R.layout.adapter_composition_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CompositionGuidanceBean.VideoListBean item) {
        helper.itemView.setFocusable(true);

        if (item.getIs_free().equals("0")){
            helper.setText(R.id.adapter_com_text_01,item.getName()+" 免费");
        }else {
            helper.setText(R.id.adapter_com_text_01,item.getName());
        }

        helper.itemView.setTag(helper.getPosition());
        helper.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
