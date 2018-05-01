package com.github.olegbal.ticketservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ticket_types")
@Data
public class TicketType {

    @Id
    @Column(name = "ticket_type_id")
    private long id;

    @Column(name = "ticket_type_description")
    private String typeDescription;

    @Column(name = "ticket_description")
    private String ticketDescription;

    @OneToMany(mappedBy = "ticketType")
    private List<Ticket> ticket;

}
