package ru.sh.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sh.entity.Consultation;

import java.util.List;

@Component
public class ConsultationHandler {

    @Autowired
    private RestTemplate restTemplate;

    private final static String URL = "http://localhost:8080/api/clinic";

    public List<Consultation> getAllConsultation() {
        ResponseEntity<List<Consultation>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Consultation>>() {
                });

        List<Consultation> consultations = response.getBody();
        return consultations;
    }

    public Consultation getConsultation(int id) {
        return restTemplate.getForObject(String.format("%s/%d", URL, id), Consultation.class);
    }

    public void saveConsultation(Consultation consultation) {
        int id = consultation.getId();
        if (id == 0) {
            ResponseEntity<String> response = restTemplate.postForEntity(URL, consultation, String.class);
            System.out.println("New Consultation is create successfully!");
            System.out.println(response.getBody());
        } else {
            restTemplate.put(URL, consultation);
            System.out.printf("Consultation %s updated success.\n", consultation);
        }
    }

    public void deleteConsultation(int id) {
        restTemplate.delete(String.format("%s/%d", URL, id));
    }
}
