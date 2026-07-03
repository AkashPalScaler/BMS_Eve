package com.scaler.BMS_Eve.repositories;

import com.scaler.BMS_Eve.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
