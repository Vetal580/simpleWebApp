package com.springapp.dao;

import com.springapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired DataSource dataSource;
    @Autowired BCryptPasswordEncoder encoder;

    @Override
    public boolean registerUser(User user) {
        String phone = user.getPhone().replaceAll("[-+)(]","");
        String userRegistration = "INSERT INTO users (username, email, password, phone) VALUES (?, ?, ?, ?)";
        int usernameValid = usernameValidation(user.getUsername());

        //Если имя уже занято или запись в БД не удалась - возвращаем false, если запись удалась, возвращаем true
        if (usernameValid == 1) return false;
        else {
            try (Connection connection = dataSource.getConnection()) {
                connection.setAutoCommit(false);
                try (PreparedStatement s = connection.prepareStatement(userRegistration)) {
                    s.setString(1, user.getUsername());
                    s.setString(2, user.getEmail());
                    s.setString(3, user.getPassword());
                    s.setString(4, phone);
                    int r = s.executeUpdate();
                    connection.commit();
                    connection.close();
                    return true;

                } catch (SQLException e) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public User findUserById(String id){
        String findUser = "SELECT * FROM users WHERE id=?";
        User user = new User();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(findUser);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setRole(resultSet.getString("role"));
                user.setId(resultSet.getInt("id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers(){
        String getUsers = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getUsers);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(4));
                user.setEmail(resultSet.getString(3));
                user.setPhone(resultSet.getString(5));
                user.setRole(resultSet.getString(6));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void updateUser(User user){
        String updateUser = "UPDATE users SET username='"+user.getUsername()+"', email='"+user.getEmail()+"', phone='"+user.getPhone()+"', role='"+user.getRole()+"' WHERE id='"+user.getId()+"'";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateUser);
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final int usernameValidation(String username) {
        String userValidate = "SELECT EXISTS(SELECT username FROM users WHERE username=?)";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(userValidate)) {
                //Возвращаем 0 если не занято, 1 если занято
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    final int count = resultSet.getInt(1);
                    if (count == 0) return 0;
                    else return 1;
                }
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void deleteUser(String id){
        String deleteUser = "DELETE FROM users WHERE id='"+id+"'";

        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
