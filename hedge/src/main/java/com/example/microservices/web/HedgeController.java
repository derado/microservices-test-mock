package com.example.microservices.web;

import com.example.microservices.dao.HedgeRepository;
import com.example.microservices.model.Hedge;
import com.example.microservices.rate.Rate;
import com.example.microservices.rate.RateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HedgeController {

    private final RateClient rateClient;

    private final HedgeRepository hedgeRepository;

    @Autowired
    public HedgeController(RateClient rateClient, HedgeRepository hedgeRepository) {
        this.rateClient = rateClient;
        this.hedgeRepository = hedgeRepository;
    }

    @GetMapping("/hedges")
    public List<Hedge> findHedges() {

        return hedgeRepository.findAll();

    }

    @PostMapping("hedges")
    public Hedge save(Hedge hedge) {

        Rate rate = rateClient.findRate();
        hedge.setRate(rate);

        return hedgeRepository.insert(hedge);

    }

}
