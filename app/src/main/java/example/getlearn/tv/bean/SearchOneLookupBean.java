package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/14.
 */

public class SearchOneLookupBean implements Parcelable {

    /**
     * page : 3
     * course : [{"id":"923","icon":"images/2017-04-25/chuzhongdanci-Invitation.png","name":"初中单词王_Invitation","t_name":"潘璐璐（闫曦）","zhicheng":"优秀教师"},{"id":"924","icon":"images/2017-04-25/chuzhongdanci-Appointment.png","name":"初中单词王_Appointment","t_name":"潘璐璐（闫曦）","zhicheng":"优秀教师"},{"id":"925","icon":"images/2017-04-25/chuzhongdanci-Taking-Leave.png","name":"初中单词王_Taking Leave","t_name":"潘璐璐（闫曦）","zhicheng":"优秀教师"},{"id":"927","icon":"images/2017-04-25/chuzhongdanci-Congratulationa.png","name":"初中单词王_Congratulationa","t_name":"潘璐璐（闫曦）","zhicheng":"优秀教师"},{"id":"928","icon":"images/2017-04-25/chuzhongdanci-Gratitude-.png","name":"初中单词王_Gratitude","t_name":"潘璐璐（闫曦）","zhicheng":"优秀教师"},{"id":"929","icon":"images/2017-04-25/chuzhongdanci-Apology.png","name":"初中单词王_Apology","t_name":"潘璐璐（闫曦）","zhicheng":"优秀教师"},{"id":"931","icon":"images/2017-04-25/chuzhongdanci-Ability.png","name":"初中单词王_Ability","t_name":"潘璐璐（闫曦）","zhicheng":"优秀教师"},{"id":"936","icon":"images/2017-04-25/chuzhongdanci-speculations.png","name":"初中单词王_speculations","t_name":"潘璐璐（闫曦）","zhicheng":"优秀教师"},{"id":"1022","icon":"images/2017-04-25/aoshu-1.png","name":"小学奥数一年级","t_name":"张杨子","zhicheng":"优秀教师"},{"id":"1024","icon":"images/2017-04-25/aoshu-2.png","name":"小学奥数二年级","t_name":"徐柳静","zhicheng":"优秀教师"},{"id":"1026","icon":"images/2017-04-25/aoshu-3.png","name":"小学奥数三年级","t_name":"王希","zhicheng":"优秀教师"},{"id":"1028","icon":"images/2017-04-25/aoshu-4.png","name":"小学奥数四年级","t_name":"杨帆","zhicheng":"中教一级"},{"id":"1030","icon":"images/2017-04-25/aoshu-5.png","name":"小学奥数五年级","t_name":"孙秀华","zhicheng":"优秀教师"},{"id":"1033","icon":"images/2017-04-25/aoshu-6.png","name":"小学奥数六年级","t_name":"王子童","zhicheng":"优秀教师"}]
     */

    private int pages;
    private List<CourseBean> course;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public static class CourseBean implements Parcelable {
        /**
         * id : 923
         * icon : images/2017-04-25/chuzhongdanci-Invitation.png
         * name : 初中单词王_Invitation
         * t_name : 潘璐璐（闫曦）
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.icon);
            dest.writeString(this.name);
            dest.writeString(this.t_name);
            dest.writeString(this.zhicheng);
        }

        public CourseBean() {
        }

        protected CourseBean(Parcel in) {
            this.id = in.readString();
            this.icon = in.readString();
            this.name = in.readString();
            this.t_name = in.readString();
            this.zhicheng = in.readString();
        }

        public static final Creator<CourseBean> CREATOR = new Creator<CourseBean>() {
            @Override
            public CourseBean createFromParcel(Parcel source) {
                return new CourseBean(source);
            }

            @Override
            public CourseBean[] newArray(int size) {
                return new CourseBean[size];
            }
        };
    }

    public SearchOneLookupBean() {
        super();
    }

    @Override
    public String toString() {
        return "SearchOneLookupBean{" +
                "pages=" + pages +
                ", course=" + course +
                '}';
    }

    public SearchOneLookupBean(int pages, List<CourseBean> course) {
        this.pages = pages;
        this.course = course;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pages);
        dest.writeList(this.course);
    }

    protected SearchOneLookupBean(Parcel in) {
        this.pages = in.readInt();
        this.course = new ArrayList<CourseBean>();
        in.readList(this.course, CourseBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchOneLookupBean> CREATOR = new Parcelable.Creator<SearchOneLookupBean>() {
        @Override
        public SearchOneLookupBean createFromParcel(Parcel source) {
            return new SearchOneLookupBean(source);
        }

        @Override
        public SearchOneLookupBean[] newArray(int size) {
            return new SearchOneLookupBean[size];
        }
    };
}
