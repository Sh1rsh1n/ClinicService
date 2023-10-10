package ru.sh;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sh.entity.Consultation;
import ru.sh.entity.Pet;
import ru.sh.handlers.ClientHandler;
import ru.sh.config.ClientConfig;
import ru.sh.entity.Client;
import ru.sh.handlers.ConsultationHandler;
import ru.sh.handlers.PetHandler;

import java.sql.Timestamp;
import java.util.List;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ClientConfig.class);

        ClientHandler clientHandler = context.getBean("clientHandler", ClientHandler.class);
        PetHandler petHandler = context.getBean("petHandler", PetHandler.class);
        ConsultationHandler consultationHandler = context.getBean("consultationHandler", ConsultationHandler.class);

        /**
         * Тест методов-запросов к REST сервису
         */

        // получаем и выводим списки всех сущностей из таблиц в БД
        List<Client> clientList = clientHandler.getAllClient();
        List<Pet> petList = petHandler.getAllPet();
        List<Consultation> consultations = consultationHandler.getAllConsultation();

        System.out.println(clientList);
        System.out.println(petList);
        System.out.println(consultations);

        // добавляем клиента
        Client client = new Client("Dima", "Petrov", Timestamp.valueOf("1980-10-16 00:00:00"));
        clientHandler.saveClient(client);
        // добавляем питомца
        Pet pet = new Pet("Vasya", Timestamp.valueOf("2018-03-25 00:00:00"));
        petHandler.savePet(pet);
        // добавляем консультацию
        Consultation consultation = new Consultation(clientHandler.getClient(1), petHandler.getPet(4), Timestamp.valueOf("2023-11-25 19:00:00"), "Cut nail");
        consultationHandler.saveConsultation(consultation);

        // запрашиваем и проверяем данные снова
        List<Client> clientList1 = clientHandler.getAllClient();
        List<Pet> petList1 = petHandler.getAllPet();
        List<Consultation> consultations1 = consultationHandler.getAllConsultation();

        System.out.println(clientList1);
        System.out.println(petList1);
        System.out.println(consultations1);
    }
}
