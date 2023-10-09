package ru.sh.clinicservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sh.clinicservice.models.Consultation;
import ru.sh.clinicservice.repository.ConsultationRepository;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public List<Consultation> getAllConsultation() {
        return consultationRepository.findAll();
    }

    public Consultation getConsultationById(Integer consultationId) {
        return consultationRepository.getReferenceById(consultationId);
    }

    @Transactional
    public void addConsultation(Consultation consultation) {
        consultationRepository.save(consultation);
    }

    @Transactional
    public void editConsultation(Consultation consultation) {
        Consultation cons = consultationRepository.findById(consultation.getId()).get();
        if (!cons.equals(consultation)) {
            cons.setClient(consultation.getClient());
            cons.setPet(consultation.getPet());
            cons.setVisitDate(consultation.getVisitDate());
            cons.setDescription(consultation.getDescription());
        }
    }

    @Transactional
    public void deleteConsultation(Integer consultationId) {
        consultationRepository.deleteById(consultationId);
    }
}
