package com.ivans.bankApp.repository;

import com.ivans.bankApp.entity.userDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<userDetails, Long> {
    userDetails findByUsernameAndPassword(String username, String password);
    userDetails findByUsername(String username);
}