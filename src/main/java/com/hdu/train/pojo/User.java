package com.hdu.train.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 15:31 18-1-7
 * @Modified By:
 */
@Entity
@Table(name = "user")
public class User {

    private int userId;
    private String userName;
    private String password;
    private int role;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
