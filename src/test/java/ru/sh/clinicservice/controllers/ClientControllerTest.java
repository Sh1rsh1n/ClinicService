package ru.sh.clinicservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.sh.clinicservice.models.Client;
import ru.sh.clinicservice.repository.ClientRepository;
import ru.sh.clinicservice.service.ClientService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController controller;

    private List<Client> list = new ArrayList<>();

    /**
     * начальные данные, загружаются перед вызовом каждого метода
     */
    @BeforeEach
    void setUp() {
        Client client1 = new Client(1, "Ivan", "Ivanov", Timestamp.valueOf("1988-08-07 00:00:00"));
        Client client2 = new Client(2, "Anna", "Ivanova", Timestamp.valueOf("1995-08-07 00:00:00"));
        Client client3 = new Client(3, "Dima", "Ivanov", Timestamp.valueOf("2001-08-07 00:00:00"));

        list.add(client1);
        list.add(client2);
        list.add(client3);
    }

    /**
     * Тестируем получение всех клиентов
     * когда мы вызываем метод clientService.getAllClients() то будет возвращаться список клиентов
     * выполняем проверку, что ответ от сервера вернулся не пустой
     * выполняем проверку, что статус ответа: 200 OK
     */
    @Test
    void getAllClient_Test() {
        when(clientService.getAllClients()).thenReturn(list);

        var responseEntity = controller.getAllClient();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * Тестируем получение клиента по ID
     * когда мы вызываем метод clientService.getClientById(anyInt()) то будет возвращаться какой-то клиент
     * выполняем проверку, что ответ от сервера вернулся не пустой
     * выполняем проверку, что статус ответа: 200 OK
     */
    @Test
    void getClientById_Test() {
        when(clientService.getClientById(anyInt())).thenReturn(new Client(1, "Ivan", "Ivanov", Timestamp.valueOf("1988-08-07 00:00:00")));

        var responseEntity = controller.getClient(anyInt());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    /**
     * Тестируем успешное редактирование клиента
     * для данного теста нужен список сотрудников
     * при вызове метода clientService.saveClient(any(Client.class)), всегда будем возвращать true
     * выполняем проверку, что статус ответа: 200 OK
     */
    @Test
    void editClient_HttpStatusIsOK_Test() {
        when(clientService.getAllClients()).thenReturn(list);
        when(clientService.saveClient(any(Client.class))).thenReturn(true);

        var status = controller.editClient(new Client(1, "Ivan", "Ivanov", Timestamp.valueOf("1988-08-07 00:00:00")));

        assertEquals(HttpStatus.OK, status);

    }

    /**
     * Тестируем редактирование клиента по каким-то причинам неуспешно
     * для данного теста нужен список сотрудников
     * при вызове метода clientService.saveClient(any(Client.class)), всегда будем возвращать false
     * выполняем проверку, что статус ответа: 400 BAD_REQUEST
     */
    @Test
    void editClient_HttpStatusIsBAD_REQUEST_Test() {
        Client client = new Client(2, "Ivan", "Ivanov", Timestamp.valueOf("1988-08-07 00:00:00"));
        when(clientService.getAllClients()).thenReturn(list);
        when(clientService.saveClient(any(Client.class))).thenReturn(false);

        var status = controller.editClient(client);

        assertEquals(HttpStatus.BAD_REQUEST, status);
    }

    /**
     * Тест добавления клиента прошло успешно
     * при вызове метода clientService.saveClient(any(Client.class)), всегда возвращаем true
     * выполняем проверку, что статус ответа: 200 OK
     */
    @Test
    void addClient_HttpStatusOK_Test() {
        when(clientService.saveClient(any(Client.class))).thenReturn(true);

        var status = controller.addClient(new Client(4, "Ivan", "Ivanov", Timestamp.valueOf("1988-08-07 00:00:00")));

        assertEquals(HttpStatus.OK, status);
    }

    /**
     * Тест добавления клиента прошло неуспешно
     * при вызове метода clientService.saveClient(any(Client.class)), всегда возвращаем false
     * выполняем проверку, что статус ответа: 400 BAD_REQUEST
     */
    @Test
    void addClient_HttpStatusBAD_REQUEST_Test() {
        when(clientService.saveClient(any(Client.class))).thenReturn(false);

        var status = controller.addClient(new Client(4, "Ivan", "Ivanov", Timestamp.valueOf("1988-08-07 00:00:00")));

        assertEquals(HttpStatus.BAD_REQUEST, status);
    }
}