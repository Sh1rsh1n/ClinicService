package ru.sh.clinicservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sh.clinicservice.models.Client;
import ru.sh.clinicservice.repository.ClientRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    List<Client> list = new ArrayList<>();

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

    /**
     * начальные данные
     */
    @BeforeEach
    void setup() {

        Client client1 = new Client(1, "Ivan", "Ivanov", Timestamp.valueOf("1988-08-07 00:00:00"));
        Client client2 = new Client(2, "Anna", "Ivanova", Timestamp.valueOf("1995-08-07 00:00:00"));
        Client client3 = new Client(3, "Dima", "Ivanov", Timestamp.valueOf("2001-08-07 00:00:00"));

        list.add(client1);
        list.add(client2);
        list.add(client3);
    }

    /**
     * тестируем получение списка всех клиентов
     * проверяем что при обращении к репозиторию возвращает какие-то данные(не null)
     * сравниваем значение, данные полученные после вызова метода clientService.getAllClients() с начальными данными
     */
    @Test
    void getAllClientsMethods_isOk_Test() {
        when(clientRepository.findAll()).thenReturn(list);

        var expectedList = clientService.getAllClients();

        assertNotNull(list);
        assertEquals(expectedList, list);
    }

    /**
     * Тестируем получение клиента по ID
     * проверяем, что при обращении к репозиторию возвращает данные о клиенте и сравниваем имена клиентов
     */
    @Test
    void getClientById_isOk_Test() {
        Client client = new Client(1, "Ivan", "Ivanov", Timestamp.valueOf("1988-08-07 00:00:00"));
        when(clientRepository.findById(anyInt())).thenReturn(Optional.of(client));

        var expected = clientService.getClientById(anyInt());

        assertNotNull(expected);
        assertEquals(expected.getName(), list.get(0).getName());
    }

    /**
     * Тестируем получение клиента по ID на генерацию исключения
     * при получении любого значения методом репозитория, генерируется исключение
     * затем при вызове метода clientService.getClientById(anyInt()) ожидаем, что будет выброшено исключение(RuntimeException)
     * @throws Exception
     */
    @Test
    void getClientById_throwException_Test() throws Exception {
        when(clientRepository.findById(anyInt())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> clientService.getClientById(anyInt()));
    }
}