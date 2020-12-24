package org.jot;

import org.jot.entity.user.User;
import org.jot.service.user.IUserService;
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
    void contextLoads() {

//        userService.add(new User("33062219900708441X", "wei wang", "15757175721", "王威", 30, true, 1L));
//        userService.add(new User("330622199209234571", "gang wang", "15757175611", "王刚", 28, true, 1L));
//        userService.add(new User("330622199112113762", "jie wang", "15666661111", "王洁", 29, false, 1L));

//        List<User> list = userService.findAll();
//        Assert.assertEquals(3, list.size());

//        User user = userService.findUser("wei wang");
//        Optional<User> user2 = userService.findByUsernameAndPassword("wei wang", "wei wang123456");
//        System.out.println(user.toString());
//        System.out.println(user2.get().toString());

        User user3 = userService.findById(4L);
        System.out.println(user3.toString());
        user3.setUpdatedBy(1L);
//        user3.resetPassword();
        User user4 = userService.edit(user3);

        System.out.println(user4.toString());
    }

}
