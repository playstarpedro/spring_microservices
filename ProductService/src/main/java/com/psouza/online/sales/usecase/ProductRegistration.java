package com.psouza.online.sales.usecase;

import com.psouza.online.sales.domain.Product;
import com.psouza.online.sales.exception.EntityNotFoundException;
import com.psouza.online.sales.repository.IProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRegistration {

    private IProductRepository productRepository;

    @Autowired
    public ProductRegistration(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product register(@Valid Product product) {
        return this.productRepository.insert(product);
    }

    public Product update(Product product) {
        Product existingProduct = productRepository.findByCode(product.getCode())
                .orElseThrow(() -> new EntityNotFoundException(Product.class, product));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setValue(product.getValue());

        return productRepository.save(existingProduct);
    }

    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

}
