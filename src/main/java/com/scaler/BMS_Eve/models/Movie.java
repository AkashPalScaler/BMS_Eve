package com.scaler.BMS_Eve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.security.auth.callback.LanguageCallback;
import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String name;
    private Integer durationInMinutes;
    @Enumerated(EnumType.STRING)
    @ElementCollection // primitive type list
    private List<Language> languages;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> features;
    @ElementCollection
    private List<String> actors;
    private String genre;
}