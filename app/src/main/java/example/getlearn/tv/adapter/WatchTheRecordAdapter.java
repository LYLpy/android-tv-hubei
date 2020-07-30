package example.getlearn.tv.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.WatchTheRecordBean;

/**
 * Created by zlw on 2018/11/6.
 * 历史记录的Adapter
 */

public class WatchTheRecordAdapter extends BaseQuickAdapter<WatchTheRecordBean.DataBean,BaseViewHolder> {
    public WatchTheRecordAdapter(int layoutResId, @Nullable List<WatchTheRecordBean.DataBean> data) {
        super(R.layout.adapte_watch_the_record_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WatchTheRecordBean.DataBean item) {
        Glide
                .with(mContext)
                .load(R.drawable.nimg168_1)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into((ImageView) helper.getView(R.id.adapter_wathc_the_record_item_img));

        helper.setText(R.id.adapter_wathc_the_record_item_title,item.getVideo().getName());
        helper.setText(R.id.adapter_wathc_the_record_item_text,item.getCourse_name());


        helper.itemView.setFocusable(true);


    }
}
