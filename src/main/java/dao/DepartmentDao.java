package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {

     void add(Department department);


     List<Department> getAll();
     Department getDepartmentById(int id);


     void update(int id, String name, String description, int employees);

     void deleteById(int id);


     void clearAll();

}
