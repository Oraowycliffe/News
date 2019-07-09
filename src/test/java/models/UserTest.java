package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        User testUser = userTest();
        assertEquals("John Doe",testUser.getName());
    }

    @Test
    public void setName() {
        User testUser = userTest();
        testUser.setName("John Smalling");
        assertEquals("John Smalling",testUser.getName());
    }

    @Test
    public void getPosition() {
        User testUser = userTest();
        assertEquals("Senior Dev",testUser.getPosition());
    }

    @Test
    public void setPosition() {
        User testUser = userTest();
        testUser.setPosition("Middle Dev");
        assertEquals("Middle Dev",testUser.getPosition());
    }

    @Test
    public void getRole() {
        User testUser = userTest();
        assertEquals("Overseeing Team",testUser.getRole());
    }

    @Test
    public void setRole() {
        User testUser = userTest();
        testUser.setRole("Creating Apps");
        assertEquals("Creating Apps",testUser.getRole());
    }

    @Test
    public void getDepartmentId() {
        User testUser = userTest();
        assertEquals(1,testUser.getDepartmentId());
    }

    @Test
    public void setDepartmentId() {
        User testUser = userTest();
        testUser.setDepartmentId(5);
        assertEquals(5,testUser.getDepartmentId());
    }

    @Test
    public void setId() {
        User testUser = userTest();
        testUser.setId(5);
        assertEquals(5,testUser.getId());
    }
    //helper method
    public User userTest(){
        return new User("John Doe","Senior Dev","Overseeing Team",1);
    }
}