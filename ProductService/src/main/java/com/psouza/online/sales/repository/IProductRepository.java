package com.psouza.online.sales.repository;

import java.util.Optional;

import com.psouza.online.sales.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface IProductRepository extends MongoRepository<Product, String>{

    Optional<Product> findByCode(String code);
}
