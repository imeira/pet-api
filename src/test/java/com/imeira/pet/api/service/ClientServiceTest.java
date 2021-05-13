package com.imeira.pet.api.service;

import com.imeira.pet.api.domain.Client;
import com.imeira.pet.api.dto.ClientDTO;
import com.imeira.pet.api.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ClientServiceTest {
    @Mock
    ClientRepository clientRepository;
    @InjectMocks
    ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteAll() {
        clientService.deleteAll();
    }

    @Test
    void testFindById() {
        when(clientRepository.findById(any())).thenReturn(Optional.of(new Client()));

        ClientDTO result = clientService.findById(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<ClientDTO> result = clientService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindByDescription() {
        when(clientRepository.findByName(anyString())).thenReturn(Optional.of(new Client()));

        ClientDTO result = clientService.findByName("Test");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateIfNotFound() {
        when(clientRepository.findByName(anyString())).thenReturn(Optional.of(new Client()));

        Client result = clientService.createIfNotFound(new Client(BigInteger.valueOf(1), "Test"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreate() {
        when(clientRepository.save(any())).thenReturn(new Client(BigInteger.valueOf(1), "Test"));

        ClientDTO result = clientService.create(new ClientDTO(BigInteger.valueOf(1), "Test"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity() {
        ClientDTO result = clientService.fromEntity(new Client(BigInteger.valueOf(1), "Test"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO() {
        Client result = clientService.fromDTO(new ClientDTO(BigInteger.valueOf(1), "Test"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity2() {
        List<ClientDTO> result = clientService.fromEntity(Arrays.<Client>asList(new Client(BigInteger.valueOf(1), "Test")));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO2() {
        List<Client> result = clientService.fromDTO(Arrays.<ClientDTO>asList(new ClientDTO(BigInteger.valueOf(1), "Test")));
        Assertions.assertNotNull(result);
    }
}

