package com.scaler.BMS_Eve.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity(name = "bms_user") // Table name
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    // User  1:M Booking
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
}


// bms_user - id, created, updated, name, email, password, [b1,b2,b3]

// bms_user_bookings (Mapping table)
// uid1 b1
// uid1 b2
// uid1 b3

// booking - id, created, updated, bookNo, price, user_id