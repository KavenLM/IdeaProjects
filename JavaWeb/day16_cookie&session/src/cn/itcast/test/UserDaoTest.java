package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.junit.Test;

public class UserDaoTest {
    UserDao dao = new UserDao();
    User loginUser = new User();
    @Test
    public void loginTest(){
        loginUser.setUsername("zhangsan");
        loginUser.setPassword("1234");
        User user = dao.login(loginUser);
        System.out.println(user);
    }
}
