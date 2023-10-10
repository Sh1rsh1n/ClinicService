package ru.sh.clinicservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sh.clinicservice.models.Client;
import ru.sh.clinicservice.service.ClientService;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/client")
@Tag(name = "Клиенты", description = "Обработка данных клиентов")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавление данных о клиенте", description = "Позволяет добавить данные о клиенте")
    public HttpStatus addClient(@RequestBody Client client) {
        clientService.saveClient(client);
        return HttpStatus.OK;
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменение данных о клиенте", description = "Позволяет изменять данные о клиенте")
    public HttpStatus editClient(@RequestBody Client client) {
        for (Client client1: clientService.getAllClients()) {
            if (client1.getId() == client.getId()) {
                clientService.saveClient(client);
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение данных о клиенте", description = "Позволяет получить данные о клиенте")
    public ResponseEntity<Client> getClient(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение данных о всех клиентах", description = "Позволяет получить данные о всех клиентах")
    public ResponseEntity<List<Client>> getAllClient() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @DeleteMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Удаление данных о клиенте", description = "Позволяет удалить данные о клиенте")
    public HttpStatus deleteClient(@PathVariable String id) {
        clientService.deleteClient(Integer.parseInt(id));
        return HttpStatus.OK;
    }

}
