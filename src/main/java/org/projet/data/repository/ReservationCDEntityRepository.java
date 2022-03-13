package org.projet.data.repository;

import org.projet.data.entity.ReservationCDEntity;
import org.projet.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCDEntityRepository extends JpaRepository<ReservationCDEntity, Long> {

	Integer countAllByUser(UserEntity user);


}
