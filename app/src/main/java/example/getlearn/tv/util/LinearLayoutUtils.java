package example.getlearn.tv.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import example.getlearn.tv.R;

/**
 * Created by zlw on 2018/11/8.
 */

public class LinearLayoutUtils extends LinearLayout {

    private int bitmapDownId;
    private int viewCompatInt;
    public LinearLayoutUtils(Context context) {
        super(context);
    }

    public LinearLayoutUtils(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutUtils(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBackgroundImage(int bitmapDownId ) {
        this.bitmapDownId = bitmapDownId;

    }
    public void setViewCompatInt(int viewCompatInt ) {
        this.viewCompatInt = viewCompatInt;

    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            scaleUp();
        } else {
            scaleDown();
        }
    }

    //1.08表示放大倍数,可以随便改
    private void scaleUp() {
        if (viewCompatInt==1){

        ViewCompat.animate(this)
                .setDuration(200)
                .scaleX(1.05f)
                .scaleY(1.05f)
                .start();
        }

        this.setBackgroundResource(bitmapDownId);

    }

    private void scaleDown() {
        if (viewCompatInt==1) {

            ViewCompat.animate(this)
                    .setDuration(200)
                    .scaleX(1f)
                    .scaleY(1f)
                    .start();

        }
        this.clearAnimation();
        this.setBackground(null);
//        this.setBackgroundResource(R.drawable.bg_corners_rel_02);
    }
}
