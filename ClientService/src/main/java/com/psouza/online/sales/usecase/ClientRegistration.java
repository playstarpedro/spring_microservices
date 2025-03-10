package com.psouza.online.sales.usecase;

import com.psouza.online.sales.domain.Client;
import com.psouza.online.sales.exception.EntityNotFoundException;

import jakarta.validation.Valid;

import com.psouza.online.sales.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientRegistration {
	
	private IClientRepository clientRepository;
	
	@Autowired
	public ClientRegistration(IClientRepository clienteRepository) {
		this.clientRepository = clienteRepository;
	}
	
	public Client register(@Valid Client client) {
		return this.clientRepository.insert(client);
	}

	public Client update(Client client) {
		Client existingClient = clientRepository.findByCpf(client.getCpf())
				.orElseThrow(() -> new EntityNotFoundException(Client.class, client));

		existingClient.setName(client.getName());
		existingClient.setTel(client.getTel());
		existingClient.setEmail(client.getEmail());
		existingClient.setAddress(client.getAddress());
		existingClient.setAddressNumber(client.getAddressNumber());
		existingClient.setCity(client.getCity());
		existingClient.setEstate(client.getEstate());

		return clientRepository.save(existingClient);
	}

	public void delete(String id) {
		this.clientRepository.deleteById(id);
	}

}
