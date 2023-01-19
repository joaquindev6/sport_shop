package com.jfarro.app.services.impl;

import com.jfarro.app.models.domains.Client;
import com.jfarro.app.repositories.ClientRepository;
import com.jfarro.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findALlClients() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAllPagesClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        byte state = 0;
        clientRepository.updateState(state, id);
    }
}
