package com.imeira.pet.api.repository;

import com.imeira.pet.api.domain.Client;
import com.imeira.pet.api.domain.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends MongoRepository<Pet, BigInteger> {

    Optional<List<Pet>> findByClient(Client client);

}
