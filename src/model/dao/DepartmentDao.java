package model.dao;


import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface DepartmentDao {

    void insert(Department dept);
    void update(Department dept);
    void deleteById(Department dept);
    Department findById(Integer id);
    List<Department> findAll();
}
