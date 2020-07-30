package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 26939 on 2018/12/14.
 */

public class UserInfoBean implements Parcelable {
    private String message;
    private OutPutBean output;
    private String requestid;
    private String status;

    protected UserInfoBean(Parcel in) {
        message = in.readString();
        output = in.readParcelable(OutPutBean.class.getClassLoader());
        requestid = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeParcelable(output, flags);
        dest.writeString(requestid);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel in) {
            return new UserInfoBean(in);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OutPutBean getOutput() {
        return output;
    }

    public void setOutput(OutPutBean output) {
        this.output = output;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class OutPutBean implements Parcelable{
        private String addr;
        private String areaid;
        private String cardno;
        private String cardtype;
        private String city;
        private String custattr;
        private String custid;
        private String custname;
        private String custtype;
        private String feebookSums;
        private String feekind;
        private String maintaindev;
        private String mobile;
        private String pgroupid;
        private String pgroupname;
        private String phone;
        private List<ServsBean> servs;
        private String subcode;
        private String subtype;
        private String users;

        protected OutPutBean(Parcel in) {
            addr = in.readString();
            areaid = in.readString();
            cardno = in.readString();
            cardtype = in.readString();
            city = in.readString();
            custattr = in.readString();
            custid = in.readString();
            custname = in.readString();
            custtype = in.readString();
            feebookSums = in.readString();
            feekind = in.readString();
            maintaindev = in.readString();
            mobile = in.readString();
            pgroupid = in.readString();
            pgroupname = in.readString();
            phone = in.readString();
            servs = in.createTypedArrayList(ServsBean.CREATOR);
            subcode = in.readString();
            subtype = in.readString();
            users = in.readString();
        }

        public static final Creator<OutPutBean> CREATOR = new Creator<OutPutBean>() {
            @Override
            public OutPutBean createFromParcel(Parcel in) {
                return new OutPutBean(in);
            }

            @Override
            public OutPutBean[] newArray(int size) {
                return new OutPutBean[size];
            }
        };

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCustattr() {
            return custattr;
        }

        public void setCustattr(String custattr) {
            this.custattr = custattr;
        }

        public String getCustid() {
            return custid;
        }

        public void setCustid(String custid) {
            this.custid = custid;
        }

        public String getCustname() {
            return custname;
        }

        public void setCustname(String custname) {
            this.custname = custname;
        }

        public String getCusttype() {
            return custtype;
        }

        public void setCusttype(String custtype) {
            this.custtype = custtype;
        }

        public String getFeebookSums() {
            return feebookSums;
        }

        public void setFeebookSums(String feebookSums) {
            this.feebookSums = feebookSums;
        }

        public String getFeekind() {
            return feekind;
        }

        public void setFeekind(String feekind) {
            this.feekind = feekind;
        }

        public String getMaintaindev() {
            return maintaindev;
        }

        public void setMaintaindev(String maintaindev) {
            this.maintaindev = maintaindev;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPgroupid() {
            return pgroupid;
        }

        public void setPgroupid(String pgroupid) {
            this.pgroupid = pgroupid;
        }

        public String getPgroupname() {
            return pgroupname;
        }

        public void setPgroupname(String pgroupname) {
            this.pgroupname = pgroupname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public List<ServsBean> getServs() {
            return servs;
        }

        public void setServs(List<ServsBean> servs) {
            this.servs = servs;
        }

        public String getSubcode() {
            return subcode;
        }

        public void setSubcode(String subcode) {
            this.subcode = subcode;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getUsers() {
            return users;
        }

        public void setUsers(String users) {
            this.users = users;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(addr);
            dest.writeString(areaid);
            dest.writeString(cardno);
            dest.writeString(cardtype);
            dest.writeString(city);
            dest.writeString(custattr);
            dest.writeString(custid);
            dest.writeString(custname);
            dest.writeString(custtype);
            dest.writeString(feebookSums);
            dest.writeString(feekind);
            dest.writeString(maintaindev);
            dest.writeString(mobile);
            dest.writeString(pgroupid);
            dest.writeString(pgroupname);
            dest.writeString(phone);
            dest.writeTypedList(servs);
            dest.writeString(subcode);
            dest.writeString(subtype);
            dest.writeString(users);
        }
    }

    public static class ServsBean implements Parcelable{
        private String addr;
        private String casubtype;
        private String cmacctno;
        private String custid;
        private String custname;
        private String definition;
        private String devno;
        private String etime;
        private String fbfees;
        private String feekind;
        private String grid;
        private String is_ectype;
        private String keyno;
        private String pcode;
        private String permark;
        private String pname;
        private String servid;
        private String servstatus;
        private String servtype;
        private String userdefinition;

        protected ServsBean(Parcel in) {
            addr = in.readString();
            casubtype = in.readString();
            cmacctno = in.readString();
            custid = in.readString();
            custname = in.readString();
            definition = in.readString();
            devno = in.readString();
            etime = in.readString();
            fbfees = in.readString();
            feekind = in.readString();
            grid = in.readString();
            is_ectype = in.readString();
            keyno = in.readString();
            pcode = in.readString();
            permark = in.readString();
            pname = in.readString();
            servid = in.readString();
            servstatus = in.readString();
            servtype = in.readString();
            userdefinition = in.readString();
        }

        public static final Creator<ServsBean> CREATOR = new Creator<ServsBean>() {
            @Override
            public ServsBean createFromParcel(Parcel in) {
                return new ServsBean(in);
            }

            @Override
            public ServsBean[] newArray(int size) {
                return new ServsBean[size];
            }
        };

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getCasubtype() {
            return casubtype;
        }

        public void setCasubtype(String casubtype) {
            this.casubtype = casubtype;
        }

        public String getCmacctno() {
            return cmacctno;
        }

        public void setCmacctno(String cmacctno) {
            this.cmacctno = cmacctno;
        }

        public String getCustid() {
            return custid;
        }

        public void setCustid(String custid) {
            this.custid = custid;
        }

        public String getCustname() {
            return custname;
        }

        public void setCustname(String custname) {
            this.custname = custname;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public String getDevno() {
            return devno;
        }

        public void setDevno(String devno) {
            this.devno = devno;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getFbfees() {
            return fbfees;
        }

        public void setFbfees(String fbfees) {
            this.fbfees = fbfees;
        }

        public String getFeekind() {
            return feekind;
        }

        public void setFeekind(String feekind) {
            this.feekind = feekind;
        }

        public String getGrid() {
            return grid;
        }

        public void setGrid(String grid) {
            this.grid = grid;
        }

        public String getIs_ectype() {
            return is_ectype;
        }

        public void setIs_ectype(String is_ectype) {
            this.is_ectype = is_ectype;
        }

        public String getKeyno() {
            return keyno;
        }

        public void setKeyno(String keyno) {
            this.keyno = keyno;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPermark() {
            return permark;
        }

        public void setPermark(String permark) {
            this.permark = permark;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getServid() {
            return servid;
        }

        public void setServid(String servid) {
            this.servid = servid;
        }

        public String getServstatus() {
            return servstatus;
        }

        public void setServstatus(String servstatus) {
            this.servstatus = servstatus;
        }

        public String getServtype() {
            return servtype;
        }

        public void setServtype(String servtype) {
            this.servtype = servtype;
        }

        public String getUserdefinition() {
            return userdefinition;
        }

        public void setUserdefinition(String userdefinition) {
            this.userdefinition = userdefinition;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(addr);
            dest.writeString(casubtype);
            dest.writeString(cmacctno);
            dest.writeString(custid);
            dest.writeString(custname);
            dest.writeString(definition);
            dest.writeString(devno);
            dest.writeString(etime);
            dest.writeString(fbfees);
            dest.writeString(feekind);
            dest.writeString(grid);
            dest.writeString(is_ectype);
            dest.writeString(keyno);
            dest.writeString(pcode);
            dest.writeString(permark);
            dest.writeString(pname);
            dest.writeString(servid);
            dest.writeString(servstatus);
            dest.writeString(servtype);
            dest.writeString(userdefinition);
        }
    }
}
