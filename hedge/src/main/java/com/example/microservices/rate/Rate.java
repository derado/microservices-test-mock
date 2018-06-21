package com.example.microservices.rate;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Rate {

    String uuid;
    BigDecimal rate;
    LocalDate date;

}
