package com.psouza.online.sales.resources;

import com.psouza.online.sales.domain.Product;
import com.psouza.online.sales.usecase.ProductRegistration;
import com.psouza.online.sales.usecase.SearchProduct;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/product")
@Tag(name = "Product", description = "Endpoints for managing products")
public class ProductResource {
    private final SearchProduct searchProduct;
    private final ProductRegistration productRegistration;

    @Autowired
    public ProductResource(SearchProduct searchProduct, ProductRegistration productRegistration) {
        this.searchProduct = searchProduct;
        this.productRegistration = productRegistration;
    }

    @GetMapping
    @Operation(summary = "Search products", description = "Retrieve a paginated list of products")
    @ApiResponse(responseCode = "200", description = "Products found", content = @Content(schema = @Schema(implementation = Page.class)))
    public ResponseEntity<Page<Product>> search(Pageable pageable) {
        return ResponseEntity.ok(searchProduct.search(pageable));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Product search by id", description = "Retrieve a product by its unique identifier")
    @ApiResponse(responseCode = "200", description = "Product found", content = @Content(schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> searchById(
            @Parameter(description = "ID of the product to be searched", required = true, example = "507f1f77bcf86cd799439011")
            @PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(searchProduct.searchById(id));
    }

    @GetMapping(value = "isRegistered/{id}")
    @Operation(summary = "Check if a product is registered", description = "Verify if a product is registered by its ID")
    @ApiResponse(responseCode = "200", description = "Product registration status", content = @Content(schema = @Schema(implementation = Boolean.class)))
    public ResponseEntity<Boolean> isRegistered(
            @Parameter(description = "ID of the product to check", required = true, example = "507f1f77bcf86cd799439011")
            @PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(searchProduct.isRegistered(id));
    }

    @PostMapping
    @Operation(summary = "Register a new product", description = "Create a new product with the provided details")
    @ApiResponse(responseCode = "200", description = "Product registered successfully", content = @Content(schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<Product> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product object to be registered", required = true, content = @Content(schema = @Schema(implementation = Product.class)))
            @RequestBody @Valid Product product) {
        return ResponseEntity.ok(productRegistration.register(product));
    }

    @GetMapping(value = "/code/{code}")
    @Operation(summary = "Search product by code", description = "Retrieve a product by its code")
    @ApiResponse(responseCode = "200", description = "Product found", content = @Content(schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> searchByCode(
            @Parameter(description = "Code of the product to be searched", required = true, example = "12345678900")
            @PathVariable(value = "code", required = true) String code) {
        return ResponseEntity.ok(searchProduct.searchByCode(code));
    }

    @PutMapping
    @Operation(summary = "Update a product", description = "Update an existing product with the provided details")
    @ApiResponse(responseCode = "200", description = "Product updated successfully", content = @Content(schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product object to be updated", required = true, content = @Content(schema = @Schema(implementation = Product.class)))
            @RequestBody @Valid Product product) {
        return ResponseEntity.ok(productRegistration.update(product));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove a product by its unique identifier", description = "Delete a product from the system")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID of the product to be deleted", required = true, example = "507f1f77bcf86cd799439011")
            @PathVariable(value = "id") String id) {
        productRegistration.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
