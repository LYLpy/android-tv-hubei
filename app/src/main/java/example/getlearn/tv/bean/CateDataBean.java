package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 26939 on 2018/12/1.
 */

public class CateDataBean  {
    private String cname;
    private String cid;

    public CateDataBean() {
        super();
    }

    @Override
    public String toString() {
        return "CateDataBean{" +
                "cname='" + cname + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public CateDataBean(String cname, String cid) {
        this.cname = cname;
        this.cid = cid;
    }



}
