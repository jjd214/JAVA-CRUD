package main;
import dao.CategoryDao;
import dao.CategoryDaoImpl;
import dao.ProductDao;
import dao.ProductDaoImpl;
import db.DBConnection;
import model.Category;
import model.Product;

import java.sql.*;

public class Main {
    public static void main (String[] args) {

        ProductDao productDao = new ProductDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();

        var product = new Product()
                .setCategory_id(1)
                .setName("Gatorade")
                .setPrice(200)
                .setStock(10);

//        productDao.create(product);

        var productDetails = productDao.find(7);
        System.out.format("CATEGORY ID: %d - NAME: %s - PRICE: %f - STOCK: %d \n",

                productDetails.getCategory_id(),
                productDetails.getName(),
                productDetails.getPrice(),
                productDetails.getStock());

        System.out.println("ALL PRODUCTS");
        productDao
                .findAll()
                .forEach(p -> {
                    System.out.format("CATEGORY ID: %d - NAME: %s - PRICE: %f - STOCK: %d \n",
                            p.getCategory_id(),
                            p.getName(),
                            p.getPrice(),
                            p.getStock());
                });
        var updateProd = new Category()
                .setId(6)
                .setName("Breakfast")
                .setDescription("TEST DESCRIPTION");

        categoryDao.update(updateProd);

        var category = new Category()
                .setName("Soft drinks")
                .setDescription("Local soft drinks.");

//        categoryDao.create(category);

        var categoryDetails = categoryDao.find(2);
        System.out.format("NAME: %s - DESCRIPTION: %s \n",
                categoryDetails.getName(),
                categoryDetails.getDescription());

        System.out.println("ALL CATEGORIES");
        categoryDao
                .findAll()
                .forEach(c -> {
                    System.out.format("NAME: %s - DESCRIPTION: %s \n",
                            c.getName(),
                            c.getDescription());
                });


    }
}
