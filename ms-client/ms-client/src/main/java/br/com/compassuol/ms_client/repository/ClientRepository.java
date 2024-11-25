package br.com.compassuol.ms_client.repository;

import br.com.compassuol.ms_client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
