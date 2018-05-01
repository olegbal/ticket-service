package com.github.olegbal.ticketservice.data;

import com.github.olegbal.ticketservice.entities.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreatorDto {
    private TicketType ticketType;
    private int amountOfTickets;
    private BigDecimal price;
}
