package com.scaler.BMS_Eve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;
    // Screen M:1 Theatre
    @ManyToOne
    private Theatre theatre;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> features;
    // Screen 1:M Seat
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;
    @Enumerated(EnumType.STRING)
    private ScreenStatus status;
}