package org.projet.data.repository;

import org.projet.data.entity.ReservationDVDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDVDEntityRepository extends JpaRepository<ReservationDVDEntity, Long> {

}
