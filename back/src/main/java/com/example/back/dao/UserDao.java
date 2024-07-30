package com.example.back.dao;

import com.example.back.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);


}
