package cn.lm.dao.impl;

import cn.lm.dao.UserDao;
import cn.lm.domain.User;
import cn.lm.util.JDBCUtils;
import com.alibaba.druid.sql.ast.statement.SQLCreateTriggerStatement;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByUsernameAndPassword(User user) {
        String sql = "select * from user where username=? and password=?";

        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return list;
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();

        Set<String> keySet = condition.keySet();
        for(String key : keySet){
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if (value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");
            }
        }
        sql = sb.toString();
        int totalCount = template.queryForObject(sql, Integer.class,params.toArray());
        return totalCount;
    }

    @Override
    public List<User> findBypage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();

        Set<String> keySet = condition.keySet();
        for(String key : keySet){
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if (value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");
            }
        }
        sb.append(" limit ?,?");
        params.add(start);
        params.add(rows);

        sql = sb.toString();
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class),params.toArray());
        return list;
    }

    @Override
    public void add(User user) {
        String sql = "insert into user(name,gender,age,address,qq,email) value(?,?,?,?,?,?)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),
                user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void delById(int id) {
        String sql = "delete from user where id=?";
        template.update(sql,id);
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id=?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    @Override
    public void update(User user) {
        System.out.println(user);
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),
                user.getQq(),user.getEmail(),user.getId());
    }


}
