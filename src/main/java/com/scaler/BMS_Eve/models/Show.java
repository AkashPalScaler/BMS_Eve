package com.scaler.BMS_Eve.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel {
    // Show M:1 Theatre
    @ManyToOne
    private Theatre theatre;
    // Show M:1 Screen
    @ManyToOne
    private Screen screen;
    private Date startTime;
    private Date endTime;
    //Show M:1 Movie
    @ManyToOne
    private Movie movie;
    // SHow 1:M showseat
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeatList;
    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypes;
    private List<Feature> features;

}
