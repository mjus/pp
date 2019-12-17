package main.java.DAO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO create() {
        switch (getProperties()) {
            case "JDBC":
                return new UserJDBCDAO();
            case "Hibernate":
                return new UserHibernateDAO();
            default:
                return null;
        }
    }

    private static String getProperties() {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "config.properties";
        Properties appProps = new Properties();
        try (FileReader reader = new FileReader(appConfigPath)){
            appProps.load(reader);
            return appProps.getProperty("db.connect");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}