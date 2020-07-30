package example.getlearn.tv.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import org.w3c.dom.Text;

import example.getlearn.tv.R;

/**
 * Created by zlw on 2018/11/14.
 */

@SuppressLint("AppCompatCustomView")
public class TexeViewUtils extends TextView {
    public TexeViewUtils(Context context) {
        super(context);
    }

    public TexeViewUtils(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TexeViewUtils(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
            ViewCompat.animate(this)
                    .setDuration(200)
                    .scaleX(1.08f)
                    .scaleY(1.08f)
                    .start();
    }

    private void scaleDown() {
            ViewCompat.animate(this)
                    .setDuration(200)
                    .scaleX(1f)
                    .scaleY(1f)
                    .start();
    }

}
