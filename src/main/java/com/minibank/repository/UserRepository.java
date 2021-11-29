package com.minibank.repository;

import com.minibank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, String> {

    @Transactional
    @Modifying
    @Query(value="UPDATE User SET password = :new_password WHERE username = :username")
    public void updatePasswordByUsername(@Param("new_password") String new_password, @Param("username") String username);

    public User findUserByUsername(String username);
}
