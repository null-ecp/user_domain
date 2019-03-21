package service;

import bean.spilpage;
import dao.userDao;

import java.util.Map;

/**
 * 事件处理
 */
public class Service {
    /**
     *
     * @param pageindex
     * @return
     */
    public spilpage inituserlist(String pageindex, Map<String, String[]> condition){
        int index = 1;
        // 判断是否是下一页 或者 上一页
        if (pageindex == null || pageindex.equals("")){
            index = 1;
        } else {
            index = Integer.parseInt(pageindex);
        }
        // 创建
        userDao userDao = new userDao();
        spilpage spilpage = new spilpage();
        // 设置页面对象
        spilpage.setTotal(userDao.getCount(condition));
        spilpage.setPageindex(index);
        spilpage.setPagecount();
        // 获取user集合
        spilpage.setList(userDao.findbycond(condition, spilpage));

        return spilpage;
    }
}
