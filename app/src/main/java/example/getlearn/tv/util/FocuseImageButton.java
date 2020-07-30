package example.getlearn.tv.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by zlw on 2018/11/8.
 */

@SuppressLint("AppCompatCustomView")
public class FocuseImageButton extends ImageView {
    public FocuseImageButton(Context context) {
        super(context);
    }

    public FocuseImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocuseImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            scaleUp();
        } else {
            scaleDown();
        }
    }

    //1.08表示放大倍数,可以随便改
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


