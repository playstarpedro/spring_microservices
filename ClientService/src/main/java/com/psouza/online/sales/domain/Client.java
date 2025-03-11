package com.psouza.online.sales.domain;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

@Document(collection = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Client", description = "Client entity")
public class Client {

    @Id
    @Schema(description = "Unique identifier of the client", hidden = true)
    private String id;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Name of the client", example = "João Silva", minLength = 1, maxLength = 50, nullable = false)
    private String name;

    @NotNull
    @Indexed(unique = true, background = true)
    @Schema(description = "CPF of the client", example = "12345678900", nullable = false)
    private String cpf;

    @NotNull
    @Schema(description = "Telephone number of the client", example = "11987654321", nullable = false)
    private Long tel;

    @NotNull
    @Size(min = 1, max = 50)
    @Indexed(unique = true, background = true)
    @Schema(description = "Email of the client", example = "joao.silva@example.com", minLength = 1, maxLength = 50, nullable = false)
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email")
    private String email;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Address of the client", example = "Rua das Flores, 123", minLength = 1, maxLength = 50, nullable = false)
    private String address;

    @NotNull
    @Schema(description = "Residential number of the client", example = "123", nullable = false)
    private Integer addressNumber;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "City of the client", example = "São Paulo", minLength = 1, maxLength = 50, nullable = false)
    private String city;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Estate of the client", example = "SP", minLength = 1, maxLength = 50, nullable = false)
    private String estate;
}