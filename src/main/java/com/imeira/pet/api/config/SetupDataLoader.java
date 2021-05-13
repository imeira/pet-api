package com.imeira.pet.api.config;

import com.imeira.pet.api.domain.Client;
import com.imeira.pet.api.domain.Pet;
import com.imeira.pet.api.service.ClientService;
import com.imeira.pet.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.math.BigInteger;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    ClientService clientService;

    @Autowired
    PetService petService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        cleanTables();
        insertTables();

    }

    private void cleanTables() {
        clientService.deleteAll();
        petService.deleteAll();
    }

    private void insertTables() {

        //create clients
        Client clientA = new Client(BigInteger.valueOf(1), "Nome Cliente 1");
        clientService.createIfNotFound(clientA);
        Client clientB = new Client(BigInteger.valueOf(2), "Nome Cliente 2");
        clientService.createIfNotFound(clientB);
        Client clientC = new Client(BigInteger.valueOf(3), "Nome Cliente 3");
        clientService.createIfNotFound(clientC);
        Client clientD = new Client(BigInteger.valueOf(4), "Nome Cliente 4");
        clientService.createIfNotFound(clientD);

        //create pets
        petService.createIfNotFound(Pet.builder()
                            .id(BigInteger.valueOf(1))
                            .name("dog")
                            .client(clientA).build());
        petService.createIfNotFound((Pet.builder()
                .id(BigInteger.valueOf(2))
                .client(clientD)
                .name("cat")
                .client(clientB).build()));
    }


}
