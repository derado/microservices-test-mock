package com.example.microservices.hedge;

import java.util.Arrays;
import java.util.List;

import com.example.microservices.model.Hedge;
import com.example.microservices.rate.Rate;
import com.example.microservices.rate.RateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HedgeController {

    @Autowired
    private RateClient rateClient;

    @GetMapping("/hedges")
    public List<Hedge> findHedges() {

        Rate rate = rateClient.findRate();

        return Arrays.asList(new Hedge("1", "H-1", rate),
                new Hedge("2", "H-2", rate),
                new Hedge("3", "H-3", rate));

    }

}
