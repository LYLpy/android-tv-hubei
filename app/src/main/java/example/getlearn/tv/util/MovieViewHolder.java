package example.getlearn.tv.util;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by zlw on 2018/11/8.
 */

public class MovieViewHolder extends BaseViewHolder {
//  helper.setlinearLayoutUtilsImag(R.id.adapter_vip_title_item_layout,R.drawable.bg_corners_rel_01,2);
    public MovieViewHolder setlinearLayoutUtilsImag(@IdRes int viewId, @StringRes int strId,int id) {
        LinearLayoutUtils view = getView(viewId);
        view.setBackgroundImage(strId);
        view.setViewCompatInt(id);
        return this;
    }

    public MovieViewHolder(View view) {
        super(view);
    }
}
