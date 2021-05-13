package com.imeira.pet.api.controller;

import com.imeira.pet.api.dto.ClientDTO;
import com.imeira.pet.api.service.ClientService;
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

import static org.mockito.Mockito.*;

class ClientControllerTest {
    @Mock
    ClientService clientService;
    @InjectMocks
    ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate() {
        when(clientService.create(any())).thenReturn(new ClientDTO(BigInteger.valueOf(1), "Test"));

        ResponseEntity<ClientDTO> result = clientController.create(new ClientDTO(BigInteger.valueOf(1), "Test"));
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    @Test
    void testFindAll() {
        when(clientService.findAll()).thenReturn(Arrays.<ClientDTO>asList(new ClientDTO(BigInteger.valueOf(1), "Test")));

        ResponseEntity<List<ClientDTO>> result = clientController.findAll();
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }
}

