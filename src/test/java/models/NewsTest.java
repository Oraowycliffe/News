package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContent() {
        News testNews = testNews();
        assertEquals("Four employees added", testNews.getContent());
    }

    @Test
    public void setContent() {
        News testNews = testNews();
        testNews.setContent("Four employees employed");
        assertEquals("Four employees employed", testNews.getContent());
    }


    @Test
    public void setId() {
        News testNews = testNews();
        testNews.setId(4);
        assertEquals(4, testNews.getId());
    }


    @Test
    public void setDepartmentId() {
        News testNews = testNews();
        testNews.setDepartmentId(1);
        assertEquals(1, testNews.getDepartmentId());
    }
    //helper method
    public News testNews(){
        return new News("Four employees added", 4);
    }
}