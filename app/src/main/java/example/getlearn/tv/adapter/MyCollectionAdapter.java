package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import example.getlearn.tv.R;
import example.getlearn.tv.bean.MyCollectionBean;
import example.getlearn.tv.util.LinearLayoutUtils;
import example.getlearn.tv.util.MovieViewHolder;
import example.getlearn.tv.util.NetworkInterfaceUtils;

/**
 * Created by zlw on 2018/11/8.
 */

public class MyCollectionAdapter extends BaseQuickAdapter<MyCollectionBean.CollectionBean,MovieViewHolder> {

    public MyCollectionAdapter(int layoutResId, @Nullable List<MyCollectionBean.CollectionBean> data) {
        super(R.layout.adapter_my_collectoon_item, data);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(MovieViewHolder helper, MyCollectionBean.CollectionBean item) {
        helper.itemView.setFocusable(true);

        helper.setlinearLayoutUtilsImag(R.id.adapter_my_collectioon_item_ll,R.drawable.selector_bg_corners_rel_01,2);
        helper.setText(R.id.adapter_my_collection_item_title, item.getName());
        helper.setText(R.id.adapter_my_collection_item_text, item.getC_name());


        Glide.with(mContext)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+item.getIcon())
                .error(R.drawable.nimg168_1)
                .into((ImageView) helper.getView(R.id.adapter_my_collection_item_imag));
    }



}
