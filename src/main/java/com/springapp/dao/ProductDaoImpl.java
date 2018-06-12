package com.springapp.dao;

import com.springapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired DataSource source;

    @Override
    public List<Product> getProductByCategory(String categoryId) {
        ArrayList<Product> productList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM product WHERE category='"+categoryId+"'";
        try(Connection connection = source.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement ps = connection.prepareStatement(sqlQuery)){
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()){
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setCategory(categoryId);
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getString("price"));
                    product.setImage(resultSet.getString("image"));
                    product.setInStock(resultSet.getInt("inStock"));
                    productList.add(product);
                }
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProductById(String id){
        Product product = new Product();
        String sqlQuery = "SELECT * FROM product WHERE id='"+id+"'";
        try(Connection connection = source.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)){
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    product.setId(resultSet.getInt("id"));
                    product.setCategory(resultSet.getString("category"));
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setShortDescription(resultSet.getString("shortDescription"));
                    product.setImage(resultSet.getString("image"));
                    product.setPrice(resultSet.getString("price"));
                    product.setInStock(resultSet.getInt("inStock"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts(){
        ArrayList<Product> productList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM product";
        try(Connection connection = source.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setCategory(resultSet.getString("category"));
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setShortDescription(resultSet.getString("shortDescription"));
                    product.setImage(resultSet.getString("image"));
                    product.setPrice(resultSet.getString("price"));
                    product.setInStock(resultSet.getInt("inStock"));
                    productList.add(product);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Map<String, String> getInStockList(){
        String getInStockValues = "SELECT * FROM in_stock";
        Map<String, String> inStockList = new LinkedHashMap<>();
        try(Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getInStockValues);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                inStockList.put(resultSet.getString("id"), resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inStockList;
    }

    @Override
    public void updateProduct(Product product){
        String updateProduct = "UPDATE product SET name='"+product.getName()+"', price='"+product.getPrice()+"', category='"+product.getCategory()+"', inStock='"+product.getInStock()+"', description='"+product.getDescription()+"', shortDescription='"+product.getShortDescription()+"' WHERE id='"+product.getId()+"'";
        try(Connection connection = source.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateProduct);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(String id){
        String deleteProduct = "DELETE FROM product WHERE id='"+id+"'";

        try (Connection connection = source.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteProduct);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
