package main.java.DAO;

import main.java.model.User;
import main.java.util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAO implements UserDAO {

    private Connection connection;

    public UserJDBCDAO() {
        this.connection = DBHelper.getInstance().getConnection();
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement("select * from users");
            ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setSurname(result.getString(3));
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
            stmt.executeUpdate("insert into users (id, name, surname, email) values (null , '"
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

    public void updateUser(Long id, String name, String surname, String email){
        try(PreparedStatement stmt
                    = connection.prepareStatement("update users set name='" + name + "', surname='"
                + surname + "', email='" + email + "' where id = '" + id + "'")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByID(Long id) {
        PreparedStatement pstmt = null;
        User user = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM pp_1 WHERE Id =?");

            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();

            user = new User(resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}