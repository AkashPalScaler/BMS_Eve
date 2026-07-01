package com.scaler.BMS_Eve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String name;
    private Integer row, col;
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
    @Enumerated(EnumType.STRING)
    private SeatType type;
    @ManyToOne
    private Screen screen;
}