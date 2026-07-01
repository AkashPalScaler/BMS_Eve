
package com.scaler.BMS_Eve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel {
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private Integer price;
}