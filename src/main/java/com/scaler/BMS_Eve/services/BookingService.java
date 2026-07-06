package com.scaler.BMS_Eve.services;

import com.scaler.BMS_Eve.models.*;
import com.scaler.BMS_Eve.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    BookingRepository bookingRepository;


    public Booking reserveBooking(Long userid, Long showId, List<Long> showSeatIds) throws IllegalAccessException {
        // TODO
        // Get a user with userID from user repository
        Optional<User> optionalUser = userRepository.findById(userid);
        if(optionalUser.isEmpty()){
            throw new IllegalAccessException("Invalid user");
        }
        User user = optionalUser.get();
        // Get a show with showId from show repository
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new IllegalArgumentException("Invalid show");
        }
        Show show = optionalShow.get();

        List<ShowSeat> validShowSeats = checkAvailabilityAndLock(show, showSeatIds);

        // Calculate the price of the tickets
        Integer amount = 0;
        for(ShowSeat showSeat : validShowSeats){
            SeatType seatType = showSeat.getSeat().getType();
            ShowSeatType showSeatType = showSeatTypeRepository.findShowSeatTypeByShowAndSeatType(show, seatType);
            amount += showSeatType.getPrice();
        }
        // Create the booking with bookingStatus as in-progress
        Booking booking = new Booking();
        booking.setBookingNumber(Booking.ticketNumberGenerator());
        booking.setStatus(BookingStatus.IN_PROGRESS);
        booking.setUser(user);
        booking.setShow(show);
        booking.setPrice(amount);
        booking.setShowSeats(validShowSeats);
        // Return the booking
        return bookingRepository.save(booking);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> checkAvailabilityAndLock(Show show, List<Long> showSeatIds){
        //--------------START A LOCK -----------------------
        // Get all the showSeats with showSeatIds from showSeatRepository
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        // Validation on showSeats - If the showSeats belong to the same show
        // Option 1: throw error if there are showSeats with multiple shows
        // Option 2: Just proceed with the showSeats from the mentioned show
        List<ShowSeat> validShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            if(showSeat.getShow().getId().equals(show.getId())){
                validShowSeats.add(showSeat);
            }
        }
        System.out.println("Valiud show seats :  " + validShowSeats);
        if(validShowSeats.isEmpty() || validShowSeats.size() > 10){
            throw new IllegalArgumentException("Invalid seat selection");
        }
        // Check if all showSeats are available
        // if not,
        //        if booked - throw an error, saying seats are already booked
        //        if locked - then we check lockedAt time,
        //          if (currTime - lockedAT) > 5 then update the locked at time
        //          else throw error
        // If yes, mark then locked and update the showSeats as locked
        for(ShowSeat showSeat : validShowSeats){
            if(showSeat.getStatus().equals(ShowSeatStatus.BOOKED)){
                throw new IllegalArgumentException("Invalid seat selection: seats are already booked!");
            }
            if(showSeat.getStatus().equals(ShowSeatStatus.LOCKED)){
                Long timePassedInMins = Duration.between(
                        new Date().toInstant(),
                        showSeat.getLockedAt().toInstant()
                ).toMinutes();

                if(timePassedInMins < 5){
                    throw new IllegalArgumentException("Invalid seat selection: seats are on hold, please try again later!");
                }
            }
            // AVAILABLE, LOCKED(with lock time greater than 5 mins)
            showSeat.setLockedAt(new Date());
            showSeat.setStatus(ShowSeatStatus.LOCKED);
//            showSeatRepository.save(showSeat); // For each showSeat one query will be made
        }
        showSeatRepository.saveAll(validShowSeats);
        return validShowSeats;
        //--------------RELEASE THE LOCK ----------------------
    }
}

// Application lock (Single server application) (RedisTransactions - can help in application level locks for multi server setup) vs DB lock(Multi server application) -
// Break till 10:05