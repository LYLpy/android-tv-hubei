package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import example.getlearn.tv.util.OnItemSelectedListener;
import example.getlearn.tv.util.OnItemSelectedListener;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.NumberOneClassBaseBean;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by zlw on 2018/11/8.
 */

public class NumberOneClassBaseAdapter  extends BaseQuickAdapter<NumberOneClassBaseBean.ListBean,BaseViewHolder> {
    private int currentPosition;
    private OnItemSelectedListener mOnItemSelectedListener;
    public NumberOneClassBaseAdapter(int layoutResId, @Nullable List<NumberOneClassBaseBean.ListBean> data) {
        super(R.layout.adapter_number_one_class_base_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, NumberOneClassBaseBean.ListBean item) {
        helper.itemView.setFocusable(true);
//        helper.itemView.setClipToOutline(false);
        helper.itemView.setClickable(false);
        Glide
                .with(mContext)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+item.getIcon())
                .error(R.drawable.nimg168_1)
                .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.ALL))
                .into((ImageView) helper.getView(R.id.adapter_number_one_class_base_imag));

        helper.itemView.setTag(R.id.image_key,helper.getPosition());
        helper.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                Log.d(TAG, "焦点监听器被调用了");
//                Log.d(TAG, "hasFocus=" + hasFocus);
                if (hasFocus) {
                    currentPosition = (int) helper.itemView.getTag(R.id.image_key);
//                    Log.d(TAG, "getTag=" + currentPosition);
//                    Log.i(TAG, "The view hasFocus=" + v + ", holder.itemView=" + helper.itemView);
                    mOnItemSelectedListener.OnItemSelected(v, currentPosition);
                }
            }
        });

    }
    public void setOnItemSelectedListenerwww(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }
}