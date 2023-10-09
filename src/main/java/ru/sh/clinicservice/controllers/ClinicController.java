package ru.sh.clinicservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sh.clinicservice.models.Client;
import ru.sh.clinicservice.models.Consultation;
import ru.sh.clinicservice.service.ClientService;
import ru.sh.clinicservice.service.ConsultationService;
import ru.sh.clinicservice.service.PetService;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/clinic")
@Tag(name = "Клиника", description = "Обработка данных консультаций в клинике")
public class ClinicController {

    private final ConsultationService consultationService;

    @Autowired
    public ClinicController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus addDataOfConsultation(@RequestBody Consultation consultation) {
        consultationService.addConsultation(consultation);
        return HttpStatus.OK;
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus editConsultation(@RequestBody Consultation consultation) {
        if (!consultationService.getAllConsultation().contains(consultation))
            return HttpStatus.NOT_FOUND;

        consultationService.editConsultation(consultation);
        return HttpStatus.OK;
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Consultation> getConsultation(@PathVariable Integer id) {
        return ResponseEntity.ok(consultationService.getConsultationById(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consultation>> getAllConsultation() {
        return ResponseEntity.ok(consultationService.getAllConsultation());
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus deleteConsultation(@RequestBody Consultation consultation) {
        consultationService.deleteConsultation(consultation.getId());
        return HttpStatus.OK;
    }
}
