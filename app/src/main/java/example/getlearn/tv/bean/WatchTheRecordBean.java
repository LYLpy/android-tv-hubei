package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/6.
 */

public class WatchTheRecordBean implements Parcelable {


    /**
     * data : [{"id":"6249","uid":"pc","stbid":"","videoid":"3694","courseid":"964","time":"16:22:18","play_time":"8","play_state":"4","Video":{"id":"3694","name":"3、对不起","course_id":"964"},"week":"6","year":"2018","months":"07","day":"21","course_name":"学前：亲子乐园-生活礼仪"},{"id":"6248","uid":"pc","stbid":"","videoid":"8457","courseid":"1008","time":"16:21:48","play_time":"13","play_state":"4","Video":{"id":"8457","name":"第1讲 考场作文分析","course_id":"1008"},"week":"6","year":"2018","months":"07","day":"21","course_name":"小学语文作文指导"},{"id":"6247","uid":"pc","stbid":"","videoid":"3198","courseid":"954","time":"16:19:25","play_time":"18","play_state":"4","Video":{"id":"3198","name":"11、汉朝名将卫青(卫)","course_id":"954"},"week":"6","year":"2018","months":"07","day":"21","course_name":"学前：国学经典-百家姓"},{"id":"6246","uid":"pc","stbid":"","videoid":"3191","courseid":"954","time":"16:18:59","play_time":"89","play_state":"4","Video":{"id":"3191","name":"4、磨杵成针(李)","course_id":"954"},"week":"6","year":"2018","months":"07","day":"21","course_name":"学前：国学经典-百家姓"},{"id":"6245","uid":"pc","stbid":"","videoid":"3191","courseid":"954","time":"16:17:29","play_time":"31","play_state":"5","Video":{"id":"3191","name":"4、磨杵成针(李)","course_id":"954"},"week":"6","year":"2018","months":"07","day":"21","course_name":"学前：国学经典-百家姓"},{"id":"6244","uid":"pc","stbid":"","videoid":"3188","courseid":"954","time":"15:46:33","play_time":"26","play_state":"4","Video":{"id":"3188","name":"1、赵普读《论语》(赵)","course_id":"954"},"week":"6","year":"2018","months":"07","day":"21","course_name":"学前：国学经典-百家姓"}]
     * count : 1575
     * page : 263
     * p : 1
     * public_skin_img : ./Public/Uploads/images/2018-08-31/5b88f93d39210.jpg
     */

    private String count;
    private int page;
    private String p;
    private String public_skin_img;
    private List<DataBean> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getPublic_skin_img() {
        return public_skin_img;
    }

    public void setPublic_skin_img(String public_skin_img) {
        this.public_skin_img = public_skin_img;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 6249
         * uid : pc
         * stbid :
         * videoid : 3694
         * courseid : 964
         * time : 16:22:18
         * play_time : 8
         * play_state : 4
         * Video : {"id":"3694","name":"3、对不起","course_id":"964"}
         * week : 6
         * year : 2018
         * months : 07
         * day : 21
         * course_name : 学前：亲子乐园-生活礼仪
         */

        private String id;
        private String uid;
        private String stbid;
        private String videoid;
        private String courseid;
        private String time;
        private String play_time;
        private String play_state;
        private VideoBean Video;
        private String week;
        private String year;
        private String months;
        private String day;
        private String course_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getStbid() {
            return stbid;
        }

        public void setStbid(String stbid) {
            this.stbid = stbid;
        }

        public String getVideoid() {
            return videoid;
        }

        public void setVideoid(String videoid) {
            this.videoid = videoid;
        }

        public String getCourseid() {
            return courseid;
        }

        public void setCourseid(String courseid) {
            this.courseid = courseid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPlay_time() {
            return play_time;
        }

        public void setPlay_time(String play_time) {
            this.play_time = play_time;
        }

        public String getPlay_state() {
            return play_state;
        }

        public void setPlay_state(String play_state) {
            this.play_state = play_state;
        }

        public VideoBean getVideo() {
            return Video;
        }

        public void setVideo(VideoBean Video) {
            this.Video = Video;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonths() {
            return months;
        }

        public void setMonths(String months) {
            this.months = months;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public static class VideoBean implements Parcelable {
            /**
             * id : 3694
             * name : 3、对不起
             * course_id : 964
             */

            private String id;
            private String name;
            private String course_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCourse_id() {
                return course_id;
            }

            public void setCourse_id(String course_id) {
                this.course_id = course_id;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.name);
                dest.writeString(this.course_id);
            }

            public VideoBean() {
            }

            protected VideoBean(Parcel in) {
                this.id = in.readString();
                this.name = in.readString();
                this.course_id = in.readString();
            }

            public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
                @Override
                public VideoBean createFromParcel(Parcel source) {
                    return new VideoBean(source);
                }

                @Override
                public VideoBean[] newArray(int size) {
                    return new VideoBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.uid);
            dest.writeString(this.stbid);
            dest.writeString(this.videoid);
            dest.writeString(this.courseid);
            dest.writeString(this.time);
            dest.writeString(this.play_time);
            dest.writeString(this.play_state);
            dest.writeParcelable(this.Video, flags);
            dest.writeString(this.week);
            dest.writeString(this.year);
            dest.writeString(this.months);
            dest.writeString(this.day);
            dest.writeString(this.course_name);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.uid = in.readString();
            this.stbid = in.readString();
            this.videoid = in.readString();
            this.courseid = in.readString();
            this.time = in.readString();
            this.play_time = in.readString();
            this.play_state = in.readString();
            this.Video = in.readParcelable(VideoBean.class.getClassLoader());
            this.week = in.readString();
            this.year = in.readString();
            this.months = in.readString();
            this.day = in.readString();
            this.course_name = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    public WatchTheRecordBean() {
        super();
    }

    @Override
    public String toString() {
        return "WatchTheRecordBean{" +
                "count='" + count + '\'' +
                ", page=" + page +
                ", p='" + p + '\'' +
                ", public_skin_img='" + public_skin_img + '\'' +
                ", data=" + data +
                '}';
    }

    public WatchTheRecordBean(String count, int page, String p, String public_skin_img, List<DataBean> data) {
        this.count = count;
        this.page = page;
        this.p = p;
        this.public_skin_img = public_skin_img;
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.count);
        dest.writeInt(this.page);
        dest.writeString(this.p);
        dest.writeString(this.public_skin_img);
        dest.writeList(this.data);
    }

    protected WatchTheRecordBean(Parcel in) {
        this.count = in.readString();
        this.page = in.readInt();
        this.p = in.readString();
        this.public_skin_img = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<WatchTheRecordBean> CREATOR = new Parcelable.Creator<WatchTheRecordBean>() {
        @Override
        public WatchTheRecordBean createFromParcel(Parcel source) {
            return new WatchTheRecordBean(source);
        }

        @Override
        public WatchTheRecordBean[] newArray(int size) {
            return new WatchTheRecordBean[size];
        }
    };
}
