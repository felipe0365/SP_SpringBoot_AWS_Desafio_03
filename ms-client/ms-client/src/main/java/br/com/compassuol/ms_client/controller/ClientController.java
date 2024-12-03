package br.com.compassuol.ms_client.controller;

import br.com.compassuol.ms_client.model.Client;
import br.com.compassuol.ms_client.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Client", description = "Endpoints for Managing Clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @Operation(summary = "Finds all Clients", description = "Finds all Clients", tags = {"Client"},
            responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Client.class))),}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<List<Client>> findAll() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @Operation(summary = "Creates a New Client", description = "Creates a New Client by passing in a JSON representation of a Client", tags = {"Client"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @PostMapping
    public ResponseEntity<Client> save(@RequestBody @Valid Client client) {
        var clientCreated = clientService.createClient(client);
        return new ResponseEntity<>(clientCreated, HttpStatus.CREATED);
    }

    @Operation(summary = "Finds Client By Id", description = "Finds Client By Id", tags = {"Client"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Optional<Client> client = Optional.ofNullable(clientService.getClientById(id));
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Updates a Client", description = "Updates a Client by passing in a JSON representation of a Client", tags = {"Client"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Client.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @PatchMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        var clientInDb = clientService.getClientById(id);
        clientInDb.setId(clientInDb.getId());
        clientInDb.setName(client.getName());
        clientInDb.setEmail(client.getEmail());
        clientService.updateClient(clientInDb);
        return new ResponseEntity<>(clientInDb, HttpStatus.OK);

    }

}
