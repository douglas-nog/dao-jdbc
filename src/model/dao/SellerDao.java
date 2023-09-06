package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller dept);
    void update(Seller dept);
    void deleteById(Seller dept);
    Seller findById(Integer id);
    List<Seller> findAll();
}
