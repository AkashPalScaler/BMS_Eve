package com.scaler.BMS_Eve.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookTicketRequestDTO {
    private Long userId;
    private Long showId;
    private List<Long> showSeatIds;
}
