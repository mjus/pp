package main.java.DAO;

import main.java.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    void addUser(String name, String soName, String email);

    void delete(Long id);

    void updateUser(Long id, String name, String soName, String email);

    User getUserByID(Long id);

    String getRoleByLoginAndPassword(String login, String password) throws SQLException;

    boolean userIsExist(String login, String password) throws SQLException;
}