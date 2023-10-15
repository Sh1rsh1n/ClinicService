package ru.sh.clinicservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sh.clinicservice.models.Pet;
import ru.sh.clinicservice.repository.PetRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    List<Pet> list = new ArrayList<>();

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    /**
     * начальные данные
     */
    @BeforeEach
    void setUp() {
        Pet pet1 = new Pet(1, "Sharik", Timestamp.valueOf("2020-02-05 00:00:00"));
        Pet pet2 = new Pet(2, "Bobik", Timestamp.valueOf("2020-02-05 00:00:00"));
        Pet pet3 = new Pet(3, "Murka", Timestamp.valueOf("2020-02-05 00:00:00"));

        list.add(pet1);
        list.add(pet2);
        list.add(pet3);
    }

    /**
     * Тестируем получение питомца по ID
     * проверяем, что при обращении к репозиторию возвращает данные о питомце и сравниваем имена питомцев
     */
    @Test
    void getPetById_isOk_Test() {
        when(petRepository.findById(anyInt())).thenReturn(Optional.of(new Pet(1, "Sharik", Timestamp.valueOf("2020-02-05 00:00:00"))));

        var expected = petService.getPetById(anyInt());

        assertNotNull(expected);
        assertEquals(expected.getName(), list.get(0).getName());
    }

    /**
     * Тестируем получение питомца по ID на генерацию исключения
     * при получении любого значения методом репозитория, генерируется исключение
     * затем при вызове метода petService.getPetById(anyInt()) ожидаем, что будет выброшено исключение(RuntimeException)
     * @throws Exception
     */
    @Test
    void getClientById_throwException_Test() throws Exception {
        when(petRepository.findById(anyInt())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> petService.getPetById(anyInt()));
    }

    /**
     * тестируем получение списка всех питомцев
     * проверяем что при обращении к репозиторию возвращает какие-то данные(не null)
     * данные полученные после вызова метода petService.getAllPet() сравниваем с начальными данными
     */
    @Test
    void getAllPets() {
        when(petRepository.findAll()).thenReturn(list);

        var expected = petService.getAllPet();

        assertNotNull(expected);
        assertEquals(expected, list);
    }

    /**
     * Тестируем добавление питомца
     * при вызове метода petService.addPet(pet) ожидаем получить True
     */
    @Test
    void addPet_existsTrue_Test() {
        Pet pet = new Pet(5, "Barbos", Timestamp.valueOf("2020-02-02 00:00:00"));
        when(petRepository.save(pet)).thenReturn(pet);

        boolean expectedAdd = petService.addPet(pet);

        assertTrue(expectedAdd);
    }

    /**
     * Тестируем редактирование питомца(по аналогии с addPet_existsTrue_Test)
     * при вызове метода petService.editPet(pet) ожидаем получить True
     */
    @Test
    void editPet_existsTrue_Test() {
        Pet pet = new Pet(1, "Barbos", Timestamp.valueOf("2020-02-02 00:00:00"));

        when(petRepository.findById(anyInt())).thenReturn(Optional.of(pet));

        boolean expectedAdd = petService.editPet(pet);

        assertTrue(expectedAdd);
    }

}