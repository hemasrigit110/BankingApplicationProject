package com.example.account.repository;

import com.example.account.model.Account;
import com.example.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT a FROM User a WHERE a.id = :userId")
    List<User> findByUserId(Long userId);


}
