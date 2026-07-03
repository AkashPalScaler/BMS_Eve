package com.scaler.BMS_Eve.controllers;

import com.scaler.BMS_Eve.DTOs.BookTicketRequestDTO;
import com.scaler.BMS_Eve.DTOs.BookTicketResponseDTO;
import com.scaler.BMS_Eve.models.Booking;
import com.scaler.BMS_Eve.models.BookingStatus;
import com.scaler.BMS_Eve.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    @Autowired // Autowired will inject the bookingService singleton here internally
    BookingService bookingService;

    BookTicketResponseDTO bookTicket(BookTicketRequestDTO requestDTO){
        BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();
        try{
            Booking booking = bookingService.reserveBooking(
                    requestDTO.getUserId(),
                    requestDTO.getShowId(),
                    requestDTO.getShowSeatIds());
            responseDTO.setBookingId(booking.getId());
            responseDTO.setBookingStatus(booking.getStatus());
            responseDTO.setMessage("Seat selection successful, please complete the payment for confirming your booking");
        }catch(Exception e){
            System.out.println("Seat selection failed! - " + e.getMessage());
            responseDTO.setBookingId(null);
            responseDTO.setMessage("Seat selection failed");
            responseDTO.setBookingStatus(BookingStatus.FAILURE);
        }
        return responseDTO;
    }
}
