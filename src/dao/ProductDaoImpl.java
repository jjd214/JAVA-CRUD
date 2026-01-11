package dao;

import db.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao {
    @Override
    public void create(Product product) {
        String sql = "insert into products (category_id,name,price,in_stock) values(?,?,?,?)";
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product.getCategory_id());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4,product.getStock());
            ps.executeUpdate();
            System.out.println("Student created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product find(int id) {
        String sql = "select * from products where id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Product found!");
                return mapRow(rs);
            } else {
                System.out.println("Product not found!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Product> findAll() {
        var products = new ArrayList<Product>();
        String sql = "select * from products";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                products.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) {

    }

    public Product mapRow(ResultSet rs) throws Exception {
        Product product = new Product();
        product.setCategory_id(rs.getInt("category_id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setStock(rs.getInt("in_stock"));
        return product;
    }
}
