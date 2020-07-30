package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zlw on 2018/11/20.
 */

public class DataFabulousBean implements Parcelable {
    public DataFabulousBean(int status, String text) {
        this.status = status;
        this.text = text;
    }

    public DataFabulousBean() {
        super();
    }

    @Override
    public String toString() {
        return "DataFabulousBean{" +
                "status=" + status +
                ", text='" + text + '\'' +
                '}';
    }

    /**
     * status : 1
     * text : 已点赞
     */

    private int status;
    private String text;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.text);
    }

    protected DataFabulousBean(Parcel in) {
        this.status = in.readInt();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<DataFabulousBean> CREATOR = new Parcelable.Creator<DataFabulousBean>() {
        @Override
        public DataFabulousBean createFromParcel(Parcel source) {
            return new DataFabulousBean(source);
        }

        @Override
        public DataFabulousBean[] newArray(int size) {
            return new DataFabulousBean[size];
        }
    };
}
