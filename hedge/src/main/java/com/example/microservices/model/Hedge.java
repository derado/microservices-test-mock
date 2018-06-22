package com.example.microservices.model;

import com.example.microservices.rate.Rate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hedge {

    @Id
    @JsonProperty("uuid")
    private String id;

    private String nickName;
    private Rate rate;

}
