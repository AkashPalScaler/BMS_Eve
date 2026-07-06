package com.scaler.BMS_Eve.repositories;

import com.scaler.BMS_Eve.models.SeatType;
import com.scaler.BMS_Eve.models.Show;
import com.scaler.BMS_Eve.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    ShowSeatType findShowSeatTypeByShowAndSeatType(Show show, SeatType seatType);
}
