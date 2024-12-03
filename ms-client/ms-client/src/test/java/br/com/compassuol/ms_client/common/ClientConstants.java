package br.com.compassuol.ms_client.common;

import br.com.compassuol.ms_client.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientConstants {
    public static final Client CLIENT = new Client("TesteName", "teste@email.com");
    public static final Client INVALID_CLIENT = new Client("", "");

    public static final Client FELIPE = new Client("felipe", "felipe@email.com");
    public static final Client GISELE = new Client("gisele", "gisele@email.com");
    public static final Client MARCELO = new Client("marcelo", "marcelo@email.com");
    public static final List<Client> CLIENTS = new ArrayList<Client>() {
        {
            add(FELIPE);
            add(GISELE);
            add(MARCELO);
        }
    };
}
