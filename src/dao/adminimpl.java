package dao;

import Util.JDBCUtil;
import bean.admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class adminimpl implements adminDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getdatasource());


    @Override
    public boolean login(String id, String pd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean flag = false;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getconnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pstmt = conn.prepareStatement("select * from admin where id = ? and pd = ?");
            pstmt.setString(1, id);
            pstmt.setString(2, pd);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            flag = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
