package dao;

import model.Category;
import java.util.ArrayList;

public interface CategoryDao {
    void create(Category category);
    Category find(int id);
    ArrayList<Category> findAll();
    void update(Category category);
    void delete(int id);
}
