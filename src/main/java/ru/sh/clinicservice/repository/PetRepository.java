package ru.sh.clinicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sh.clinicservice.models.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
