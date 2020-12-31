package org.jot;

import org.jot.entity.user.User;
import org.jot.enumeration.Gender;
import org.jot.service.user.IUserService;
import org.jot.util.GlobalException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class BootApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() throws GlobalException {

        insertAdmin();

//        userService.add(new User("33062219900708441X", "wei wang", "15757175721", "王威", 30, true, 1L));
//        userService.add(new User("330622199209234571", "gang wang", "15757175611", "王刚", 28, true, 1L));
//        userService.add(new User("330622199112113762", "jie wang", "15666661111", "王洁", 29, false, 1L));

//        List<User> list = userService.findAll();
//        Assert.assertEquals(3, list.size());

//        User user = userService.findUser("wei wang");
//        Optional<User> user2 = userService.findByUsernameAndPassword("wei wang", "wei wang123456");
//        System.out.println(user.toString());
//        System.out.println(user2.get().toString());

        Optional<User> user3 = userService.findByUsername("qjj");
        if (user3.isPresent()) {
            System.out.println(user3.toString());
        }
    }

    private void insertAdmin() throws GlobalException {
        User user = new User();
        user.setCode("330682199304284415");
        user.setUsername("qjj");
        user.setTelephone("17764586172");
        user.setName("钱嘉俊");
        user.setAge(27);
        user.setGender(Gender.MALE);
        user.setCreatedBy(1L);
        User u = userService.addUser(user, null);
        Assert.assertNotNull(u.getId());
    }

}
