package example.getlearn.tv.util;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by zlw on 2018/12/6.
 */

public class LayoutManagerUtils extends GridLayoutManager {

    public LayoutManagerUtils(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public LayoutManagerUtils(Context context, int spanCount) {
        super(context, spanCount);
    }

    public LayoutManagerUtils(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }



    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
//        int span = getSpanCount();
//        int count = getItemCount();
//        int fromPos = getPosition(focused);
//
//        switch (direction) {
//            case View.FOCUS_UP:
//                fromPos = (fromPos - span);
//                break;
//            case View.FOCUS_DOWN:
//                fromPos = (fromPos + span);
//                break;
//            case View.FOCUS_RIGHT:
//                fromPos++;
//                break;
//            case View.FOCUS_LEFT:
//                fromPos--;
//                break;
//        }
//
//        if (fromPos < 0) {
//            return focused;
//        } else if (fromPos >= count) {
//            return focused;
//        } else {
//            return findViewByPosition(fromPos);
//        }
        int count = getItemCount();//获取item的总数
        int fromPos = getPosition(focused);//当前焦点的位置
        int lastVisibleItemPos = findLastVisibleItemPosition();//最新的已显示的Item的位置
        switch (direction) {//根据按键逻辑控制position
            case View.FOCUS_RIGHT:
                fromPos++;
                break;
            case View.FOCUS_LEFT:
                fromPos--;
                break;
        }

//        Log.i("zzz", "onInterceptFocusSearch , fromPos = " + fromPos + " , count = " + count+" , lastVisibleItemPos = "+lastVisibleItemPos);
        if(fromPos < 0 || fromPos >= count ) {
            //如果下一个位置<0,或者超出item的总数，则返回当前的View，即焦点不动
            return focused;
        } else {
            //如果下一个位置大于最新的已显示的item，即下一个位置的View没有显示，则滑动到那个位置，让他显示，就可以获取焦点了
            if (fromPos > lastVisibleItemPos) {
                scrollToPosition(fromPos);
            }
        }
        return super.onInterceptFocusSearch(focused, direction);
    }


}
