package com.example.microservices.rate;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rate {

    String uuid;
    BigDecimal rate;
    LocalDate date;

}
