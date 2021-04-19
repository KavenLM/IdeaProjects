package cn.itcast.test;

import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domian.User;
import org.junit.Test;

import java.util.List;

public class UserDaoImplTest {
    UserDaoImpl udi = new UserDaoImpl();
    @Test
    public void findAllTest(){
        List<User> users = udi.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void findUserByUsernameAndPasswordTest(){
        User user = new User();
//        user.setPassword("123");
//        user.setUsername("zhangsan");
        User loginUser = udi.findUserByUsernameAndPassword(user);
        System.out.println(loginUser);

    }

    @Test
    public void addTest(){
        User user = new User();
        user.setName("李二狗");
        user.setGender("男");
        user.setAge(22);
        user.setAddress("佳木和");
        user.setQq("1231232");
        user.setEmail("leg@qq.com");
        udi.add(user);
    }
}
