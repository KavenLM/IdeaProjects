package cn.itcast.service;

import cn.itcast.domian.PageBean;
import cn.itcast.domian.User;

import java.util.List;
import java.util.Map;

/**
 * @author LM
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户的信息
     * @return
     */
    List<User> findAll();

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 保存user
     * @param user
     */
    void addUser(User user);

    /**
     * 删除user
     * @param id
     */
    void delUser(int id);

    /**
     * 根据id查找
     * @param id
     * @return user
     */
    User findUserById(int id);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 批量删除用户信息
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
