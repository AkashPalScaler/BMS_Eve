package com.scaler.BMS_Eve.DTOs;

import com.scaler.BMS_Eve.models.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDTO {
    Long bookingId;
    String message;
    BookingStatus bookingStatus;
    Integer amount;
}
