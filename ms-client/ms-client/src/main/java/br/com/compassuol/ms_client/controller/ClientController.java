package br.com.compassuol.ms_client.controller;

import br.com.compassuol.ms_client.model.Client;
import br.com.compassuol.ms_client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        var clientCreated = clientService.createClient(client);
        return new ResponseEntity<>(clientCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        var client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
