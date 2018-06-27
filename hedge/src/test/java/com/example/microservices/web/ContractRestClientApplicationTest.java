package com.example.microservices.web;

import java.math.BigDecimal;

import com.example.microservices.rate.Rate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractRestClientApplicationTest {

    @Rule
    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
            .downloadStub("com.example.microservices", "rate", "0.0.1-SNAPSHOT", "stubs")
            .withPort(8100)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Test
    public void getRate() {
        // given:
        RestTemplate restTemplate = new RestTemplate();

        // when:
        ResponseEntity<Rate> personResponseEntity = restTemplate.getForEntity("http://localhost:8100/rate", Rate.class);

        // then:
        assertThat(personResponseEntity.getStatusCodeValue(), is(200));
        assertThat(personResponseEntity.getBody().getUuid(), instanceOf(String.class));
        assertThat(personResponseEntity.getBody().getRate(), is(BigDecimal.TEN));

    }

}
