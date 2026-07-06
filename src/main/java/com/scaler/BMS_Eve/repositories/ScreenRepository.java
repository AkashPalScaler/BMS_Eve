package com.scaler.BMS_Eve.repositories;

import com.scaler.BMS_Eve.models.Screen;
import com.scaler.BMS_Eve.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
