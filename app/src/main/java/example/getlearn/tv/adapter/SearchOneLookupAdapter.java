package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.SearchOneLookupBean;
import example.getlearn.tv.util.MovieViewHolder;
import example.getlearn.tv.util.NetworkInterfaceUtils;

/**
 * Created by zlw on 2018/11/14.
 */

public class SearchOneLookupAdapter extends BaseQuickAdapter<SearchOneLookupBean.CourseBean,MovieViewHolder>{
    public SearchOneLookupAdapter(int layoutResId, @Nullable List<SearchOneLookupBean.CourseBean> data) {
        super(R.layout.adapter_search_one_data_item_two, data);
    }
    @SuppressLint("ResourceType")
    @Override
    protected void convert(MovieViewHolder helper, SearchOneLookupBean.CourseBean item) {
        helper.itemView.setFocusable(true);
        helper.setlinearLayoutUtilsImag(R.id.adapter_search_one_data_item_ll,R.drawable.selector_bg_corners_rel_01,1);
        Glide
                .with(mContext)
                .load(NetworkInterfaceUtils.InterFace_Imag+NetworkInterfaceUtils.HTTP_Imag+item.getIcon())
                .placeholder(R.drawable.nimg168_1)//图片加载出来前，显示的图片
                .dontAnimate()
                .skipMemoryCache(true)
                .override(57,57)
                .error(R.drawable.nimg168_1)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into((ImageView) helper.getView(R.id.adapter_search_one_data_item_imag));

        helper.setText(R.id.adapter_search_one_data_item_title,item.getName());
        helper.setText(R.id.adapter_search_one_data_item_text,item.getT_name()+item.getZhicheng());
    }
}
