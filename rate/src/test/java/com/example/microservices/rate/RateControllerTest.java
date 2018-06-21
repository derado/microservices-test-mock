package com.example.microservices.rate;

import com.example.microservices.rate.model.Rate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RateApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RateControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findRate() throws Exception {

        ResponseEntity<Rate> rateResponseEntity = restTemplate.getForEntity("/rate", Rate.class);

        Rate rate = rateResponseEntity.getBody();
        assertNotNull(rate);

        log.info("Rate response: {}", rate);

    }

}