package service;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService instance;
    private final UserDAO userDao;

    private UserService() {
        userDao = new UserHibernateDAO();
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

//    private static UserService instance;
//
//    private final Connection connection;
//
//    private UserService() {
//        connection = DBHelper.getMysqlConnection();
//    }
//
//    public static UserService getInstance() {
//        if (instance == null) {
//            synchronized (UserService.class) {
//                if (instance == null) {
//                    instance = new UserService();
//                }
//            }
//        }
//        return instance;
//    }

    public List<User> getAllUsers() {
//        UserDAO userDao = new UserJDBCDAO(connection);
        return userDao.getAllUsers();
    }

    public void addUser(String name, String surname, String email){
//        UserDAO userDao = new UserJDBCDAO(connection);
        try {
            userDao.addUser(name, surname, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Long id, String name, String surname, String email) {
//        UserDAO userDao = new UserJDBCDAO(connection);
        try {
            userDao.updateUser(id, name, surname, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
//        UserDAO userDao = new UserJDBCDAO(connection);
        userDao.delete(id);
    }

    public User getUserById(long id) {
//        UserDAO userDao = new UserJDBCDAO(connection);
        try {
            return userDao.getUserByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}