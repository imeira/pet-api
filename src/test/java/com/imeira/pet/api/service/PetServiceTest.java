package com.imeira.pet.api.service;

import com.imeira.pet.api.domain.Client;
import com.imeira.pet.api.domain.Pet;
import com.imeira.pet.api.dto.ClientDTO;
import com.imeira.pet.api.dto.PetDTO;
import com.imeira.pet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PetServiceTest {
    @Mock
    PetRepository petRepository;
    @InjectMocks
    PetService petService;

    @Mock
    ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteAll() {
        petService.deleteAll();
    }

    private PetDTO getDto() {
        return new PetDTO(BigInteger.valueOf(1), "dog", BigInteger.valueOf(1));
    }

    private Pet getEntity() {
        return new Pet(BigInteger.valueOf(1), "dog", new Client(BigInteger.valueOf(1), "name"));
    }

    @Test
    void testFindById() {
        when(petRepository.findById(any())).thenReturn(Optional.of(getEntity()));

        PetDTO result = petService.findById(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<PetDTO> result = petService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateIfNotFound() {
        Pet result = petService.createIfNotFound(getEntity());
        Assertions.assertNull(result);
    }

    @Test
    void testCreate() {
        when(clientService.findById(any())).thenReturn(new ClientDTO(BigInteger.valueOf(1), "Name"));
        when(petRepository.save(any())).thenReturn(getEntity());


        PetDTO result = petService.create(getDto());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity() {
        PetDTO result = petService.fromEntity(getEntity());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO() {
        Pet result = petService.fromDTO(getDto());
        Assertions.assertNotNull(result);
    }

}
