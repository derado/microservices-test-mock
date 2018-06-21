package com.example.microservices.hedge;

import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.example.microservices.model.Hedge;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HedgeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class HedgeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void findHedges() throws Exception {

        URI uri = ClassLoader.getSystemResource("data/rate.json").toURI();
        String testResponse = new String (Files.readAllBytes(Paths.get(uri)),
                Charset.forName("UTF-8"));

        wireMockRule.stubFor(get(urlPathEqualTo("/rate"))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(testResponse)
                        .withStatus(200)));

        ResponseEntity<Hedge[]> response = restTemplate.getForEntity("/hedges", Hedge[].class);

        List<Hedge> hedges = Arrays.asList(response.getBody());
        assertThat(hedges.size(), is(3));
        assertThat(hedges.get(0).getRate().getRate(), is(BigDecimal.valueOf(20)));

        log.info("Hedges: {}", hedges);

    }

}