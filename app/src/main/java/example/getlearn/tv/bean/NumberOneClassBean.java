package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/8.
 */

public class NumberOneClassBean implements Parcelable {
    @Override
    public String toString() {
        return "NumberOneClassBean{" +
                "topic=" + topic +
                ", data=" + data +
                '}';
    }

    public NumberOneClassBean() {
        super();
    }

    public NumberOneClassBean(TopicBean topic, List<DataBean> data) {
        this.topic = topic;
        this.data = data;
    }

    /**
     * data : [{"lid":"52","type":"3","id":"27","sort":"33","title":"","name":"人教版小学数学1年级","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png","lname":"课程","url":"/index.php?m=Index&c=index&a=coursedetail&pid=52"},{"lid":"62","type":"3","id":"4","sort":"12","title":"","name":"人教版高中数学必修二","icon":"images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshuxuebixiu2.png","lname":"课程","url":"/index.php?m=Index&c=index&a=coursedetail&pid=62"},{"lid":"584","type":"4","id":"17","sort":"2","title":"","name":"人教版高中语文必修一第10课 短新闻两篇（一）","icon":"","lname":"视频","url":"/index.php?m=Index&c=index&a=video&id=584&danji=42831"},{"lid":"47","type":"3","id":"26","sort":"0","title":"","name":"人教版高中语文必修一","icon":"images/2017-04-26/zhongxuetongbujingjiang-renjiaoban6yuwenbixiu1.png","lname":"课程","url":"/index.php?m=Index&c=index&a=coursedetail&pid=47"},{"lid":"2004","type":"3","id":"67","sort":"0","title":"","name":"状元大课堂语文五年级","icon":"images/2017-04-25/zhuangyuanketang-5yw.png","lname":"课程","url":"/index.php?m=Index&c=index&a=coursedetail&pid=2004"}]
     * topic : {"id":"1","name":"中秋季节 快乐","num":"6","topicdesc":"hello 中秋","imgpath":"/Public/Uploads/topic/5b8615c047e27.png","up_status":"0","is_delete":"0","created_at":"2018-08-23 06:48:22","updated_at":"2018-08-30 02:02:13"}
     */

    private TopicBean topic;
    private List<DataBean> data;

    public TopicBean getTopic() {
        return topic;
    }

    public void setTopic(TopicBean topic) {
        this.topic = topic;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class TopicBean implements Parcelable {
        /**
         * id : 1
         * name : 中秋季节 快乐
         * num : 6
         * topicdesc : hello 中秋
         * imgpath : /Public/Uploads/topic/5b8615c047e27.png
         * up_status : 0
         * is_delete : 0
         * created_at : 2018-08-23 06:48:22
         * updated_at : 2018-08-30 02:02:13
         */

        private String id;
        private String name;
        private String num;
        private String topicdesc;
        private String imgpath;
        private String up_status;
        private String is_delete;
        private String created_at;
        private String updated_at;

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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTopicdesc() {
            return topicdesc;
        }

        public void setTopicdesc(String topicdesc) {
            this.topicdesc = topicdesc;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getUp_status() {
            return up_status;
        }

        public void setUp_status(String up_status) {
            this.up_status = up_status;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.num);
            dest.writeString(this.topicdesc);
            dest.writeString(this.imgpath);
            dest.writeString(this.up_status);
            dest.writeString(this.is_delete);
            dest.writeString(this.created_at);
            dest.writeString(this.updated_at);
        }

        public TopicBean() {
        }

        protected TopicBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.num = in.readString();
            this.topicdesc = in.readString();
            this.imgpath = in.readString();
            this.up_status = in.readString();
            this.is_delete = in.readString();
            this.created_at = in.readString();
            this.updated_at = in.readString();
        }

        public static final Creator<TopicBean> CREATOR = new Creator<TopicBean>() {
            @Override
            public TopicBean createFromParcel(Parcel source) {
                return new TopicBean(source);
            }

            @Override
            public TopicBean[] newArray(int size) {
                return new TopicBean[size];
            }
        };
    }

    public static class DataBean implements Parcelable {
        /**
         * lid : 52
         * type : 3
         * id : 27
         * sort : 33
         * title :
         * name : 人教版小学数学1年级
         * icon : images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png
         * lname : 课程
         * url : /index.php?m=Index&c=index&a=coursedetail&pid=52
         */

        private String lid;
        private String type;
        private String id;
        private String sort;
        private String title;
        private String name;
        private String icon;
        private String lname;
        private String url;

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.lid);
            dest.writeString(this.type);
            dest.writeString(this.id);
            dest.writeString(this.sort);
            dest.writeString(this.title);
            dest.writeString(this.name);
            dest.writeString(this.icon);
            dest.writeString(this.lname);
            dest.writeString(this.url);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.lid = in.readString();
            this.type = in.readString();
            this.id = in.readString();
            this.sort = in.readString();
            this.title = in.readString();
            this.name = in.readString();
            this.icon = in.readString();
            this.lname = in.readString();
            this.url = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.topic, flags);
        dest.writeList(this.data);
    }

    protected NumberOneClassBean(Parcel in) {
        this.topic = in.readParcelable(TopicBean.class.getClassLoader());
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<NumberOneClassBean> CREATOR = new Parcelable.Creator<NumberOneClassBean>() {
        @Override
        public NumberOneClassBean createFromParcel(Parcel source) {
            return new NumberOneClassBean(source);
        }

        @Override
        public NumberOneClassBean[] newArray(int size) {
            return new NumberOneClassBean[size];
        }
    };
}
