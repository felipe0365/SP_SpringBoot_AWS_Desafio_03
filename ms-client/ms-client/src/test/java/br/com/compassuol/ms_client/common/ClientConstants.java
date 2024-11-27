package br.com.compassuol.ms_client.common;

import br.com.compassuol.ms_client.model.Client;

public class ClientConstants {
    public static final Client CLIENT = new Client("TesteName", "teste@email.com");
    public static final Client INVALID_CLIENT = new Client("", "");
}
