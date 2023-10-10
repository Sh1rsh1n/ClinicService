package ru.sh.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Consultation {

    private int id;

    private Client client;

    private Pet pet;

    private Timestamp visitDate;

    private String description;

    public Consultation() {
    }

    public Consultation(Client client, Pet pet, Timestamp visitDate, String description) {
        this.client = client;
        this.pet = pet;
        this.visitDate = visitDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consultation that = (Consultation) o;

        if (id != that.id) return false;
        if (!Objects.equals(client, that.client)) return false;
        if (!Objects.equals(pet, that.pet)) return false;
        if (!Objects.equals(visitDate, that.visitDate)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (pet != null ? pet.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", client=" + client +
                ", pet=" + pet +
                ", visitDate=" + visitDate.toLocalDateTime().toLocalDate() +
                ", description='" + description + '\'' +
                '}';
    }
}
