package com.jfarro.app.services;

import com.jfarro.app.models.domains.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findALlClients();
    Page<Client> findAllPagesClients(Pageable pageable);
    Optional<Client> findByIdClient(Long id);
    Client findByEmailClient(String email);
    void saveClient(Client client);
    void updateStateClient(Byte state, Long id);
}
