package dao;

import bean.spilpage;
import bean.user;

import java.util.List;
import java.util.Map;

/**
 * 数据库操作对象接口
 * 用于定义需要完成的操作
 */
public interface dao {
    
    /**
     * 查询数据库中所有的用户
     * @return
     */
    public List<user> findall();

    /**
     * 更新用户数据
     * @param id
     */
    public void update(int id, Map<String, String> values);

    /**
     * 添加用户记录
     */
    public void adduser(Map<String, String> values);

    /**
     * 删除用户记录
     * @param id
     */
    public void delete(int id);

    /**
     * 获取记录总条数
     * @return
     */
    public int getCount(Map<String, String[]> condition);

    /**
     * 根据用户id获取指定用户
     * @param id
     * @return 指定用户的封装对象
     */
    public user findbyid(int id);

    /**
     * 分页查询
     * @return
     */
    public List<user> findbypage(spilpage spilpage);

    /**
     * 条件查询
     * @param condition
     * @param spilpage
     * @return
     */
    public List<user> findbycond(Map<String, String[]> condition, spilpage spilpage);
}
