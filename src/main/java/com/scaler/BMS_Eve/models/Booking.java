package com.scaler.BMS_Eve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
// Booking is ticket
@Entity
public class Booking extends BaseModel {
    //Booking M:1 User
    @ManyToOne
    private User user;
    private String bookingNumber;
    private Integer price;
    // Booking M:1 Show
    @ManyToOne
    private Show show;
    //Booking 1:M Payment
    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
