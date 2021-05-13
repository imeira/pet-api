package com.imeira.pet.api.repository;

import com.imeira.pet.api.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, BigInteger> {

    Optional<Client> findByName(String name);

}
