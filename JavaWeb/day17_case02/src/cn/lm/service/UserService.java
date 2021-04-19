package cn.lm.service;

import cn.lm.domain.PageBean;
import cn.lm.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 分页查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

    /**
     * 添加
     * @param user
     */
    void addUser(User user);

    /**
     * 删除
     * @param id
     */
    void delUserById(String id);

    /**
     * 根据id找user
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 修改user记录
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除选中
     * @param uids
     */
    void delSelectedUser(String[] uids);
}
