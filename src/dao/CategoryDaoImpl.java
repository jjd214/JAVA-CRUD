package dao;

import db.DBConnection;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void create(Category category) {
        String sql = "insert into categories (name,description) values(?,?)";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,category.getName());
            ps.setString(2, category.getDescription());
            ps.executeUpdate();

            System.out.println("Category created.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Category find(int id) {
        String sql = "select * from categories where id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Category found!");
                return mapRow(rs);
            } else {
                System.out.println("Category not found!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Category> findAll() {
        var categories = new ArrayList<Category>();
        String sql = "select * from categories";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                categories.add(mapRow(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public void update(Category category) {
        String sql = """
                update categories
                set name = ?,
                description = ?
                where id = ?
                """;

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());
            ps.executeUpdate();
            System.out.println("Category updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from categories where id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Category deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Category mapRow(ResultSet rs) throws Exception {
        Category category = new Category();
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));
        return category;
    }
}
