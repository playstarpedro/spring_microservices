package com.psouza.online.sales.usecase;

import java.util.Optional;

import com.psouza.online.sales.domain.Client;
import com.psouza.online.sales.exception.EntityNotFoundException;
import com.psouza.online.sales.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchClient {

    private IClientRepository clientRepository;

    @Autowired
    public SearchClient(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> search(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client searchById(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Client.class, "id", id));
    }

    public Boolean isRegistered(String id) {
        Optional<Client> cliente = clientRepository.findById(id);
        return cliente.isPresent() ? true : false;
    }

    public Client searchByCpf(String cpf) {
        return clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException(Client.class, "cpf", String.valueOf(cpf)));
    }


}
