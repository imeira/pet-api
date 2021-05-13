package com.imeira.pet.api.controller;


import com.imeira.pet.api.dto.ClientDTO;
import com.imeira.pet.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
        clientDTO = clientService.create(clientDTO);
        return ResponseEntity.ok().body(clientDTO);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> all = clientService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id) {
        ClientDTO dto = clientService.findById(new BigInteger(id));
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable String id, @RequestBody ClientDTO clientDTO) {
        ClientDTO dto = clientService.update(id, clientDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        clientService.delete(id);;
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
