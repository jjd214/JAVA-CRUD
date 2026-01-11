package model;

public class Product {
    private int category_id;
    private String category_name;
    private String name;
    private double price;
    private int stock;

    public int getCategory_id() {
        return category_id;
    }

    public Product setCategory_id(int category_id) {
        this.category_id = category_id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public Product setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public String getCategoryName() {
        return category_name;
    }

    public Product setCategoryName(String category_name) {
        this.category_name = category_name;
        return this;
    }
}
