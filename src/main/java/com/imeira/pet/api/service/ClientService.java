package com.imeira.pet.api.service;

import com.imeira.pet.api.domain.Client;
import com.imeira.pet.api.dto.ClientDTO;
import com.imeira.pet.api.repository.ClientRepository;
import com.imeira.pet.api.service.exception.ObjectAlreadyExistException;
import com.imeira.pet.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public void deleteAll() {
        clientRepository.deleteAll();
    }

    public ClientDTO findById(BigInteger id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.map(this::fromEntity).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));
    }


    public  List<ClientDTO> findAll() {
        List<Client> all = clientRepository.findAll();
        return fromEntity(all);
    }

    public ClientDTO findByName(String name) {
        Optional<Client> obj = clientRepository.findByName(name);
        return obj.map(this::fromEntity).orElse(null);
    }

    public Client createIfNotFound(Client client) {
        Optional<Client> obj = clientRepository.findByName(client.getName());
        return obj.orElseGet(() -> clientRepository.save(client));
    }

    public ClientDTO create(ClientDTO clientDTO) {
        Optional<Client> obj = clientRepository.findByName(clientDTO.getName());
        if (obj.isPresent()) {
            throw new ObjectAlreadyExistException(String.format("Já existe um Cliente com esse nome!"));
        }
        return fromEntity(clientRepository.save(fromDTO(clientDTO)));
    }

    public ClientDTO update(String id, ClientDTO clientDTO) {
        Optional<Client> obj = clientRepository.findById(new BigInteger(id));
        if (!obj.isPresent()) {
            throw new ObjectNotFoundException(String.format("Cliente não encontrado!"));
        }
        clientDTO.setId(obj.get().getId());
        return fromEntity(clientRepository.save(fromDTO(clientDTO)));
    }

    public void delete(String id) {
        Optional<Client> obj = clientRepository.findById(new BigInteger(id));
        if (!obj.isPresent()) {
            throw new ObjectNotFoundException(String.format("Cliente não encontrado!"));
        }
        clientRepository.delete(obj.get());
    }

    public ClientDTO fromEntity(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .build();
    }

    public Client fromDTO (ClientDTO clientDTO) {
        return Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .build();
    }

    public List<ClientDTO> fromEntity(List<Client> clients) {
        return clients.stream()
                .map(a -> new ClientDTO(a.getId(), a.getName())
        ).collect(Collectors.toList());
    }

    public List<Client> fromDTO (List<ClientDTO> dtos) {
        return dtos.stream().map(
                a -> new Client(a.getId(), a.getName())
        ).collect(Collectors.toList());
    }


}
