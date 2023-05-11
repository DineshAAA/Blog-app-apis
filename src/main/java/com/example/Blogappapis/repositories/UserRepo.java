package com.example.Blogappapis.repositories;

import com.example.Blogappapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
