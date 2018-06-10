package com.github.olegbal.ticketservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
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

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "event_date")
    private DateTime data;

    @Column(name = "event_img_url")
    private String imgUrl;

    @Column(name = "event_approved")
    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "event_place_id")
    private EventPlace eventPlace;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
