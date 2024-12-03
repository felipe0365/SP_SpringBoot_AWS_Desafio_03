package br.com.compassuol.ms_client.service;

import br.com.compassuol.ms_client.exception.ClientNotFoundException;
import br.com.compassuol.ms_client.model.Client;
import br.com.compassuol.ms_client.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.compassuol.ms_client.common.ClientConstants.CLIENT;
import static br.com.compassuol.ms_client.common.ClientConstants.INVALID_CLIENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@SpringBootTest(classes = ClientService.class)
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    //@Autowired
    @InjectMocks
    private ClientService clientService;
    //@MockitoBean
    @Mock
    private ClientRepository clientRepository;

    @Test
    public void createClient_WithValidData_ReturnsClient() {
        when(clientRepository.save(CLIENT)).thenReturn(CLIENT);
        // system under test
        Client sut = clientService.createClient(CLIENT);

        assertEquals(sut, CLIENT);
    }

    @Test
    public void createClient_WithInvalidData_ThrowsException() {
        when(clientRepository.save(INVALID_CLIENT)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> clientService.createClient(INVALID_CLIENT)) ;
    }

    @Test
    public void findClient_ByExistingId_ReturnsClient() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(CLIENT));

        Optional<Client> sut = Optional.ofNullable(clientService.getClientById(1L));

        assertEquals(sut.get(), CLIENT);
        assertNotNull(sut.get());
    }

    @Test
    public void findClient_ByInvalidId_ThrowsException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.getClientById(1L));
    }

    @Test
    public void getAllClients_ReturnsAllClients() {
        List<Client> clients = new ArrayList<>() {
            {
                add(CLIENT);
            }
        };
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> sut = clientService.getAllClients();

        assertEquals(sut.size(), clients.size());
        assertNotNull(sut.get(0));
    }

    @Test
    public void getAllClients_returnsNoClients() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        List<Client> sut = clientService.getAllClients();

        assertEquals(sut.size(), 0);
    }
}
