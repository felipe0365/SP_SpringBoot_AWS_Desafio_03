package br.com.compassuol.ms_client.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static br.com.compassuol.ms_client.common.ClientConstants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import br.com.compassuol.ms_client.model.Client;
import br.com.compassuol.ms_client.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ClientService clientService;

    @Test
    public void createClient_WithValidData_ReturnsCreated() throws Exception {
        when(clientService.createClient(CLIENT)).thenReturn(CLIENT);

        mockMvc.perform(post("/api/clients").content(objectMapper.writeValueAsString(CLIENT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(CLIENT));

    }

    @Test
    public void createClient_WithInvalidData_ReturnsBadRequest() throws Exception {
        Client emptyClient = new Client("", "");

        mockMvc.perform(post("/api/clients").content(objectMapper.writeValueAsString(emptyClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createClient_WithExistingName_ReturnsConflict() throws Exception {
        when(clientService.createClient(any())).thenThrow(DataIntegrityViolationException.class);

        mockMvc.perform(post("/api/clients").content(objectMapper.writeValueAsString(CLIENT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void getClient_ByExistingId_ReturnsClient() throws Exception {
        when(clientService.getClientById(1L)).thenReturn(CLIENT);

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(CLIENT));
    }

    @Test
    public void getClient_ByUnexistingId_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void listClients_ReturnsAllClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(CLIENTS);

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void listClients_ReturnsNoClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
