package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import example.getlearn.tv.util.OnItemSelectedListener;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import example.getlearn.tv.util.OnItemSelectedListener;
import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.CompositionGuidanceBean;
import example.getlearn.tv.util.CornerTransform;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * Created by zlw on 2018/11/5.
 */

public  class CompositionGuidanceXAdapter extends BaseQuickAdapter<CompositionGuidanceBean.RecommendListBean,BaseViewHolder>  {
    private int currentPosition;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    private OnItemSelectedListener mOnItemSelectedListener;
    public CompositionGuidanceXAdapter(int layoutResId, @Nullable List<CompositionGuidanceBean.RecommendListBean> data) {
        super(R.layout.adapter_composition_x_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CompositionGuidanceBean.RecommendListBean item) {
        Glide
                .with(mContext)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+item.getIcon())
                .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.ALL))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(R.drawable.nimg168_1)
                .into((ImageView) helper.getView(R.id.adapter_composition_x_item_imag));

        helper.itemView.setFocusable(true);

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
