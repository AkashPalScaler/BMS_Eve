
package com.scaler.BMS_Eve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    // Payment M:1 User
    @ManyToOne
    private User user;
    @ManyToOne
    private Booking booking;
    private Integer amount;
    private String txn_id;
    @Enumerated(EnumType.STRING)
    private PaymentMode mode;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}