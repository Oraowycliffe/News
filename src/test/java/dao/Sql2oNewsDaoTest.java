package dao;

import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private Connection conn ;
    private Sql2oNewsDao newsDao;


    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://localhost:5432/newsapi";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","Wycky@1998");
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
//        newsDao.clearAll();
        conn.close();

    }

    @Test
    public void add() {
        News testNews = testNews();
        newsDao.add(testNews);
        assertTrue(newsDao.getAll().contains(testNews));

    }

    @Test
    public void getAll() {
        News testNews = testNews();
        News testNews2 = testNews2();
        newsDao.add(testNews);
        newsDao.add(testNews2);
        assertTrue(newsDao.getAll().contains(testNews));
        assertTrue(newsDao.getAll().contains(testNews2));

    }

    @Test
    public void getAllNewsForDepartment() {
        News testNews = testNews();
        News testNews2 = testNews2();
        newsDao.add(testNews);
        newsDao.add(testNews2);
        assertTrue(newsDao.getAllNewsForDepartment(2).contains(testNews));
        assertTrue(newsDao.getAllNewsForDepartment(2).contains(testNews2));
    }

//    @Test
//    public void update() {
//        News testNews = testNews();
//        newsDao.add(testNews);
//        newsDao.update(testNews.getId(),"New Machines arrives from Japan",2);
//
//        assertEquals("New Machines arrives from Japan",newsDao.getAll().get(0).getContent());
//    }

    @Test
    public void deleteById() {
        News testNews = testNews();
        newsDao.add(testNews);
        newsDao.deleteById(testNews.getId());
        assertEquals(0,newsDao.getAll().size());
    }

    @Test
    public void clearAll() {
        News testNews = testNews();
        newsDao.add(testNews);
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());

    }

// helper method
    public News testNews(){
        return new News("Five sacked at Infra-Team",2);
    }
    public News testNews2(){
        return new News("Four sacked at Infra-Team",2);
    }

}