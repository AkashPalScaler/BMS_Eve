package com.scaler.BMS_Eve.repositories;

import com.scaler.BMS_Eve.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
}
