package com.example.microservices.rate;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateControllerTest {

    @Autowired
    private RateController rateController;

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(rateController);
    }

    @Test
    public void test() {

    }



}
