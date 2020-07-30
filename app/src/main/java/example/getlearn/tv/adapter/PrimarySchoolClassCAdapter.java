package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import example.getlearn.tv.util.OnItemSelectedListener;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.PrimarySchoolClasCBean;
import example.getlearn.tv.bean.PrimarySchoolClassABean;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import example.getlearn.tv.util.OnItemSelectedListenerUtls;
import example.getlearn.tv.view.activity.CompositionGuidanceActivity;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by zlw on 2018/11/7.
 */

public class PrimarySchoolClassCAdapter extends BaseQuickAdapter<PrimarySchoolClasCBean.CourseListBean.ListBean,BaseViewHolder> {
    private int currentPosition;
    private OnItemSelectedListenerUtls mOnItemSelectedListener;
    public static boolean currentPositionInt;
    private  String item_name;
    public PrimarySchoolClassCAdapter(int layoutResId, @Nullable List<PrimarySchoolClasCBean.CourseListBean.ListBean> data) {
        super(R.layout.adapter_primary_school_class_item_c, data);
    }
    public int getCurrentPosition() {
        return currentPosition;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final PrimarySchoolClasCBean.CourseListBean.ListBean item) {
        helper.itemView.setFocusable(true);
        helper.getView(R.id.adapter_primary_school_class_item_c_imag).setTag(null);
        Glide.with(mContext)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+item.getIcon())
                .error(R.drawable.nimg168_1)
                .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.ALL))
                .into((ImageView) helper.getView(R.id.adapter_primary_school_class_item_c_imag));

        helper.itemView.setTag(helper.getPosition());
        helper.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                Log.d(TAG, "焦点监听器被调用了");
//                Log.d(TAG, "hasFocus=" + hasFocus);
                currentPositionInt=hasFocus;
                if (hasFocus) {
                    currentPosition = (int) helper.itemView.getTag();
//                    Log.d(TAG, "getTag=" + currentPosition);
//                    Log.i(TAG, "The view hasFocus=" + v + ", holder.itemView=" + helper.itemView);
                    mOnItemSelectedListener.OnItemSelected(v, currentPosition, mData.get(currentPosition).getId());
                }
            }
        });
    }

    @Override
    public void setOnItemClick(View v, int position) {
        super.setOnItemClick(v, position);

        CompositionGuidanceActivity.starActivity((Activity) mContext,"1",
                mData.get(position).getId()
                ,mData.get(position).getType());
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    public void setOnItemSelectedListener(OnItemSelectedListenerUtls onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

}