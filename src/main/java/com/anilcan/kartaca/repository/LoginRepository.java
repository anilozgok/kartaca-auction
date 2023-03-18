package com.anilcan.kartaca.repository;

import com.anilcan.kartaca.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
