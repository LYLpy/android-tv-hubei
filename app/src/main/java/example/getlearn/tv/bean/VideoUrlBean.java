package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 26939 on 2018/12/14.
 */

public class VideoUrlBean implements Parcelable {
    private Long code;
    private String data;
    private Long timeStamp;
    private String beginEnd;

    protected VideoUrlBean(Parcel in) {
        if (in.readByte() == 0) {
            code = null;
        } else {
            code = in.readLong();
        }
        data = in.readString();
        if (in.readByte() == 0) {
            timeStamp = null;
        } else {
            timeStamp = in.readLong();
        }
        beginEnd = in.readString();
    }

    public static final Creator<VideoUrlBean> CREATOR = new Creator<VideoUrlBean>() {
        @Override
        public VideoUrlBean createFromParcel(Parcel in) {
            return new VideoUrlBean(in);
        }

        @Override
        public VideoUrlBean[] newArray(int size) {
            return new VideoUrlBean[size];
        }
    };

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBeginEnd() {
        return beginEnd;
    }

    public void setBeginEnd(String beginEnd) {
        this.beginEnd = beginEnd;
    }

    @Override
    public String toString() {
        return "VideoUrlBean{" +
                "code=" + code +
                ", data='" + data + '\'' +
                ", timeStamp=" + timeStamp +
                ", beginEnd='" + beginEnd + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(code);
        }
        dest.writeString(data);
        if (timeStamp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(timeStamp);
        }
        dest.writeString(beginEnd);
    }

    public static class UrlBean implements Parcelable{
        private String url;
        private Long timestamp;
        private int upState;

        protected UrlBean(Parcel in) {
            url = in.readString();
            if (in.readByte() == 0) {
                timestamp = null;
            } else {
                timestamp = in.readLong();
            }
            upState = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
            if (timestamp == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeLong(timestamp);
            }
            dest.writeInt(upState);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<UrlBean> CREATOR = new Creator<UrlBean>() {
            @Override
            public UrlBean createFromParcel(Parcel in) {
                return new UrlBean(in);
            }

            @Override
            public UrlBean[] newArray(int size) {
                return new UrlBean[size];
            }
        };

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public int getUpState() {
            return upState;
        }

        public void setUpState(int upState) {
            this.upState = upState;
        }

        @Override
        public String toString() {
            return "UrlBean{" +
                    "url='" + url + '\'' +
                    ", timestamp=" + timestamp +
                    ", upState=" + upState +
                    '}';
        }
    }
}
