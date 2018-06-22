package com.example.microservices.dao;

import com.example.microservices.model.Hedge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HedgeRepository extends MongoRepository<Hedge, String> {




}
