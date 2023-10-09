package ru.sh.clinicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sh.clinicservice.models.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
