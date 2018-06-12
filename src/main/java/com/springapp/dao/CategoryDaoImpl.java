package com.springapp.dao;

import com.springapp.model.Category;
import com.springapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDaoImpl implements CategoryDao{
    @Autowired DataSource dataSource;

    @Override
    public Map<String, String> categoriesMap(){
        String getCategoriesQuery = "SELECT * FROM categories";
        Map<String, String> categoriesList = new LinkedHashMap<>();
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getCategoriesQuery);

            while (resultSet.next()){
                categoriesList.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoriesList;
    }

    @Override
    public void addNewProduct(Product product){
        String addProduct = "INSERT INTO product (name, category, price, description, shortDescription, image, inStock) VALUES (?,?,?,?,?,?,?)";
        StringBuilder builder = new StringBuilder();
        if (product.getImagesList().get(0) == ""){
            builder.append("");
        }else {
            for (String img : product.getImagesList()) {
                builder.append(img);
                builder.append(",");
            }
        }
        try(Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(addProduct)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getCategory());
                preparedStatement.setString(3, product.getPrice());
                preparedStatement.setString(4, product.getDescription());
                preparedStatement.setString(5, product.getShortDescription());
                preparedStatement.setString(6, builder.toString());
                preparedStatement.setInt(7, product.getInStock());

                preparedStatement.executeUpdate();
                connection.commit();
                connection.close();
            } catch (SQLException ex){
                ex.printStackTrace();
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> getCategoryList(){
        String getCategories = "SELECT * FROM categories";
        List<Category> categoryList = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getCategories);

            while (resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setCategoryName(resultSet.getString(2));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public void addCategory(Category category){
        String addCategory = "INSERT INTO categories (id, name) VALUES(?, ?)";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(addCategory);
            statement.setString(1, category.getCategoryId());
            statement.setString(2, category.getCategoryName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(String id){
        String deleteCategory = "DELETE FROM categories WHERE id='"+id+"'";

        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteCategory);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category getCategoryById(String id){
        String getCategory = "SELECT * FROM categories WHERE id='"+id+"'";
        Category category = new Category();
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getCategory);

            while (resultSet.next()){
                category.setCategoryId(resultSet.getString(1));
                category.setCategoryName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void updateCategory(Category category){
        String updateCategory = "UPDATE categories SET id='"+category.getCategoryId()+"', name='"+category.getCategoryName()+"' WHERE id='"+category.getCategoryId()+"'";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateCategory);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int categoryValidator(String categoryName){
        String getCategory = "SELECT * FROM categories WHERE name='"+categoryName+"'";
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getCategory);

            while (resultSet.next()){
                final int count = resultSet.getInt(1);
                if (count == 0) return 0;
                else return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
