package com.psouza.online.sales.resources;

import com.psouza.online.sales.domain.Client;
import com.psouza.online.sales.usecase.ClientRegistration;
import com.psouza.online.sales.usecase.SearchClient;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(value = "/client")
@Tag(name = "Client", description = "Endpoints for managing clients")
public class ClientResource {

	private final SearchClient searchClient;
	private final ClientRegistration clientRegistration;

	@Autowired
	public ClientResource(SearchClient searchClient, ClientRegistration clientRegistration) {
		this.searchClient = searchClient;
		this.clientRegistration = clientRegistration;
	}

	@GetMapping
	@Operation(summary = "Search clients", description = "Retrieve a paginated list of clients")
	@ApiResponse(responseCode = "200", description = "Clients found", content = @Content(schema = @Schema(implementation = Page.class)))
	public ResponseEntity<Page<Client>> search(Pageable pageable) {
		return ResponseEntity.ok(searchClient.search(pageable));
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Client search by id", description = "Retrieve a client by its unique identifier")
	@ApiResponse(responseCode = "200", description = "Client found", content = @Content(schema = @Schema(implementation = Client.class)))
	@ApiResponse(responseCode = "404", description = "Client not found")
	public ResponseEntity<Client> searchById(
			@Parameter(description = "ID of the client to be searched", required = true, example = "507f1f77bcf86cd799439011")
			@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(searchClient.searchById(id));
	}

	@GetMapping(value = "isRegistered/{id}")
	@Operation(summary = "Check if a client is registered", description = "Verify if a client is registered by its ID")
	@ApiResponse(responseCode = "200", description = "Client registration status", content = @Content(schema = @Schema(implementation = Boolean.class)))
	public ResponseEntity<Boolean> isRegistered(
			@Parameter(description = "ID of the client to check", required = true, example = "507f1f77bcf86cd799439011")
			@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(searchClient.isRegistered(id));
	}

	@PostMapping
	@Operation(summary = "Register a new client", description = "Create a new client with the provided details")
	@ApiResponse(responseCode = "200", description = "Client registered successfully", content = @Content(schema = @Schema(implementation = Client.class)))
	@ApiResponse(responseCode = "400", description = "Invalid input")
	public ResponseEntity<Client> register(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Client object to be registered", required = true, content = @Content(schema = @Schema(implementation = Client.class)))
			@RequestBody @Valid Client client) {
		return ResponseEntity.ok(clientRegistration.register(client));
	}

	@GetMapping(value = "/cpf/{cpf}")
	@Operation(summary = "Search client by cpf", description = "Retrieve a client by its CPF")
	@ApiResponse(responseCode = "200", description = "Client found", content = @Content(schema = @Schema(implementation = Client.class)))
	@ApiResponse(responseCode = "404", description = "Client not found")
	public ResponseEntity<Client> searchByCpf(
			@Parameter(description = "CPF of the client to be searched", required = true, example = "12345678900")
			@PathVariable(value = "cpf", required = true) String cpf) {
		return ResponseEntity.ok(searchClient.searchByCpf(cpf));
	}

	@PutMapping
	@Operation(summary = "Update a client", description = "Update an existing client with the provided details")
	@ApiResponse(responseCode = "200", description = "Client updated successfully", content = @Content(schema = @Schema(implementation = Client.class)))
	@ApiResponse(responseCode = "400", description = "Invalid input")
	@ApiResponse(responseCode = "404", description = "Client not found")
	public ResponseEntity<Client> update(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Client object to be updated", required = true, content = @Content(schema = @Schema(implementation = Client.class)))
			@RequestBody @Valid Client client) {
		return ResponseEntity.ok(clientRegistration.update(client));
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove a client by its unique identifier", description = "Delete a client from the system")
	@ApiResponse(responseCode = "200", description = "Client deleted successfully")
	@ApiResponse(responseCode = "404", description = "Client not found")
	public ResponseEntity<String> delete(
			@Parameter(description = "ID of the client to be deleted", required = true, example = "507f1f77bcf86cd799439011")
			@PathVariable(value = "id") String id) {
		clientRegistration.delete(id);
		return ResponseEntity.ok("Successfully deleted");
	}
}