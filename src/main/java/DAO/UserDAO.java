package main.java.DAO;

import main.java.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    public void addUser(String name, String soName, String email);

    public void delete(Long id);

    public void updateUser(Long id, String name, String soName, String email);

    public User getUserByID(Long id);
}