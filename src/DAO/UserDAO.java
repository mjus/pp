package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement("select * from users");
            ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setSoName(result.getString(3));
                user.setEmail(result.getString(4));
                list.add(user);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addUser(String name, String soName, String email) {
        try(Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("insert into users (id, name, soName, email) values (null , '"
                    + name + "' , '" + soName + "' , '" + email + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try(Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM users WHERE id='" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClientsMoney(Long id, String name, String soName, String email) throws SQLException {
        try(PreparedStatement stmt
                    = connection.prepareStatement("update users set name='" + name + "', soName='"
                + soName + "', email='" + email + "' where id = '" + id + "'")) {
            stmt.executeUpdate();
        }
    }
}