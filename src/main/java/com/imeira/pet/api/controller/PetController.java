package com.imeira.pet.api.controller;


import com.imeira.pet.api.dto.PetDTO;
import com.imeira.pet.api.dto.PetDTO;
import com.imeira.pet.api.service.PetService;
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
@RequestMapping("/pets")
public class PetController {

    @Autowired
    PetService petService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetDTO> create(@RequestBody PetDTO petDTO) {
        petDTO = petService.create(petDTO);
        return ResponseEntity.ok().body(petDTO);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<PetDTO>> findAll() {
        List<PetDTO> all = petService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable String id) {
        PetDTO dto = petService.findById(new BigInteger(id));
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> update(@PathVariable String id, @RequestBody PetDTO clientDTO) {
        PetDTO dto = petService.update(id, clientDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        petService.delete(id);;
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
