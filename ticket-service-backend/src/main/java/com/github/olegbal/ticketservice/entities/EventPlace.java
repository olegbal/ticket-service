package com.github.olegbal.ticketservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "event_place_address")
    private String placeAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "eventPlace")
    private List<Event> eventList;
}
