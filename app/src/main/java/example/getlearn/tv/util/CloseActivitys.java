package example.getlearn.tv.util;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘佳佳 on 2018/12/22.
 * 关闭多个activity
 */

public class CloseActivitys extends Application {
    private static List<Activity> lists = new ArrayList<>();
    public static void addActivity(Activity activity) {
        lists.add(activity);
    }
    public static void clearActivity() {
        if (lists != null) {
            for (Activity activity : lists) {
                activity.finish();
            }
            for(int i = lists.size()-1; i >= 0; i--){
                lists.get(i).finish();
            }
            lists.clear();
        }
    }
}
