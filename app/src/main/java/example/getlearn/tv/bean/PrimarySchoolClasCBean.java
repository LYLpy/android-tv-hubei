package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/7.
 */

public class PrimarySchoolClasCBean implements Parcelable {
    @Override
    public String toString() {
        return "PrimarySchoolClasCBean{" +
                "status=" + status +
                ", xuekeList=" + xuekeList +
                ", courseList=" + courseList +
                '}';
    }

    public PrimarySchoolClasCBean() {
        super();
    }

    public PrimarySchoolClasCBean(int status, List<XuekeListBean> xuekeList, List<CourseListBean> courseList) {
        this.status = status;
        this.xuekeList = xuekeList;
        this.courseList = courseList;
    }

    /**
     * status : 1
     * xuekeList : [{"xueke_id":"1","name":"语文"},{"xueke_id":"2","name":"数学"},{"xueke_id":"3","name":"英语"}]
     * courseList : [{"name":"同步精讲","tags":"2","list":[{"id":"38","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","type":"course"}],"pages":1,"p":1},{"name":"状元课堂","tags":"3","list":[{"id":"2000","icon":"images/2017-04-25/zhuangyuanketang-1yw.png","type":"course"}],"pages":1,"p":1},{"name":"状元课堂","tags":"3","list":[{"id":"2000","icon":"images/2017-04-25/zhuangyuanketang-1yw.png","type":"course"}],"pages":1,"p":1}]
     */

    private int status;
    private List<XuekeListBean> xuekeList;
    private List<CourseListBean> courseList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<XuekeListBean> getXuekeList() {
        return xuekeList;
    }

    public void setXuekeList(List<XuekeListBean> xuekeList) {
        this.xuekeList = xuekeList;
    }

    public List<CourseListBean> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseListBean> courseList) {
        this.courseList = courseList;
    }

    public static class XuekeListBean implements Parcelable {
        /**
         * xueke_id : 1
         * name : 语文
         */

        private String xueke_id;
        private String name;

        public String getXueke_id() {
            return xueke_id;
        }

        public void setXueke_id(String xueke_id) {
            this.xueke_id = xueke_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.xueke_id);
            dest.writeString(this.name);
        }

        public XuekeListBean() {
        }

        protected XuekeListBean(Parcel in) {
            this.xueke_id = in.readString();
            this.name = in.readString();
        }

        public static final Creator<XuekeListBean> CREATOR = new Creator<XuekeListBean>() {
            @Override
            public XuekeListBean createFromParcel(Parcel source) {
                return new XuekeListBean(source);
            }

            @Override
            public XuekeListBean[] newArray(int size) {
                return new XuekeListBean[size];
            }
        };
    }

    public static class CourseListBean implements Parcelable {
        /**
         * name : 同步精讲
         * tags : 2
         * list : [{"id":"38","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","type":"course"}]
         * pages : 1
         * p : 1
         */

        private String name;
        private String tags;
        private int pages;
        private int p;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * id : 38
             * icon : images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png
             * type : course
             */

            private String id;
            private String icon;
            private String type;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.icon);
                dest.writeString(this.type);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.id = in.readString();
                this.icon = in.readString();
                this.type = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.tags);
            dest.writeInt(this.pages);
            dest.writeInt(this.p);
            dest.writeList(this.list);
        }

        public CourseListBean() {
        }

        protected CourseListBean(Parcel in) {
            this.name = in.readString();
            this.tags = in.readString();
            this.pages = in.readInt();
            this.p = in.readInt();
            this.list = new ArrayList<ListBean>();
            in.readList(this.list, ListBean.class.getClassLoader());
        }

        public static final Creator<CourseListBean> CREATOR = new Creator<CourseListBean>() {
            @Override
            public CourseListBean createFromParcel(Parcel source) {
                return new CourseListBean(source);
            }

            @Override
            public CourseListBean[] newArray(int size) {
                return new CourseListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeList(this.xuekeList);
        dest.writeList(this.courseList);
    }

    protected PrimarySchoolClasCBean(Parcel in) {
        this.status = in.readInt();
        this.xuekeList = new ArrayList<XuekeListBean>();
        in.readList(this.xuekeList, XuekeListBean.class.getClassLoader());
        this.courseList = new ArrayList<CourseListBean>();
        in.readList(this.courseList, CourseListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PrimarySchoolClasCBean> CREATOR = new Parcelable.Creator<PrimarySchoolClasCBean>() {
        @Override
        public PrimarySchoolClasCBean createFromParcel(Parcel source) {
            return new PrimarySchoolClasCBean(source);
        }

        @Override
        public PrimarySchoolClasCBean[] newArray(int size) {
            return new PrimarySchoolClasCBean[size];
        }
    };
}
