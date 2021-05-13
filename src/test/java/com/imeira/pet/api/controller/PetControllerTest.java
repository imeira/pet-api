package com.imeira.pet.api.controller;

import com.imeira.pet.api.dto.PetDTO;
import com.imeira.pet.api.service.PetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PetControllerTest  {
    @Mock
    PetService petService;
    @InjectMocks
    PetController petController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    private PetDTO getDto() {
        return new PetDTO(BigInteger.valueOf(1), "dog", BigInteger.valueOf(1));
    }


    @Test
    void testCreate() {
        when(petService.create(any())).thenReturn(getDto());

        ResponseEntity<PetDTO> result = petController.create(getDto());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    @Test
    void testFindAll() {
        when(petService.findAll()).thenReturn(Arrays.<PetDTO>asList(getDto()));

        ResponseEntity<List<PetDTO>> result = petController.findAll();
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }
}

