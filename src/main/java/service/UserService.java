package main.java.service;

import main.java.DAO.UserDAO;
import main.java.DAO.UserDaoFactory;
import main.java.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService instance;
    private final UserDAO userDao;

    private UserService() {
        userDao = UserDaoFactory.create();
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(String name, String surname, String email){
        userDao.addUser(name, surname, email);
    }

    public void updateUser(Long id, String name, String surname, String email) {
        userDao.updateUser(id, name, surname, email);
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    public User getUserById(long id) {
        return userDao.getUserByID(id);
    }

    public String getRoleByLoginAndPassword(String login, String password) {
        try {
            return userDao.getRoleByLoginAndPassword(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUserExist(String login, String password) {
        try {
            return userDao.userIsExist(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}