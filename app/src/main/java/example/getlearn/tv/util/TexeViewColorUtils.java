package example.getlearn.tv.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import example.getlearn.tv.R;

/**
 * Created by zlw on 2018/12/2.
 */

@SuppressLint("AppCompatCustomView")
public class TexeViewColorUtils extends TextView {
    public TexeViewColorUtils(Context context) {
        super(context);
    }

    public TexeViewColorUtils(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TexeViewColorUtils(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    //1.08表示放大倍数,可以随便改  1  max     2min
    private void scaleUp() {
//        this.setBackgroundResource(R.drawable.rimg200_2);
        this.setTextColor(getResources().getColor(R.color.color_01));
    }

    private void scaleDown() {
    }

}
