package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("INSERT INTO seller " +
                            "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new Date(seller.getBithDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartament().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    seller.setId(id);
                }
            } else {
                throw new DbException("Unexpected error. No rows affected");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Seller seller) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("update seller set Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
            "where Id = ?");

            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new Date(seller.getBithDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartament().getId());
            st.setInt(6, seller.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void deleteById(Seller seller) {


    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("select seller.*, department.Name as deptName from seller inner join department" +
                    " on seller.DepartmentId = department.Id where seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dept = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, dept);
                return seller;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("select seller.*, department.Name as deptName from seller inner join department" +
                    " on seller.DepartmentId = department.Id where DepartmentId = ?" +
                    " order by name");

            st.setInt(1, department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dept = map.get(rs.getInt("DepartmentId"));

                if (dept == null) {
                    dept = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dept);
                }
                Seller seller = instantiateSeller(rs, dept);
                list.add(seller);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dept) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBithDate(rs.getDate("BirthDate"));
        seller.setDepartament(dept);
        return seller;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dept = new Department();
        dept.setId(rs.getInt("DepartmentId"));
        dept.setName(rs.getString("DeptName"));
        return dept;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("select seller.*, department.Name as deptName from seller inner join department" +
                    " on seller.DepartmentId = department.Id" +
                    " order by name");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dept = map.get(rs.getInt("DepartmentId"));

                if (dept == null) {
                    dept = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dept);
                }
                Seller seller = instantiateSeller(rs, dept);
                list.add(seller);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
