package com.example.microservices.web;

import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.microservices.HedgeApplication;
import com.example.microservices.dao.HedgeRepository;
import com.example.microservices.model.Hedge;
import com.example.microservices.rate.Rate;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
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

    @Autowired
    private HedgeRepository hedgeRepository;

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

    @Test
    @Ignore
    public void generateGedges() {
        insertHedges();
    }

    private void insertHedges() {

        Rate rate = Rate.builder()
                .date(LocalDate.now())
                .rate(BigDecimal.valueOf(10))
                .uuid(UUID.randomUUID().toString())
                .build();

        hedgeRepository.insert(Arrays.asList(new Hedge("1", "TestH-1", rate),
                new Hedge("2", "TestH-2", rate),
                new Hedge("3", "TestH-3", rate)));

    }

}