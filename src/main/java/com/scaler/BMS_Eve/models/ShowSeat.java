package com.scaler.BMS_Eve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;
import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus status;
    private Date lockedAt;
    @ManyToOne
    private Booking booking;
}
// 9AMShow1 9AMShow1_2A
// 9AMShow1 9AMShow1_1A
// Show 1:M ShowSeat