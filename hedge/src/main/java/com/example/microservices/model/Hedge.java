package com.example.microservices.model;

import com.example.microservices.rate.Rate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hedge {

    private String uuid;
    private String nickName;
    private Rate rate;

}
