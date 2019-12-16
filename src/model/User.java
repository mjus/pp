package model;

import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String name;
    private String soName;
    private String email;

    public User(){}

    public User(String name, String soName, String email) {
        this.name = name;
        this.soName = soName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoName() {
        return soName;
    }

    public void setSoName(String soName) {
        this.soName = soName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}