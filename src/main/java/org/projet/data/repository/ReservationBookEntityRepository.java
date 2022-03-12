package org.projet.data.repository;

import org.projet.data.entity.ReservationBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationBookEntityRepository extends JpaRepository<ReservationBookEntity, Long> {

}
