package com.scaler.BMS_Eve.repositories;

import com.scaler.BMS_Eve.models.City;
import com.scaler.BMS_Eve.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
