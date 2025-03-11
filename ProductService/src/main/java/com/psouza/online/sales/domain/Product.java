package com.psouza.online.sales.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

import lombok.*;

@Document(collection = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Product", description = "Product entity")
public class Product {

    @Id
    @Schema(description = "Unique identifier of the client", hidden = true)
    private String id;

    @NotNull
    @Indexed(unique = true, background = true)
    @Schema(description = "Code of the product", example = "12345678900", nullable = false)
    private String code;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Name of the product", example = "Apple IPhone 16", minLength = 1, maxLength = 50, nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 300)
    @Schema(description = "Description of the product", example = "iPhone 16." +
            "Novo Controle da Câmera, câmera Fusion de 48 MP, cinco cores lindas e o chip A18.", minLength = 1, maxLength = 50, nullable = true)
    private String description;


    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "The value must be greater than 0(zero)")
    @Digits(integer = 10, fraction = 2, message = "The value must have a maximum of 10 integer digits an 2 decimals")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    @Schema(
            description = "Price of the product in R$ (Brazilian Real). Must be greater than zero and have up to 2 decimal places.",
            example = "5500.00",
            nullable = false
    )
    private BigDecimal value;
}
