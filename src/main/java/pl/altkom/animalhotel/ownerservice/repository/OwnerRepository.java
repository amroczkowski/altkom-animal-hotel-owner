package pl.altkom.animalhotel.ownerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.altkom.animalhotel.ownerservice.repository.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
