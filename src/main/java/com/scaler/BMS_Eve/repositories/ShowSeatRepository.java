package com.scaler.BMS_Eve.repositories;

import com.scaler.BMS_Eve.models.ShowSeat;
import com.scaler.BMS_Eve.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
}
