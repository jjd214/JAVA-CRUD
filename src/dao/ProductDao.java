package dao;

import model.Product;
import java.util.ArrayList;

public interface ProductDao {
    void create(Product product);
    Product find(int id);
    ArrayList<Product> findAll();
    void update(Product product);
    void delete(int id);
}
