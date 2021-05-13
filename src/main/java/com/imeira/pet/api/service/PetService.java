package com.imeira.pet.api.service;

import com.imeira.pet.api.domain.Client;
import com.imeira.pet.api.domain.Pet;
import com.imeira.pet.api.dto.ClientDTO;
import com.imeira.pet.api.dto.PetDTO;
import com.imeira.pet.api.repository.PetRepository;
import com.imeira.pet.api.service.exception.InvalidTransactionException;
import com.imeira.pet.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    ClientService clientService;


    public void deleteAll() {
        petRepository.deleteAll();
    }

    public PetDTO findById(BigInteger id) {
        Optional<Pet> obj = petRepository.findById(id);
        return obj.map(this::fromEntity).orElseThrow(() -> new ObjectNotFoundException("Pet não encontrada!"));
    }

    public List<PetDTO> find(Client client) {
        Optional<List<Pet>> all = petRepository.findByClient(client);
        return fromEntity(all.orElseThrow(() -> new ObjectNotFoundException("Pet não encontrada por cliente!")));
    }


    public  List<PetDTO> findAll() {
        List<Pet> all = petRepository.findAll();
        return fromEntity(all);
    }

    public Pet createIfNotFound(Pet pet) {
        Optional<Pet> obj = petRepository.findById(pet.getId());
        return obj.orElseGet(() -> petRepository.save(pet));
    }


    public PetDTO create(PetDTO petDTO) {

        try {
            ClientDTO clientDTO = clientService.findById(petDTO.getClientId());
            Objects.requireNonNull(clientDTO, String.format("Cliente inválido!"));

            petDTO = fromEntity(petRepository.save(fromDTO(petDTO)));

        } catch (Exception e) {
            throw new InvalidTransactionException(e.getMessage());
        }
        return petDTO;
    }

    public PetDTO update(String id, PetDTO clientDTO) {
        Optional<Pet> obj = petRepository.findById(new BigInteger(id));
        if (!obj.isPresent()) {
            throw new ObjectNotFoundException(String.format("Pet não encontrado!"));
        }
        clientDTO.setId(obj.get().getId());
        return fromEntity(petRepository.save(fromDTO(clientDTO)));
    }

    public void delete(String id) {
        Optional<Pet> obj = petRepository.findById(new BigInteger(id));
        if (!obj.isPresent()) {
            throw new ObjectNotFoundException(String.format("Pet não encontrado!"));
        }
        petRepository.delete(obj.get());
    }

    public PetDTO fromEntity(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .clientId(pet.getClient().getId())
                .build();
    }


    public Pet fromDTO (PetDTO petDTO) {
        return Pet.builder()
                .id(petDTO.getId())
                .name(petDTO.getName())
                .client(Client.builder().id(petDTO.getClientId()).build())
                .build();
    }

    public List<PetDTO> fromEntity(List<Pet> pets) {
        return pets.stream()
                .map(a -> new PetDTO(a.getId(), a.getName(), a.getClient().getId())
        ).collect(Collectors.toList());
    }

    public List<Pet> fromDTO (List<PetDTO> dtos) {
        return dtos.stream().map(
                a -> new Pet(a.getId(), a.getName(), Client.builder().id(a.getClientId()).build())
        ).collect(Collectors.toList());
    }

}
