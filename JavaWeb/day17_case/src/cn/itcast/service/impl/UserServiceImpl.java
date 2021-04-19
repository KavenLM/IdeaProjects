package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domian.PageBean;
import cn.itcast.domian.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author LM
 * UserService实现类
 */
public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user);
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void delUser(int id) {
        dao.del(id);
    }

    @Override
    public User findUserById(int id) {
        return dao.findById(id);
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids!=null&& ids.length>0){
            //1.遍历数组
            for(String id : ids){
                //2.调用dao删除
                dao.del(Integer.parseInt(id));
            }
        }

    }

    @Override
    public PageBean<User> findUserByPage(String s_currentPage, String s_rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(s_currentPage);
        int rows = Integer.parseInt(s_rows);
        System.out.println("currentPage3:"+currentPage);
        if(currentPage < 1){
            currentPage = 1;
        }
        System.out.println("currentPage4:"+currentPage);

        PageBean<User> pb = new PageBean<User>();
        //1.设置currentPage rows
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //2.调用dao，查询并设置总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //3.设置总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows ): (totalCount / rows) + 1 ;
        pb.setTotalPage(totalPage);

        System.out.println("totalCount1："+totalCount);

        if(currentPage > totalPage && totalPage!=0){
            currentPage = totalPage;
        }

        System.out.println("currentPage5:"+currentPage);
        //4.计算分页查询开始索引
        int start = (currentPage-1)*rows;
        //5.调用dao查询，查询并设置list
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        return pb;
    }
}
