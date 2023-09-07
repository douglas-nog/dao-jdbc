package app;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
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

//        System.out.println();
//        System.out.println("Seller insert: ");
//        Seller newSeller = new Seller(null, "Rose Pink", "pinkrose@gmail.com", new Date(), 4000, new Department(2, null));
//        sellerDao.insert(newSeller);
//        System.out.println("New seller inserted id: " + newSeller.getId());


        System.out.println();
        System.out.println("Seller update: ");
        seller = sellerDao.findById(1);
        seller.setName("Bruce Willis");
        sellerDao.update(seller);
        System.out.println("Update completed");

        System.out.println();
        System.out.println("Seller Delete: ");
        seller = sellerDao.findById(11);
        System.out.println(seller);
        sellerDao.deleteById(seller.getId());
        System.out.println("Seller deleted");
    }
}
