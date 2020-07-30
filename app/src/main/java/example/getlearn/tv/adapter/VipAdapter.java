package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.VipBean;
import example.getlearn.tv.util.MovieViewHolder;
import example.getlearn.tv.util.NetworkInterfaceUtils;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by zlw on 2018/11/9.
 */

public class VipAdapter extends BaseQuickAdapter<VipBean.PriceResBean,MovieViewHolder> {
    public VipAdapter(int layoutResId, @Nullable List<VipBean.PriceResBean> data) {
        super(R.layout.adapter_vip_item, data);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(MovieViewHolder helper, VipBean.PriceResBean item) {
        helper.itemView.setFocusable(true);
        helper.setlinearLayoutUtilsImag(R.id.adapter_vip_tem_ll_utils,R.drawable.bg_corners_rel_01,1);
            Glide.with(mContext)
                    .load(NetworkInterfaceUtils.InterFace_Imag+item.getIcon())
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into((ImageView) helper.getView(R.id.adapter_vip_tem_imag));

        helper.setText(R.id.adapter_vip_item_txt,item.getPrice()+"元");

        if(item.getProduct_isOrder().equals("true")){
            if(item.getCard_isOrder().equals("true")){
                helper.setText(R.id.adapter_vip_item_text_ordered, "已订购");
                helper.setGone(R.id.adapter_vip_item_text_ordered, true);
                helper.setText(R.id.adapter_vip_item_text_validTime, "到期时间：" + item.getOrder_time());
                helper.setGone(R.id.adapter_vip_item_text_validTime, true);
            }else{
                helper.setText(R.id.adapter_vip_item_text_ordered, "您已订购一个，暂时不能订购");
                helper.setGone(R.id.adapter_vip_item_text_ordered, true);
                helper.setGone(R.id.adapter_vip_item_text_validTime, false);
            }
        }else {
            helper.setGone(R.id.adapter_vip_item_text_ordered, false);
            helper.setGone(R.id.adapter_vip_item_text_validTime, false);
        }
    }
}
