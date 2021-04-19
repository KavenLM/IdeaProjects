package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domian.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author LM
 * UserDao的实现类
 */
public class UserDaoImpl implements UserDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(User user) {
        String sql = "select * from user where username=? and password=?";
        User loginUser = null;
        try {
            loginUser = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    user.getUsername(),
                    user.getPassword());
            return loginUser;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {
//        String sql = "insert into user(`name`,`gender`,`age`,`address`,`qq`,`email`) values(?,?,?,?,?,?)";
        String sql = "insert into user(name,gender,age,address,qq,email) values(?,?,?,?,?,?)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),
                user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void del(int id) {
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
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),
                user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义模板
        String sql = "select count(*) from user where 1=1";
        StringBuilder sb = new StringBuilder(sql); //用于存储模糊查询条件
        List<Object> params = new ArrayList<Object>();//用于存储？的值
        //遍历map
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            //判断value不为空
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ?" );
                params.add("%"+value+"%");//?的值
            }
        }

        System.out.println(sb.toString());
        System.out.println(params);

        Integer totalCount = template.queryForObject(sb.toString(), Integer.class,params.toArray());
        return totalCount;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {

        String sql = "select * from user where 1=1";

        StringBuilder sb = new StringBuilder(sql); //用于存储模糊查询条件
        List<Object> params = new ArrayList<Object>();//用于存储？的值
        //遍历map
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            //判断value不为空
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ?" );
                params.add("%"+value+"%");//?的值
            }
        }

        sb.append(" limit ?,?");
        params.add(start);
        params.add(rows);
        sql = sb.toString();

        System.out.println(sql);
        System.out.println(params);
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
        return list;
    }
}
