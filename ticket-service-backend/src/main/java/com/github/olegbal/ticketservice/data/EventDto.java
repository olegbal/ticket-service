package com.github.olegbal.ticketservice.data;

import com.github.olegbal.ticketservice.entities.EventPlace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private long id;
    private String title;
    private Date date;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String imgUrl;
    private EventPlace eventPlace;
}
