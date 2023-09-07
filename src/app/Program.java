package app;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.creatSellerDao();

        System.out.println("Seller FindById: ");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();
        System.out.println("Seller FindByDepartment: ");
        List<Seller> list = sellerDao.findByDepartment(new Department(2, null));
        for (Seller s : list) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println("Seller FindAll: ");
        List<Seller> listAll = sellerDao.findAll();
        for (Seller s : listAll) {
            System.out.println(s);
        }
    }
}
