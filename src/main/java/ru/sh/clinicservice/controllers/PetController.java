package ru.sh.clinicservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sh.clinicservice.models.Pet;
import ru.sh.clinicservice.service.PetService;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> getPetById(@PathVariable String id) {
        return ResponseEntity.ok(petService.getPetById(Integer.parseInt(id)));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPet());
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus addPet(@RequestBody Pet pet) {
        if (petService.addPet(pet)) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus editPet(@RequestBody Pet pet) {
        if (petService.getAllPet().contains(pet) && petService.editPet(pet)) {
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @DeleteMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public HttpStatus deletePet(@PathVariable String id) {
        petService.deletePet(Integer.parseInt(id));
        return HttpStatus.OK;
    }
}
