package com.github.olegbal.ticketservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String id;
    private String title;
    private Date date;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String imgUrl;
    private String address;
}
