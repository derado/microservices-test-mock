package com.example.microservices.rate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.microservices.rate.model.Rate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {

    @GetMapping("/rate")
    public Rate findRate() {

        return new Rate(UUID.randomUUID().toString(), BigDecimal.TEN, LocalDate.now());

    }

}
