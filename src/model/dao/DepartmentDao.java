package model.dao;

import model.entities.Departament;

import java.util.List;

public interface DepartmentDao {

    void insert(Departament dept);
    void update(Departament dept);
    void deleteById(Departament dept);
    Departament findById(Integer id);
    List<Departament> findAll();
}
