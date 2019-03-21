package bean;

import dao.userDao;

import java.util.List;

/**
 * 分页对象
 * 用于分页查询数据库记录
 */
public class spilpage {

    private int total;              // 总记录数
    private int pageindex;          // 当前页面索引
    private int pagesize;           // 每页显示的条目数
    private List<user> list;        // 存放查询的user对象集合
    private int pagecount;          // 页码数

    public spilpage(){
        this.pagesize = 4;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount() {
        int count = this.getTotal() / this.getPagesize();
        this.pagecount = this.getTotal() % this.getPagesize() == 0 ? count : count + 1;
    }

    public List<user> getList() {
        return list;
    }

    public void setList(List<user> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
}
