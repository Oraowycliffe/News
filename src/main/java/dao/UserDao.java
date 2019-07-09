package dao;

import models.User;

import java.util.List;

public interface UserDao {
    //create new user

    void add(User user);


    //retrieve user
    List<User>getAll();
    List<User>getAllUsersInADepartment(int departmentid);

    //update
    void update (int id, String name, String position, String role, int departmentId);

    //delete
    void deleteById(int id);
    void clearAll();
}
