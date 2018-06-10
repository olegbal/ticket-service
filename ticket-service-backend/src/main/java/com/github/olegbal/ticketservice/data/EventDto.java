package com.github.olegbal.ticketservice.data;

import com.github.olegbal.ticketservice.entities.EventPlace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private long id;
    private String title;
    private DateTime date;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String imgUrl;
    private boolean approved;
    private EventPlace eventPlace;
}
