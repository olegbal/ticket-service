package com.github.olegbal.ticketservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_places")
public class EventPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_place_id")
    private long id;

    @Column(name = "event_place_name")
    private String placeName;

    @Column(name = "event_street_name")
    private String streetAddress;

    @Column(name = "event_city_name")
    private String city;

    @Column(name = "event_country_name")
    private String country;
}
