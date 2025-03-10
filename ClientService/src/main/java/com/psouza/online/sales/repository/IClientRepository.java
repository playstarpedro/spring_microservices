package com.psouza.online.sales.repository;

import java.util.Optional;

import com.psouza.online.sales.domain.Client;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface IClientRepository extends MongoRepository<Client, String>{

    Optional<Client> findByCpf(String cpf);
}
