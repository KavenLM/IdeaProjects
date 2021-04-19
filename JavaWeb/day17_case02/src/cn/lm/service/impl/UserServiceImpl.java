package cn.lm.service.impl;

import cn.lm.dao.UserDao;
import cn.lm.dao.impl.UserDaoImpl;
import cn.lm.domain.PageBean;
import cn.lm.domain.User;
import cn.lm.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();
    @Override
    public User login(User user) {

        return dao.findUserByUsernameAndPassword(user);
    }

    @Override
    public List<User> findAll() {

        return dao.findAll();
    }


    @Override
    public PageBean<User> findUserByPage(String currentPage_str, String rows_str, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(currentPage_str);
        int rows = Integer.parseInt(rows_str);
        if(currentPage < 1){
            currentPage=1;
        }

        PageBean<User> pb = new PageBean<User>();

        //2.设置totalCount
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //3.设置totalPage
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount/rows + 1);
        pb.setTotalPage(totalPage);
        //设置下一页不超过最大页数，并且不等于零(避免求start时为负数)
        if (currentPage > totalPage && totalPage!=0){
            currentPage = totalPage;
        }
        //1.设置总记录数currentPage,rows
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //4.设置list<user>
        int start = (currentPage-1)*rows;
        List<User> list = dao.findBypage(start,rows,condition);
        pb.setList(list);


        return pb;
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void delUserById(String id_str) {
        int id = Integer.parseInt(id_str);
        dao.delById(id);
    }

    @Override
    public User findUserById(int id) {
        User user = dao.findById(id);

        return user;
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] uids) {
        if (uids != null && uids.length>0){
            for (String uid : uids) {
                dao.delById(Integer.parseInt(uid));
            }
        }

    }


}
