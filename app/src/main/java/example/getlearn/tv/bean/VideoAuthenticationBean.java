package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 26939 on 2018/12/14.
 */

public class VideoAuthenticationBean implements Parcelable {
    private String url;
    private boolean status;
    private String moduleid;
    private String params;

    protected VideoAuthenticationBean(Parcel in) {
        this.url = in.readString();
        this.status = in.readByte() != 0;
        this.moduleid = in.readString();
        this.params = in.readString();
    }

    public static final Creator<VideoAuthenticationBean> CREATOR = new Creator<VideoAuthenticationBean>() {
        @Override
        public VideoAuthenticationBean createFromParcel(Parcel in) {
            return new VideoAuthenticationBean(in);
        }

        @Override
        public VideoAuthenticationBean[] newArray(int size) {
            return new VideoAuthenticationBean[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "VideoAuthenticationBean{" +
                "url='" + url + '\'' +
                ", status=" + status +
                ", moduleid='" + moduleid + '\'' +
                ", params='" + params + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeString(moduleid);
        dest.writeString(this.params);
    }

    public static class ParamsBean implements Parcelable{
        private String id;
        private String appAk;
        private String apiName;
        private String timeStamp;
        private String sign;

        protected ParamsBean(Parcel in) {
            id = in.readString();
            appAk = in.readString();
            apiName = in.readString();
            timeStamp = in.readString();
            sign = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(appAk);
            dest.writeString(apiName);
            dest.writeString(timeStamp);
            dest.writeString(sign);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ParamsBean> CREATOR = new Creator<ParamsBean>() {
            @Override
            public ParamsBean createFromParcel(Parcel in) {
                return new ParamsBean(in);
            }

            @Override
            public ParamsBean[] newArray(int size) {
                return new ParamsBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppAk() {
            return appAk;
        }

        public void setAppAk(String appAk) {
            this.appAk = appAk;
        }

        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        @Override
        public String toString() {
            return "ParamsBean{" +
                    "id='" + id + '\'' +
                    ", appAk='" + appAk + '\'' +
                    ", apiName='" + apiName + '\'' +
                    ", timeStamp='" + timeStamp + '\'' +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }
}
