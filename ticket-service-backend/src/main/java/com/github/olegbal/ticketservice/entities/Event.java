package com.github.olegbal.ticketservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;

    @Column(name = "event_title")
    private String title;

    @Column(name = "event_date")
    private Date data;

    @Column(name = "event_img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "event_place_id")
    private EventPlace eventPlace;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
