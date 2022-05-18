package com.niit.repository;

import com.niit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    public User findByUsernameAndPassword(String userName,String password);
    public  User findByUsername(String userName);
}
