package ru.sh.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sh.entity.Client;

import java.util.List;

@Component
public class ClientHandler {

    @Autowired
    private RestTemplate restTemplate;

    private final static String URL = "http://localhost:8080/api/client";

    public List<Client> getAllClient() {
        ResponseEntity<List<Client>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>() {
                });

        List<Client> clientList = response.getBody();
        return clientList;
    }

    public Client getClient(int id) {
        return restTemplate.getForObject(String.format("%s/%d", URL, id), Client.class);
    }

    public void saveClient(Client client) {
        int id = client.getId();
        if (id == 0) {
            ResponseEntity<String> response = restTemplate.postForEntity(URL, client, String.class);
            System.out.println("New client is create successfully!");
            System.out.println(response.getBody());
        } else {
            restTemplate.put(URL, client);
            System.out.printf("Client %s updated success.\n", client);
        }
    }

    public void deleteClient(int id) {
        restTemplate.delete(String.format("%s/%d", URL, id));
    }
}
