package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/9.
 */

public class SearchOneDataBean implements Parcelable {

    /**
     * course : [{"id":"38","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","name":"人教版小学语文1年级","t_name":"陈剑","zhicheng":"优秀教师"},{"id":"40","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yuwen.png","name":"人教版小学语文3年级","t_name":"黄邱玲","zhicheng":"高级教师"},{"id":"41","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban4yuwen.png","name":"人教版小学语文4年级","t_name":"周小满 ","zhicheng":"高级教师"},{"id":"42","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban5yuwen.png","name":"人教版小学语文5年级","t_name":"肖俐","zhicheng":"中教一级"},{"id":"43","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yuwen.png","name":"人教版小学语文6年级","t_name":"余慧俐","zhicheng":"高级教师"},{"id":"44","icon":"images/2017-04-26/zhongxuetongbujingjiang-renjiaoban7yuwen.png","name":"人教版初中语文7年级","t_name":"龙琳","zhicheng":"优秀教师"},{"id":"63","icon":"images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshuxuebixiu3.png","name":"人教版高中数学必修三","t_name":"薛江辉","zhicheng":"高级教师"},{"id":"64","icon":"images/2017-04-26/zhongxuetongbujingjiang-renjiaobanshuxuebixiu4.png","name":"人教版高中数学必修四","t_name":null,"zhicheng":null},{"id":"65","icon":"images/2017-04-26/zhongxuetongbujingjiang-renjiaobanshuxuebixiu5.png","name":"人教版高中数学必修五","t_name":"纪文军 ","zhicheng":"高级教师"}]
     * count : 28
     * page : 4
     * public_skin_img : /Public/Uploads/images/5b8ce146a29f4.png
     */

    private int count;
    private int page;
    private String public_skin_img;
    private List<SearchOneDataBean.CourseBean> course;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPublic_skin_img() {
        return public_skin_img;
    }

    public void setPublic_skin_img(String public_skin_img) {
        this.public_skin_img = public_skin_img;
    }

    public List<SearchOneDataBean.CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<SearchOneDataBean.CourseBean> course) {
        this.course = course;
    }

    public static class CourseBean {
        /**
         * id : 38
         * icon : images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png
         * name : 人教版小学语文1年级
         * t_name : 陈剑
         * zhicheng : 优秀教师
         */

        private String id;
        private String icon;
        private String name;
        private String t_name;
        private String zhicheng;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getT_name() {
            return t_name;
        }

        public void setT_name(String t_name) {
            this.t_name = t_name;
        }

        public String getZhicheng() {
            return zhicheng;
        }

        public void setZhicheng(String zhicheng) {
            this.zhicheng = zhicheng;
        }
    }

    public SearchOneDataBean() {
        super();
    }

    @Override
    public String toString() {
        return "SearchOneDataBean{" +
                "count=" + count +
                ", page=" + page +
                ", public_skin_img='" + public_skin_img + '\'' +
                ", course=" + course +
                '}';
    }

    public SearchOneDataBean(int count, int page, String public_skin_img, List<SearchOneDataBean.CourseBean> course) {
        this.count = count;
        this.page = page;
        this.public_skin_img = public_skin_img;
        this.course = course;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeInt(this.page);
        dest.writeString(this.public_skin_img);
        dest.writeList(this.course);
    }

    protected SearchOneDataBean(Parcel in) {
        this.count = in.readInt();
        this.page = in.readInt();
        this.public_skin_img = in.readString();
        this.course = new ArrayList<SearchOneDataBean.CourseBean>();
        in.readList(this.course, SearchOneDataBean.CourseBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchOneDataBean> CREATOR = new Parcelable.Creator<SearchOneDataBean>() {
        @Override
        public SearchOneDataBean createFromParcel(Parcel source) {
            return new SearchOneDataBean(source);
        }

        @Override
        public SearchOneDataBean[] newArray(int size) {
            return new SearchOneDataBean[size];
        }
    };
}
