package example.getlearn.tv.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.SeekBar;

/**
 * Created by zlw on 2018/12/23.
 */

public class SeekBarUtils extends android.support.v7.widget.AppCompatSeekBar   {
    public SeekBarUtils(Context context) {
        super(context);
    }

    public SeekBarUtils(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SeekBarUtils(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}
