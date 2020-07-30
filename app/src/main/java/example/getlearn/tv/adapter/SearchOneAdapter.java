package example.getlearn.tv.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.getlearn.tv.R;
import example.getlearn.tv.bean.SearchOneBean;
import example.getlearn.tv.util.MovieViewHolder;

/**
 * Created by zlw on 2018/11/8.
 */

public class SearchOneAdapter extends BaseQuickAdapter<SearchOneBean,MovieViewHolder> {
    public SearchOneAdapter(int layoutResId, @Nullable List<SearchOneBean> data) {
        super(R.layout.adapter_search_one_item, data);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(MovieViewHolder helper, SearchOneBean item) {
        helper.itemView.setFocusable(true);
        helper.setlinearLayoutUtilsImag(R.id.adapter_search_one_adapter_layout,R.drawable.bg_num2,2);
        helper.setText(R.id.adapter_search_one_adapter_text,item.getNumber());
    }
}
