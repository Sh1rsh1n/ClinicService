package ru.sh.clinicservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sh.clinicservice.models.Pet;
import ru.sh.clinicservice.repository.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Transactional
    public void addPet(Pet pet) {
        petRepository.save(pet);
    }

    public List<Pet> getAllPet() {
        return petRepository.findAll();
    }

    public Pet getPetById(int id) {
        return petRepository.findAll().stream().filter(pet -> pet.getId() == id).findFirst().orElseGet(null);
    }

    @Transactional
    public void editPet(Pet pet) {
        Optional<Pet> optional = petRepository.findById(pet.getId());
        if (optional.isPresent()) {
            Pet editPet = optional.get();
            editPet.setName(pet.getName());
            editPet.setBirthday(pet.getBirthday());
        }
    }

    @Transactional
    public void deletePet(int id) {
        petRepository.deleteById(id);
    }


}
