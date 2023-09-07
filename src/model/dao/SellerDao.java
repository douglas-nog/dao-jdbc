package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller seller);
    void update(Seller seller);
    void deleteById(Seller seller);
    Seller findById(Integer id);
    List<Seller> findByDepartment(Department department);
    List<Seller> findAll();
}
