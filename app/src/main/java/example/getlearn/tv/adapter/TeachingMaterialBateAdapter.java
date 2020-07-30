package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import example.getlearn.tv.util.OnItemSelectedListener;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.TeachingMaterialBateBean;
import example.getlearn.tv.bean.TeachingMaterialBean;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by zlw on 2018/11/7.
 */

public class TeachingMaterialBateAdapter extends BaseQuickAdapter<TeachingMaterialBateBean.CourseBean,BaseViewHolder> {
    private int currentPosition;
    private OnItemSelectedListener mOnItemSelectedListener;
    public TeachingMaterialBateAdapter(int layoutResId, @Nullable List<TeachingMaterialBateBean.CourseBean> data) {
        super(R.layout.adapter_teaching_material_bate_item, data);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    protected void convert(final BaseViewHolder helper, TeachingMaterialBateBean.CourseBean item) {
        helper.itemView.setFocusable(true);
        Glide
                        .with(mContext)
                        .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+item.getIcon2())
                        .bitmapTransform(new RoundedCornersTransformation(mContext, 20, 0, RoundedCornersTransformation.CornerType.ALL))
                        .into((ImageView) helper.getView(R.id.adapter_teaching_materail_bate_item_01));

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
//    /**
//     * Same as QuickAdapter#QuickAdapter(Context,int) but with
//     * some initialization data.
//     *
//     * @param data A new list is created out of this one to avoid mutable list
//     */
//    public TeachingMaterialBateAdapter(List<TeachingMaterialBateBean> data) {
//        super(data);
//        addItemType(TeachingMaterialBateBean.TABE_A,R.layout.adapter_teaching_material_bate_item);
//        addItemType(TeachingMaterialBateBean.TABE_B,R.layout.adapter_teaching_material_bate_item_x);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, TeachingMaterialBateBean item) {
//        switch (helper.getItemViewType()){
//            case TeachingMaterialBateBean.TABE_A:
//                Glide
//                        .with(mContext)
//                        .load(item.getImag())
//                        .into((ImageView) helper.getView(R.id.adapter_teaching_materail_bate_item_01));
//                break;
//            case TeachingMaterialBateBean.TABE_B:
//                Glide
//                        .with(mContext)
//                        .load(item.getImag())
//                        .into((ImageView) helper.getView(R.id.adapter_teaching_materail_bate_item_02));
//                break;
//        }
//
//    }
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if (manager instanceof GridLayoutManager) {
//            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
//            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//
//                    int type = getItemViewType(position);
//
//                    if (mData.get(position).getItemType()==2) {
//                        type = 2;
//                        return type;
//                    }else {
//                        type=1;
//                        return type;
//                    }
//                }
//            });
//        }
//    }
}
