package dao;

import Util.JDBCUtil;
import bean.spilpage;
import bean.user;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * user数据库操作对象
 */
public class userDao implements dao{

    // 封装一个jdbctemplate用于怼数据库进行操作
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getdatasource());

    /**
     * 查询user表中的所有记录封装成一个list集合
     * @return users 封装user对象的list集合
     */
    @Override
    public List<user> findall() {
        List<user> users = jdbcTemplate.query("select * from user_info", new BeanPropertyRowMapper<user>(user.class));
        return users;
    }

    /**
     * 根据请求的id查询指定用户 , 并修改信息
     * @param id
     */
    @Override
    public void update(int id, Map<String, String> values) {
        jdbcTemplate.update("update user_info set uname = ?, age = ?," +
                "sex = ?, address = ?, email = ? where id = ?",values.get("uname"), values.get("age"),
                values.get("sex"), values.get("address"), values.get("email"),id);
    }

    /**
     * 添加用户信息
     * @param values
     */
    @Override
    public void adduser(Map<String, String> values) {
        jdbcTemplate.update("insert user_info values(?,?,?,?,?,?)",
                values.get("id"), values.get("uname"), values.get("age"),
                values.get("sex"), values.get("address"), values.get("email"));
    }

    /**
     * 根据id来删除用户记录
     * @param id values
     */
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from user_info where id = ?",id);
    }

    /**
     * 删除选中的用户记录
     * @param ids
     */
    @Override
    public void deletecheck(String[] ids) {
        for (String id : ids) {
            int i = Integer.parseInt(id);
            jdbcTemplate.update("delete from user_info where id = ?", id);
        }
    }

    /**
     * 获取记录总条数
     * @return
     */
    @Override
    public int getCount(Map<String, String[]> condition) {
        // 用于存储 ? 的值
        List<Object> values = new ArrayList<Object>();
        StringBuffer sbf = spilString(condition, values);

        try {
            List<user> users = jdbcTemplate.query(sbf.toString(), new BeanPropertyRowMapper<user>(user.class), values.toArray());
            return users.size();
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据id获取指定的用户
     * @param id
     * @return
     */
    @Override
    public user findbyid(int id) {
        String sql = "select * from user_info where id = ?";
        user user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<user>(user.class), id);
        return user;
    }

    /**
     * 分页查询 返回每一页的用户集合
     * @param spilpage
     * @return
     */
    @Override
    public List<user> findbypage(spilpage spilpage) {
        String sql = "select * from user_info limit ? , ?";
        int start = (spilpage.getPageindex() - 1) * spilpage.getPagesize();
        int end = spilpage.getPagesize();
        List<user> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<user>(user.class), start, end);
        return users;
    }

    /**
     * 复杂条件查询
     * @param condition
     * @param spilpage
     * @return
     */
    @Override
    public List<user> findbycond(Map<String, String[]> condition, spilpage spilpage){
        // 用于存储 ? 的值
        List<Object> values = new ArrayList<Object>();
        StringBuffer sbf = spilString(condition, values);
//        System.out.println(sbf.toString());
        // 拼接分页查询
        sbf.append(" limit ? , ?");
//        System.out.println(sbf.toString());
        int start = (spilpage.getPageindex() - 1) * spilpage.getPagesize();
        int end = spilpage.getPagesize();
        values.add(start);
        values.add(end);

        return jdbcTemplate.query(sbf.toString(), new BeanPropertyRowMapper<user>(user.class), values.toArray());
    }

    /**
     * 拼接复杂条件查询的字符串
     * @param condition
     * @param values
     * @return
     */
    public static StringBuffer spilString(Map<String, String[]> condition, List<Object> values){
        // 方便拼接查询字符串
        String sql = "select * from user_info where 1 = 1";
        StringBuffer sbf = new StringBuffer(sql);
        for (String s : condition.keySet()) {
            //排除分页条件参数
            if("pageindex".equals(s) || "pagesize".equals(s)){
                continue;
            }
            String value = condition.get(s)[0];
            if (value != null && value != ""){
                sbf.append(" and "+s+"= ?");
                values.add(value);
            }
        }
        return sbf;
    }
}
