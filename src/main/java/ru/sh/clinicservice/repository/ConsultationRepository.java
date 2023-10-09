package ru.sh.clinicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sh.clinicservice.models.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
}
