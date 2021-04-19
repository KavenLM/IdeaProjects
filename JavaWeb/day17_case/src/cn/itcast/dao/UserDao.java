package cn.itcast.dao;

import cn.itcast.domian.User;

import java.util.List;
import java.util.Map;

/**
 * @author LM
 * 操作用户数据接口
 */
public interface UserDao {
    /**
     * 查询user表所有信息
     * @return 返回所有用户信息
     */
    List<User> findAll();

    /**
     * 根据用户输入的用户名和密码，查找对应用户
     * @param user
     * @return 查找到的用户
     */
    User findUserByUsernameAndPassword(User user);

    /**
     * 向user表中添加一条信息
     * @param user
     */
    void add(User user);

    /**
     * 删除user表中对应id的信息
     * @param id
     */
    void del(int id);

    /**
     * 根据id查找对应记录
     * @param id
     * @return user
     */
    User findById(int id);

    /**
     * 修改记录
     * @param user
     */
    void update(User user);

    /**
     * 查询所有记录数量
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * user信息分页查询
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
