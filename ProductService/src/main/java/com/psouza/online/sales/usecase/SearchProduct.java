package com.psouza.online.sales.usecase;

import com.psouza.online.sales.domain.Product;
import com.psouza.online.sales.exception.EntityNotFoundException;
import com.psouza.online.sales.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchProduct {
    private IProductRepository productRepository;

    @Autowired
    public SearchProduct(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product searchById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id));
    }

    public Boolean isRegistered(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent() ? true : false;
    }

    public Product searchByCode(String code) {
        return productRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "code", String.valueOf(code)));
    }
}
