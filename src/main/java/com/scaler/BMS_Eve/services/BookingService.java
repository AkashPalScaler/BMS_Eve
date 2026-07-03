package com.scaler.BMS_Eve.services;

import com.scaler.BMS_Eve.models.Show;
import com.scaler.BMS_Eve.repositories.ShowRepository;
import com.scaler.BMS_Eve.repositories.UserRepository;
import com.scaler.BMS_Eve.models.Booking;
import com.scaler.BMS_Eve.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;
    public Booking reserveBooking(Long userid, Long showId, List<Long> showSeatIds) throws IllegalAccessException {
        // TODO
        // Get a user with userID from user repository
        Optional<User> optionalUser = userRepository.findById(userid);
        if(optionalUser.isEmpty()){
            throw new IllegalAccessException("Invalid user");
        }
        // Get a show with showId from show repository
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new IllegalArgumentException("Invalid show");
        }
        //--------------START A LOCK -----------------------
        // Get all the showSeats with showSeatIds from showSeatRepository
        // Check if all showSeats are available
        // if not,
        //        if booked - throw an error, saying seats are already booked
        //        if locked - then we check lockedAt time,
        //          if (currTime - lockedAT) > 5 then update the locked at time
        //          else throw error
        // If yes, mark then locked and update the showSeats as locked
        //--------------RELEASE THE LOCK ----------------------
        // Create the booking
        // Return the booking
        return null;
    }
}
