package br.com.compassuol.ms_client.service;

import br.com.compassuol.ms_client.exception.ClientNotFoundException;
import br.com.compassuol.ms_client.model.Client;
import br.com.compassuol.ms_client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client createClient(Client clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setEmail(clientRequest.getEmail());
        return clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client with id: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client updateClient(Client clientRequest) {
        return clientRepository.save(clientRequest);
    }
}
