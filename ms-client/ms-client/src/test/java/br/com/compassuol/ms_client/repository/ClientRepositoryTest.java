package br.com.compassuol.ms_client.repository;

import static br.com.compassuol.ms_client.common.ClientConstants.CLIENT;
import static br.com.compassuol.ms_client.common.ClientConstants.INVALID_CLIENT;
import static org.junit.jupiter.api.Assertions.*;

import br.com.compassuol.ms_client.model.Client;
import br.com.compassuol.ms_client.util.QueryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void afterEach() {
        CLIENT.setId(null);
    }

    @Test
    public void createClient_WithValidData_ReturnsClient() {
        Client client = clientRepository.save(CLIENT);

        Client sut = testEntityManager.find(Client.class, client.getId());

        assertNotNull(sut);
        assertEquals(sut.getName(), client.getName());
        assertEquals(sut.getEmail(), client.getEmail());
    }

    @Test
    public void createClient_WithInvalidData_ThrowsException() {
        Client invalidClient = new Client("", "");
        assertThrows(RuntimeException.class, () -> clientRepository.save(invalidClient));
    }

    @Test
    public void createClient_WithExistingName_throwsException() {
        Client client = testEntityManager.persistFlushFind(CLIENT);
        testEntityManager.detach(client);
        client.setId(null);

        assertThrows(RuntimeException.class, () -> clientRepository.save(client));
    }

    @Test
    public void getClient_ByExistingId_ReturnsClient() {
        Client client = testEntityManager.persistFlushFind(CLIENT);

        Optional<Client> clientOptional = clientRepository.findById(client.getId());

        assertNotNull(clientOptional);
        assertEquals(clientOptional.get(), client);
    }

    @Test
    public void getClient_ByUnexistingId_ReturnsClient() {
        Optional<Client> client = clientRepository.findById(1L);

        assertTrue(client.isEmpty());
    }

    @Test
    public void listClientes_ReturnsAllClients() {
        clientRepository.save(new Client("Cliente 1", "client1@example.com"));
        clientRepository.save(new Client("Cliente 2", "client2@example.com"));
        clientRepository.save(new Client("Cliente 3", "client3@example.com"));

        List<Client> response = clientRepository.findAll();

        assertNotNull(response);
        assertEquals(response.size(), 3);
    }

    @Test
    public void listClients_ReturnsNoClients() {
        Example<Client> query = QueryBuilder.makeQuery(new Client());

        List<Client> response = clientRepository.findAll(query);

        assertTrue(response.isEmpty());
    }
}
