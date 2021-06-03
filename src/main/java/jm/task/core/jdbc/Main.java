package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        User Tom = new User("Tom", "Toms", (byte) 1);
        User Bob = new User("Bob", "Bobs", (byte) 2);
        User Sam = new User("Sam", "Sams", (byte) 3);
        User Tim = new User("Tim", "Tims", (byte) 4);

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser(Tom.getName(), Tom.getLastName(), Tom.getAge());
        userService.saveUser(Bob.getName(), Bob.getLastName(), Bob.getAge());
        userService.saveUser(Sam.getName(), Sam.getLastName(), Sam.getAge());
        userService.saveUser(Tim.getName(), Tim.getLastName(), Tim.getAge());
        userService.removeUserById(Bob.getId());
        List<User> list = userService.getAllUsers();
        for(User user : list){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
