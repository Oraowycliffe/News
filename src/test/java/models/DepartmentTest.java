package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        Department testDepartment = testdepartment();
        assertEquals("ICT",testDepartment.getName());
    }

    @Test
    public void setName() {
        Department testDeparment = testdepartment();
        testDeparment.setName("Accounts");
        assertEquals("Accounts", testDeparment.getName());
    }

    @Test
    public void setDescription() {
        Department testDepartment = testdepartment();
        assertEquals("Software development", testDepartment.getDescription());
    }

    @Test
    public void getEmployees() {
        Department testDepartment = testdepartment();
        assertEquals(4, testDepartment.getEmployees());
    }

    @Test
    public void setEmployees() {
        Department testDepartment = testdepartment();
        testDepartment.setEmployees(12);
        assertEquals(12, testDepartment.getEmployees());
    }

    @Test
    public void setId() {
        Department testDepartment = testdepartment();
        testDepartment.setId(1);
        assertEquals(1, testDepartment.getId());
    }

    //helper method
    public Department testdepartment(){
        return new Department("ICT","Software development", 4);
    }
}