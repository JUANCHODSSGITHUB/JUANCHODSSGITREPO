package com.dss.dss3msloginv1.repository;

import com.dss.dss3msloginv1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String Password);
    User findByUserIdAndPassword(String LoginId, String Password);

    User findByPhoneNumber(String phoneNumber);
}

