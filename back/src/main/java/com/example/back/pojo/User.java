package com.example.back.pojo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(Integer userid, String Username, String email, String password) {
        this.userid = userid;
        this.username = Username;
        this.email = email;
        this.password = password;
    }

    /**
     * 获取
     * @return userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param Username
     */
    public void setUsername(String Username) {
        this.username = Username;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{userid = " + userid + ", Username = " + username + ", email = " + email + ", password = " + password + "}";
    }
}
