package cn.lm.dao;

import cn.lm.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 根据用户名密码查找用户
     * @param user
     * @return
     */
    User findUserByUsernameAndPassword(User user);

    /**
     * 插叙所有用户信息
     * @return
     */
    List<User> findAll();


    int findTotalCount(Map<String, String[]> condition);

    List<User> findBypage(int start, int rows, Map<String, String[]> condition);

    void add(User user);

    void delById(int id);

    User findById(int id);

    void update(User user);
}
