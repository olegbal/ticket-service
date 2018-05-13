package com.github.olegbal.ticketservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ticket_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_type_id")
    private long id;

    @Column(name = "ticket_type_description")
    private String typeDescription;

    @Column(name = "ticket_description")
    private String ticketDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "ticketType", fetch = FetchType.EAGER)
    private List<Ticket> ticketList;

}
