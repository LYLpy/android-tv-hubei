package example.getlearn.tv.bean;

/**
 * Created by zlw on 2018/12/2.
 */

public class PageBean {
    private  int page_int;

    @Override
    public String toString() {
        return "PageBean{" +
                "page_int=" + page_int +
                '}';
    }

    public PageBean() {
        super();
    }

    public int getPage_int() {
        return page_int;
    }

    public void setPage_int(int page_int) {
        this.page_int = page_int;
    }

    public PageBean(int page_int) {
        this.page_int = page_int;
    }
}
