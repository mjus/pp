package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    public void addUser(String name, String soName, String email) throws SQLException;

    public void delete(Long id);

    public void updateUser(Long id, String name, String soName, String email) throws SQLException;

    public User getUserByID(Long id) throws SQLException;
}