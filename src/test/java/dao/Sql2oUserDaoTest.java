package dao;

import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private Connection conn;
    private Sql2oUserDao userDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/newsapi";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","Wycky@1998");
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        userDao.clearAll();
        conn.close();
    }

    @Test
    public void add() {
        User testUser = testUser();
        userDao.add(testUser);
        assertTrue(userDao.getAll().contains(testUser));
    }

    @Test
    public void getAll() {
        User testUser = testUser();
        User testUser2 = testUser2();
        userDao.add(testUser);
        userDao.add(testUser2);
        assertTrue(userDao.getAll().contains(testUser));
        assertEquals(userDao.getAll().size(),2);
    }

    @Test
    public void getAllUsersInADepartment() {
        User testUser = testUser();
        User testedUser = testedUser();
        userDao.add(testUser);
        userDao.add(testedUser);
        assertTrue(userDao.getAllUsersInADepartment(2).contains(testUser));
        assertTrue(userDao.getAllUsersInADepartment(2).contains(testedUser));

    }

//    @Test
//    public void update() {
//        User testUser = testUser();
//        userDao.add(testUser);
//        userDao.update(testUser.getId(),"Jane Mary","Lead Tech","Organize Accounts",2);
//        assertEquals("Jane Mary",userDao.getAll().get(0).getName());
//
//    }

    @Test
    public void deleteById() {
        User testUser = testUser();
        userDao.add(testUser);
        userDao.deleteById(testUser.getId());
        assertEquals(0,userDao.getAll().size());
    }

    @Test
    public void clearAll() {
        User testUser = testUser();
        userDao.add(testUser);
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());

    }

    public User testUser2(){
        return  new User("John Stephen", "IT-Support"," Infra -team Support",1);
    }

    public User testUser(){
        return  new User("John Doe", "IT-incharge","Lead Infra -team",2);
    }
    public User testedUser(){return  new User("John Dos", "IT-incharge","Lead Infra -team",2);}
}