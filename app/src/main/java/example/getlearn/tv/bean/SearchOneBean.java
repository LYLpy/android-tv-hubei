package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zlw on 2018/11/8.
 */

public class SearchOneBean implements Parcelable {
    private String Number;

    public SearchOneBean() {
        super();
    }

    @Override
    public String toString() {
        return "SearchOneBean{" +
                "Number='" + Number + '\'' +
                '}';
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public SearchOneBean(String number) {
        Number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Number);
    }

    protected SearchOneBean(Parcel in) {
        this.Number = in.readString();
    }

    public static final Parcelable.Creator<SearchOneBean> CREATOR = new Parcelable.Creator<SearchOneBean>() {
        @Override
        public SearchOneBean createFromParcel(Parcel source) {
            return new SearchOneBean(source);
        }

        @Override
        public SearchOneBean[] newArray(int size) {
            return new SearchOneBean[size];
        }
    };
}
