package dao;
import models.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private Connection conn ;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://localhost:5432/newsapi";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","Wycky@1998");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
//        departmentDao.clearAll();
        conn.close();
    }

    @Test
    public void add() {
        Department testDepartment = departmentTest();
        departmentDao.add(testDepartment);
        assertTrue(departmentDao.getAll().contains(testDepartment));
    }

    @Test
    public void getAll() {
        Department testdepartment = departmentTest();
        Department testdepartment2 = departmentTest2();
        departmentDao.add(testdepartment);
        departmentDao.add(testdepartment2);
        assertTrue(departmentDao.getAll().contains(testdepartment));
        assertTrue(departmentDao.getAll().contains(testdepartment2));
    }

    @Test
    public void getDepartmentById() {
        Department testdepartment = departmentTest();
        departmentDao.add(testdepartment);
        assertEquals(testdepartment,departmentDao.getDepartmentById(testdepartment.getId()));
    }

//    @Test
//    public void update() {
//        Department testDepartment = departmentTest();
//        departmentDao.add(testDepartment);
//        departmentDao.update(testDepartment.getId(),"Accounting", "Finance records",10);
//        System.out.println(testDepartment.getName());
//        assertEquals(departmentDao.getAll().get(0).getName(),"Accounting");
//    }

    @Test
    public void deleteById() {
        Department testDepartment = departmentTest();
        departmentDao.add(testDepartment);
        departmentDao.deleteById(testDepartment.getId());
        assertEquals(0,departmentDao.getAll().size());
    }

    @Test
    public void clearAll() {
        Department testdepartment = departmentTest();
        departmentDao.add(testdepartment);
        departmentDao.clearAll();
        assertEquals(0,departmentDao.getAll().size());
    }
    //helper methods
    public Department departmentTest(){
        return new Department("Infra-red", "Moringa threshold",5);
    }
    public Department departmentTest2(){
        return new Department("Space Management", "Moringa Team",3);
    }
}