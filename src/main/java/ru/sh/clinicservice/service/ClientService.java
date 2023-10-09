package ru.sh.clinicservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sh.clinicservice.models.Client;
import ru.sh.clinicservice.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Integer clientId) {
        Optional<Client> optional = clientRepository.findById(clientId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("Incorrect client ID");
    }

    @Transactional
    public void addClient(Client client) {
        if (!clientRepository.findAll().contains(client)) {
            clientRepository.save(client);
        }
    }

    @Transactional
    public void editClient(Client client) {
        Optional<Client> optional = clientRepository.findById(client.getId());
        if (optional.isPresent()) {

        }
    }

    @Transactional
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }
}
