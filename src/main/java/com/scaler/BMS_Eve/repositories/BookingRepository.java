package com.scaler.BMS_Eve.repositories;

import com.scaler.BMS_Eve.controllers.BookingController;
import com.scaler.BMS_Eve.models.Booking;
import com.scaler.BMS_Eve.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
