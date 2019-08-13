package com.example.pethelper.pet_helper_secure.dao;

import com.example.pethelper.pet_helper_secure.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserDao extends JpaRepository<User,Integer> {
    UserDetails findUserByUsername(String username);
}
