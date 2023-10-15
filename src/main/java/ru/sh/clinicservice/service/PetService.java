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
    public boolean addPet(Pet pet) {
        if (pet != null) {
            petRepository.save(pet);
            return true;
        }
        throw new RuntimeException("Incorrect request of adding Pet");
    }

    public List<Pet> getAllPet() {
        return petRepository.findAll();
    }

    public Pet getPetById(int id) {
        Optional<Pet> optional = petRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("Incorrect pet ID");
    }

    @Transactional
    public boolean editPet(Pet pet) {
        Optional<Pet> optional = petRepository.findById(pet.getId());
        if (optional.isPresent()) {
            Pet editPet = optional.get();
            editPet.setName(pet.getName());
            editPet.setBirthday(pet.getBirthday());
            return true;
        }
        return false;
    }

    @Transactional
    public void deletePet(int id) {
        petRepository.deleteById(id);
    }


}
