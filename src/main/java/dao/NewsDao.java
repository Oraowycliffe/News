package dao;

import models.News;

import java.util.List;

public interface NewsDao {
    //create
    void add(News news);

    //retrieve
    List<News> getAll();
    List<News> getAllNewsForDepartment(int departmentId);

    //Update
    void update(int id, String content,int departmentId);


    //delete
    void deleteById(int id);
    void clearAll();
}
