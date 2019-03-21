package dao;

/**
 * 登录用户管理接口
 */
public interface adminDao {
    /**
     * 用户登录
     * @param id
     * @param pd
     * @return
     */
    public boolean login(String id, String pd);
}
